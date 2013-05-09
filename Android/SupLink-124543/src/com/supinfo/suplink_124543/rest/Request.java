package com.supinfo.suplink_124543.rest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

public class Request {
	
	public static JSONObject sendPostRequest(String address, JSONObject obj) throws IllegalStateException, IOException, JSONException {	
	    Log.e("REST REQUEST", "********* POST request");
	    Log.e("REST REQUEST", "JSON sent : " + obj.toString());

		HttpPost post = new HttpPost(address);
				
	    post.setHeader("Content-type", "application/json");
	    post.setHeader("Accept", "application/json");

	    post.setEntity(new ByteArrayEntity(obj.toString().getBytes("UTF8")));
		HttpClient httpclient = new DefaultHttpClient();
		HttpResponse response = httpclient.execute(post);
		
		return getRestResponse(response);	
	}
	
	
	public static JSONObject sendGetRequest(String address) throws IllegalStateException, IOException, JSONException {	
	    Log.e("REST REQUEST", "********* GET request");

		HttpGet get = new HttpGet(address);
				
		get.setHeader("Accept", "application/json");

		HttpClient httpclient = new DefaultHttpClient();
		HttpResponse response = httpclient.execute(get);
		
		return getRestResponse(response);	
	}

	public static JSONObject sendPutRequest(String address, JSONObject obj) throws IllegalStateException, IOException, JSONException {	
	    Log.e("REST REQUEST", "********* PUT request");
	    Log.e("REST REQUEST", "JSON sent : " + obj.toString());

		HttpPut put = new HttpPut(address);
				
		put.setHeader("Content-type", "application/json");
		put.setHeader("Accept", "application/json");

		put.setEntity(new ByteArrayEntity(obj.toString().getBytes("UTF8")));
		HttpClient httpclient = new DefaultHttpClient();
		HttpResponse response = httpclient.execute(put);
		
		return getRestResponse(response);
	}
	
	public static JSONObject sendDeleteRequest(String address) throws IllegalStateException, IOException, JSONException {	
	    Log.e("REST REQUEST", "********* DELETE request");

		HttpDelete delete = new HttpDelete(address);
				
		delete.setHeader("Accept", "application/json");

		HttpClient httpclient = new DefaultHttpClient();
		HttpResponse response = httpclient.execute(delete);
		
		return getRestResponse(response);
	}
	
	public static String convertStreamToString(InputStream is) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is), 8192);
        StringBuilder sb = new StringBuilder();

        String line = null;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
        }
        return sb.toString();
    }
	
	protected static JSONObject getRestResponse(HttpResponse response) throws JSONException, IllegalStateException, IOException{
		HttpEntity entity = response.getEntity();
        
	    Log.e("REST REQUEST", "Code : " + response.getStatusLine().getStatusCode());

        if (response.getStatusLine().getStatusCode() == 200 && entity != null) {

            InputStream instream = entity.getContent();
            String result = convertStreamToString(instream);
            
            JSONObject json = new JSONObject();
            json = new JSONObject(result);
               
            instream.close();
            
    	    Log.e("REST REQUEST", "The result was returned.");
    	    return json;
        }
        else{
    	    Log.e("REST REQUEST", "No result.");
    	    return null;
        }
	}

}
