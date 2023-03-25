package studia.paulinanowak.petsdiary.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "announcements")
public class Announcement extends BaseEntity {
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "pet_id", referencedColumnName = "id")
    private Pet pet;
    @Column(name = "published")
    private boolean published;
    @Column(name = "status")
    private String status;
    @Column(name = "price")
    private double price;
    @ManyToOne
    @JoinColumn(name = "breeding_id")
    private Breeding breeding;
    @Column(name = "username")
    private String username;
}
