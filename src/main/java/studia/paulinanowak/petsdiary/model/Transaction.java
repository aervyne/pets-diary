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
@Table(name = "transactions")
public class Transaction extends BaseEntity{
    @ManyToOne
    @JoinColumn(name = "category_id")
    private TransactionCategory category;
    @Column(name = "value")
    private double value;
    @Column(name = "date")
    private LocalDate date;
    @Column(name = "username")
    private String username;
    @Column(name = "note")
    private String note;

}
