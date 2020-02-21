package com.example.demo;

import com.example.demo.controller.UserController;
import com.example.demo.object.User;


import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Fun7 {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(Fun7.class, args);
		
		UserController userController = new UserController();
					  
		//UNIT TESTS
		//mp
		UnitTests tests = new UnitTests();
		tests.multiplayerTestFromUSManyAPICallsShouldReturnTrue();
		tests.multiplayerTestFromUSNotEnoughAPICallsShouldReturnFalse();
		tests.multiplayerTestFromNotUSEnoughAPICallsShouldReturnFalse();
		//user-support
		tests.userSupportTestDuringWeekInWorkHoursReturnTrue();
		tests.userSupportTestDuringWeekendInWorkHoursReturnFalse();
		tests.userSupportTestDuringWeekInNOTWorkHoursReturnFalse();
		//ads
		tests.adsTestSuccessReturnTrue();
		tests.adsTestFailedReturnFalse();
		
		
		
		//INTEGRATION TESTS
		//example 1 - integration test
		User test = new User("John", "Europe/Amsterdam", "AT");
		userController.deleteUser("John"); //if John exists delete him (so we have apicall: 1)
		TimeUnit.SECONDS.sleep(1);
		userController.createNewUser(test); //creating that user
		TimeUnit.SECONDS.sleep(1);
		Fun7App("John", "Sat, 12:20"); //output should be: {"multiplayer": "disabled", "user-support": "disabled", "ads": {any}}
		
		
		//example 2 - integration test
		User katty = new User("Katty", "Europe/Amsterdam", "US");
		userController.deleteUser("Katty"); 
		TimeUnit.SECONDS.sleep(1);
		userController.createNewUser(katty);
		TimeUnit.SECONDS.sleep(1);
		for(int i = 0; i <= 5; i++) {
			userController.updateUser(katty); // so api is more than 5
			TimeUnit.SECONDS.sleep(1);
		}
		Fun7App("Katty", "Mon, 12:20"); //output should be: {"multiplayer": "enabled", "user-support": "enabled", "ads": {any}}
        
	}
	
	
	//this methos is used for integration tests
	public static String Fun7App(String userID, String timeConsideringTimeZone) throws InterruptedException, ExecutionException {
						
		UserController userController = new UserController();
		CustomerSupportHandler sup = new CustomerSupportHandler();
		MultiplayerHandler mpHndlr = new MultiplayerHandler();
		AdsHandler adsHandler = new AdsHandler();
		
		String disabled = " \"disabled\"";
		String enabled = " \"enabled\"";
		String mp = "{\"multiplayer\":";
		String support =", \"user-support\":";
		String ads = ", \"ads\":";
		
		if(userController.getUserDetails(userID) != null) {
			User user = userController.getUserDetails(userID);
			if(mpHndlr.isFromUSAndHasApiCalls(user)) {mp += enabled;}
			else{mp += disabled;}
			
			if(sup.isSupportAvailable(user, timeConsideringTimeZone)) {support += enabled;}
			else {{support += disabled;}}
			
			if(adsHandler.adsEnabled(user)) {ads += enabled + "}";}
			else {ads += disabled + "}";}
							
			String fullString = mp + support + ads;
					
			System.out.println(fullString);
			
			return fullString;
		}
		
		else return "Error in Fun7App method.";
		
		
	}
}
