package tech.marie.todolist.Exceptions;

public class TaskNotFoundException extends RuntimeException {
    public TaskNotFoundException(int id) {
        super(String.valueOf(id));


    }

}
