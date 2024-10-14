package yurilenzi.entities;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;


@Entity
@DiscriminatorValue("rivenditore_fisico")
public class RivenditoreFisico {

        private String Nome;
}
