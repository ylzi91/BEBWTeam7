package yurilenzi.entities;


import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Entity
public class Tessere {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID idTessera;

    @OneToMany(mappedBy = "tessere")
    private List<Abbonamento> abbonamenti;
    @ManyToOne
    @JoinColumn(name = "id_utente", nullable = false)
    private Utenti utenti;

    private LocalDate dataEmissione;
    private LocalDate dataScadenza;


    public Tessere(){

    }

    public Tessere( Utenti utenti, LocalDate dataEmissione, LocalDate dataScadenza) {
        this.utenti = utenti;
        this.dataEmissione = dataEmissione;
        this.dataScadenza = dataScadenza;
    }

    public UUID getIdTessera() {
        return idTessera;
    }

    public List<Abbonamento> getAbbonamenti() {
        return abbonamenti;
    }

    public void setAbbonamenti(List<Abbonamento> abbonamenti) {
        this.abbonamenti = abbonamenti;
    }

    public Utenti getUtenti() {
        return utenti;
    }

    public void setUtenti(Utenti utenti) {
        this.utenti = utenti;
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

    @Override
    public String toString() {
        return "Tessere{" +
                "idTessera=" + idTessera +
                ", abbonamenti=" + abbonamenti +
                ", utenti=" + utenti +
                ", dataEmissione=" + dataEmissione +
                ", dataScadenza=" + dataScadenza +
                '}';
    }
}
