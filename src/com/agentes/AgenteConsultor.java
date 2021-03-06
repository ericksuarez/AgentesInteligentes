package com.agentes;

import static com.agentes.PreparaBusqueda.ListaAllConsulta;
import com.conexion.Conexion;
import com.mysql.jdbc.exceptions.MySQLSyntaxErrorException;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author erick
 */
public class AgenteConsultor extends Agent {

    private Conexion conexion;

    protected void setup() {

        System.out.println("Bienvenido! Agente-Consultor " + getAID().getName() + " en acción.");

        ConsultorBehaviour  consultor_behaviour  = new ConsultorBehaviour ();
        consultor_behaviour.action();

    }

    protected void takeDown() {
        System.out.println("Agente-Consultor " + getAID().getName() + " Terminado.");
    }

    public boolean consulta(ArrayList<String> sql) throws SQLException {

        Boolean resp = false;
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
                } catch (MySQLSyntaxErrorException se) {
                    JOptionPane.showMessageDialog(null, se);
                } catch (NullPointerException ex) {
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "Fallo la conexión.");
            return false;
        }

        return resp;
    }

    private class ConsultorBehaviour extends CyclicBehaviour  {

        @Override
        public void action() {
            PreparaBusqueda prebusq = new PreparaBusqueda();

            ArrayList<String> querys = new ArrayList<String>();

            prebusq.initialize();

            querys = ListaAllConsulta;
            try {
                if (!consulta(querys)) {
                    JOptionPane.showMessageDialog(null, "No se encontraron resultados.");
                }
            } catch (SQLException ex) {
                Logger.getLogger(AgenteConsultor.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

    }

}
