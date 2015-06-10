/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.agentes;

import com.conexion.Conexion;
import com.ventana.GuiBusquedas;
import jade.core.Agent;
import jade.domain.DFService;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.domain.FIPAException;
import java.awt.List;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author erick
 */
public class AgenteOrquestador extends Agent {

    private GuiBusquedas ventana;
    private Conexion conexion;
    public int cnt = 0;

    protected void setup() {
        System.out.println("Bienvenido! Agente-Orquestador " + getAID().getName() + " en acción.");
        ventana = new GuiBusquedas(this);
        ventana.showGui();

    }

    protected void takeDown() {
        ventana.dispose();
        System.out.println("Agente-Orquestador " + getAID().getName() + " terminado.");
    }

      
    public void creaConsultor(){
        AgenteConsultor consultor = null;
        ArrayList<String> querys = new ArrayList<String>();
        
        setCnt();
        consultor = new AgenteConsultor("Consultor_" + getCnt());
        querys = consultor.creaConsulta();
        
        if(!consultor.consulta(querys)){
            JOptionPane.showMessageDialog(null, "No se encontraron resultados.");
        }
       
    }

    public int getCnt() {
        return cnt;
    }

    public void setCnt() {
        this.cnt++;
    }
    
    
}
