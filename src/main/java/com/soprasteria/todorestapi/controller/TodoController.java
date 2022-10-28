package com.soprasteria.todorestapi.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.soprasteria.todorestapi.entity.Todo;
import com.soprasteria.todorestapi.service.TodoService;

@CrossOrigin
@RestController
@RequestMapping("/api/todo")
public class TodoController {
	
	@Autowired
	private TodoService todoService;
	
	@GetMapping("/alltodos")
	public ResponseEntity<Object> allTodos() {
		try {
			return new ResponseEntity<Object>(todoService.findAll(), HttpStatus.OK);
		} catch(Exception e) {
			return new ResponseEntity<Object>("Error", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/todobyid/{todoId}")
	public ResponseEntity<Object> todoById(@PathVariable(value = "todoId") Integer todoId) {
		try {
			Optional<Todo> findById = todoService.findTodoById(todoId);
			if(findById.isPresent()) {
				return new ResponseEntity<Object>(findById.get(), HttpStatus.OK);
			} else {
				return new ResponseEntity<Object>("Todo not found",HttpStatus.NOT_FOUND);
			}
		} catch(Exception e) {
			return new ResponseEntity<Object>("Error",HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping("/inserttodo")
	public ResponseEntity<Object> insertTodo(@RequestBody Todo todo) {
		try {
			return new ResponseEntity<Object>(todoService.createOrUpdateTodo(todo),HttpStatus.CREATED);
		} catch(Exception e) {
			return new ResponseEntity<Object>("Error",HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping("/deletetodo/{todoId}")
	public ResponseEntity<Object> deleteTodo(@PathVariable(value = "todoId") Integer todoId) {
		try {
			todoService.deleteTodoById(todoId);
			return new ResponseEntity<Object>(HttpStatus.OK);
		} catch(Exception e) {
			return new ResponseEntity<Object>("Todo not found",HttpStatus.NOT_FOUND);
		}
	}
	
	@PutMapping("/updatetodo")
	public ResponseEntity<Object> updateTodo(@RequestBody Todo todo) {
		try {
			Optional<Todo> findById = todoService.findTodoById(todo.getId());
			if(findById.isPresent()) {
				return new ResponseEntity<Object>(todoService.createOrUpdateTodo(todo), HttpStatus.OK);
			} else {
				return new ResponseEntity<Object>("Todo not found",HttpStatus.NOT_FOUND);
			}
		} catch(Exception e) {
			return new ResponseEntity<Object>("Error",HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping("/togglecompleted/{todoId}")
	public ResponseEntity<Object> toggleCompleted(@PathVariable(value = "todoId") Integer todoId) {
		try {
			Optional<Todo> findById = todoService.findTodoById(todoId);
			if(findById.isPresent()) {
				return new ResponseEntity<Object>(todoService.updateCompleted(findById.get()), HttpStatus.OK);
			} else {
				return new ResponseEntity<Object>("Todo not found", HttpStatus.NOT_FOUND);
			}
		} catch(Exception e) {
			return new ResponseEntity<Object>("Error", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
