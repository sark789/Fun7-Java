package com.example.demo.controller;

import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.object.User;
import com.example.demo.object.service.FirebaseService;

@RestController
public class UserController {
	
	@Autowired
	FirebaseService fireBaseService = new FirebaseService();

	@GetMapping("/getUserDetails")
	public User getUserDetails(@RequestHeader String userID) throws InterruptedException, ExecutionException //req. header -> gives back param
	{
		if(!userID.equalsIgnoreCase("")) {
			return fireBaseService.getUserDetails(userID);
		}else return null;
	}
	
	//importing the service we created
	@PostMapping("/createUser")
	public User createNewUser(@RequestBody User user) throws InterruptedException, ExecutionException
	{
		//if user does not exist create it else +1 apicall
		User read = getUserDetails(user.getUserID());
		if(read == null) {
			user.setApiCalls("1");
			return fireBaseService.saveUserDetails(user); //we are getting this from req. body	
		}else {
			return fireBaseService.updateUserDetails(user);
		}
	}

	@PutMapping("/updateUser")
	public User updateUser(@RequestBody User user) throws InterruptedException, ExecutionException {
		int apiCalls = Integer.parseInt(user.getApiCalls()) + 1;
		user.setApiCalls(Integer.toString(apiCalls));
		return fireBaseService.updateUserDetails(user);
	}
	
	@DeleteMapping("/deleteUser")
	public String deleteUser(@RequestHeader String userID) throws InterruptedException, ExecutionException {

			return fireBaseService.deleteUser(userID);

	}
}
