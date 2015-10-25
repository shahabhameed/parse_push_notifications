package com.folio3.push;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import com.folio3.parse.core.Push;
import com.google.gson.Gson;

@Component
public class PushSenderServiceImpl implements PushSenderService {

    private static Logger LOG = LoggerFactory.getLogger(PushSenderServiceImpl.class);
    
    private static final String PARSE_APPLICATION_ID = "parse.application.id";
    private static final String PARSE_REST_API_KEY = "parse.rest.api.key";
    private static final String PARSE_MASTER_API_KEY = "parse.master.api.key";
    private static final String PARSE_PUSH_URL= "parse.push.url";
    private static final String PARSE_HOST = "parse.host";
    private static final String PARSE_PORT = "parse.port";
    
    @Value("${" + PARSE_HOST + "}")
    private String parseHost;
    
    @Value("${" + PARSE_PORT + "}")
    private String parsePort;
    
    @Value("${" + PARSE_APPLICATION_ID + "}")
    private String parseApplicationID;
    
    @Value("${" + PARSE_REST_API_KEY + "}")
    private String parseRestAPIKey;
    
    @Value("${" + PARSE_MASTER_API_KEY + "}")
    private String parseMasterAPIKey;
    
    @Value("${" + PARSE_PUSH_URL + "}")
    private String parsePushURL;
    
    private Properties properties;
    
    private HttpURLConnection configure(String requestMethod, URL url) {
    	
    	LOG.info("[+] Configuring Parse server parameters");
    	LOG.info("[+] URL : " + url.toString());
    	LOG.info("[+] Request Method : " + requestMethod);
        HttpURLConnection connection = null;
		
        try {
			
			connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod(requestMethod);
			connection.setRequestProperty("Content-Type", "application/json");
			connection.setRequestProperty("X-Parse-REST-API-KEY", parseRestAPIKey);
			connection.setRequestProperty("X-Parse-Application-Id", parseApplicationID);
			connection.setDoInput(true);
			connection.setDoOutput(true);
			
		} catch (IOException ex) {
			ex.printStackTrace();
			LOG.info("[-] Error configuring Parse Push URL " + ex.getStackTrace());
		}
        return connection;
    }
    
    public PushResponse sendPush(Push push) {
       
        PushResponse pushResponse = new PushResponse();
        pushResponse.setStatus(false);
        pushResponse.setErrorMessage("");
        
        try {
            
        	LOG.info("[+] Send push request received.");
        	
            Assert.notNull(parseRestAPIKey, "Mandatory configuration argument 'rest_api' missing.");
            Assert.notNull(parseApplicationID, "Mandatory configuration argument 'application_id' missing.");
            Assert.notNull(parsePushURL, "Mandatory configuration argument 'push_url' missing.");
            Assert.notNull(parseHost, "Mandatory configuration argument 'host' missing.");
            Assert.notNull(parsePort, "Mandatory configuration argument 'port' missing.");
            
            LOG.info("[+] Preparing the push message for parse.");
            URL url = new URL(parsePushURL);
            HttpURLConnection connection = configure("POST", url);
            
            if (connection != null ) {
            
	            try (OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream())) {
	                out.write(new Gson().toJson(push.getQuery()));
	            } catch (Exception e) {
	            	LOG.info("[-] Couldn't write push notifications criteria byte stream");
				}
	
	            LOG.info("[+] Sending the Push Notification Criteria to Parse. ");
	            connection.connect();
	            
	            if (connection.getResponseCode() == 200 && connection.getResponseMessage().equalsIgnoreCase("OK")) {
	                pushResponse.setStatus(true);
	                LOG.info("[+] Push sent successfully.");
	            } else {
	            	LOG.info("Parse Response : " + connection.getResponseCode() + " : " + connection.getResponseMessage());
	            	LOG.info("[-] Push failed, error in push criteria");
	            }
	            
            } else {
            	// connection was not made
            	pushResponse.setErrorMessage("Error configuring the connection with Parse URL  / Connection ");
            }
        } catch (MalformedURLException ex) {
        	
        	LOG.error("[-] Error configuring Parse Push URL " + ex.getMessage());
			ex.printStackTrace();
			pushResponse.setErrorMessage(ex.getMessage());
			
		} catch (Exception ex) {
            LOG.info("[-] Exception Occurred: " + ex.getMessage());
            ex.printStackTrace();
            pushResponse.setErrorMessage(ex.getMessage());
        } 
        return pushResponse;
    }
    
    public Properties getProperties() {
        return properties;
    }

    public void setProperties(Properties properties) {
        this.properties = properties;
    }

	
}
