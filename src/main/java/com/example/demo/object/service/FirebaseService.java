package com.example.demo.object.service;

import java.util.concurrent.ExecutionException;

import org.springframework.stereotype.Service;

import com.example.demo.object.User;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;

@Service
public class FirebaseService {
	
	//method for creating/adding a user into a db
	public User saveUserDetails(User user) throws InterruptedException, ExecutionException {
		//getting our database
		Firestore dbFirestore = FirestoreClient.getFirestore();
		//getting the collection from database
		ApiFuture<WriteResult> collectionsApiFuture = dbFirestore.collection("users").document(user.getUserID()).set(user); //grabing the document ID and setting the user object
		//returning the time when the collection is created
		System.out.println("User with ID: " + user.getUserID() + " has been created.");
		return user;	
	}
	
	//getting a certain user by its ID
	public User getUserDetails(String userID) throws InterruptedException, ExecutionException {
		Firestore dbFirestore = FirestoreClient.getFirestore();
		DocumentReference documentReference = dbFirestore.collection("users").document(userID);
		ApiFuture<DocumentSnapshot> future = documentReference.get();
		
		DocumentSnapshot document = future.get();
		
		User user = null;
		
		if(document.exists()) {
			user = document.toObject(User.class); //if it finds the persons id it will try and get its Object with all data
			System.out.println("Reading User with ID: " + user.getUserID());
			return user;
		}else {
			System.out.println("User with ID: " + userID + " does not exist.");
			return null;
		}
	}
	
	//update method
	public User updateUserDetails(User user) throws InterruptedException, ExecutionException {
		//getting our database
		Firestore dbFirestore = FirestoreClient.getFirestore();
		//getting the collection from database
		ApiFuture<WriteResult> collectionsApiFuture = dbFirestore.collection("users").document(user.getUserID()).set(user); //grabing the document ID and setting the user object
		//returning the time when the collection is created
		System.out.println("User with ID: " + user.getUserID() + " has been updated.");
		return user;
	}
	
	//delete method
	public String deleteUser(String userID) {
		//getting our database
		Firestore dbFirestore = FirestoreClient.getFirestore();
		//getting the collection from database
		ApiFuture<WriteResult> writeResult = dbFirestore.collection("users").document(userID).delete();
		System.out.println("User with ID: " + userID + " has been deleted.");
		return userID;
	}
	
}
