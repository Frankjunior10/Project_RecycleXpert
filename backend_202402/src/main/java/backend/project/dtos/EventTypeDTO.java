package backend.project.dtos;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Data
public class EventTypeDTO {
    private Long id;
    private String eventTypeName;
    private String description;
    private int rewards;

    public EventTypeDTO(long l, String salud, String campañaDeSalud, int i, Object o) {
    }
}
