package com.example.todoapp.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


import lombok.Data;

@Data
@Entity
@Table(name="contents")
public class Todo {

	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	@Column(name="todo_contents")
	private String todoContents;
	
	public  void setId(long id) {
		this.id = id;
	}
	
	public long getId() {
		return this.id;
	}
	
	public void setTodoContents(String todoContents) {
		this.todoContents = todoContents;
	}
	
	public String getTodoContents() {
		return this.todoContents;
	}
}
