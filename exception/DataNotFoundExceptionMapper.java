package com.sal.alba.Messenger.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.sal.alba.Messenger.model.ErrorMessage;

/**
 * This mapper just to catch if not data under concrete id
 * ex. GET /webapi/messages/3321  -> if no data under id 3321 the error alert
 *
 * @author Salem
 */

@Provider
public class DataNotFoundExceptionMapper implements ExceptionMapper<DataNotFoundException>{

	@Override
	public Response toResponse(DataNotFoundException ex) {
		ErrorMessage errorMessage = new ErrorMessage(ex.getMessage(), 404, "http://google.com");
		return Response.status(Status.NOT_FOUND)
				.entity(errorMessage)
				.build();
	}

}
