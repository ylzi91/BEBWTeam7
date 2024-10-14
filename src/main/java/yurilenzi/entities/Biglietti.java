package yurilenzi.entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "biglietto")

public class Biglietti {
    @Id
    @GeneratedValue
    private UUID id;
    private LocalDate dataEmissione;
    private LocalDate dataScadenza;
    @ManyToMany
    @JoinTable(name = "luogo_acquisto",
            joinColumns = @JoinColumn(name = "biglietto_id", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "distributori_id", nullable = false)
    )
    private List<Ditributori> categories;

}
