package tech.marie.todolist.CustomValidation;


import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import tech.marie.todolist.DTO.TaskDto;

import java.time.ZonedDateTime;

public class DateRangeValidator implements ConstraintValidator <ValidDateRange, TaskDto>{
    @Override
    public boolean isValid(TaskDto taskDto, ConstraintValidatorContext context) {
        ZonedDateTime startDate = taskDto.getStartDate();
        ZonedDateTime endDate = taskDto.getEndDate();
        return startDate == null || endDate == null || startDate.isAfter(endDate);
    }
}
