package yurilenzi.entities;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;


@Entity
@DiscriminatorValue("biglietto singolo")
public class BigliettoSingolo extends Biglietti {

    @ManyToOne
    @JoinColumn(name="id_mezzo")
    private Mezzi mezzi;

    private boolean convalidato;
}
