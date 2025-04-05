package backend.project.dtos;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@Data
public class OrganizationDTO {
    private String organizationName;
    private String orgType;
    private String contactInfo;
    private String collaborationArea;
    private Long id;
    private List<Long> eventIds;
    private List<Long> consultationIds;
}
