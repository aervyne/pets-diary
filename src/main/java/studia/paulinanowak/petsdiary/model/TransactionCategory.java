package studia.paulinanowak.petsdiary.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "transaction_categories")
public class TransactionCategory extends BaseEntity {
    @Column(name = "category_name")
    private String name;
    @Column(name = "category_type")
    private String categoryType;
    @Column(name = "username")
    private String username;
}
