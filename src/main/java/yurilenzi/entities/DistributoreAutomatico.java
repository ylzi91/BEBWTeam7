package yurilenzi.entities;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;


@Entity
@DiscriminatorValue("distributore_automatico")
public class DistributoreAutomatico extends Ditributori {

        private boolean attivo;

        public DistributoreAutomatico(){

        }
        public DistributoreAutomatico(boolean attivo) {
                this.attivo = attivo;
        }

        public boolean isAttivo() {
                return attivo;
        }

        @Override
        public String toString() {
                return "DistributoreAutomatico{" +
                        "attivo=" + attivo +
                        "} " + super.toString();
        }
}
