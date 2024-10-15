package yurilenzi;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class Application {

    public static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("BEBWTeam7");
    public static EntityManager em = emf.createEntityManager();
    public static void main(String[] args) {
        System.out.println("Hello World!");
        //Util.saveFakeIUser(20, em);
        //Util.saveFakeTratte(20,em);
        //Util.SaveFakeDistributori(30, em);
        //Util.saveBigliettoSingolo(em);
    }
}
