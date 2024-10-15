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
        return "Mezzi{" +
                "mezziId=" + mezziId +
                ", tipologiaMezzo=" + tipologiaMezzo +
                ", inServizio=" + inServizio +
                ", capienza=" + capienza +
                ", tratte=" + tratte +
                '}';
    }
}
