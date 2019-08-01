package com.duckdns.jast.chatroom.api;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.duckdns.jast.chatroom.exception.NewUserInvalidException;
import com.duckdns.jast.chatroom.model.ChatUser;
import com.duckdns.jast.chatroom.model.UserCredential;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Controller
@Api(value = "chat-users", description = "the chat-rooms API", tags = { "chat users" })
public class ChatUserApiFacade implements ChatUserApi {

	private final ChatUserApiController chatUserApiCtrl;
	
	public ChatUserApiFacade(ChatUserApiController chatUserApiCtrl) {
		this.chatUserApiCtrl = chatUserApiCtrl;
	}

	@ApiOperation(
			value = "Save user", 
			nickname = "saveUser", 
			notes = "Save a new user", 
			response = ApiResponseMessage.class,
			tags = { "chat users" })
	@ApiResponses(value = { 
			@ApiResponse(code = 201, message = "Success", response = ApiResponseMessage.class),
			@ApiResponse(code = 400, message = "Invalid username or email", response = ApiResponseMessage.class),
			@ApiResponse(code = 500, message = "Server error", response = ApiResponseMessage.class) })
	@RequestMapping(
			value = "/users", 
			produces = { "application/json" }, 
			consumes = { "application/json" }, 
			method = RequestMethod.POST)
	public ResponseEntity<?> saveUser(
			@ApiParam(value = "User data", required = true) @Valid @RequestBody ChatUser chatUser) throws NewUserInvalidException {
		return chatUserApiCtrl.saveUser(chatUser);
	}

	@ApiOperation(
			value = "Validate username", 
			nickname = "isValidUsername", 
			notes = "Get the username status", 
			response = ApiResponseMessage.class, 
			tags = { "chat users", })
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "Success", response = ApiResponseMessage.class),
			@ApiResponse(code = 500, message = "Server error", response = ApiResponseMessage.class) })
	@RequestMapping(
			value = "/validators/username", 
			produces = { "application/json" }, 
			method = RequestMethod.GET)
	public ResponseEntity<?> isValidUsername(
			@ApiParam(value = "Username to validate", required = true) @RequestParam(name = "value", required = true) String username) {
		return chatUserApiCtrl.isValidUsername(username);
	}

	
	@ApiOperation(
			value = "Validate email", 
			nickname = "isValidEmail", 
			notes = "Get the email status", 
			response = ApiResponseMessage.class, 
			tags = { "chat users", })
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "Success", response = ApiResponseMessage.class),
			@ApiResponse(code = 500, message = "Server error", response = ApiResponseMessage.class) })
	@RequestMapping(
			value = "/validators/email", 
			produces = { "application/json" }, 
			method = RequestMethod.GET)
	public ResponseEntity<?> isValidEmail(
			@ApiParam(value = "Email to validate", required = true) @RequestParam(name = "value", required = true) String email) {
		return chatUserApiCtrl.isValidEmail(email);
	}

	@ApiOperation(
			value = "Login", 
			nickname = "loginUser", 
			notes = "Login user with credentials", 
			response = ApiResponseMessage.class,
			tags = { "chat users" })
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "Success", response = ChatUser.class),
			@ApiResponse(code = 401, message = "Invalid username or password", response = ApiResponseMessage.class),
			@ApiResponse(code = 500, message = "Server error", response = ApiResponseMessage.class) })
	@RequestMapping(
			value = "/login", 
			produces = { "application/json" }, 
			consumes = { "application/json" }, 
			method = RequestMethod.POST)
	public ResponseEntity<?> login(
			@ApiParam(value = "User credential", required = true) @Valid @RequestBody UserCredential userCredential) {
		return chatUserApiCtrl.login(userCredential);
	}
	
}
