package com.duckdns.jast.chatroom.model;

import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

import io.swagger.annotations.ApiModelProperty;

@Validated
public class UserCredential {

	private String username;
	private String password;
	
	public UserCredential() {
	}

	@ApiModelProperty(required = true, value = "Username")
	@NotNull
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@ApiModelProperty(required = true, value = "Password")
	@NotNull
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
}
