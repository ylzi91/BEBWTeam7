package yurilenzi.entities;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;


@Entity
@DiscriminatorValue("rivenditore_fisico")
public class RivenditoreFisico extends Ditributori {

        private String Nome;

        public RivenditoreFisico(){

        }

        public RivenditoreFisico(String nome) {
                Nome = nome;
        }

        public String getNome() {
                return Nome;
        }

        public void setNome(String nome) {
                Nome = nome;
        }

        @Override
        public String toString() {
                return "RivenditoreFisico{" +
                        "Nome='" + Nome + '\'' +
                        "} " + super.toString();
        }
}
