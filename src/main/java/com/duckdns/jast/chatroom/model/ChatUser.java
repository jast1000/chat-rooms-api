package com.duckdns.jast.chatroom.model;

import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import io.swagger.annotations.ApiModelProperty;

@Validated
@JsonInclude(Include.NON_NULL)
public class ChatUser {
	
	private Integer id;
	private String fullName;
	private String email;
	private String username;
	private String password;
	
	public ChatUser() {
	}

	@ApiModelProperty(readOnly = true, value = "User identifier")
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@ApiModelProperty(value = "User full name")
	@NotNull
	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	@ApiModelProperty(value = "User email")
	@NotNull
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@ApiModelProperty(value = "User nickname")
	@NotNull
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@ApiModelProperty(value = "User password")
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
