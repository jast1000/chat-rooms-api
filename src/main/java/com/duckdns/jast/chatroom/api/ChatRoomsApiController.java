package com.duckdns.jast.chatroom.api;

import com.duckdns.jast.chatroom.exception.ChatRoomNotFoundException;
import com.duckdns.jast.chatroom.model.ChatRoom;
import com.duckdns.jast.chatroom.service.ChatRoomsService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class ChatRoomsApiController implements ChatRoomsApi {

    private final ChatRoomsService chatRoomSrv;
    
    public ChatRoomsApiController(ChatRoomsService chatRoomSrv) {
    	this.chatRoomSrv = chatRoomSrv;
    }

    public ResponseEntity<?> deleteChatRoom(Integer chatRoomId) throws ChatRoomNotFoundException {
    	chatRoomSrv.deleteChatRoom(chatRoomId);
        return ResponseEntity.ok().build();
    }

    public ResponseEntity<?> findChatRoom(Integer chatRoomId) throws ChatRoomNotFoundException {
    	ChatRoom chatRoom = chatRoomSrv.findChatRoom(chatRoomId);
        return ResponseEntity.ok(chatRoom);
    }

    public ResponseEntity<?> getChatRooms() {
    	List<ChatRoom> chatRooms = chatRoomSrv.getAllChatRooms();
        return ResponseEntity.ok(chatRooms);
    }

    public ResponseEntity<?> saveChatRoom(ChatRoom body) {
    	chatRoomSrv.saveChatRoom(body);
    	return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    public ResponseEntity<?> updateChatRoom(Integer chatRoomId, ChatRoom body) throws ChatRoomNotFoundException {
    	chatRoomSrv.updateChatRoom(chatRoomId, body);
    	return ResponseEntity.ok().build();
    }

}
