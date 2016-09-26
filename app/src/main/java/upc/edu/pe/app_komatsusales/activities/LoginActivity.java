package upc.edu.pe.app_komatsusales.activities;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import android.content.Intent;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import android.util.Log;

import upc.edu.pe.app_komatsusales.R;
import upc.edu.pe.app_komatsusales.model.Usuario;


public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    EditText user, pass;
    Button login, cancel;
    //Usuario  usruario;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // setting default screen to login.xml
        setContentView(R.layout.activity_login);

        user   = (EditText) findViewById(R.id.txtUsuario);
        pass   = (EditText) findViewById(R.id.txtPassword);
        login  = (Button) findViewById(R.id.btnLogin);
        cancel = (Button) findViewById(R.id.btnCancel);

        login.setOnClickListener(this);
        cancel.setOnClickListener(this);

    }

    public void onClick(View v){

        String strUser = user.getText().toString();
        String strPass = pass.getText().toString();

        switch (v.getId()){
            case R.id.btnLogin:
                if(strUser.equals("alicia") && strPass.equals("12345")){
                    Intent i = new Intent(this, MainActivity.class);
                    startActivity(i);
                }else{

                }
                break;

            case R.id.btnCancel:

                user.setText(" ");
                pass.setText(" ");
                consumeWSRest();
                break;

            default:
                break;

        }

    }

    public void consumeWSRest(){

        String restURL ="http://52.88.24.228/ServiciosKomatsuSales/Service1.svc/Login";

        Usuario usuario = new Usuario();

        usuario.setUsuario("jperez");//user.toString());
        usuario.setPassword("jperez");//pass.toString());
        Log.i("Objeto Usuario : ", usuario.getUsuario());


        String txt = new RestOperation().SendHttoPostRequest(restURL, usuario);

        Log.i("TEXTO RES ",txt);
        TextView t = (TextView)findViewById(R.id.txtUsuario);

        t.setText(txt);

    }








}

