package com.duckdns.jast.chatroom.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.duckdns.jast.chatroom.api.ApiResponseMessage;
import com.duckdns.jast.chatroom.api.ChatRoomsApi;
import com.duckdns.jast.chatroom.exception.ChatRoomNotFoundException;

@ControllerAdvice(assignableTypes = ChatRoomsApi.class )
public class ChatRoomsApiHandlerException {
	
	private final Logger LOG = LoggerFactory.getLogger(ChatRoomsApiHandlerException.class);
	
	@ExceptionHandler(ChatRoomNotFoundException.class)
	private ResponseEntity<?> handlerChatRoomNotFoundException(ChatRoomNotFoundException ex) {
		LOG.warn(ex.getMessage());
		return ResponseEntity.notFound().build();
	}
	
	@ExceptionHandler(Exception.class) 
	private ResponseEntity<?> handlerException(Exception ex) {
		LOG.error(ex.getMessage(), ex);
		ApiResponseMessage response = new ApiResponseMessage(ApiResponseMessage.ERROR, "Server error");
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
	}
	
}
