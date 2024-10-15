package yurilenzi.entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;


@Entity
public class BigliettoSingolo extends Biglietti {


    private boolean convalidato;
    @Enumerated(EnumType.STRING)
    private TipologiaMezzo tipologiaMezzo;
    @ManyToOne
    @JoinColumn(name = "id_mezzo")
    private Mezzi mezzi;
    private boolean vidimato;

    public BigliettoSingolo(){

    }

    public BigliettoSingolo(LocalDate dataEmissione, Ditributori ditributori, boolean convalidato, TipologiaMezzo tipologiaMezzo) {
        super(dataEmissione, ditributori);
        this.convalidato = convalidato;
        this.tipologiaMezzo = tipologiaMezzo;
        this.vidimato = false;
    }

    public boolean isConvalidato() {
        return convalidato;
    }

    public void setConvalidato(boolean convalidato) {
        this.convalidato = convalidato;
    }

    public Mezzi getMezzi() {
        return mezzi;
    }

    public void setMezzi(Mezzi mezzi) {
        if(this.tipologiaMezzo != mezzi.getTipologiaMezzo())
            System.out.println("Non puoi salire su questo mezzo");
        else
        this.mezzi = mezzi;
    }

    public TipologiaMezzo getTipologiaMezzo() {
        return tipologiaMezzo;
    }

    public void setTipologiaMezzo(TipologiaMezzo tipologiaMezzo) {
        this.tipologiaMezzo = tipologiaMezzo;
    }

    public boolean isVidimato() {
        return vidimato;
    }

    public void setVidimato(boolean vidimato) {
        this.vidimato = vidimato;
    }

    @Override
    public String toString() {
        return "BigliettoSingolo{" +
                ", convalidato=" + convalidato +
                "} " + super.toString();
    }
}
