package yurilenzi.entities;

import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name="mezzi")
public class Mezzi {
    @Id
    @GeneratedValue
    @Column(name="id_mezzo")
    private UUID mezziId;

    @Enumerated(EnumType.STRING)
    private TipologiaMezzo tipologiaMezzo;
    private boolean inServizio;
    public boolean inManutenzione;

    private int capienza;

    @ManyToOne
    @JoinColumn(name="id_tratta")
    private Tratte tratte;


    // costruttore vuoto
    public Mezzi() {}

    // costruttore


    public Mezzi(TipologiaMezzo tipologiaMezzo, boolean inServizio, Tratte tratte) {
        this.tipologiaMezzo = tipologiaMezzo;
        if(tipologiaMezzo == TipologiaMezzo.TRAM) this.capienza = 45;
        else if (tipologiaMezzo == TipologiaMezzo.AUTOBUS) this.capienza = 60;
        this.inServizio = inServizio;
        this.inManutenzione = false;


        this.tratte = tratte;

    }

    public UUID getMezziId() {
        return mezziId;
    }

    public TipologiaMezzo getTipologiaMezzo() {
        return tipologiaMezzo;
    }

    public void setTipologiaMezzo(TipologiaMezzo tipologiaMezzo) {
        this.tipologiaMezzo = tipologiaMezzo;
    }

    public boolean isInManutenzione() {
        return inManutenzione;
    }

    public void setInManutenzione(boolean inManutenzione) {
        this.inManutenzione = inManutenzione;
    }

    public boolean isInServizio() {
        return inServizio;
    }

    public void setInServizio(boolean inServizio) {
        this.inServizio = inServizio;
    }

    public int getCapienza() {
        return capienza;
    }

    public void setCapienza(int capienza) {
        this.capienza = capienza;
    }

    public Tratte getTratte() {
        return tratte;
    }

    public void setTratte(Tratte tratte) {
        this.tratte = tratte;
    }


    @Override
    public String toString() {
        return "\n------------------------Mezzo------------------" +
                "\nmezziId: " + mezziId +
                "\ntipologiaMezzo: " + tipologiaMezzo +
                "\ninServizio: " + inServizio +
                "\ncapienza: " + capienza +
                 (tratte == null ? "\nNessuna tratta" : "\n" + tratte) +
                "\n------------------Fine mezzo-----------------";
    }
}
