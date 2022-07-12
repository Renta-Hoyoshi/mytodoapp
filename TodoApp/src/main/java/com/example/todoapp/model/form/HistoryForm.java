package com.example.todoapp.model.form;

import java.io.Serializable;

public class HistoryForm implements Serializable{
    private static final long serialVersionUID = 1L;
    
    private long id;
    private String todoContents;

	public String getTodoContents() {
		return todoContents;
	}

	public void setTodoContents(String todoContents) {
		this.todoContents = todoContents;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
}
