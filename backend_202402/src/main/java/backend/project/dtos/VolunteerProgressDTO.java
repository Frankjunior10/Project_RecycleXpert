package backend.project.dtos;
import lombok.*;

@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class VolunteerProgressDTO {
    private int currentPoints;
    private int pointsToNextLevel;
    private int level;
    private double progressPercentage; // porcentaje de progreso en el nivel actual

}
