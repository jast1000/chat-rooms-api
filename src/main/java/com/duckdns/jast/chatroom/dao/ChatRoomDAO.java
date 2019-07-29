package com.duckdns.jast.chatroom.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.duckdns.jast.chatroom.model.ChatRoom;

@Repository
public class ChatRoomDAO {
	
	private final JdbcTemplate jdbc;
	
	public ChatRoomDAO(JdbcTemplate jdbc) {
		this.jdbc = jdbc;
	}

	public void save(ChatRoom chatRoom) {
		String sql = "insert into public.chat_room(\"name\", description, created) values(?, ?, now())";
		jdbc.update(sql, chatRoom.getName(), chatRoom.getDescription());
	}
	
	public void delete(Integer chatRoomId) {
		String sql = "delete from public.chat_room where chat_room_id = ?";
		jdbc.update(sql, chatRoomId);
	}
	
	public ChatRoom find(Integer chatRoomId) {
		String sql = "select chat_room_id as id, \"name\", description, created from chat_room where chat_room_id = ?";
		try {
			return jdbc.queryForObject(sql, new BeanPropertyRowMapper<>(ChatRoom.class), chatRoomId);
		} catch (DataAccessException ex) {
			return null;
		}
	}
	
	public List<ChatRoom> getAll() {
		String sql = "select chat_room_id as id, \"name\", description, created from chat_room order by upper(\"name\")";
		return jdbc.query(sql, new BeanPropertyRowMapper<>(ChatRoom.class));
	}
	
	public void update(Integer chatRoomId, ChatRoom chatRoom) {
		String sql = "update public.chat_room set \"name\" = ?, description = ? where chat_room_id = ?";
		jdbc.update(sql, chatRoom.getName(), chatRoom.getDescription(), chatRoomId);
	}
	
}
