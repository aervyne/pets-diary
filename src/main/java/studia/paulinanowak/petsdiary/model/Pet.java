package studia.paulinanowak.petsdiary.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "pets")
public class Pet extends BaseEntity {
    @Column(name = "name")
    private String name;
    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;
    @ManyToOne
    @JoinColumn(name = "pet_type_id")
    private PetType petType;
    @Column(name = "breed")
    private String breed;
    @Column(name = "mother")
    private String mother;
    @Column(name = "father")
    private String father;
    @Column(name = "microchip_number")
    private String microchipNumber;
    @Lob
    @Column(name = "image")
    private Byte[] image;

    @Column(name = "username")
    private String username;
}
