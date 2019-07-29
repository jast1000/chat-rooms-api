package com.duckdns.jast.chatroom.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.duckdns.jast.chatroom.dao.ChatRoomDAO;
import com.duckdns.jast.chatroom.exception.ChatRoomNotFoundException;
import com.duckdns.jast.chatroom.model.ChatRoom;

@Service
public class ChatRoomsService {

	private final ChatRoomDAO chatRoomDAO;
	
	public ChatRoomsService(ChatRoomDAO chatRoomDAO) {
		this.chatRoomDAO = chatRoomDAO;
	}
	
	public ChatRoom findChatRoom(Integer chatRoomId) throws ChatRoomNotFoundException {
		ChatRoom chatRoom = this.chatRoomDAO.find(chatRoomId);
		if (chatRoom == null) {
			throw new ChatRoomNotFoundException("Chat room not found...");
		}
		return chatRoom;
	}
	
	public void saveChatRoom(ChatRoom chatRoom) {
		this.chatRoomDAO.save(chatRoom);
	}
	
	public void updateChatRoom(Integer chatRoomId, ChatRoom chatRoom) throws ChatRoomNotFoundException {
		if (this.chatRoomDAO.find(chatRoomId) == null) {
			throw new ChatRoomNotFoundException("Chat room not found...");
		}
		this.chatRoomDAO.update(chatRoomId, chatRoom);
	}
	
	public List<ChatRoom> getAllChatRooms() {
		return chatRoomDAO.getAll();
	}
	
	public void deleteChatRoom(Integer chatRoomId) throws ChatRoomNotFoundException {
		if (this.chatRoomDAO.find(chatRoomId) == null) {
			throw new ChatRoomNotFoundException("Chat room not found...");
		}
		this.chatRoomDAO.delete(chatRoomId);
	}
	
}
