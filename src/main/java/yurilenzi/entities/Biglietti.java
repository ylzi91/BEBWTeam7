package yurilenzi.entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "biglietti")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class Biglietti {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private LocalDate dataEmissione;
    private LocalDate dataScadenza;
    @ManyToMany
    @JoinTable(name = "luogo_acquisto",
            joinColumns = @JoinColumn(name = "biglietto_id", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "distributori_id", nullable = false)
    )
    private List<Ditributori> categories;

    public Biglietti(){

    }
    public Biglietti(LocalDate dataEmissione, LocalDate dataScadenza, List<Ditributori> categories) {
        this.dataEmissione = dataEmissione;
        this.dataScadenza = dataScadenza;
        this.categories = categories;
    }

    public UUID getId() {
        return id;
    }

    public LocalDate getDataEmissione() {
        return dataEmissione;
    }

    public void setDataEmissione(LocalDate dataEmissione) {
        this.dataEmissione = dataEmissione;
    }

    public LocalDate getDataScadenza() {
        return dataScadenza;
    }

    public void setDataScadenza(LocalDate dataScadenza) {
        this.dataScadenza = dataScadenza;
    }

    public List<Ditributori> getCategories() {
        return categories;
    }

    public void setCategories(List<Ditributori> categories) {
        this.categories = categories;
    }

    @Override
    public String toString() {
        return "Biglietti{" +
                "id=" + id +
                ", dataEmissione=" + dataEmissione +
                ", dataScadenza=" + dataScadenza +
                ", categories=" + categories +
                '}';
    }
}
