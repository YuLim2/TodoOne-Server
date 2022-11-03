package com.todoone.controller;

import com.todoone.domain.Todo;
import com.todoone.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TodoController {
    @Autowired
    private final TodoRepository todoRepository;
    public TodoController(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    @RequestMapping("/todo")
    public String Todo(@RequestBody Todo todo) throws Exception{
        todoRepository.addTodo(todo);
        return "성공 : Todo 추가 완료";
    }

    @RequestMapping(value = "/getTodo", method = RequestMethod.GET)
    public List<Todo> GetTodo(@RequestParam int couple_id) throws Exception{
        return todoRepository.getTodoByCoupleId(couple_id);
    }

    @RequestMapping(value = "/deleteTodo", method = RequestMethod.DELETE)
    public String DeleteTodo(@RequestParam int todo_id) throws Exception{
        todoRepository.deleteTodo(todo_id);
        return "성공: Todo 삭제 완료";
    }

    @RequestMapping(value = "/completeTodo", method = RequestMethod.POST)
    public String CompleteTodo(@RequestBody Todo todo) throws Exception{
        int complete_status = todoRepository.findCs(todo.getTodo_id());
        todo.setComplete_status(Math.abs(complete_status - 1));
        todoRepository.updateCompleteStatus(todo.getTodo_id(), todo.getComplete_status());
        return "성공: Todo 상태 변경 완료";
    }

}
