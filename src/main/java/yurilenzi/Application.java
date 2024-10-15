package yurilenzi;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import yurilenzi.entities.TipologiaMezzo;
import yurilenzi.exceptions.NotFoundException;

import java.util.Random;

public class Application {

    public static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("BEBWTeam7");
    public static EntityManager em = emf.createEntityManager();
    public static void main(String[] args) {
        System.out.println("Hello World!");
        //Util.saveFakeIUser(20, em);
        //Util.saveFakeTratte(20,em);
        //Util.SaveFakeDistributori(30, em);
//        try {
//            Util.saveBigliettoSingolo(em, "d613f8d2-45cc-4f2f-8e83-aa22b0398686", TipologiaMezzo.AUTOBUS);
//        } catch (NotFoundException e) {
//            System.out.println(e.getMessage());
//        }
       // Util.SaveMezzi(em);

//        try {
//            Util.vidimaBiglietto(em, "07c685ba-be3a-4436-8e69-4663afa78e0e", "b4489c97-63ec-4a2f-863f-e03b9cee0e14");
//        } catch (NotFoundException e) {
//            System.out.println(e.getMessage());
//        }
    }
}
