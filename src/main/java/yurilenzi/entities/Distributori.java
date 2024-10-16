package yurilenzi.entities;


import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "rivenditori")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo_distributore")
public abstract class Distributori {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID idRivenditore;


    public Distributori(){
    }

    public UUID getId() {
        return idRivenditore;
    }

    @Override
    public String toString() {
        return "Ditributori{" +
                "id=" + idRivenditore +
                '}';
    }
}





