package yurilenzi;



import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import yurilenzi.dao.BigliettiDAO;
import yurilenzi.dao.GenericDAO;
import yurilenzi.dao.TessereDAO;
import yurilenzi.dao.UtentiDAO;
import yurilenzi.entities.DistributoreAutomatico;
import yurilenzi.entities.RivenditoreFisico;
import yurilenzi.entities.Utenti;


public class ApplicationTest {

    public static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("BEBWTeam7");
    public static EntityManager em = emf.createEntityManager();
    public static void main(String[] args) {

        GenericDAO ed = new GenericDAO(em);
       /* RivenditoreFisico rivenditore1 = new RivenditoreFisico("Rivenditore Fisico 1");
        RivenditoreFisico rivenditore2 = new RivenditoreFisico("Rivenditore Fisico 2");


        DistributoreAutomatico distributore1 = new DistributoreAutomatico(true);
        DistributoreAutomatico distributore2 = new DistributoreAutomatico(false);


        ed.save(rivenditore1);
       ed.save(rivenditore2);
        ed.save(distributore1);
        ed.save(distributore2);*/

        UtentiDAO ut = new UtentiDAO(em);
        TessereDAO te = new TessereDAO(em);
        BigliettiDAO bi = new BigliettiDAO(em);
       Utenti u = ut.logIn();
        bi.sceltaViaggio(u);

    }
}
