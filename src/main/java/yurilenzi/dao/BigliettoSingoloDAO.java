package yurilenzi.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import yurilenzi.entities.BigliettoSingolo;
import yurilenzi.entities.Mezzi;

import java.util.UUID;

public class BigliettoSingoloDAO {

    public final EntityManager entityManager;

    public BigliettoSingoloDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void upadateVidimato(BigliettoSingolo bigliettoSingolo, Mezzi mezzo){
        System.out.println(bigliettoSingolo.getId());
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        Query query = entityManager.createQuery("update BigliettoSingolo b set b.convalidato = true, b.mezzi = :id_mezzo_trovato, b.giornoDiVidimatura = :giorno where b.id = :id_biglietto");
        query.setParameter("id_biglietto", bigliettoSingolo.getId());
        query.setParameter("giorno", bigliettoSingolo.getGiornoDiVidimatura());
        query.setParameter("id_mezzo_trovato", mezzo);
        query.executeUpdate();
        transaction.commit();
        System.out.println("Biglietto vidimato");

    }

}
