package yurilenzi.entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "biglietti")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class Biglietti {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    protected UUID id;
    protected LocalDate dataEmissione;
    protected LocalDate dataScadenza;
    @ManyToOne
    @JoinColumn(name = "id_distributore")
    protected Distributori ditributori;


    public Biglietti(){

    }
    public Biglietti(LocalDate dataEmissione, Distributori ditributori) {
        this.dataEmissione = dataEmissione;
        this.dataScadenza = dataEmissione.plusDays(10);
        this.ditributori = ditributori;
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

    public Distributori getdistributori() {
        return ditributori;
    }
    public void setDistributori(Distributori ditributori) {
        this.ditributori = ditributori;
    }

    @Override
    public String toString() {
        return "Biglietti{" +
                "id=" + id +
                ", dataEmissione=" + dataEmissione +
                ", dataScadenza=" + dataScadenza +
                '}';
    }
}
