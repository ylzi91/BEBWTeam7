package yurilenzi.entities;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;


@Entity
@DiscriminatorValue("distributore_automatico")
public class DistributoreAutomatico {

        private boolean attivo;
}
