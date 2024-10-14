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

    private TipologiaMezzo tipologiaMezzo;
    private boolean inServizio;
    private Capienza capienza;

    @ManyToOne
    @JoinColumn(name="id_tratta")
    private Tratte tratte;

    @OneToMany(mappedBy="mezzi")
    private List<BigliettoSingolo> listaBiglietti;


    // costruttore vuoto
    public Mezzi() {}

    // costruttore


    public Mezzi(TipologiaMezzo tipologiaMezzo, boolean inServizio, Capienza capienza, Tratte tratte, List<BigliettoSingolo> listaBiglietti) {
        this.tipologiaMezzo = tipologiaMezzo;
        this.inServizio = inServizio;
        this.capienza = capienza;
        this.tratte = tratte;
        this.listaBiglietti = listaBiglietti;
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

    public Capienza getCapienza() {
        return capienza;
    }

    public void setCapienza(Capienza capienza) {
        this.capienza = capienza;
    }

    public Tratte getTratte() {
        return tratte;
    }

    public void setTratte(Tratte tratte) {
        this.tratte = tratte;
    }

    public List<BigliettoSingolo> getListaBiglietti() {
        return listaBiglietti;
    }

    public void setListaBiglietti(List<BigliettoSingolo> listaBiglietti) {
        this.listaBiglietti = listaBiglietti;
    }

    @Override
    public String toString() {
        return "Mezzi{" +
                "mezziId=" + mezziId +
                ", tipologiaMezzo=" + tipologiaMezzo +
                ", inServizio=" + inServizio +
                ", capienza=" + capienza +
                ", tratte=" + tratte +
                ", listaBiglietti=" + listaBiglietti +
                '}';
    }
}
