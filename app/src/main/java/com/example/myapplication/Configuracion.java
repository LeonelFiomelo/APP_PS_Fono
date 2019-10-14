package com.example.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

import com.example.myapplication.logica.Ejercicio_Completar;
import com.example.myapplication.logica.Ejercicio_Tres_Opciones;

public class Configuracion extends AppCompatActivity
        implements AdapterView.OnItemSelectedListener{

        String[] tipoDato = {"Fonema", "Palabra", "Oraciones", "Canciones","Instrumentos","Estilos Musicales", "Voces Familiares"};
        String[] tipoDataPalabra = {"Animales","Colores","Comidas","Dias de la Semana", "Meses", "Nombres", "Numeros", };
        String [] ruido = {"Ambulancia","Tráfico", "Multitud de gente", "Recreo de niños" };
        String [] tipoEjercicio = {"Identificar entre 3 opciones", "Identificar entre 5 opciones", "Escribir lo que oyó"};

    String confTipoDato;
    String confSubDato;
    String confTipoEjercio;
    String confRuido;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuracion);
        final Toolbar toolbar = findViewById(R.id.toolbar_configuracion);
        toolbar.setTitle("Configuracion");

        final Spinner spinnerDato = findViewById(R.id.spinner_tipoDato);
        final Spinner spinnerSubDato = findViewById(R.id.spinner_subTipoDato);
        final Spinner spinnerRuido = findViewById(R.id.spinner_tipoRuido);
        final Spinner spinnerEjercicio = findViewById(R.id.spinner_TipoEjercicio);
        //spinnerDato.setOnItemSelectedListener(this);

        ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item,tipoDato);
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinnerDato.setAdapter(adapter);

        final ArrayAdapter adapeterRuido = new ArrayAdapter(this,android.R.layout.simple_spinner_item,ruido);
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinnerRuido.setAdapter(adapeterRuido);

        ArrayAdapter adapterEjercicio = new ArrayAdapter(this,android.R.layout.simple_spinner_item,tipoEjercicio);
        adapterEjercicio.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinnerEjercicio.setAdapter(adapterEjercicio);
        //Toast.makeText(this, spinnerDato.getSelectedItem().toString(), Toast.LENGTH_SHORT).show();

        spinnerDato.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String opcion = spinnerDato.getSelectedItem().toString();
                confTipoDato = opcion;
                if(opcion.equals("Palabra")){
                    ArrayAdapter adapter2 = new ArrayAdapter(getApplicationContext(),android.R.layout.simple_spinner_item,tipoDataPalabra);
                    spinnerSubDato.setAdapter(adapter2);
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinnerSubDato.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String opcion = spinnerSubDato.getSelectedItem().toString();
                confSubDato = opcion;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        spinnerEjercicio.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String opcion = spinnerEjercicio.getSelectedItem().toString();
                confTipoEjercio = opcion;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        spinnerRuido.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String opcion = spinnerRuido.getSelectedItem().toString();
                confRuido = opcion;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



        //RUIDO

        final Button btn = findViewById(R.id.btn_ejercicio);
        final TextView tvIntensidad = findViewById(R.id.tvIntensidad);
        final SeekBar seekBarIntensidad = findViewById(R.id.seekBar);

        final Switch switchRuido = findViewById(R.id.switchRuido);
        switchRuido.setChecked(false);

        switchRuido.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    spinnerRuido.setVisibility(View.VISIBLE);
                    tvIntensidad.setVisibility(View.VISIBLE);
                    seekBarIntensidad.setVisibility(View.VISIBLE);
                }else{
                    spinnerRuido.setVisibility(View.INVISIBLE);
                    tvIntensidad.setVisibility(View.INVISIBLE);
                    seekBarIntensidad.setVisibility(View.INVISIBLE);
                }
            }
        });



        //FINALIZADA LA CONFIGURACIÓN INICIO LA EJERCITACIÓN


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                switch (confTipoEjercio){
                    case "Identificar entre 3 opciones":
                        Intent intent = new Intent(getApplicationContext(), Ejercicio_Tres_Opciones.class);
                        if(!switchRuido.isChecked()){
                            confRuido="Sin Ruido";
                        }
                        intent.putExtra("subDato", confSubDato);
                        intent.putExtra("tipoRuido", confRuido);
                        startActivity(intent);
                        break;
                    case "Escribir lo que oyó":
                        Intent intent2 = new Intent(getApplicationContext(), Ejercicio_Completar.class);
                        if(!switchRuido.isChecked()){
                            confRuido="Sin Ruido";
                        }
                        intent2.putExtra("subDato", confSubDato);
                        intent2.putExtra("tipoRuido", confRuido);
                        startActivity(intent2);
                }



            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }
}