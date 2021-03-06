package com.learn.firstwebapplication.controller;

import com.learn.firstwebapplication.model.Todo;
import com.learn.firstwebapplication.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.*;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@SessionAttributes("name")
public class TodoController {

  @Autowired
  TodoService todoService;

  @InitBinder
  public void initDateBinder(WebDataBinder binder){
    // Date - dd/MM/YYYY
    SimpleDateFormat dateFromat = new SimpleDateFormat("dd/MM/yyyy");
    binder.registerCustomEditor(Date.class, new CustomDateEditor(
      dateFromat, false));
  }

  private String getLoggedInUserName(ModelMap model) {
    return (String) model.getAttribute("name");
  }

  @RequestMapping(value="/list-todos", method = RequestMethod.GET)
  public String showListTodos(ModelMap model) {
    String name = getLoggedInUserName(model);
    model.put("todos", todoService.retrieveTodos(name));
    return "list-todos";
  }

  @RequestMapping(value="/add-todo", method = RequestMethod.GET)
  public String showAddTodoPage(ModelMap model) {
    model.put("todo", new Todo(0, getLoggedInUserName(model), "", new Date(),
      false));
    return "todo";
  }

  @RequestMapping(value="/add-todo", method = RequestMethod.POST)
  public String addTodo(ModelMap model, @Valid Todo todo, BindingResult result) {
    if(result.hasErrors()){
      return "redirect:/todo";
    }
    todoService.addTodo(getLoggedInUserName(model), todo.getDesc(), todo.getTargetDate(), false);
    return "redirect:/list-todos";
  }


  @RequestMapping(value="/delete-todo", method = RequestMethod.GET)
  public String deleteTodo(@RequestParam int id) {
    todoService.deletedTodo(id);
    return "redirect:/list-todos";
  }

  @RequestMapping(value="/update-todo", method = RequestMethod.GET)
  public String showUpdateTodoPage(ModelMap model, @RequestParam int id) {
    Todo todo = todoService.retrieveTodo(id);
    model.put("todo", todo);
    return "todo";
  }

  @RequestMapping(value="/update-todo", method = RequestMethod.POST)
  public String updateTodo(ModelMap model, @RequestParam int id, @Valid Todo todo, BindingResult result) {
    if(result.hasErrors()){
      return "todo";
    }
    todo.setUser((String) model.get("name"));

    todoService.updateTodo(todo);
    return "redirect:/list-todos";
  }
}
