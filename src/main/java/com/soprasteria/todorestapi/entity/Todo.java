package com.soprasteria.todorestapi.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class Todo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@NotNull(message = "todo cannot be null")
	private String testoTodo;
	@NotNull(message = "completato cannot be null")
	private Boolean completato = false;	//DEFAULT = FALSE
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTestoTodo() {
		return testoTodo;
	}
	public void setTestoTodo(String testoTodo) {
		this.testoTodo = testoTodo;
	}
	public Boolean getCompletato() {
		return completato;
	}
	public void setCompletato(Boolean completato) {
		this.completato = completato;
	}

}
