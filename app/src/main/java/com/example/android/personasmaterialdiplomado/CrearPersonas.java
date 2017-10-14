package com.example.android.personasmaterialdiplomado;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Handler;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

public class CrearPersonas extends AppCompatActivity {
    private EditText cajaNombre, cajaApellido, cajaCedula;
    private TextInputLayout icajaNombre, icajaApellido, icajaCedula;
    private Spinner spSexo;
    private ArrayList<Integer> fotos;
    private Resources res;
    private String[] sexos;
    private boolean editable;
    private TextView tvTitulo;
    private Persona p;
    private Bundle b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_personas);

        cajaNombre = (EditText)findViewById(R.id.txtNombre);
        cajaApellido=(EditText)findViewById(R.id.txtApellido);
        cajaCedula = (EditText)findViewById(R.id.txtCedula);
        res = this.getResources();
        icajaNombre = (TextInputLayout) findViewById(R.id.cajaNombre);
        icajaApellido = (TextInputLayout)findViewById(R.id.cajaApellido);
        icajaCedula = (TextInputLayout) findViewById(R.id.cajaCedula);
        fotos = new ArrayList<>();
        fotos.add(R.drawable.images);
        fotos.add(R.drawable.images2);
        fotos.add(R.drawable.images3);
        spSexo = (Spinner) findViewById(R.id.cmbSexo);
        sexos = res.getStringArray(R.array.sexo);
        ArrayAdapter<String> adp = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, sexos);
        spSexo.setAdapter(adp);

        b = getIntent().getExtras();
        if (b != null) {
            cajaNombre.setText(b.getString("nombre"));
            cajaApellido.setText(b.getString("apellido"));
            cajaCedula.setText(b.getString("cedula"));
            spSexo.setSelection(b.getInt("sexo"));
            editable = true;
        } else {
            editable = false;
        }
        tvTitulo = (TextView) findViewById(R.id.tvTitPersona);
        String tit = res.getString(editable ? R.string.modificar : R.string.crear);
        tvTitulo.setText(tit);
    }

    private boolean validar() {
        if (Metodos.validarEditText(cajaCedula, res.getString(R.string.errCedula))
                && Metodos.validarEditText(cajaNombre, res.getString(R.string.errNombre))
                && Metodos.validarEditText(cajaApellido, res.getString(R.string.errApellido))) {
            p = new Persona(editable ? b.getInt("foto") : Metodos.fotoAleatoria(fotos),
                    cajaNombre.getText().toString(),cajaApellido.getText().toString(), cajaCedula.getText().toString());
            int pos = editable ? b.getInt("pos") : -1;
            if (Metodos.existeCedula(Datos.obtenerPersonas(), p, pos)) {
                cajaCedula.setError(res.getString(R.string.personaExiste));
                cajaCedula.requestFocus();
            } else {
                cajaCedula.setError(null);
                return true;
            }
        }
        return false;
    }

    public void guardar(View v) {
        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        if (validar()) {
            p.setSexo(spSexo.getSelectedItemPosition());
            String msj;
            if (editable) {
                msj = res.getString(R.string.mensaje_editado);
                p.editar(b.getInt("pos"));
            } else {
                msj = res.getString(R.string.mensaje_guardado);
                p.guardar();
            }
            Snackbar.make(v, msj, Snackbar.LENGTH_LONG).addCallback(new Snackbar.Callback() {
                @Override
                public void onDismissed(Snackbar transientBottomBar, int event) {
                    onBackPressed();
                }
            }).show();
            if (!editable) limpiar();
        }
    }

    public void limpiar(){
        cajaNombre.setText("");
        cajaApellido.setText("");
        cajaCedula.setText("");
        cajaNombre.requestFocus();
    }

    public void limpiar(View v) { limpiar(); }

    public void onBackPressed(){
        finish();
        Intent i = new Intent(CrearPersonas.this, Principal.class);
        startActivity(i);
    }
}
