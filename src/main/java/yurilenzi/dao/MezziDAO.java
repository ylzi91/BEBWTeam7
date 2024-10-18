package yurilenzi.dao;

import jakarta.persistence.*;
import yurilenzi.entities.Manutenzioni;
import yurilenzi.entities.Mezzi;
import yurilenzi.entities.Tratte;
import yurilenzi.exceptions.NotFoundException;
import yurilenzi.exceptions.NothingGenException;

import java.util.List;
import java.util.Random;

public class MezziDAO {

    public final EntityManager entityManager;

    public MezziDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }


    public void mezzoFuoriServizio(Mezzi mezzo){
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        Query query = entityManager.createQuery("update Mezzi m set m.inServizio = false, m.inManutenzione = false, m.tratte = null where m.mezziId = :idmezzoDaCambiare");
        query.setParameter("idmezzoDaCambiare", mezzo.getMezziId());
        query.executeUpdate();
        transaction.commit();
        System.out.println("Il mezzo " + mezzo.getMezziId() + " è fuori servizio");
    }
    public void mezzoInManutenzione(Mezzi mezzo){

        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        Query query = entityManager.createQuery("update Mezzi m set m.inServizio = false, m.inManutenzione = true, m.tratte = null where m.mezziId = :idmezzoDaCambiare");
        query.setParameter("idmezzoDaCambiare", mezzo.getMezziId());
        query.executeUpdate();
        transaction.commit();
        System.out.println("Il mezzo " + mezzo.getMezziId() + " è fuori servizio");
    }


    public void mezzoDiNuovoInServizio(Mezzi mezzo){
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        Query query = entityManager.createQuery("update Mezzi m set m.inServizio = false, m.inManutenzione = false where m.mezziId = :idmezzoDaCambiare");
        query.setParameter("idmezzoDaCambiare", mezzo.getMezziId());
        query.executeUpdate();
        transaction.commit();
        System.out.println("Il mezzo " + mezzo.getMezziId() + " è tornato in servizio");
    }


    public void setTratta(Mezzi mezzo, Tratte tratta){
        try {
        TypedQuery<Mezzi> query1 = entityManager.createQuery("SELECT m FROM Mezzi m WHERE m.tratte =:nuovaTratta", Mezzi.class);
        query1.setParameter("nuovaTratta", tratta);
        query1.getSingleResult();
            System.out.println("La tratta è gia coperta");
        } catch (NoResultException e){
            Double randomEffettivo = new Random().nextDouble((tratta.getTempoPrevisto() * tratta.getNumeroGiri() -5), (tratta.getTempoPrevisto() * tratta.getNumeroGiri() +5));
            EntityTransaction transaction = entityManager.getTransaction();
            transaction.begin();
            Query query = entityManager.createQuery("update Mezzi m set m.tratte = :nuovaTratta, m.inServizio  = true where m.mezziId = :idMezzoDaAggiungere");
            Query query1 = entityManager.createQuery("update Tratte t set t.tempoEffettivo = :tempoRand where t.tratteId = :trattaId");
            query.setParameter("nuovaTratta", tratta);
            query.setParameter("idMezzoDaAggiungere", mezzo.getMezziId());
            query1.setParameter("trattaId", tratta.getTratteId());
            query1.setParameter("tempoRand", randomEffettivo);
            query.executeUpdate();
            query1.executeUpdate();
            transaction.commit();
            System.out.println("Il mezzo " + mezzo.getMezziId() + " è stato aggiunta alla tratta " + tratta.getTratteId());
        }
    }

    public Double calcolaTempoMedio(Mezzi mezzo) {
        TypedQuery<Double> query = entityManager.createQuery("SELECT m.tratte.tempoEffettivo / m.tratte.numeroGiri FROM Mezzi m WHERE m.tratte = :trattaPassata", Double.class);
        query.setParameter("trattaPassata", mezzo.getTratte());
        return(query.getSingleResult());
    }

    public List<Manutenzioni> dettagliMezziInManutenzione() throws NothingGenException {
        TypedQuery<Manutenzioni> query = entityManager.createQuery("select ma from Mezzi m join Manutenzioni ma on ma.mezzo = m", Manutenzioni.class);
        if(query.getResultList().isEmpty()) throw new NothingGenException(Mezzi.class);
        return query.getResultList();
    }

    public List<Mezzi> mezziInManutenzione() throws NothingGenException {
        TypedQuery<Mezzi> query = entityManager.createQuery("select m from Mezzi m join Manutenzioni ma on ma.mezzo = m", Mezzi.class);
        if(query.getResultList().isEmpty()) throw new NothingGenException(Mezzi.class);
        return query.getResultList();
    }

    public List<Mezzi> mezziDiponibili() throws NothingGenException {
        TypedQuery<Mezzi> query = entityManager.createQuery("select m from Mezzi m where m.inManutenzione = false and m.inServizio = false", Mezzi.class);
        if(query.getResultList().isEmpty()) throw new NothingGenException(Mezzi.class);
        return query.getResultList();
    }
    public List<Mezzi> mezziInServizio() throws NothingGenException {
        TypedQuery<Mezzi> query = entityManager.createQuery("select m from Mezzi m where m.inManutenzione = false and m.inServizio = true", Mezzi.class);
        if(query.getResultList().isEmpty()) throw new NothingGenException(Mezzi.class);
        return query.getResultList();
    }

    public long contaggioManutenzioni(String idMezzo) throws NotFoundException {
        GenericDAO genericDAO = new GenericDAO(entityManager);
        Mezzi found = genericDAO.findById(Mezzi.class, idMezzo);
        TypedQuery<Long> query = entityManager.createQuery("select count(m) from Manutenzioni m where m.mezzo = :idMezzo ", Long.class);
        query.setParameter("idMezzo", found);
        return query.getSingleResult();
    }




}
