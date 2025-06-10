package tech.marie.todolist.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.hibernate.annotations.Parameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import tech.marie.todolist.DTO.TaskDto;
import tech.marie.todolist.DTO.TaskDtoResponse;
import tech.marie.todolist.Model.Status;
import tech.marie.todolist.service.TaskService;

import java.util.List;
import java.util.Optional;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(path = "/tasks",produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Task Management", description = "API for managing task")


public class TaskController {


    // injection de beans service
    private TaskService taskService;


    //injection a l'aide du constructeur
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }



    // function to create tasks
    @ResponseStatus(value = HttpStatus.CREATED)
    @Operation(summary= "Create a task", description = "create new task for a project type")
    @PostMapping(consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<TaskDtoResponse> createTasks(@RequestBody TaskDto taskDto) {
        return new ResponseEntity<>(taskService.createTasks(taskDto), HttpStatus.CREATED);


}


// get all tasks
    @ResponseStatus(value = HttpStatus.OK)
    @Operation(summary = "Get all tasks" , description = "return all filter tasks that user need to know")
    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<TaskDtoResponse>> getAllTasks(@RequestParam(required = false)Status status) {
        return ResponseEntity.ok(taskService.getAllTasks(Optional.ofNullable(status)));
    }




    // Update a Task
    @ResponseStatus(value = HttpStatus.OK)
    @Operation(summary = "Update a task", description = "updates task statuses")
    @PutMapping(value = "/{id}")
    public ResponseEntity<TaskDtoResponse> updateTask(@PathVariable int id, @RequestBody TaskDto taskDto) {
        return ResponseEntity.ok(taskService.updateTasks(id,taskDto));
    }



    // delete task
    @ResponseStatus(HttpStatus.ACCEPTED)
    @Operation(summary = "Delete a task ", description = "delete a task done")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable int id) {
        taskService.deleteTask(id);
        return ResponseEntity.noContent().build();


    }




}
