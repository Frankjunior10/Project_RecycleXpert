package backend.project.entities;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "consultations")
public class Consultation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String comment;
    private Date date;
    private int rating;

    @ManyToOne
    @JoinColumn(name = "organization_id")
    private Organization organization;

    @ManyToOne
    @JoinColumn(name = "volunteer_id")
    private Volunteer volunteer;
}
