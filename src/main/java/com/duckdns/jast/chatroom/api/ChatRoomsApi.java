package com.duckdns.jast.chatroom.api;

import org.springframework.http.ResponseEntity;

import com.duckdns.jast.chatroom.exception.ChatRoomNotFoundException;
import com.duckdns.jast.chatroom.model.ChatRoom;

public interface ChatRoomsApi {

    ResponseEntity<?> deleteChatRoom(Integer chatRoomId) throws ChatRoomNotFoundException;

    ResponseEntity<?> findChatRoom(Integer chatRoomId) throws ChatRoomNotFoundException;

    ResponseEntity<?> getChatRooms();

    ResponseEntity<?> saveChatRoom(ChatRoom body);

    ResponseEntity<?> updateChatRoom(Integer chatRoomId, ChatRoom body) throws ChatRoomNotFoundException;

}
