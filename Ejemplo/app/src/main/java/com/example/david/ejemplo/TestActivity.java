package com.example.david.ejemplo;

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Layout;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class TestActivity extends AppCompatActivity implements View.OnClickListener{
    public final static int correct =2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        TextView textWording=(TextView)findViewById(R.id.test_pregunta);
        textWording.setText("¿Cúal de las siguientes opciones NO se indica en el archivo de manifiesto de la app?");
        RadioGroup group=(RadioGroup)findViewById(R.id.test_choices);
        int i=0;
        String[] pregunt= new String[5];
        pregunt[0]="Version de la aplicacion";
        pregunt[1]="Listado de los componentes de la aplicacion";
        pregunt[2]="Opciones del menu de ajustes";
        pregunt[3]="Nivel minimo de la API Android requerida";
        pregunt[4]="Nombre del paquete java de la aplicacion";
        for(i=0;i<=4;i++){
            RadioButton radio=new RadioButton(this);
            radio.setText(pregunt[i]);
            radio.setOnClickListener(this);
            group.addView(radio);
        }
    }
    @Override
    public void onClick(View v){
        findViewById(R.id.button_send_test).setVisibility(View.VISIBLE);
    }
    public void send (View view){
        RadioGroup group=(RadioGroup)findViewById(R.id.test_choices);
        int choices=group.getChildCount();
        for(int i=0;i<choices;i++)
            group.getChildAt(i).setEnabled(false);
        LinearLayout layout=(LinearLayout)findViewById(R.id.test_id_Layout);
        layout.removeView(findViewById(R.id.button_send_test));

        group.getChildAt(correct).setBackgroundColor(Color.GREEN);

    }


}
