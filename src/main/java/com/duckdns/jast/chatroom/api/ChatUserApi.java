package com.duckdns.jast.chatroom.api;

import org.springframework.http.ResponseEntity;

import com.duckdns.jast.chatroom.exception.NewUserInvalidException;
import com.duckdns.jast.chatroom.model.ChatUser;
import com.duckdns.jast.chatroom.model.UserCredential;

public interface ChatUserApi {

	ResponseEntity<?> saveUser(ChatUser chatUser) throws NewUserInvalidException;
	
	ResponseEntity<?> isValidUsername(String username);
	
	ResponseEntity<?> isValidEmail(String email);
	
	ResponseEntity<?> login(UserCredential userCredential);
	
}
