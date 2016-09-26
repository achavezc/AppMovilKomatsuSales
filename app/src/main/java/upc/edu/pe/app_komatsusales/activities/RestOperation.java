package upc.edu.pe.app_komatsusales.activities;

/**
 * Created by Alicia on 25/09/2016.
 */
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import org.apache.http.HttpEntity;
import org.apache.http.util.EntityUtils;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;



import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URLEncoder;
import java.util.UUID;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;
import org.json.JSONException;
import org.json.JSONObject;
import android.util.Log;





import upc.edu.pe.app_komatsusales.model.Usuario;


public class RestOperation
{
    private final String HTTP_EVENT="http://52.88.24.228/ServiciosKomatsuSales/Service1.svc/Logi";
    private HttpClient httpclient;

    public String SendHttoPostRequest(String URL, Usuario obj){

        HttpClient httpClient = new DefaultHttpClient();
        HttpPost httpPost = new HttpPost(URL);
        httpPost.addHeader("Content-Type", "application/json");

        JSONObject jsonObject = new JSONObject();

        List<NameValuePair> nameValuePair = new ArrayList<NameValuePair>(2);
        nameValuePair.add(new BasicNameValuePair("usuario", obj.getUsuario().toString() ));
        nameValuePair.add(new BasicNameValuePair("password", obj.getPassword()));

        //Encoding POST data
        try {
          //  httpPost.setEntity(new UrlEncodedFormEntity(nameValuePair));

            UrlEncodedFormEntity ent     = new UrlEncodedFormEntity(nameValuePair,"utf-8");
            //httpPost.setEntity(new UrlEncodedFormEntity(nameValuePair, "UTF-8"));
            HttpResponse responsePOST   = httpClient.execute(httpPost);
            HttpEntity resEntity        = responsePOST.getEntity();

            String text = EntityUtils.toString(resEntity);

            if (resEntity != null) {
                Log.i("RESPONSE",EntityUtils.toString(resEntity));
            }

            return text;

        } catch (UnsupportedEncodingException e) {
            // log exception
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //making POST request.
        try {
            HttpResponse response = httpClient.execute(httpPost);
            // write response to log
            Log.d("Http Post Response:", response.toString());
        } catch (ClientProtocolException e) {
            // Log exception
            e.printStackTrace();
        } catch (IOException e) {
            // Log exception
            e.printStackTrace();
        }

        return null;
    }
    public String addEventPost(String name, String age) //throws ClientProtocolException, IOException, JSONException
    {
        httpclient = new DefaultHttpClient();

        //url y tipo de contenido
        HttpPost httppost = new HttpPost(HTTP_EVENT);
        httppost.addHeader("Content-Type", "application/json");
        //forma el JSON y tipo de contenido
        JSONObject jsonObject = new JSONObject();
        Log.i("jsonObject        9999",jsonObject.toString());
        try {
            jsonObject.put("usuario", name );
            jsonObject.put("password", age );
            StringEntity stringEntity = new StringEntity( jsonObject.toString());
            stringEntity.setContentType( (Header) new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
            httppost.setEntity(stringEntity);
            //ejecuta
            HttpResponse response = httpclient.execute(httppost);
            //obtiene la respuesta y transorma a objeto JSON
            String jsonResult = inputStreamToString(response.getEntity().getContent()).toString();
            JSONObject object = new JSONObject(jsonResult);
            Log.i("jsonResult",jsonResult);
            if( object.getString("Result").equals("200"))
            {
                return "Petición POST: Exito";
            }

            return "Petición POST: Fracaso";

        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Transforma el InputStream en un String
     * @return StringBuilder
     * */
    private StringBuilder inputStreamToString(InputStream is)
    {
        String line = "";
        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader rd = new BufferedReader( new InputStreamReader(is) );
        try
        {
            while( (line = rd.readLine()) != null )
            {
                stringBuilder.append(line);
            }
        }
        catch( IOException e)
        {
            e.printStackTrace();
        }

        return stringBuilder;
    }



}
