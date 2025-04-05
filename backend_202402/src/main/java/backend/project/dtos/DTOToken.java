package backend.project.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor

public class DTOToken {
    private String jwtToken;
    private Long user_id;
    private String authorities;

}
