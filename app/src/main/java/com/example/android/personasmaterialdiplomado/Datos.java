package com.example.android.personasmaterialdiplomado;

import java.util.ArrayList;

/**
 * Created by android on 07/10/2017.
 */

public class Datos {
    private static ArrayList<Persona> personas = new ArrayList();

    public static void guardarPersona(Persona p){
        personas.add(p);
    }

    public static int posPersona(Persona p) {
        int i = 0;
        for (Persona pers: personas) {
            if (pers.getCedula().equals(p.getCedula())) {
                return i;
            }
            i++;
        }
        return -1;
    }

    public static boolean eliminarPersona(Persona p) {
        int idx = posPersona(p);
        if (idx >= 0) personas.remove(idx);
        return false;
    }

    public static void editarPersona(Persona p, int pos) {
        personas.set(pos, p);
    }

    public static ArrayList<Persona> obtenerPersonas(){
        return personas;
    }
}

