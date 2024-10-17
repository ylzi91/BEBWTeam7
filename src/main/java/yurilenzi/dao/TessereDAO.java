package yurilenzi.dao;


import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import yurilenzi.entities.Tessere;
import yurilenzi.entities.Utenti;

import java.time.LocalDate;
import java.util.Scanner;

public class TessereDAO {
    public final EntityManager entityManager;
    Scanner scanner = new Scanner(System.in);

    public TessereDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }


    public Tessere findTesseraByUtente(Utenti utente) {
        TypedQuery<Tessere> query = entityManager.createQuery("SELECT t FROM Tessere t WHERE t.utenti = :utente", Tessere.class);
        query.setParameter("utente", utente);
        return query.getSingleResult();
    }

    public Tessere creaTessera(Utenti utente) {
        LocalDate dataEmissione = LocalDate.now();
        LocalDate dataRinnovo = dataEmissione.plusYears(1);
        Tessere tessera = new Tessere(utente, dataEmissione, dataRinnovo);
        GenericDAO ed = new GenericDAO(entityManager);
        ed.save(tessera);

        return findTesseraByUtente(utente);

    }

}

