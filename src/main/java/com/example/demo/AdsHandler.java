package com.example.demo;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

import com.example.demo.object.User;

public class AdsHandler {
	public AdsHandler() {
		super();
	}
	public boolean adsEnabled(User user) {
		try{
	        //adding cc param
	        String url = "https://us-central1-o7tools.cloudfunctions.net/fun7-ad-partner";
	        url += "?countryCode="+user.getCc();
	        //creating URL object and opening connection
	        URL urlOBJ = new URL(url);
	        HttpURLConnection connection = (HttpURLConnection) urlOBJ.openConnection();
	        
	        //REQUEST SETUP
	        connection.setRequestMethod("GET");
	        connection.setConnectTimeout(5000);
	        connection.setReadTimeout(5000);
	        
	        //AUTHENTICATION
	        String auth = "fun7user:fun7pass";
	        //encoding
	        String encodedAuth = Base64.getEncoder().encodeToString(auth.getBytes(StandardCharsets.UTF_8));
	        //header value
	        String authHeaderValue = "Basic " + new String(encodedAuth);
	        connection.setRequestProperty("Authorization", authHeaderValue);
	        
	        //http response code
	        int responseCode = connection.getResponseCode();
	        System.out.println(responseCode);
	        //response body
	        InputStream inputStream;
	        if (200 <= responseCode && responseCode <= 299) {
	            inputStream = connection.getInputStream();
	        } else {
	            inputStream = connection.getErrorStream();
	        }
	        //buffer allows us to read text from InputStream we get from connection.
	        BufferedReader in = new BufferedReader(
	                new InputStreamReader(
	                        inputStream));
	        String result = in.readLine();	            
	        in.close();
	        System.out.println(result);
	        String search  = "sure,";
	        
	        //simple word check
	        if ( result.toLowerCase().indexOf(search.toLowerCase()) != -1 ) {

	        	   return true;

	        	} else {

	        	  return false;

	        	}
	        
	    }
	    catch(Exception e){
	        System.out.println(e);
	        return false;
    }
	}
}
