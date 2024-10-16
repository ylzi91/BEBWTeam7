package yurilenzi.entities;

import jakarta.persistence.*;

import java.time.LocalDate;


@Entity
public class BigliettoSingolo extends Biglietti {


    private boolean convalidato;
    @Enumerated(EnumType.STRING)
    private TipologiaMezzo tipologiaMezzo;
    @ManyToOne
    @JoinColumn(name = "id_mezzo")
    private Mezzi mezzi;
    private LocalDate giornoDiVidimatura;

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

    public LocalDate getGiornoDiVidimatura() {
        return giornoDiVidimatura;
    }

    public void setGiornoDiVidimatura() {
        if(dataScadenza.isBefore(LocalDate.now()))
            System.out.println("Biglietto Scaduto");
        else
            this.giornoDiVidimatura = LocalDate.now();
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

    @Override
    public String toString() {
        return "BigliettoSingolo{" +
                ", convalidato=" + convalidato +
                "} " + super.toString();
    }
}
