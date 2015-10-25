package com.folio3.parse.core;

import com.google.gson.annotations.SerializedName;

public class PushData {

	private String alert;
	
	/** IOS specific variables */
	private String badge = "Increment"; 
	private String sound = "default.mp3";
	
	@SerializedName("content-available")
	private String content_available;
	private String category;
	
	/** Android specific variables */
	private String title; 
	private String uri;
	
    public PushData() {
    	super();
    }

    public PushData (String alert,  String badge, String sound, String content_available, String category, String title, String uri) {
    	this.alert = alert;
    	this.badge = badge;
    	this.sound = sound;
    	this.content_available = content_available;
    	this.category = category;
    	this.title = title;
    	this.uri = uri;
    }
    
	public String getAlert() {
		return alert;
	}

	public void setAlert(String alert) {
		this.alert = alert;
	}

	public String getBadge() {
		return badge;
	}

	public void setBadge(String badge) {
		this.badge = badge;
	}

	public String getSound() {
		return sound;
	}

	public void setSound(String sound) {
		this.sound = sound;
	}

	public String getContent_available() {
		return content_available;
	}

	public void setContent_available(String content_available) {
		this.content_available = content_available;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}
}
