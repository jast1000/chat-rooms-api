package com.duckdns.jast.chatroom.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.duckdns.jast.chatroom.exception.NewUserInvalidException;
import com.duckdns.jast.chatroom.model.ChatUser;
import com.duckdns.jast.chatroom.model.UserCredential;
import com.duckdns.jast.chatroom.service.ChatUserService;

@Component
public class ChatUserApiController implements ChatUserApi {

	private final ChatUserService chatUserSrv;
	
	public ChatUserApiController(ChatUserService chatUserSrv) {
		this.chatUserSrv = chatUserSrv;
	}
	
	@Override
	public ResponseEntity<?> saveUser(ChatUser chatUser) throws NewUserInvalidException {
		chatUserSrv.saveUser(chatUser);
		ApiResponseMessage response = new ApiResponseMessage(ApiResponseMessage.OK, "User created");
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}

	@Override
	public ResponseEntity<?> isValidUsername(String username) {
		ApiResponseMessage response = null;
		boolean valid = chatUserSrv.isValidUsername(username);
		if(valid) {
			response = new ApiResponseMessage(ApiResponseMessage.OK, "Usename valid");
		} else {
			response = new ApiResponseMessage(ApiResponseMessage.ERROR, "Username invalid");
		}
		return ResponseEntity.ok(response);
	}

	@Override
	public ResponseEntity<?> isValidEmail(String email) {
		ApiResponseMessage response = null;
		boolean valid = chatUserSrv.isValidEmail(email);
		if(valid) {
			response = new ApiResponseMessage(ApiResponseMessage.OK, "Email valid");
		} else {
			response = new ApiResponseMessage(ApiResponseMessage.ERROR, "Email invalid");
		}
		return ResponseEntity.ok(response);
	}

	@Override
	public ResponseEntity<?> login(UserCredential userCredential) {
		ChatUser user = chatUserSrv.login(userCredential.getUsername(), userCredential.getPassword());
		if(user != null) {
			return ResponseEntity.ok(user);
		} else {
			ApiResponseMessage response = new ApiResponseMessage(ApiResponseMessage.ERROR, "The username or password is invalid");
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
		}
	}

}
