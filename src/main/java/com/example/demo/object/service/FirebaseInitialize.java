package com.example.demo.object.service;

import java.io.FileInputStream;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

@Service
public class FirebaseInitialize {
	
	@PostConstruct
	public void initialize() {
		//unique key is created on firebase admin acc. settings - service accounts - generate private key - copy the code
		try {
		FileInputStream serviceAccount =
				  new FileInputStream("./serviceAccountKey.json");

				FirebaseOptions options = new FirebaseOptions.Builder()
				  .setCredentials(GoogleCredentials.fromStream(serviceAccount))
				  .setDatabaseUrl("https://fun7-b79f4.firebaseio.com")
				  .build();

				FirebaseApp.initializeApp(options);
		}catch(Exception e) {System.out.println(e);}
		
	}
}
