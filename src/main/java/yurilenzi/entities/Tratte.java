package yurilenzi.entities;

import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name="tratte")

public class Tratte {
    @Id
    @GeneratedValue
    @Column(name="tratte")
    private UUID tratteId;

    @OneToMany(mappedBy = "id_tratte")
    private List<Mezzi> listaMezzi;
    private String zonaPartenza;
    private String capolinea;
    private Double tempoPrevisto;



}
