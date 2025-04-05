package backend.project.entities;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "volunteers")

public class Volunteer {

    //ID Autogenerado de la tabla
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String volunteerName;
    private String email;
    private String address;
    private Integer points;
    private String category;
    private Integer level;

    // Relation con la entidad Rango (ManyToOne)
    @ManyToOne
    @JoinColumn(name = "rango_id")
    private Rango rango;

    //______________________________________________
    // Relation con la entidad User (ManyToOne)
    //VERIFIER ESTA RELATION WITH USER, según el profesor debe ser ONE A ONE
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
    // _____________________________________________

    // Relation con Consultations (OneToMany)
    @OneToMany(mappedBy = "volunteer",fetch = FetchType.EAGER)
    private List<Consultation> consultations;

    // Relation con Donation (OneToMany)
    @OneToMany(mappedBy = "volunteer",fetch = FetchType.EAGER)
    private List<Donation> donations;

    // Relation con QuestionEvent (OneToMany)
    @OneToMany(mappedBy = "volunteer",fetch = FetchType.EAGER)
    private List<QuestionEvent> questionEvents;

    // Relation con RegisterEvent (OneToMany)
    @OneToMany(mappedBy = "volunteer",fetch = FetchType.EAGER)
    private List<RegisterEvent> registerEvents;
}
