package com.example.android.personasmaterialdiplomado;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.util.ArrayList;

public class Principal extends AppCompatActivity {
    private RecyclerView listado;
    private ArrayList<Persona> personas;
    private Resources res;
    private AdaptadorPersona adapter;
    private LinearLayoutManager llm;
    private Intent i;
    private AdaptadorPersona.OnPersonaClickListener personaClick;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        listado = (RecyclerView)findViewById(R.id.lstOpciones);

        res = this.getResources();
        personas = Datos.obtenerPersonas();


        llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);

        personaClick = new AdaptadorPersona.OnPersonaClickListener() {
            @Override
            public void onPersonaClick(Persona p) {
                Intent i = new Intent(Principal.this, DetallePersona.class);
                Bundle b = new Bundle();
                b.putInt("foto", p.getFoto());
                b.putString("cedula", p.getCedula());
                b.putString("nombre", p.getNombre());
                b.putString("apellido", p.getApellido());
                b.putInt("sexo", p.getSexo());
                b.putInt("pos", Datos.posPersona(p));
                i.putExtras(b);
                startActivity(i);
                finish();
            }
        };

        adapter = new AdaptadorPersona(this.getApplicationContext(), personas, personaClick);

        listado.setLayoutManager(llm);
        listado.setAdapter(adapter);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              i = new Intent(Principal.this,CrearPersonas.class);
              startActivity(i);
            }
        });
    }
}
