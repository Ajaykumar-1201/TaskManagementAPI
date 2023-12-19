package com.example.TaskManagementAPI.Controllers;

import com.example.TaskManagementAPI.Models.Task;
import com.example.TaskManagementAPI.Models.User;
import com.example.TaskManagementAPI.Services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private  TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }


    @PostMapping
    public ResponseEntity<Task> addTask(@RequestBody Task task, User user) {
        Task createdTask = taskService.addTask(task, user);
        return new ResponseEntity<>(createdTask, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Task>> getAllTasks( User user) {
        List<Task> tasks = taskService.getAllTasks(user);
        return new ResponseEntity<>(tasks, HttpStatus.OK);
    }

    @GetMapping("/{taskId}")
    public ResponseEntity<Task> getTaskById(@PathVariable Long taskId, User user) {
        Task task = taskService.getTaskById(taskId, user);
        return new ResponseEntity<>(task, HttpStatus.OK);
    }

    @PutMapping("/{taskId}")
    public ResponseEntity<Task> updateTask(@PathVariable Long taskId, @RequestBody Task updatedTask,
                                            User user) {
        Task updated = taskService.updateTask(taskId, updatedTask, user);
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    @PatchMapping("/{taskId}/complete")
    public ResponseEntity<Task> markTaskAsCompleted(@PathVariable Long taskId, User user) {
        Task completedTask = taskService.markTaskAsCompleted(taskId, user);
        return new ResponseEntity<>(completedTask, HttpStatus.OK);
    }

    @DeleteMapping("/{taskId}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long taskId,  User user) {
        taskService.deleteTask(taskId, user);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
