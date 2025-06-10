package tech.marie.todolist.service;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import tech.marie.todolist.DTO.TaskDto;
import tech.marie.todolist.DTO.TaskDtoResponse;
import tech.marie.todolist.Exceptions.TaskNotFoundException;
import tech.marie.todolist.Model.Status;
import tech.marie.todolist.Model.Tasks;
import tech.marie.todolist.repository.TasksRepository;

import java.time.ZoneId;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TaskService {
    //  Repository injection
    private final TasksRepository tasksRepository;

    /* injection using constructor
    public TaskService(TasksRepository tasksRepository) {
        this.tasksRepository = tasksRepository;
    }*/

    // Create task with UTC conversion
    public TaskDtoResponse createTasks(TaskDto taskDto) {
       Tasks tasks = new Tasks();
       tasks.setTitle(taskDto.getTitle());
       tasks.setDescription(taskDto.getDescription());
       tasks.setStatus(taskDto.getStatus());
       tasks.setType(taskDto.getType());
       tasks.setStartDate(taskDto.getStartDate().withZoneSameInstant(ZoneId.of("UTC")));
       tasks.setEndDate(taskDto.getEndDate().withZoneSameInstant(ZoneId.of("UTC")));

          Tasks savedTasks =tasksRepository.save(tasks);
          return  mapToResponse(savedTasks);


    }


    //Method to get all task

    public List<TaskDtoResponse> getAllTasks(Optional<Status>status) {
        List<Tasks> tasks = status.map(tasksRepository::findByStatus)
                .orElseGet(tasksRepository::findAll);
        return  tasks.stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }



    // Update a task

    public  TaskDtoResponse updateTasks(int id, TaskDto taskDto) {
        return  tasksRepository.findById(id)
                .map(tasks -> {
                    tasks.setTitle(taskDto.getTitle());
                    tasks.setDescription(taskDto.getDescription());
                    tasks.setStatus(taskDto.getStatus());
                    tasks.setType(taskDto.getType());
                    tasks.setStartDate(taskDto.getStartDate().withZoneSameInstant(ZoneId.of("UTC")));
                    tasks.setEndDate(taskDto.getEndDate().withZoneSameInstant(ZoneId.of("UTC")));
                    Tasks updatedTasks = tasksRepository.save(tasks);
                    return mapToResponse(updatedTasks);

                })
                .orElseThrow(() -> new TaskNotFoundException(id));
    }





    private TaskDtoResponse mapToResponse(Tasks tasks) {
        return new TaskDtoResponse(
                tasks.getTitle(),
                tasks.getDescription(),
                tasks.getStartDate().withZoneSameInstant(ZoneId.of("Europe/Paris")),
                tasks.getEndDate().withZoneSameInstant(ZoneId.of("Europe/Paris")),
                tasks.getStatus(),
                tasks.getType()

        );
    }
    // deleteTask
    public void deleteTask(int id) {
        tasksRepository.deleteById(id);
    }




   /*

    public void createTask(Tasks tasks) {
        // use the function that allow the task by status
         List<Tasks> existingTasks = tasksRepository.findByStatus(tasks.getStatus());


        Tasks newTask;
        if (existingTasks.isEmpty()) {
            newTask = new Tasks(); // Créer une nouvelle tâche
        } else {
            newTask = existingTasks.get(0); // Prendre la première tâche trouvée
        }

        // Définir le statut de la tâche
        newTask.setStatus(tasks.getStatus());

        // Définir le type en fonction du statut
        if (tasks.getStatus() == TypeTasks.TODO) {
            newTask.setType(TypeTasks.IN_PROGRESS);
        } else {
            newTask.setType(TypeTasks.COMPLETED);
        }

        // Sauvegarde dans la base de données
        this.tasksRepository.save(newTask);


    }
    // function to read and filter tasks by status
    public List<Tasks> readAndFilterTaskByStatus(TypeTasks status) {
        return tasksRepository.findByStatus(status);
                //.filter(task -> task.getStatus().equals(status))
                //.collect(Collectors.toList());


    }

    // function to updates task
    public  void updateTask(int id) {
        Tasks tasksOfDB = this.tasksRepository.findById(id).get();
        tasksOfDB.setStatus(tasksOfDB.getStatus());
        tasksOfDB.setTitle(tasksOfDB.getTitle());
        tasksOfDB.setDescription(tasksOfDB.getDescription());
        this.tasksRepository.save(tasksOfDB);

    }

    // fuction to delete the Task

    public void deleteTask(int id) {
       this.tasksRepository.deleteById(id);
    }*/


}



