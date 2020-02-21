package com.example.demo;

import java.time.LocalDate;
import java.time.Month;
import java.time.ZoneId;
import java.util.Date;
import java.util.Set;

import com.example.demo.object.User;

public class CustomerSupportHandler {	
	
	public CustomerSupportHandler(){
		super();
	}
	
	//use format:EEE, hh:mm
	public boolean isSupportAvailable(User user, String timePattern) {
		if(!isCorrectFormat(timePattern)) return false;
		Set<String> allZones = ZoneId.getAvailableZoneIds();
		if(allZones.contains(user.getTimezone())) {
		  String hourDiff = ZoneId.of(user.getTimezone())
			        .getRules()
			        .getOffset( 
			            LocalDate.of( 2018 , Month.AUGUST , 23 )          
			            .atStartOfDay( ZoneId.of( "Asia/Tokyo" ) )  
			            .toInstant()                                     
			        ) 
			        .toString();
			        int hourDiffInInt = Integer.parseInt(hourDiff.substring(1,3)) - 1; //-1 because SLO is +1 
			        
		String dayOfWeek = timePattern.substring(0,3);
		String hour = timePattern.substring(5,7);
		int rightHour = (Integer.parseInt(hour) + hourDiffInInt) % 24;
		String minute = timePattern.substring(8,10);
		
		if((!dayOfWeek.equalsIgnoreCase("Sat") && !dayOfWeek.equalsIgnoreCase("Sun")) && rightHour >= 9 
				&& rightHour < 15) {
			return true;
		}else return false;		
	}else {
		System.out.println("Wrong timezone format."); 
		return false;
		}
	}
	
	public boolean isCorrectFormat(String timePattern) {
		String dayOfWeek = timePattern.substring(0,3);
		String hour = timePattern.substring(5,7);
		int hourr = Integer.parseInt(hour);
		

		if((dayOfWeek.equalsIgnoreCase("Mon")||
				dayOfWeek.equalsIgnoreCase("Tue") ||
				dayOfWeek.equalsIgnoreCase("Wed") ||
				dayOfWeek.equalsIgnoreCase("Thu") ||
				dayOfWeek.equalsIgnoreCase("Fri") ||
				dayOfWeek.equalsIgnoreCase("Sat") ||
				dayOfWeek.equalsIgnoreCase("Sun")) 
				&& ((hourr >= 0) && (hourr <= 23))) {return true;}
				else {
					System.out.println("Wrong format of date and time! Format:'EEE, hh:mm'");
					return false;}
	}
}
