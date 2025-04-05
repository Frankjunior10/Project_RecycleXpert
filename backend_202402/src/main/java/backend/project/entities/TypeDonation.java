package backend.project.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "typeDonations")
public class TypeDonation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String donationType;
    private double amount;
    private String description;
    private String date;

    @OneToMany(mappedBy = "typeDonation",fetch = FetchType.EAGER)
    private List<Donation> donations;  // Relation with Donation
}
