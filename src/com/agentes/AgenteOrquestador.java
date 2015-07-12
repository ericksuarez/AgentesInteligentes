package com.agentes;

import static com.agentes.Inicio.runjade;
import com.ventana.GuiBusquedas;
import jade.core.Agent;
import jade.wrapper.AgentController;
import jade.wrapper.ContainerController;
import jade.wrapper.StaleProxyException;

/**
 *
 * @author erick
 */
public class AgenteOrquestador extends Agent {

    private GuiBusquedas ventana;
    public int cnt = 0;
    private ContainerController home = null;

    protected void setup() {
        System.out.println("Bienvenido! Agente-Orquestador " + getAID().getName() + " en acción.");
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
            AgentController a = home.createNewAgent("Consultor_" + getCnt(), AgenteConsultor.class.getName(), new Object[0]);
            a.start();
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

}
