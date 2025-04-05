package backend.project.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TypeDonationDTO {
    private Long id;
    private String typeName;
}
