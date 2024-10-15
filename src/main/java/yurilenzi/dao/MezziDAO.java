package yurilenzi.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import yurilenzi.entities.Mezzi;

import java.time.LocalDate;
import java.util.Locale;

public class MezziDAO {

    public final EntityManager entityManager;

    public MezziDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void cercaFineManutenzione(LocalDate data){
        data = LocalDate.now();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        TypedQuery<Mezzi> query = entityManager.createQuery("update Mezzi m set Mezzi m.inServizio = true where m.manutenzioni.dataFineManutenzione >= :data ", Mezzi.class);
        query.setParameter("data", data);
        query.executeUpdate();
        transaction.commit();
        System.out.println("I mezzi sono tornati in servizio");


    }
}
