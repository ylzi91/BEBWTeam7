package yurilenzi.entities;


import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "luogo_acquisto")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo_distributore")

public class Ditributori {
    @Id
    @GeneratedValue
    private UUID id;
    
    @ManyToMany (mappedBy = "distributori_id")
    private List<Biglietti> bigliettoList;
}





