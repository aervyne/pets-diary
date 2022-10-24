package studia.paulinanowak.petsdiary.model;

import java.time.LocalDate;

public class Pet extends BaseEntity {
    private String name;
    private LocalDate dateOfBirth;
    private PetType petType;
    private String breed;

    // TODO dodać pozostałe pola zgodnie z diagramem


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
