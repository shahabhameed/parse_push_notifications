package com.folio3.push;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_EMPTY)
public class PushResponse {
    
	private String responsePayload;
    private String errorMessage;
    private Boolean status;
    
    public PushResponse() {}


    public String getErrorMessage() {
        return errorMessage;
    }


    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }


    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }


	public String getResponsePayload() {
		return responsePayload;
	}


	public void setResponsePayload(String responsePayload) {
		this.responsePayload = responsePayload;
	}
    
}
