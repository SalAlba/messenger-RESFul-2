package com.sal.alba.Messenger.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import com.sal.alba.Messenger.database.DatabaseClass;
import com.sal.alba.Messenger.exception.DataNotFoundException;
import com.sal.alba.Messenger.model.Message;

public class MessageService {

	// fields ...
	private Map<Long, Message> messages = DatabaseClass.getMessages();
	
	// constructor ...
	public MessageService(){
		messages.put(1L, new Message(1, "Hi there !!", "Sal. Alba."));
		messages.put(2L, new Message(2, "JAX-RS", "Jersey"));
	}
	
	// methods ...
	// GET
	public List<Message> getAllMessages(){ return new ArrayList<Message>( messages.values() ); }
	
	public Message getMessage(long messageId){
		Message message =  messages.get(messageId);
		if( message == null) 
			throw new DataNotFoundException("Message with id : "+messageId+" not found.");
		return message; 
	}
	
	public List<Message> getAllMessagesForYear(int year){
		List<Message> messageForYear = new ArrayList<>();
		Calendar calendar = Calendar.getInstance();
		
		for(Message message : messages.values()){
			calendar.setTime(message.getCreated());
			if(calendar.get(Calendar.YEAR) == year ) messageForYear.add(message);
		}		
		return messageForYear;
	}
	
	public List<Message> getAllMessagesPaginated(int start, int size){
		ArrayList<Message> list = new ArrayList<>(messages.values());
		if( start+size > list.size()) return new ArrayList<>();
		return list.subList(start, start+size);
	}
	
	// POST
	public Message addMessage(Message message){
		message.setId(messages.size()+1);
		messages.put(message.getId(), message);
		return message;
	}
	
	// PUT
	public Message updateMessage(Message message){
		if( message.getId() <= 0) return null;
		messages.put(message.getId(), message);
		return message;
	}
	
	// DELETE
	public Message removeMessage(long id){ return messages.remove(id); }
	
	
}