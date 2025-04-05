package backend.project.dtos;

import lombok.*;

import java.util.Date;

@Setter
@Getter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventDTO {
    private String eventName;
    private String description;
    private Date date;
    private String location;
    private int capacity;
    private Long eventTypeId;
    private Long organizationId;

}
