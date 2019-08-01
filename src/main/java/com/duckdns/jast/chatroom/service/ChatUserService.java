package com.duckdns.jast.chatroom.service;

import org.springframework.stereotype.Service;

import com.duckdns.jast.chatroom.dao.ChatUserDAO;
import com.duckdns.jast.chatroom.exception.NewUserInvalidException;
import com.duckdns.jast.chatroom.model.ChatUser;

@Service
public class ChatUserService {

	private final ChatUserDAO chatUserDAO;

	public ChatUserService(ChatUserDAO chatUserDAO) {
		this.chatUserDAO = chatUserDAO;
	}

	public boolean isValidUsername(String username) {
		return chatUserDAO.isValidUsername(username);
	}
	
	public boolean isValidEmail(String email) {
		return chatUserDAO.isValidEmail(email);
	}
	
	public void saveUser(ChatUser user) throws NewUserInvalidException {
		boolean userValid = chatUserDAO.isValidUsername(user.getUsername());
		if (!userValid)
			throw new NewUserInvalidException("The username is invalid...");
		boolean emailValid = chatUserDAO.isValidEmail(user.getEmail());
		if (!emailValid)
			throw new NewUserInvalidException("The email is invalid...");
		
		chatUserDAO.save(user);
	}

	public ChatUser login(String username, String password) {
		return chatUserDAO.getBasicUser(username, password);
	}
	
}
