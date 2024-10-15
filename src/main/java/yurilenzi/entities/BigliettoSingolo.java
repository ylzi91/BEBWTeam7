package yurilenzi.entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;


@Entity
public class BigliettoSingolo extends Biglietti {


    private boolean convalidato;
    @Enumerated(EnumType.STRING)
    private TipologiaMezzo tipologiaMezzo;

    public BigliettoSingolo(){

    }

    public BigliettoSingolo(LocalDate dataEmissione, Ditributori ditributori, boolean convalidato, TipologiaMezzo tipologiaMezzo) {
        super(dataEmissione, ditributori);
        this.convalidato = convalidato;
        this.tipologiaMezzo = tipologiaMezzo;
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
                ", convalidato=" + convalidato +
                "} " + super.toString();
    }
}
