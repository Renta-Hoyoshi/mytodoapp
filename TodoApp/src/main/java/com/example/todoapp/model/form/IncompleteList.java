package com.example.todoapp.model.form;

import java.io.Serializable;
import java.util.List;

public class IncompleteList implements Serializable{
    private static final long serialVersionUID = 1L;
    private long id;
    private  List<TodoForm> imcompleteList;
	
    public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public List<TodoForm> getImcompleteList() {
		return imcompleteList;
	}
	public void setImcompleteList(List<TodoForm> imcompleteList) {
		this.imcompleteList = imcompleteList;
	}
}
