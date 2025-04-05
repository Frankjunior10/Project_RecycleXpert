package backend.project.dtos;

import lombok.*;

import java.util.Date;

@Setter
@Getter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DonationDTO {
    private Long id;
    private String donorName;
    private double amount;
    private Date donationDate;
    private Long eventId; // ID del evento asociado
    private Long volunteerId; // ID del voluntario asociado
}
