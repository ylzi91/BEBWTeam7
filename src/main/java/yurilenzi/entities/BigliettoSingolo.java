package yurilenzi.entities;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.time.LocalDate;
import java.util.List;


@Entity
public class BigliettoSingolo extends Biglietti {

    @ManyToOne
    @JoinColumn(name="id_mezzo")
    private Mezzi mezzi;

    private boolean convalidato;

    public BigliettoSingolo(){

    }

    public BigliettoSingolo(LocalDate dataEmissione, LocalDate dataScadenza, List<Ditributori> categories, Mezzi mezzi, boolean convalidato) {
        super(dataEmissione, dataScadenza, categories);
        this.mezzi = mezzi;
        this.convalidato = convalidato;
    }

    public Mezzi getMezzi() {
        return mezzi;
    }

    public void setMezzi(Mezzi mezzi) {
        this.mezzi = mezzi;
    }

    public boolean isConvalidato() {
        return convalidato;
    }

    public void setConvalidato(boolean convalidato) {
        this.convalidato = convalidato;
    }

    @Override
    public String toString() {
        return "BigliettoSingolo{" +
                "mezzi=" + mezzi +
                ", convalidato=" + convalidato +
                "} " + super.toString();
    }
}
