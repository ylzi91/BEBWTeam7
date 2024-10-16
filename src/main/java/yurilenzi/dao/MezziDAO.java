package yurilenzi.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
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
        TypedQuery<Mezzi> query1 = entityManager.createQuery("SELECT m FROM Mezzi m WHERE m.tratte =:nuovaTratta", Mezzi.class);
        query1.setParameter("nuovaTratta", tratta);
        if(query1.getSingleResult() != null) {
            System.out.print("La tratta è già coperta");
        } else {
            EntityTransaction transaction = entityManager.getTransaction();
            transaction.begin();
            Query query = entityManager.createQuery("update Mezzi m set m.tratte = :nuovaTratta, m.inServizio  = true where m.mezziId = :idMezzoDaAggiungere");
            query.setParameter("nuovaTratta", tratta);
            query.setParameter("idMezzoDaAggiungere", mezzo.getMezziId());
            query.executeUpdate();
            transaction.commit();
            System.out.println("Il mezzo " + mezzo.getMezziId() + " è stato aggiunta alla tratta " + tratta.getTratteId());
        }
    }

    public Double calcolaTempoMedio(Mezzi mezzo) {
        TypedQuery<Double> query = entityManager.createQuery("SELECT m.tratte.tempoEffettivo / m.tratte.numeroGiri FROM Mezzi m WHERE m.tratte = :trattaPassata", Double.class);
        query.setParameter("trattaPassata", mezzo.getTratte());
        return(query.getSingleResult());
    }

}
