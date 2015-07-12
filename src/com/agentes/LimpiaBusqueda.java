package com.agentes;

/**
 *
 * @author erick
 */
public class LimpiaBusqueda {

    public String[] conjunciones() {
        String[] conjuncion = {
            "a condición de que",
            "a menos que",
            "a pesar de",
            "a pesar de todo",
            "además",
            "además de",
            "adonde",
            "adonde quiera que",
            "ahora que",
            "antes que",
            "aún así",
            "aún cuando",
            "aunque",
            "como",
            "como si",
            "con tal que",
            "cualquiera que sea",
            "cuando",
            "de lo contrario",
            "desde que",
            "después que",
            "donde",
            "donde quiera que",
            "el",
            "lo",
            "la",
            "los",
            "las mismo",
            "a",
            "os",
            "as",
            "que",
            "en cuanto",
            "hasta que",
            "lo mismo que",
            "mientras",
            "mientras que",
            "ni",
            "no importa",
            "no obstante",
            "no sólo",
            "sino también",
            "o",
            "para",
            "para que",
            "pero",
            "por",
            "por miedo a",
            "por mucho que",
            "por muy",
            "por si",
            "porque",
            "puesto que",
            "si",
            "siempre que",
            "sin embargo",
            "suponiendo que",
            "tan pronto como",
            "tanto",
            "una vez que",
            "y",
            "ya que"

        };

        return conjuncion;
    }

    public String[] preposiciones() {
        String[] preposicion = {
            "a",
            "ante",
            "bajo",
            "cane",
            "con",
            "contra",
            "de",
            "desde",
            "en",
            "entre",
            "hacia",
            "hasta",
            "para",
            "por",
            "segun",
            "sin",
            "so",
            "sobre",
            "tras",
            "durante",
            "mediante",
            "pro",
            "via",
            "donde",
            "cuando",
            "como"
        };

        return preposicion;
    }

    public String[] quitaPalabra(String buscando) {
        String[] palabras = buscando.split(" ");
        String[] conjunciones = conjunciones();
        String[] preposiciones = preposiciones();

        for (int i = 0; i < palabras.length; i++) {
            for (int j = 0; j < conjunciones.length; j++) {
                if (palabras[i].equalsIgnoreCase(conjunciones[j])) {
                    palabras[i] = "";
                }
            }
        }
        for (int i = 0; i < palabras.length; i++) {
            for (int j = 0; j < preposiciones.length; j++) {
                if (palabras[i].equalsIgnoreCase(preposiciones[j])) {
                    palabras[i] = "";
                }
            }
        }
        return palabras;
    }
    
    public String clearBusqueda(String buscando){
        String palabra = "";
        String[] words = quitaPalabra(buscando);
        
        for (int i = 0; i < words.length; i++) {
            if (!words[i].equals("")) {
                palabra += words[i] + " ";
            }
        }
        
        return palabra.trim();
    }
}
