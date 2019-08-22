package model;

import javax.persistence.PostPersist;
import javax.persistence.PrePersist;
import javax.websocket.WebSocketContainer;

import socket.WebSocket;

public class ChatChangeListener {
    @PostPersist
    public void onChange(Chat chat) {
    	System.out.println("OLSUUUUUUB"+chat.getMesaj());
    	WebSocket.mesajGonder(chat.getGönderenId(), chat.getGidenId(), chat.getMesaj());
    }
}
