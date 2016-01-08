package com.example.david.ejemplo;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Layout;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import java.io.IOException;
import java.net.URI;

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
        RestClient conex =new RestClient("http://u017633.ehu.eus:18080/AlumnoTta/rest/tta");
    }
    @Override
    public void onClick(View v){
        findViewById(R.id.button_send_test).setVisibility(View.VISIBLE);
    }
    public void send (View view){
        LinearLayout layout=(LinearLayout)findViewById(R.id.test_id_Layout);
        layout.removeView(findViewById(R.id.button_send_test));
        RadioGroup group=(RadioGroup)findViewById(R.id.test_choices);
        int seleccionado=group.getCheckedRadioButtonId();
        RadioButton boton = (RadioButton)findViewById(seleccionado);
        int choices=group.getChildCount();
        for(int i=0;i<choices;i++)
            group.getChildAt(i).setEnabled(false);
        int indicesel=group.indexOfChild(boton);
        if(indicesel==correct){
            Toast.makeText(this,"Respuesta Correcta",Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(this,"Respuesta Incorrecta",Toast.LENGTH_SHORT).show();
            group.getChildAt(correct).setBackgroundColor(Color.GREEN);
            group.getChildAt(indicesel).setBackgroundColor(Color.RED);
            Button botonayuda=(Button)findViewById(R.id.button_ayuda_test);
            botonayuda.setVisibility(View.VISIBLE);
        }
    }
    public void ayuda (View view) {
        Button boton = (Button) findViewById(R.id.button_ayuda_test);
        boton.setEnabled(false);
        LinearLayout layout = (LinearLayout) findViewById(R.id.test_id_Layout);
        WebView web = new WebView(this);
        RadioGroup group=(RadioGroup)findViewById(R.id.test_choices);
        int seleccionado=group.getCheckedRadioButtonId();
        RadioButton boton2 = (RadioButton)findViewById(seleccionado);
        int indicesel=group.indexOfChild(boton2);
        if(indicesel==0){
            showHtml("<html><body>Has selecionado la opcion 0</body></html>");
        }
        else if(indicesel==1){
            showHtml("http://www.google.com");
        }
        else if(indicesel==3){
            showVideo("http://u017633.ehu.eus:18080/static/AndroidManifest.mp4");
        }
        else if (indicesel == 4) {
            showAudio("http://u017633.ehu.eus:18080/static/AndroidManifest.mp4<");
        }
    }
    private void showHtml(String advise){
        if(advise.substring(0,10).contains("://")){
            Uri uri= Uri.parse(advise);
            Intent intent=new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intent);
        }
        else{
            WebView web = new WebView(this);
            web.loadData(advise, "text/html", null);
            web.setBackgroundColor(Color.TRANSPARENT);
            web.setLayerType(WebView.LAYER_TYPE_SOFTWARE, null);
            LinearLayout layout = (LinearLayout) findViewById(R.id.test_id_Layout);
            layout.addView(web);
        }
    }
    public void showVideo(String advise){

        VideoView video=new VideoView(this);
        video.setVideoURI(Uri.parse(advise));
        ViewGroup.LayoutParams params=new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT);
        video.setLayoutParams(params);
        MediaController controller = new MediaController(this){

            @Override
            public void hide(){
            }

            public boolean dispatchKeyEvent(KeyEvent event){
                if(event.getKeyCode()==KeyEvent.KEYCODE_BACK){
                    finish();
                }
                return super.dispatchKeyEvent(event);
            }
        };
        controller.setAnchorView(video);
        video.setMediaController(controller);
        LinearLayout layout=(LinearLayout)findViewById(R.id.test_id_Layout);
        layout.addView(video);
        video.start();
    }
    public void showAudio(String advise){
        View view=new View(this);
        AudioPlayer audio=new AudioPlayer(view);
        try {
            audio.setAudioUri(Uri.parse(advise));
        }
        catch(IOException e) {
        }
        LinearLayout layout=(LinearLayout)findViewById(R.id.test_id_Layout);
        layout.addView(view);
        audio.start();
    }
}
