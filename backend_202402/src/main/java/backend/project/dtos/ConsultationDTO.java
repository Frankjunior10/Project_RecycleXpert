package backend.project.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ConsultationDTO {
    private Long id;
    private String comment;
    private LocalDate date;
    private int rating;
    private Long organizationId;  // Foreign key reference to Organization
    private Long volunteerId;     // Foreign key reference to Volunteer
}
