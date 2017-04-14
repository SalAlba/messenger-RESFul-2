package com.sal.alba.Messenger.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.sal.alba.Messenger.model.ErrorMessage;

/**
 * This mapper for all kinds of errors 
 *
 *  Throwable catch every thing 
 *
 * @author Salem
 */

@Provider
public class GenericExceptionMapper implements ExceptionMapper<Throwable>{

	@Override
	public Response toResponse(Throwable ex) {
		ErrorMessage errorMessage = new ErrorMessage("An error occurred",500,"http://wp.pl");
		return Response.status(Status.INTERNAL_SERVER_ERROR)
				.entity(errorMessage)
				.build();
	}

}
