/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.agentes;

import jade.wrapper.AgentController;
import jade.wrapper.ContainerController;
import jade.wrapper.StaleProxyException;

/**
 *
 * @author erick
 */
public class Inicio {
    
    public static final RunJade runjade = new RunJade(true, "1111");
    
    public static void main(String[] args) {
         
        ContainerController home = runjade.getHome();

        try {
            AgentController a = home.createNewAgent("Orquestador", AgenteOrquestador.class.getName(), new Object[0]);
            a.start();
        } catch (StaleProxyException e) {
            e.printStackTrace();
        }
    }
}
