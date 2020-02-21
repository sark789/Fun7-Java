package com.example.demo;


import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import com.example.demo.controller.UserController;
import com.example.demo.object.User;

public class UnitTests {

	
	
	public void multiplayerTestFromUSManyAPICallsShouldReturnTrue() throws Exception {
		
		boolean isTrue = false;
		
		MultiplayerHandler mp = new MultiplayerHandler();
		User user = new User("Franz", "Europe/Amsterdam", "US");
		UserController userController = new UserController();
		userController.deleteUser("Franz"); 
		TimeUnit.SECONDS.sleep(1);
		userController.createNewUser(user);	
		TimeUnit.SECONDS.sleep(1);
		
		for(int i = 0; i <= 10; i++) {
			userController.updateUser(user); // so api is more than 5
			TimeUnit.SECONDS.sleep(1);
		}
		
		isTrue= mp.isFromUSAndHasApiCalls(user);
		if(isTrue) {
			System.out.println("Test: multiplayerTestFromUSManyAPICallsShouldReturnTrue      passed.");
		}else throw new Exception("Test: multiplayerTestFromUSManyAPICallsShouldReturnTrue        failed");
	}
	
public void multiplayerTestFromUSNotEnoughAPICallsShouldReturnFalse() throws Exception {
		
		boolean isTrue = false;
		
		MultiplayerHandler mp = new MultiplayerHandler();
		User user = new User("Greg", "Europe/Amsterdam", "US");
		UserController userController = new UserController();
		userController.deleteUser("Greg"); 
		TimeUnit.SECONDS.sleep(1);
		userController.createNewUser(user);	
		TimeUnit.SECONDS.sleep(1);
		
		for(int i = 0; i <= 2; i++) {
			userController.updateUser(user); // so api is more than 5
			TimeUnit.SECONDS.sleep(1);
		}
		
		isTrue= mp.isFromUSAndHasApiCalls(user);
		if(!isTrue) {
			System.out.println("Test: multiplayerTestFromUSNotEnoughAPICallsShouldReturnFalse      passed.");
		}else throw new Exception("Test: multiplayerTestFromUSNotEnoughAPICallsShouldReturnFalse        failed");
	}

public void multiplayerTestFromNotUSEnoughAPICallsShouldReturnFalse() throws Exception {
	
	boolean isTrue = false;
	
	MultiplayerHandler mp = new MultiplayerHandler();
	User user = new User("Jos", "Africa/Asmara", "AU");
	UserController userController = new UserController();
	userController.deleteUser("Jos"); 
	TimeUnit.SECONDS.sleep(1);
	userController.createNewUser(user);	
	TimeUnit.SECONDS.sleep(1);
	
	for(int i = 0; i <= 10; i++) {
		userController.updateUser(user); // so api is more than 5
		TimeUnit.SECONDS.sleep(1);
	}
	
	isTrue= mp.isFromUSAndHasApiCalls(user);
	if(!isTrue) {
		System.out.println("Test: multiplayerTestFromNotUSEnoughAPICallsShouldReturnFalse      passed.");
	}else throw new Exception("Test: multiplayerTestFromNotUSEnoughAPICallsShouldReturnFalse        failed");
}

public void userSupportTestDuringWeekInWorkHoursReturnTrue() throws Exception {
	
	boolean isTrue = false;
	
	CustomerSupportHandler support = new CustomerSupportHandler();
	User user = new User("Masa", "Europe/Sarajevo", "AU");
	UserController userController = new UserController();
	userController.deleteUser("Masa"); 
	TimeUnit.SECONDS.sleep(1);
	userController.createNewUser(user);	
	TimeUnit.SECONDS.sleep(1);
	
	isTrue = support.isSupportAvailable(user, "Mon, 12:20");
	if(isTrue) {
		System.out.println("Test: userSupportTestDuringWeekInWorkHoursReturnTrue      passed.");
	}else throw new Exception("Test: userSupportTestDuringWeekInWorkHoursReturnTrue        failed");
}

public void userSupportTestDuringWeekendInWorkHoursReturnFalse() throws Exception {
	
	boolean isTrue = false;
	
	CustomerSupportHandler support = new CustomerSupportHandler();
	User user = new User("Kaja", "Europe/Sarajevo", "AU");
	UserController userController = new UserController();
	userController.deleteUser("Kaja"); 
	TimeUnit.SECONDS.sleep(1);
	userController.createNewUser(user);	
	TimeUnit.SECONDS.sleep(1);
	
	isTrue = support.isSupportAvailable(user, "Sun, 12:20");
	if(!isTrue) {
		System.out.println("Test: userSupportTestDuringWeekendInWorkHoursReturnFalse      passed.");
	}else throw new Exception("Test: userSupportTestDuringWeekendInWorkHoursReturnFalse        failed");
}

public void userSupportTestDuringWeekInNOTWorkHoursReturnFalse() throws Exception {
	
	boolean isTrue = false;
	
	CustomerSupportHandler support = new CustomerSupportHandler();
	User user = new User("Matjaz", "Australia/Darwin", "AU");
	UserController userController = new UserController();
	userController.deleteUser("Matjaz"); 
	TimeUnit.SECONDS.sleep(1);
	userController.createNewUser(user);	
	TimeUnit.SECONDS.sleep(1);
	
	isTrue = support.isSupportAvailable(user, "Wed, 12:20");
	if(!isTrue) {
		System.out.println("Test: userSupportTestDuringWeekInNOTWorkHoursReturnFalse      passed.");
	}else throw new Exception("Test: userSupportTestDuringWeekInNOTWorkHoursReturnFalse        failed");
}

public void adsTestSuccessReturnTrue() throws IOException {
	 //adding cc param
	User user = new User("Andreja", "Europe/Sarajevo", "AU");
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
    	System.out.println("Test: adsTestSuccessReturnTrue      passed.");
    }else {
    	System.out.println("Test: adsTestSuccessReturnTrue      failed.");
    }      
}

public void adsTestFailedReturnFalse() throws IOException {
	 //adding cc param
	User user = new User("Morg", "Europe/Sarajevo", "AU");
   String url = "https://us-central1-o7tools.cloudfunctions.net/fun7-ad-partner";
   url += "?countryCode="+user.getCc();
   //creating URL object and opening connection
   URL urlOBJ = new URL(url);
   HttpURLConnection connection = (HttpURLConnection) urlOBJ.openConnection();
   
   //REQUEST SETUP
   connection.setRequestMethod("GET");
   connection.setConnectTimeout(5000);
   connection.setReadTimeout(5000);
      
   //http response code
   int responseCode = connection.getResponseCode();
   System.out.println(responseCode);
   //response body
   InputStream inputStream;
   if (200 <= responseCode && responseCode <= 299) {
   	System.out.println("Test: adsTestFailedReturnFalse      failed.");
   }else {
   	System.out.println("Test: adsTestFailedReturnFalse      passed.");
   }    
}

}
