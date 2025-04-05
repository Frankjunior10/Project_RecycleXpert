package backend.project.dtos;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VolunteerDTO {
    private Long id;
    private String volunteerName;
    private String email;
    private String address;
    private Integer points;
    private String category;
    private Integer level;
    private Long userId;   // Foreign key reference to User
}
