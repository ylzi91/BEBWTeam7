package yurilenzi.entities;

import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name="tratte")

public class Tratte {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name="tratte")
    private UUID tratteId;

    private String zonaPartenza;
    private String capolinea;
    private Double tempoPrevisto;
    private Double tempoEffettivo;
    private int numeroGiri;

    public Tratte(){

    }

    public Tratte(String capolinea, Double tempoEffettivo, Double tempoPrevisto, String zonaPartenza, int numeroGiri) {
        this.capolinea = capolinea;
        this.numeroGiri = numeroGiri;
        this.tempoEffettivo = tempoEffettivo;
        this.tempoPrevisto = tempoPrevisto;
        this.zonaPartenza = zonaPartenza;
    }

    public UUID getTratteId() {
        return tratteId;
    }

    public int getNumeroGiri() {
        return numeroGiri;
    }

    public void setNumeroGiri(int numeroGiri) {
        this.numeroGiri = numeroGiri;
    }

    public String getZonaPartenza() {
        return zonaPartenza;
    }

    public void setZonaPartenza(String zonaPartenza) {
        this.zonaPartenza = zonaPartenza;
    }

    public String getCapolinea() {
        return capolinea;
    }

    public void setCapolinea(String capolinea) {
        this.capolinea = capolinea;
    }

    public Double getTempoPrevisto() {
        return tempoPrevisto;
    }

    public void setTempoPrevisto(Double tempoPrevisto) {
        this.tempoPrevisto = tempoPrevisto;
    }

    public Double getTempoEffettivo() {
        return tempoEffettivo;
    }

    public void setTempoEffettivo(Double tempoEffettivo) {
        this.tempoEffettivo = tempoEffettivo;
    }

    @Override
    public String toString() {
        return   "\n-----------------Tratta----------------------\n" +
                "trattaId: " + tratteId +
                "\nzonaPartenza: " + zonaPartenza +
                "\ncapolinea: " + capolinea  +
                "\ntempoPrevisto: " + tempoPrevisto +
                "\ntempoEffettivo: " + tempoEffettivo +
                "\nnumeroGiri: " + numeroGiri;
    }
}
