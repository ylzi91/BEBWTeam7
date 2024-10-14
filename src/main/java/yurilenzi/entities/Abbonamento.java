package yurilenzi.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.time.LocalDate;
import java.util.List;

@Entity
public class Abbonamento extends Biglietti {
    @ManyToOne
    @JoinColumn(name = "id_tessera", nullable = false)
    private Tessere tessere;

    private TipologiaAbbonamento tipologiaAbbonamento;

    public Abbonamento(){

    }
    public Abbonamento(LocalDate dataEmissione, LocalDate dataScadenza, List<Ditributori> categories, Tessere tessere, TipologiaAbbonamento tipologiaAbbonamento) {
        super(dataEmissione, dataScadenza, categories);
        this.tessere = tessere;
        this.tipologiaAbbonamento = tipologiaAbbonamento;
    }

    public Tessere getTessere() {
        return tessere;
    }

    public void setTessere(Tessere tessere) {
        this.tessere = tessere;
    }

    public TipologiaAbbonamento getTipologiaAbbonamento() {
        return tipologiaAbbonamento;
    }

    public void setTipologiaAbbonamento(TipologiaAbbonamento tipologiaAbbonamento) {
        this.tipologiaAbbonamento = tipologiaAbbonamento;
    }

    @Override
    public String toString() {
        return "Abbonamento{" +
                "tessere=" + tessere +
                ", tipologiaAbbonamento=" + tipologiaAbbonamento +
                "} " + super.toString();
    }
}
