package yurilenzi.entities;


import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "tessera")
public class Tessere {

    @Id
    @GeneratedValue
    private UUID idTessera;

    @OneToMany(mappedBy = "tessera")
    private List<Abbonamento> abbonamenti;

    @ManyToOne
    @JoinColumn(name = "id_utente", nullable = false)
    private LocalDate dataEmissione;
    private LocalDate dataScadenza;
}
