package com.example.david.ejemplo;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;


import java.io.File;
import java.io.IOException;

public class ExerciseActivity extends AppCompatActivity {

    public final static int READ_REQUEST_CODE=0;
    public final static int PICTURE_REQUEST_CODE=1;
    public final static int VIDEO_REQUEST_CODE=2;
    public final static int AUDIO_REQUEST_CODE=3;

    Uri pictureUri;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise);
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
    }
    public void sendFile(View view){

    }
    public void takePhoto(View view){
        if(!getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA)){
            Toast.makeText(this,"No hay camara", Toast.LENGTH_SHORT).show();
        }
        else{
            Intent intent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            if(intent.resolveActivity(getPackageManager())!=null){
                File dir= Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS);
                try{
                    File file=File.createTempFile("tta", ".jpg", dir);
                    pictureUri= Uri.fromFile(file);
                    intent.putExtra(MediaStore.EXTRA_OUTPUT,pictureUri);
                    startActivityForResult(intent,PICTURE_REQUEST_CODE);
                }catch(IOException e){
                    e.printStackTrace();
                }
            }
            else{
                Toast.makeText(this,"Fallo al sacar foto", Toast.LENGTH_SHORT).show();
            }
        }
    }
    public void recordAudio(View view){
        if(!getPackageManager().hasSystemFeature(PackageManager.FEATURE_MICROPHONE)){
            Toast.makeText(this,"No hay micro", Toast.LENGTH_SHORT).show();
        }
        else{
            Intent intent=new Intent(MediaStore.Audio.Media.RECORD_SOUND_ACTION);
            if(intent.resolveActivity(getPackageManager())!=null){

                startActivityForResult(intent,AUDIO_REQUEST_CODE);
            }
            else{
                Toast.makeText(this,"Fallo al grabar", Toast.LENGTH_SHORT).show();
            }
        }
    }
    public void recordVideo(View view){
        if(!getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA)){
            Toast.makeText(this,"No hay camara", Toast.LENGTH_SHORT).show();
        }
        else{
            Intent intent=new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
            if(intent.resolveActivity(getPackageManager())!=null){

                startActivityForResult(intent,VIDEO_REQUEST_CODE);//Tras ejecutar el intent se llama a onActivityResult
            }
            else{
                Toast.makeText(this,"Fallo al grabar video", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void onActivityResult(int requestCode,int resultCode,Intent data){
        if(resultCode!= Activity.RESULT_OK)
            return;
        switch(requestCode){
            case READ_REQUEST_CODE:
                String Fpath = data.getDataString();
                Toast.makeText(this,Fpath, Toast.LENGTH_SHORT).show();
                break;
            case VIDEO_REQUEST_CODE:
            case AUDIO_REQUEST_CODE:
                Toast.makeText(this,"senddata", Toast.LENGTH_SHORT).show();
                break;
            case PICTURE_REQUEST_CODE:
                Toast.makeText(this,"sendPicture", Toast.LENGTH_SHORT).show();
                break;


        }
    }
}
