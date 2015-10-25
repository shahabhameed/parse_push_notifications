package com.folio3.push;

import com.folio3.parse.core.Push;

/**
 * Interface definition for sending a push.
 * 
 * @author Muhammad Shahab Hameed
 *
 */
public interface PushSenderService {

	/**
	 * Responsible for sending the criteria for push notification.  
	 * 
	 * @param push The {@link Push} object containing the criteria and data to send
	 * @return {@link PushResponse} containing the status code and message
	 */
    public PushResponse sendPush(Push push);

}
