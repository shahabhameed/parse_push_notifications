package com.folio3.parse.core;

import java.util.List;

public class PushCriteria {

	private List<String> list;
	
	public PushCriteria() {
	   	super();
	}
	
	public PushCriteria(List<String> list) {
	   	super();
	   	this.list = list;
	}

	public List<String> getList() {
		return list;
	}

	public void setList(List<String> list) {
		this.list = list;
	}
	

}
