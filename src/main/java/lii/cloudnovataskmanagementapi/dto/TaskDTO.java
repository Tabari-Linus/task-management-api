package lii.cloudnovataskmanagementapi.dto;


import lii.cloudnovataskmanagementapi.enums.TaskEnums.TaskStatus;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TaskDTO {
    private UUID id;
    private String title;
    private String description;
    private TaskStatus status;
    private LocalDateTime dueDate;
}
