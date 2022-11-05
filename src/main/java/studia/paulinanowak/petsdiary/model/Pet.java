package studia.paulinanowak.petsdiary.model;

import javax.persistence.*;
import java.time.LocalDate;

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


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public PetType getPetType() {
        return petType;
    }

    public void setPetType(PetType petType) {
        this.petType = petType;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }
}
