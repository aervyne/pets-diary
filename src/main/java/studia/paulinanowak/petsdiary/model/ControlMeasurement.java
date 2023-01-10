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
@Table(name = "control_measurements")
public class ControlMeasurement extends BaseEntity {
    @Column(name = "height")
    private double height;
    @Column(name = "weight")
    private double weight;
    @ManyToOne
    @JoinColumn(name = "ID_pet")
    private Pet pet;
}
