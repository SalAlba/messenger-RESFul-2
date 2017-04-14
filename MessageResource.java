package com.sal.alba.Messenger.resources;

import java.net.URI;
import java.util.List;

import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import com.sal.alba.Messenger.model.Message;
import com.sal.alba.Messenger.resources.bean.MessageFilterBean;
import com.sal.alba.Messenger.service.MessageService;

@Path("/messages")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class MessageResource {

	// fields ...
	private MessageService messageService = new MessageService();
	
	// methods ...
	@GET
	public List<Message> getMessages(@BeanParam MessageFilterBean filterBean){
		if(filterBean.getYear() > 0) return messageService.getAllMessagesForYear(filterBean.getYear());
		if( filterBean.getStart() > 0 && filterBean.getStart() > 0) 
			return messageService.getAllMessagesPaginated(filterBean.getStart(), filterBean.getSize());
		return  messageService.getAllMessages();
	}
	
	@GET
	@Path("/{messageId}")
	public Message getMessage(@PathParam("messageId") long messageId){ 
		return messageService.getMessage(messageId);
	}
		
	@POST
	public Response addMessage(Message message, @Context UriInfo uriInfo){
		Message newMessage = messageService.addMessage(message);
		
		URI uri = uriInfo.getAbsolutePathBuilder().path(String.valueOf(newMessage.getId())).build();
		return Response.created(uri)
				.entity(newMessage)
				.build();
	}
	
	
	@PUT
	@Path("/{messageId}")
	public Message updateMessage(@PathParam("messageId") long id, Message message){
		message.setId(id);
		return messageService.updateMessage(message);
	}
	
	
	@DELETE
	@Path("/{messageId}")
	public void removeMessage(@PathParam("messageId") long id){
		messageService.removeMessage(id);
	}
	
	/*@Path("/{messageId}/comments")
	public CommentResource getCommentResource(){
		return new CommentResource();
	}*/
	
	/*@Path("/{messageId}/comments")
	public CommentResource getCommentResource(@PathParam("messageId") int messageId){
		return new CommentResource(messageId);
	}*/
	
}






