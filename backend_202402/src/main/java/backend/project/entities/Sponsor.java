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
@Table(name = "sponsors")
public class Sponsor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String location;
    private String donationType;

    @OneToMany(mappedBy = "cSponsor",fetch = FetchType.EAGER)
    private List<Donation> donations;  // Relation con Donation
}
