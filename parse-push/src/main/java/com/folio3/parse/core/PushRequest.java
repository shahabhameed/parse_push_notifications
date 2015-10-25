package com.folio3.parse.core;

import java.util.List;

public class PushRequest {

	private List<String> channels;
	private PushData data;
	
    public PushRequest() {
    	super();
    }

	public List<String> getChannels() {
		return channels;
	}

	public void setChannels(List<String> channels) {
		this.channels = channels;
	}

	public PushData getData() {
		return data;
	}

	public void setData(PushData data) {
		this.data = data;
	}

}
