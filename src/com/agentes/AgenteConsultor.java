package com.agentes;

import com.conexion.Conexion;
import jade.core.AID;
import jade.core.Agent;
import java.awt.List;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.JOptionPane;

/**
 *
 * @author erick
 */
public class AgenteConsultor extends Agent {

    private Conexion conexion;
    String nombre;

    public AgenteConsultor(String nombre) {
        this.nombre = nombre;
        AID id = new AID(nombre, AID.ISLOCALNAME);
    }

    protected void setup() {
        System.out.println("Bienvenido! Agente-Consultor " + getAID().getName() + " en acción.");
    }

    protected void takeDown() {
        System.out.println("Agente-Consultor " + getAID().getName() + " Terminado.");
    }

    public boolean consulta(ArrayList<String> sql) {

        Boolean resp = true;
        ResultSet resultados;
        Iterator<String> it = sql.iterator();
        conexion = new Conexion();

        if (conexion.conexionDB()) {

            while (it.hasNext()) {
                resultados = conexion.consulta(it.next());
                try {
                    int size = 0;
                    while (resultados.next()) {
                        size++;
                    }
                    JOptionPane.showMessageDialog(null, "N° de tuplas resultantes (" + size + ")");
                    return true;
                } catch (SQLException se) {
                    JOptionPane.showMessageDialog(null, se);
                    return false;
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "Fallo la conexión.");
            return false;
        }

        return resp;
    }

    public ArrayList<String> creaConsulta() {
        ArrayList<String> query = new ArrayList<String>();
        query.add("select * from autosnuevos;");
        return query;
    }

}
