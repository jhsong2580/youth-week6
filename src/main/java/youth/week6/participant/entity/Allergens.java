package youth.week6.participant.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Allergens {

    @Column(name = "allergens_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    private String name;

    public Allergens(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Allergens{" +
            "name='" + name + '\'' +
            '}';
    }
}
