package com.learn.firstwebapplication.service;

import com.learn.firstwebapplication.model.Todo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Service
public class TodoService {
  private static List<Todo> todos = new ArrayList<Todo>();
  private static int todoCount = 3;

  static {
    todos.add(new Todo(1, "abc2", "learn springbooth", new Date(), false));
    todos.add(new Todo(2, "abc2", "learn backend", new Date(), false));
    todos.add(new Todo(3, "abc2", "learn to be full stack", new Date(), false));
  }

  public List<Todo> retrieveTodos(String user) {
    List<Todo> filteredTodos = new ArrayList<Todo>();
    for (Todo todo: todos){
      if (todo.getUser().equals(user)) {
        filteredTodos.add(todo);
      }
    }
    return filteredTodos;
  }

  public void addTodo(String name, String desc, Date targetDate, boolean isDone) {
    todos.add(new Todo(++todoCount, name, desc, targetDate, isDone));
  }

  public void deletedTodo(int id) {
    Iterator<Todo> iterator = todos.iterator();
    while (iterator.hasNext()){
      Todo todo = iterator.next();
      if(todo.getId() == id){
        iterator.remove();
      }
    }
  }

  public Todo retrieveTodo(int id) {
    List<Todo> filteredTodos = new ArrayList<Todo>();
    for (Todo todo: todos){
      if (todo.getId() == id) {
        return todo;
      }
    }
    return null;
  }

  public void updateTodo(Todo todo){
    todos.remove(todo);
    todos.add(todo);
  }

}
