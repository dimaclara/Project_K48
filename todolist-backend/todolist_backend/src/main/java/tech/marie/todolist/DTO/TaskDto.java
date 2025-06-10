package tech.marie.todolist.DTO;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tech.marie.todolist.Model.Status;

import java.time.ZonedDateTime;


@Data
@AllArgsConstructor
@NoArgsConstructor

public class TaskDto {
    // Mandatory tittle with validation
    @NotBlank(message = "Title is mandatory")
    private String title;

    // optional description
    private String description;


    @NotNull(message = "Start date is mendatory")
    private ZonedDateTime startDate;

    @NotNull(message = "End date is mandatory")
    private ZonedDateTime endDate;
    // Mandatory status with validation
    @NotBlank(message = "Status is mandatory")
    private Status status;

    // Mandatory type with validation
    @NotBlank(message = "Type is mandatory")
    private String type;

}

// TaskResponse : Represent client reponse data






