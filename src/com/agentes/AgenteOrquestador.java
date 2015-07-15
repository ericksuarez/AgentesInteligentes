package com.agentes;

import static com.agentes.Inicio.runjade;
import com.ventana.GuiBusquedas;
import jade.core.AID;
import jade.core.Agent;
import jade.domain.AMSService;
import jade.domain.FIPAAgentManagement.AMSAgentDescription;
import jade.domain.FIPAAgentManagement.SearchConstraints;
import jade.wrapper.AgentController;
import jade.wrapper.ContainerController;
import jade.wrapper.StaleProxyException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author erick
 */
public class AgenteOrquestador extends Agent {

    private GuiBusquedas ventana;
    public int cnt = 0;
    private ContainerController home = null;
    public AgenteConsultor[][] listaConsultores = new AgenteConsultor[10][];

    protected void setup() {
        System.out.println("Bienvenido! Agente-Orquestador " + getAID().getName() + " en acción.");

        for (int i = 1; i < 10; i++) {
            listaConsultores[i] = new AgenteConsultor[1];
        }

        ventana = new GuiBusquedas(this);
        ventana.showGui();

    }

    protected void takeDown() {
        ventana.dispose();
        System.out.println("Agente-Orquestador " + getAID().getName() + " terminado.");
    }

    public void creaConsultor() {

        ContainerController home = runjade.getHome();
        setCnt();

        try {
//            if (StatusAgentesConsultores()) {
//                AgentController a = home.createNewAgent("Consultor_" + getCnt(), AgenteConsultor.class.getName(), listaConsultores[getCnt()]);
//                a.start();
//            } else {
                AgentController a = home.createNewAgent("Consultor_" + getCnt(), AgenteConsultor.class.getName(), listaConsultores[getCnt()]);
                a.start();
//            }

        } catch (StaleProxyException e) {
            e.printStackTrace();
        }

    }

    public int getCnt() {
        return cnt;
    }

    public void setCnt() {
        this.cnt++;
    }

    public Boolean StatusAgentesConsultores() {
        Boolean resp = false;

        AMSAgentDescription[] agents = null;
        try {
            SearchConstraints c = new SearchConstraints();
            c.setMaxResults(new Long(-1));
            agents = AMSService.search(this, new AMSAgentDescription(), c);
        } catch (Exception e) {
        }
        for (int i = 0; i < agents.length; i++) {
            AID agentID = agents[i].getName();
//            System.out.println(agents[i].getState() + "---" + agentID.getLocalName());
            Pattern pat = Pattern.compile("Consultor_*[\\d]$");
            Matcher mat = pat.matcher(agentID.getLocalName());
            if (mat.matches()) {
                if (agents[i].getState().equals("active")) {
                    resp = true;
                }
            }
        }

        return resp;
    }

}
