package com.example.android.personasmaterialdiplomado;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {

    @Test
    public void cedulaExiste() {
        Persona p = new Persona("123456");
        p.guardar();
        Persona p1 = new Persona(0, "Javier", "Mejia", "123456");
        assertTrue(Metodos.existeCedula(Datos.obtenerPersonas(), p1, 0));
    }

    @Test
    public void permitirCambioCedula() {
        Persona p = new Persona(0, "Javier", "Mejia", "123456");
        p.guardar();
        int pos = 0; //Posición de Javier en el arrayList
        Persona p1 = new Persona(1, "Kare", "Alvarez", "1122334455");
        p1.guardar();
        p.setCedula("1143157429");
        assertFalse(Metodos.existeCedula(Datos.obtenerPersonas(), p, pos));
    }
}