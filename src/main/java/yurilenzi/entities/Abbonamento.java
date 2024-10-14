package yurilenzi.entities;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

public class Abbonamento {
    @ManyToOne
    @JoinColumn(name = "id_tessera", nullable = false)
    private Tessere tessere;

    private TipologiaAbbonamento tipologiaAbbonamento;
}
