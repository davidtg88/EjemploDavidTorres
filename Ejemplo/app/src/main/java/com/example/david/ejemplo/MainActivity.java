package com.example.david.ejemplo;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    public final static String EXTRA_LOGIN ="es.tta.ejemplo31.login";
    public final static String EXTRA_PASSWD ="es.tta.ejemplo31.passwd";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    public void login (View view){
        Intent intent = new Intent(this,MenuActivity.class);
        EditText editLogin=(EditText)findViewById(R.id.login);
        EditText editPasswd=(EditText)findViewById(R.id.passwd);
        String log="david";
        String pass="david";
        if(log.equals(editLogin.getText().toString()) && pass.equals(editPasswd.getText().toString())){
            intent.putExtra(EXTRA_LOGIN,editLogin.getText().toString());
            intent.putExtra(EXTRA_PASSWD, editPasswd.getText().toString());
            Toast.makeText(this, "Bienvenido", Toast.LENGTH_SHORT).show();
            startActivity(intent);
        }
       else if(TextUtils.isEmpty(editLogin.getText().toString()) || TextUtils.isEmpty(editPasswd.toString())){
            Toast.makeText(getApplicationContext(),"Campo Login/Pass en blanco",Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(this,"Login Incorrecto",Toast.LENGTH_SHORT).show();
        }
    }
    private String[] loadLogin(){
        SharedPreferences preferences=getPreferences(MODE_PRIVATE);
        String[] logeo=new String[2];
        logeo[0]=preferences.getString(EXTRA_LOGIN,null);
        logeo[1]=preferences.getString(EXTRA_PASSWD,null);
        return logeo;
    }
    private void saveLogin (String usu, String pass){
        SharedPreferences pref =getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor editor=pref.edit();
        editor.putString(EXTRA_LOGIN,usu);
        editor.putString(EXTRA_PASSWD,pass);
        editor.commit();
    }
}
