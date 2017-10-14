package com.example.android.personasmaterialdiplomado;

import android.widget.EditText;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by android on 07/10/2017.
 */

public class Metodos {
    public static int fotoAleatoria(ArrayList<Integer> fotos){
        int fotoSeleccionada;
        Random r = new Random();
        fotoSeleccionada = r.nextInt(fotos.size());
        return fotos.get(fotoSeleccionada);
    }

    public static boolean existeCedula(ArrayList<Persona> personas, Persona pers, int pos){
        int idx = -1;
        for (Persona p : personas) {
            idx++;
            if (idx == pos) continue;
            if (p.getCedula().equals(pers.getCedula())) return true;
        }
        return false;
    }

    public static boolean validarEditText(EditText et, String msj) {
        if (et.getText().toString().isEmpty()) {
            et.setError(msj);
            et.requestFocus();
            return false;
        } else {
            et.setError(null);
        }
        return true;
    }
}
