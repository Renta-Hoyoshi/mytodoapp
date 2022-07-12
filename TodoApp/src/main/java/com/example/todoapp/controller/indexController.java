package com.example.todoapp.controller;



import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.example.todoapp.model.dao.HistoryRepository;
import com.example.todoapp.model.dao.TodoRepository;
import com.example.todoapp.model.entity.History;
import com.example.todoapp.model.entity.Todo;
import com.example.todoapp.model.form.TodoForm;
import com.google.gson.Gson;


@Controller
@RequestMapping("/todoapp")
public class indexController {
    
	@Autowired
	private TodoRepository todoRepos;
	
	@Autowired
	private HistoryRepository historyRepos;
	
	@Autowired
	private Gson gson = new Gson();
	
	
	@RequestMapping("/")
	public String index(Model model) {
		List<Todo> todoList = todoRepos.findAll();
		List<History> historyList = historyRepos.findAll();
		model.addAttribute("todoList", todoList);
		model.addAttribute("histList" , historyList);
		
		return "index";
	}
	
	@ResponseBody
	@RequestMapping("/api/addTodo")
	public String addTodo(@RequestBody TodoForm todoForm,  Model model) {
		
		Todo todo = new Todo();
		todo.setTodoContents(todoForm.getTodoContents());
	    todoRepos.saveAndFlush(todo);
	    
	    return "redirect:/todoapp/";
	}
	
	@ResponseBody
	@PostMapping("/api/deleteTodo")
	public long deleteTodo(@RequestBody Todo todo, Model model) {
		try {
                    
					todoRepos.deleteById(todo.getId());
		} catch (IllegalArgumentException e){
			return -1;
		}
		
		return 1;
	}
	
	@ResponseBody
	@PostMapping("/api/deleteAjaxTodo")
	public long deleteAjaxTodo(@RequestBody Todo todo, Model model) {
		try {
                    
					todoRepos.deleteByTodoContents(todo.getTodoContents());
		} catch (IllegalArgumentException e){
			return -2;
		}
		
		return 2;
	}
	
	
	@ResponseBody
	@PostMapping("/api/doneTodo")
	public long doneTodo(@RequestBody History history,  Model model) {	
		history.setTodoContents(history.getTodoContents());
		todoRepos.deleteById(history.getId());
	    historyRepos.saveAndFlush(history);   
	   
	    return 1;
	}
	
	@ResponseBody
	@PostMapping("/api/doneAjaxTodo")
	public long doneAjaxTodo(@RequestBody History history,  Model model) {
		history.setTodoContents(history.getTodoContents());
		todoRepos.deleteByTodoContents(history.getTodoContents());
	    historyRepos.saveAndFlush(history);
	    
	    return 1;
	}

	@ResponseBody
	@PostMapping("/api/history")
	public String historyApi(@RequestBody History hist) {
		List<History> historyDataList = historyRepos.findAll();
	    for (History historyData : historyDataList ) {
	    	historyData.getTodoContents();
	    }
	    return gson.toJson(historyDataList);
    }
}