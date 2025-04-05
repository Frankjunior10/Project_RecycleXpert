package backend.project.entities;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {
    //ID Autogenerado de la tabla
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userName;
    private String password;
    private boolean isActive;

    private LocalDateTime registrationDate;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name="users_authorities",
            joinColumns = {
                    @JoinColumn(
                            name="user_id",
                            referencedColumnName = "id",
                            nullable = false
                    )
            },
            inverseJoinColumns = {
                    @JoinColumn(
                            name="authority_id",
                            referencedColumnName = "id",
                            nullable = false
                    )
            }
    )
    private List<Authority> authorities;

    @OneToOne(mappedBy = "user",fetch = FetchType.EAGER)
    private Volunteer volunteer;

    public boolean isVolunteer() {
        return this.authorities.stream()
                .anyMatch(auth -> "VOLUNTARIO".equals(auth.getName()));
    }

    public boolean isOrganization() {
        return this.authorities.stream()
                .anyMatch(auth -> "ORGANIZACION".equals(auth.getName()));
    }

}
