package com.example.demo;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import com.example.demo.object.User;

public class MultiplayerHandler {

	public MultiplayerHandler() {
		super();
	}
	
	//all available TimeZones/country codes
	private static void availableIDs() {
	    String[] ids = TimeZone.getAvailableIDs();
	    for (int i = 0; i < ids.length; i++ ) {
	        System.out.println(" ID : " + ids[i]);
	    }
	}
		
	public boolean isFromUSAndHasApiCalls(User user) {
		String[] countryCodes = Locale.getISOCountries(); // all country codes
		// Convert String Array to List
        List<String> list = Arrays.asList(countryCodes);
		if(list.contains(user.getCc())) {
			if(user.getCc().equalsIgnoreCase("US") && (Integer.parseInt(user.getApiCalls()) >= 5)) { 
				return true;
		}
		else { 
			return false;
		}
		}else {
			System.out.println("Invalid data. Please enter country code in 2 letter ISO code.");
			return false;
		}
		
	}
	
}
