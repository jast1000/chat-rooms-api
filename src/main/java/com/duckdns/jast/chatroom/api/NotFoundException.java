package com.duckdns.jast.chatroom.api;

public class NotFoundException extends ApiException {
	
    private int code;
    
    public NotFoundException (int code, String msg) {
        super(code, msg);
        this.code = code;
    }
    
}
