package backend.project.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
@Setter
@Getter
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "organizations")
public class Organization {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String organizationName;
    private String orgType;
    private String contactInfo;
    private String collaborationArea;

    @OneToMany(mappedBy = "organization",fetch = FetchType.EAGER)
    @JsonIgnore
    private List<Event> events;  // Relation with Event

    @OneToMany(mappedBy = "organization",fetch = FetchType.EAGER)
    private List<Consultation> consultations;  // Relation with Consultations

}
