package backend.project.dtos;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    private Long id;
    private String userName;
    private String password;
    private String authorities;  // Se usa para associate la authority
}
