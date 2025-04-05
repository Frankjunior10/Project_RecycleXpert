package backend.project.dtos;

import lombok.*;

@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RegisterEventDTO {
    private Long id;
    private boolean attendance;

    private Long eventId;  // Relation con Event
    private Long volunteerId;  // Relation con Volunteer
}
