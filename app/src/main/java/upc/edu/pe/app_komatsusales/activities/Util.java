package upc.edu.pe.app_komatsusales.activities;

import android.app.Application;
import android.content.Intent;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.*;
import java.util.HashMap;
import android.util.*;

import upc.edu.pe.app_komatsusales.model.Personal;
import upc.edu.pe.app_komatsusales.model.Usuario;
/**
 * Created by Alicia on 28/09/2016.
 */

public class Util  extends Application {

    Personal personal = new Personal();

    public void RestLogin(String URL, Usuario users) {
        System.out.println(" :::::::::::::::::::::::::: Ingresando a RestLogin :::::::::::::::::::::::::: ");

        System.out.println(" ::::::::::: users :::::::::::::::::::::::::: "+users.getUsuario() +"    "+users.getPassword());
        System.out.println("URL = " + URL);
        Map<String, String> jsonParams = new HashMap<String, String>();
        try {
            // POST parameters

            jsonParams.put("usuario", users.getUsuario().toString());
            jsonParams.put("password", users.getPassword().toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(" :::::::::::::::::::::::::: params :::::::::::::::::::::::::: " +jsonParams.toString());

        JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.GET, URL, new JSONObject(jsonParams), new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                // the response is already constructed as a JSONObject!
                Log.e("Response::::", response.toString());
              //  verificationSuccess(response);
                try {

                    String ape = response.getString("Apellidos");
                    String cargo = response.getString("Cargo");
                    System.out.println(" :::::::::::::::::::::::::: JsonObjectRequest :::::::::::::::::::::::::: "+cargo +"     "+ape);

                    personal.setApellidos(response.getString("Apellidos"));
                    personal.setCargo(response.getString("Cargo"));
                    personal.setDNI(response.getString("DNI"));
                    personal.setEmail(response.getString("Email"));
                    personal.setIdCargo(response.getString("IdCargo"));
                    personal.setIdPersonal(response.getString("IdPersonal"));
                    personal.setNombre(response.getString("Nombre"));
                    personal.setSexo(response.getString("Sexo"));
                    personal.setTelefono(response.getString("Telefono"));

                   System.out.println("Datos del Personal: " +personal.getNombre() +"," +personal.getApellidos());


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println(":::::::::::::::::::::::Error: "+error.getMessage());

                error.printStackTrace();
            }
        });
    //    Volley.newRequestQueue(this).add(jsonRequest);

    }
}
