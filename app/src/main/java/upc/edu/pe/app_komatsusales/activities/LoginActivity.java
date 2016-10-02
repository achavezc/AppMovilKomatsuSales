package upc.edu.pe.app_komatsusales.activities;


import android.database.Cursor;
import android.support.v4.app.LoaderManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import android.content.Intent;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import android.util.Log;
import android.widget.Toast;

import upc.edu.pe.app_komatsusales.R;
import upc.edu.pe.app_komatsusales.model.Personal;
import upc.edu.pe.app_komatsusales.model.Usuario;


public class LoginActivity extends AppCompatActivity {

    private static String LOGIN_URL = "http://52.88.24.228/ServiciosKomatsuSales/Service1.svc/Login";

    Util util = new Util();

    Button btnLogin ,  btnCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // setting default screen to login.xml
        setContentView(R.layout.activity_login);


        btnLogin  = (Button) findViewById(R.id.btnLogin);
        btnCancel = (Button) findViewById(R.id.btnCancel);


        btnLogin.setOnClickListener(new View.OnClickListener(){

        @Override
        public void onClick(View v){

            String user   = ((EditText) findViewById(R.id.txtUsuario)).getText().toString();
            String pass   = ((EditText) findViewById(R.id.txtPassword)).getText().toString();
            System.out.println("::::::::::::::::::::::::::Datos iNGRESADOS: " +user +"," +pass);

            Usuario usuario = new Usuario(user.toString(), pass.toString());
            util.RestLogin(LOGIN_URL, usuario);

            if(user.equals("jperez") && pass.equals("jperez")){
                Intent nextForm= new Intent(LoginActivity.this, MainActivity.class);
                startActivity(nextForm);
            }else{
                Toast.makeText(getApplicationContext(), "Usuario Incorrecto", Toast.LENGTH_SHORT).show();
            }
        }

        });

    }



}

