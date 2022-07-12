package com.example.todoapp.model.form;

import java.io.Serializable;
import java.util.List;

public class CompleteList implements Serializable{
	private static final long serialVersionUID = 1L;
	private long id;
    private List<Complete> completeList;
    
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public List<Complete> getCompleteList() {
		return completeList;
	}
	public void setCompleteList(List<Complete> completeList) {
		this.completeList = completeList;
	}

}
