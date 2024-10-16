package yurilenzi.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import yurilenzi.entities.Mezzi;
import yurilenzi.entities.Tratte;

public class MezziDAO {

    public final EntityManager entityManager;

    public MezziDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }


    public void mezzoFuoriServizio(Mezzi mezzo){
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        Query query = entityManager.createQuery("update Mezzi m set m.inServizio = false, m.tratte = null where m.mezziId = :idmezzoDaCambiare");
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

    public void setTratta(Mezzi mezzo, Tratte tratta){
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        Query query = entityManager.createQuery("update Mezzi m set m.tratte = :nuovaTratta where m.mezziId = :idMezzoDaAggiungere");
        query.setParameter("nuovaTratta", tratta);
        query.setParameter("idMezzoDaAggiungere", mezzo.getMezziId());
        query.executeUpdate();
        transaction.commit();
        System.out.println("Il mezzo " + mezzo.getMezziId() + " è stato aggiunta alla tratta " + tratta.getTratteId());
    }

}
