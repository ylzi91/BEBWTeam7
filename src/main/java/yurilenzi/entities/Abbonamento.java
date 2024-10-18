package yurilenzi.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

import java.time.LocalDate;

@Entity
public class Abbonamento extends Biglietti {
    @OneToOne
    @JoinColumn(name = "id_tessera")
    private Tessere tessere;

    private TipologiaAbbonamento tipologiaAbbonamento;

    public Abbonamento(){

    }

    public Abbonamento(LocalDate dataEmissione, Distributori ditributori, Tessere tessere, TipologiaAbbonamento tipologiaAbbonamento) {
        super(dataEmissione, ditributori);
        this.tessere = tessere;
        if(tipologiaAbbonamento == TipologiaAbbonamento.MENSILE) this.dataScadenza = dataEmissione.plusMonths(1);
        else if (tipologiaAbbonamento == TipologiaAbbonamento.ANNUALE) this.dataScadenza = dataEmissione.plusYears(1);
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
               // "tessere=" + tessere +
                ", tipologiaAbbonamento=" + tipologiaAbbonamento +
                "} " + super.toString();
    }
}
