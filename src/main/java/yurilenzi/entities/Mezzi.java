package yurilenzi.entities;

import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name="mezzi")

public class Mezzi {
    @Id
    @GeneratedValue
    @Column(name="user")
    private UUID mezziId;

    private TipologiaMezzo tipologiaMezzo;
    private boolean inServizio;
    private Capienza capienza;

    @ManyToOne
    @JoinColumn(name="id_tratta")
    private Tratte tratte;

    @OneToMany(mappedBy="id_mezzo")
    private List<BigliettoSingolo> listaBiglietti;


    // costruttore vuoto
    public Mezzi() {}

    // costruttore

}
