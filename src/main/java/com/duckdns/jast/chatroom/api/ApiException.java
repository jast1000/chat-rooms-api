package com.duckdns.jast.chatroom.api;

public class ApiException extends Exception{
	
	private static final long serialVersionUID = -8827047162815588937L;
	
	private int code;
    
	public ApiException (int code, String msg) {
        super(msg);
        this.code = code;
    }
	
}
