package com.duckdns.jast.chatroom.dao;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.duckdns.jast.chatroom.model.ChatUser;

@Repository
public class ChatUserDAO {
	
	private final JdbcTemplate jdbc;
	
	public ChatUserDAO(JdbcTemplate jdbc) {
		this.jdbc = jdbc;
	}
	
	public boolean isValidUsername(String username) {
		String sql = "select count(*) from chat_user where username ilike ?";
		Integer users = jdbc.queryForObject(sql, Integer.class, username);
		return users.intValue() == 0;
	}
	
	public boolean isValidEmail(String email) {
		String sql = "select count(*) from chat_user where email ilike ?";
		Integer users = jdbc.queryForObject(sql, Integer.class, email);
		return users.intValue() == 0;
	}
	
	public void save(ChatUser chatUser) {
		String sql = "INSERT INTO public.chat_user(full_name, email, username, \"password\") VALUES(?, ?, ?, encode(digest(?, 'sha256'), 'base64'))";
		jdbc.update(sql, chatUser.getFullName(), chatUser.getEmail(), chatUser.getUsername(), chatUser.getPassword());
	}

	public ChatUser getBasicUser(String username, String password) {
		String sql = "select chat_user_id as id, full_name as \"fullName\", username, email from chat_user "
				+ "where (username = ? or email = ?) and \"password\" = encode(digest(?, 'sha256'), 'base64')";
		try {
			return jdbc.queryForObject(sql, new Object[] {	username, username, password }, new BeanPropertyRowMapper<>(ChatUser.class));
		} catch (DataAccessException ex) {
			return null;
		}
	}
	
}
