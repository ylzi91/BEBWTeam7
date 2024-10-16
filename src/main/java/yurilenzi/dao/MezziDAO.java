package yurilenzi.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import yurilenzi.entities.Mezzi;

import java.time.LocalDate;
import java.util.Locale;

public class MezziDAO {

    public final EntityManager entityManager;

    public MezziDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }


    public void mezzoInManutenzione(Mezzi mezzo){
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        Query query = entityManager.createQuery("update Mezzi m set m.inServizio = false where m.mezziId = :idmezzoDaCambiare");
        query.setParameter("idmezzoDaCambiare", mezzo.getMezziId());
        query.executeUpdate();
        transaction.commit();

        System.out.println("Il mezzo " + mezzo.getMezziId() + " è fuori servizio");
    }
    public void mezzoDiNuovoInServizio(Mezzi mezzo){
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        Query query = entityManager.createQuery("update Mezzi m set m.inServizio = true where m.mezziId = :idmezzoDaCambiare");
        query.setParameter("idmezzoDaCambiare", mezzo.getMezziId());
        query.executeUpdate();
        transaction.commit();
        System.out.println("Il mezzo " + mezzo.getMezziId() + " è tornato in servizio");
    }
}
