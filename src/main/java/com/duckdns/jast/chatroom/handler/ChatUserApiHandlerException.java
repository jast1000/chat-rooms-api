package com.duckdns.jast.chatroom.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.duckdns.jast.chatroom.api.ApiResponseMessage;
import com.duckdns.jast.chatroom.api.ChatUserApi;
import com.duckdns.jast.chatroom.exception.NewUserInvalidException;

@ControllerAdvice(assignableTypes = ChatUserApi.class)
public class ChatUserApiHandlerException {

	private final Logger LOG = LoggerFactory.getLogger(ChatRoomsApiHandlerException.class);
	
	@ExceptionHandler(NewUserInvalidException.class)
	private ResponseEntity<?> handlerNewUserInvalidException(NewUserInvalidException ex) {
		LOG.warn(ex.getMessage());
		ApiResponseMessage response = new ApiResponseMessage(ApiResponseMessage.ERROR, "The username or password is invalid");
		return ResponseEntity.badRequest().body(response);
	}
	
	@ExceptionHandler(Exception.class) 
	private ResponseEntity<?> handlerException(Exception ex) {
		LOG.error(ex.getMessage(), ex);
		ApiResponseMessage response = new ApiResponseMessage(ApiResponseMessage.ERROR, "Server error");
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
	}
	
}
