package com.folio3.resource;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.folio3.parse.core.Push;
import com.folio3.parse.core.PushRequest;
import com.folio3.push.PushSenderService;
import com.google.gson.Gson;

/**
 * Services / APIs related to Push Notifications
 * 
 * @author Muhammad Shahab Hameed
 * @version 1.0
 *
 */
@Path("/push")
@Component
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class PushResource {

	@Autowired
	private PushSenderService pushSenderService;
	
    private static final Logger LOG = LoggerFactory.getLogger(PushResource.class);
    
    /**
     * Service that will be used to send parse push notifications
     * @param request The request json containing which channel to send request
     * @return 
     */
	@POST
    public Response sendPushNotification(final PushRequest request) {
		
		LOG.info("[+] Send Push Notification API has been triggered.");
		
		Push push = new Push();
	    push.setQuery(request);
	    LOG.info("Query : " + new Gson().toJson(push.getQuery()));
	     
		return Response.ok(pushSenderService.sendPush(push)).build();
    }
}
