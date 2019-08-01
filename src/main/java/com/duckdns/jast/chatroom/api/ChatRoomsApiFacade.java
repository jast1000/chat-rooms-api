package com.duckdns.jast.chatroom.api;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.duckdns.jast.chatroom.exception.ChatRoomNotFoundException;
import com.duckdns.jast.chatroom.model.ChatRoom;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Controller
@Api(value = "chat-rooms", description = "the chat-rooms API", tags = { "chat rooms" })
public class ChatRoomsApiFacade implements ChatRoomsApi {

	private ChatRoomsApiController controller;

	public ChatRoomsApiFacade(ChatRoomsApiController controller) {
		this.controller = controller;
	}

	@ApiOperation(
			value = "Delete chat room", 
			nickname = "deleteChatRoom", 
			notes = "Delete a chat room by identifier", 
			tags = { "chat rooms", })
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "Success", response = ApiResponseMessage.class ),
			@ApiResponse(code = 404, message = "Chat room not found"),
			@ApiResponse(code = 500, message = "Server error", response = ApiResponseMessage.class ) })
	@RequestMapping(
			value = "/chat-rooms/{chatRoomId}", 
			produces = { "application/json" }, 
			method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteChatRoom(
			@ApiParam(value = "Chat room identifier", required = true) @PathVariable("chatRoomId") Integer chatRoomId) 
					throws ChatRoomNotFoundException {
		return controller.deleteChatRoom(chatRoomId);
	}

	@ApiOperation(
			value = "Get chat room", 
			nickname = "findChatRoom", 
			notes = "Get a chat room", 
			response = ChatRoom.class, 
			tags = { "chat rooms", })
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "Success", response = ChatRoom.class),
			@ApiResponse(code = 404, message = "Chat room not found"),
			@ApiResponse(code = 500, message = "Server error", response = ApiResponseMessage.class) })
	@RequestMapping(
			value = "/chat-rooms/{chatRoomId}", 
			produces = { "application/json" }, 
			method = RequestMethod.GET)
	public ResponseEntity<?> findChatRoom(
			@ApiParam(value = "Chat room identifier", required = true) @PathVariable("chatRoomId") Integer chatRoomId) 
					throws ChatRoomNotFoundException {
		return controller.findChatRoom(chatRoomId);
	}

	@ApiOperation(
			value = "Get chat rooms", 
			nickname = "getChatRooms", 
			notes = "Get all chat rooms", 
			response = ChatRoom.class, 
			responseContainer = "List", 
			tags = { "chat rooms", })
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Success", response = ChatRoom.class, responseContainer = "List"),
			@ApiResponse(code = 500, message = "Server error", response = ApiResponseMessage.class) })
	@RequestMapping(
			value = "/chat-rooms", 
			produces = { "application/json" }, 
			method = RequestMethod.GET)
	public ResponseEntity<?> getChatRooms() {
		return controller.getChatRooms();
	}

	@ApiOperation(
			value = "Create chat room", 
			nickname = "saveChatRoom", 
			notes = "Create a chat room", 
			tags = { "chat rooms", })
	@ApiResponses(value = { 
			@ApiResponse(code = 201, message = "Success", response = ApiResponseMessage.class),
			@ApiResponse(code = 500, message = "Server error", response = ApiResponseMessage.class) })
	@RequestMapping(
			value = "/chat-rooms", 
			produces = { "application/json" }, 
			consumes = { "application/json" }, 
			method = RequestMethod.POST)
	public ResponseEntity<?> saveChatRoom(
			@ApiParam(value = "Chatroom data", required = true) @Valid @RequestBody ChatRoom body) {
		return controller.saveChatRoom(body);
	}

	@ApiOperation(
			value = "Update chat room", 
			nickname = "updateChatRoom", 
			notes = "Update chat room data", 
			tags = { "chat rooms", })
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "Success", response = ApiResponseMessage.class),
			@ApiResponse(code = 404, message = "Chat room not found"),
			@ApiResponse(code = 500, message = "Server error", response = ApiResponseMessage.class) })
	@RequestMapping(
			value = "/chat-rooms/{chatRoomId}", 
			produces = { "application/json" }, 
			method = RequestMethod.PUT)
	public ResponseEntity<?> updateChatRoom(
			@ApiParam(value = "Chat room identifier", required = true) @PathVariable("chatRoomId") Integer chatRoomId,
			@ApiParam(value = "Chat room data", required = true) @Valid @RequestBody ChatRoom body) 
					throws ChatRoomNotFoundException {
		return controller.updateChatRoom(chatRoomId, body);
	}

}
