package yurilenzi.entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.UUID;

@Entity
public class Manutenzioni {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id_manutenzione")
    private UUID idManutenzione;
    private LocalDate dataInizioManutenzione;
    private LocalDate dataFineManutenzione;
    @ManyToOne
    @JoinColumn(name = "mezzo")
    private Mezzi mezzo;

    public Manutenzioni(){

    }
    public Manutenzioni(LocalDate dataInizioManutenzione, LocalDate dataFineManutenzione, Mezzi mezzo) {
        this.dataInizioManutenzione = dataInizioManutenzione;
        this.dataFineManutenzione = dataFineManutenzione;
        this.mezzo = mezzo;
    }

    public LocalDate getDataInizioManutenzione() {
        return dataInizioManutenzione;
    }

    public UUID getIdMezzi() {
        return idManutenzione;
    }

    public void setDataInizioManutenzione(LocalDate dataInizioManutenzione) {
        this.dataInizioManutenzione = dataInizioManutenzione;
    }

    public LocalDate getDataFineManutenzione() {
        return dataFineManutenzione;
    }

    public void setDataFineManutenzione(LocalDate dataFineManutenzione) {
        this.dataFineManutenzione = dataFineManutenzione;
    }

    public Mezzi getMezzo() {
        return mezzo;
    }

    public void setMezzo(Mezzi mezzo) {
        this.mezzo = mezzo;
    }

    @Override
    public String toString() {
        return "Manutenzioni{" +
                "idMezzi=" + idManutenzione +
                ", dataInizioManutenzione=" + dataInizioManutenzione +
                ", dataFineManutenzione=" + dataFineManutenzione +
                ", mezzo=" + mezzo +
                '}';
    }
}
