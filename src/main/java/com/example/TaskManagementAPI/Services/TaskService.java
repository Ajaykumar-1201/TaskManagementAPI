package com.example.TaskManagementAPI.Services;

import com.example.TaskManagementAPI.Enums.TaskStatus;
import com.example.TaskManagementAPI.Models.Task;
import com.example.TaskManagementAPI.Models.User;

import com.example.TaskManagementAPI.Repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    @Autowired
    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public Task addTask(Task task, User user) {
        task.setUser(user);
        return taskRepository.save(task);
    }

    public List<Task> getAllTasks(User user) {
        if (user.isAdmin()) {
            return taskRepository.findAll();
        } else {
            return taskRepository.findByUser(user);
        }
    }

    public Task getTaskById(Long taskId, User user) {
        Optional<Task> optionalTask = taskRepository.findById(taskId);
        if (optionalTask.isPresent()) {
            Task task = optionalTask.get();
            if (user.isAdmin() || task.getUser().equals(user)) {
                return task;
            }
        }
        // Handle not found or unauthorized access
        return null;
    }

    public Task updateTask(Long taskId, Task updatedTask, User user) {
        Task existingTask = getTaskById(taskId, user);
        if (existingTask != null) {
            // Update task details
            existingTask.setTitle(updatedTask.getTitle());
            existingTask.setDescription(updatedTask.getDescription());
            existingTask.setDueDate(updatedTask.getDueDate());
            return taskRepository.save(existingTask);
        }
        // Handle not found or unauthorized access
        return null;
    }

    public Task markTaskAsCompleted(Long taskId, User user) {
        Task task = getTaskById(taskId, user);
        if (task != null) {
            task.setStatus(TaskStatus.COMPLETED);
            return taskRepository.save(task);
        }
        // Handle not found or unauthorized access
        return null;
    }

    public void deleteTask(Long taskId, User user) {
        Task task = getTaskById(taskId, user);
        if (task != null) {
            taskRepository.delete(task);
        }
        // Handle not found or unauthorized access
    }
}

