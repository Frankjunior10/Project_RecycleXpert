package backend.project.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuestionEventDTO {
    private Long id;
    private String question;
    private Long volunteerId;  // Relation con Volunteer
}
