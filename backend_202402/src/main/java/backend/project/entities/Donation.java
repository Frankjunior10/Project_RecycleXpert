package backend.project.entities;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Setter
@Getter
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "donations")
public class Donation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String donorName;
    private String creditCard;
    private double amount;
    private Date donationDate;

    @ManyToOne
    @JoinColumn(name = "event_id")
    private Event event;

    @ManyToOne
    @JoinColumn(name = "volunteer_id")
    private Volunteer volunteer;

    @ManyToOne
    @JoinColumn(name = "Sponsor_id")
    private Sponsor cSponsor;

    @ManyToOne
    @JoinColumn(name = "typeDonation_id")
    private TypeDonation typeDonation;


}
