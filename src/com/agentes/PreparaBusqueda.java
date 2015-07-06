/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.agentes;

import java.awt.List;
import java.sql.Array;
import java.util.ArrayList;

/**
 *
 * @author erick
 */
public class PreparaBusqueda {

    public static String AllQuerys = "";
    public static PreparaBusqueda prebu = new PreparaBusqueda();
    public static ArrayList<String> ListaAllConsulta = new ArrayList<String>();

    public static void main(String[] args) {
         PreparaBusqueda p = new PreparaBusqueda();
         p.initialize();
    }
    
    public void initialize() {

        String[] elementos = "autosnuevos ford".split(" ");//Busqueda
        int n = 0;                  //Tipos para escoger
        int r = elementos.length;   //Elementos elegidos

        for (int i = 1; i <= elementos.length; i++) {
            Permutacion(elementos, "", i, r);
        }

        System.out.println(AllQuerys);
        String[] sent = AllQuerys.split(";");

            for (int i = 0; i < sent.length; i++) {
                String[] sent_2 = sent[i].split(",");
                if (sent_2.length == 1) {
                    for (int j = 0; j < sent_2.length; j++) {
                        System.out.println(prebu.SelectBasico(sent_2[j]));
                        ListaAllConsulta.add(prebu.SelectBasico(sent_2[j]));
                    }
                }
                if (sent_2.length == 2) {
                    System.out.println(prebu.SelectColumna(sent_2[0], sent_2[1]));
                    ListaAllConsulta.add(prebu.SelectColumna(sent_2[0], sent_2[1]));
                }
                if (sent_2.length == 3) {
                    System.out.println(prebu.SelectWhere(sent_2[0], sent_2[1], sent_2[2]));
                    System.out.println(prebu.SelectWhereColumna(sent_2[0], sent_2[1], sent_2[2]));
                    ListaAllConsulta.add(prebu.SelectWhere(sent_2[0], sent_2[1], sent_2[2]));
                    ListaAllConsulta.add(prebu.SelectWhereColumna(sent_2[0], sent_2[1], sent_2[2]));
                }
                if (sent_2.length == 4) {
                    System.out.println(prebu.SelectJoin(sent_2[0], sent_2[1], sent_2[2], sent_2[3]));
                    System.out.println(prebu.SelectJoin(sent_2[1], sent_2[2], sent_2[3], sent_2[2]));
                    System.out.println(prebu.SelectJoin(sent_2[2], sent_2[3], sent_2[0], sent_2[1]));
                    System.out.println(prebu.SelectJoin(sent_2[3], sent_2[0], sent_2[1], sent_2[0]));
                    ListaAllConsulta.add(prebu.SelectJoin(sent_2[0], sent_2[1], sent_2[2], sent_2[3]));
                    ListaAllConsulta.add(prebu.SelectJoin(sent_2[1], sent_2[2], sent_2[3], sent_2[2]));
                    ListaAllConsulta.add(prebu.SelectJoin(sent_2[2], sent_2[3], sent_2[0], sent_2[1]));
                    ListaAllConsulta.add(prebu.SelectJoin(sent_2[3], sent_2[0], sent_2[1], sent_2[0]));
                }
            }
    }

    private static void Permutacion(String[] elem, String act, int n, int r) {
        if (n == 0) {
            AllQuerys += act + ";";
        } else {
            for (int i = 0; i < r; i++) {
                if (!act.contains(elem[i])) { // Controla que no haya repeticiones
                    Permutacion(elem, act + elem[i] + ",", n - 1, r);
                }
            }
        }
    }

    public String SelectBasico(String s) {
        return "SELECT * FROM " + s + ";";
    }

    public String SelectColumna(String s, String s1) {
        return "SELECT " + s + " FROM " + s1 + ";";
    }

    public String SelectWhereColumna(String s, String s1, String s2) {
        return "SELECT * FROM " + s + " WHERE " + s1 + " = " + s2 + ";";
    }

    public String SelectWhere(String s, String s1, String s2) {
        return "SELECT " + s + " FROM " + s1 + " WHERE " + s + " = " + s2 + ";";
    }

    public String SelectJoin(String s, String s1, String s2, String s3){
        return "SELECT * FROM " + s + " JOIN " + s1 + " ON " +  s2 + " = " + s3 + ";";
    }
//    public String[] GramaticaSql(int caso) {
//
//        String[] q = {
//            "SELECT [ *,DISTINCT,columna ] FROM [ tabla_referencia ] ;",
//            "SELECT [ *,DISTINCT,columna ] FROM [ tabla_referencia ] "
//            + "WHERE [columna] [OPERADOR(X)] [valor]",
//            "SELECT [ *,DISTINCT,columna ] FROM [ tabla_referencia ]"
//            + "WHERE [columna] [OPERADOR(X)] [valor]"
//            + "[EXTRA-CONDICION(X)]"
//            + "[columna] [OPERADOR(X)] [valor];",
//            "SELECT [ *,DISTINCT,columna ] FROM [ tabla_referencia ]"
//            + "[NATURAL(X)] JOIN [tabla_referencia] ON [tabla_referencia.columna] [OPERADOR(X)] [valor] ;",
//            "SELECT [ *,DISTINCT,columna ] FROM [ tabla_referencia ]"
//            + "[NATURAL(X)] JOIN [tabla_referencia] ON [tabla_referencia.columna] [OPERADOR(X)] [valor]"
//            + "WHERE [columna] [OPERADOR(X)] [valor] ;",
//            "SELECT [ *,DISTINCT,columna ] FROM [ tabla_referencia ]"
//            + "[NATURAL(X)] JOIN [tabla_referencia] ON [tabla_referencia.columna] [OPERADOR(X)] [valor]"
//            + "WHERE [columna] [OPERADOR(X)] [valor] ;"
//            + "ORDER BY [ORDEN(X)]",};
//
//        return q;
//    }

}
