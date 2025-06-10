package tech.marie.todolist.repository;

import org.springframework.data.jpa.repository.JpaRepository;
//import tech.marie.todolist.dto.TaskDto;
import tech.marie.todolist.Model.Tasks;
import tech.marie.todolist.Model.Status;

import java.util.List;

public interface TasksRepository extends JpaRepository<Tasks, Integer> {
    // method to filter tasks by status

    List<Tasks> findByStatus(Status status);



}
