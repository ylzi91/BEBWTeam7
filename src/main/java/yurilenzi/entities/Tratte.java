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

    public Tratte(){

    }

    public Tratte(String zonaPartenza, String capolinea, Double tempoPrevisto, Double tempoEffettivo) {

        this.zonaPartenza = zonaPartenza;
        this.capolinea = capolinea;
        this.tempoPrevisto = tempoPrevisto;
        this.tempoEffettivo = tempoEffettivo;
    }

    public UUID getTratteId() {
        return tratteId;
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
        return "Tratte{" +
                "tratteId=" + tratteId +
                ", zonaPartenza='" + zonaPartenza + '\'' +
                ", capolinea='" + capolinea + '\'' +
                ", tempoPrevisto=" + tempoPrevisto +
                ", tempoEffettivo=" + tempoEffettivo +
                '}';
    }
}
