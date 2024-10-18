package yurilenzi.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import yurilenzi.Util;
import yurilenzi.entities.Biglietti;
import yurilenzi.entities.BigliettoSingolo;
import yurilenzi.entities.Mezzi;
import yurilenzi.entities.Utenti;
import yurilenzi.exceptions.NotFoundException;

import java.time.LocalDate;
import java.util.UUID;

import static yurilenzi.Application.em;
import static yurilenzi.Application.scanner;

public class BigliettoSingoloDAO {

    public final EntityManager entityManager;
    GenericDAO gd= new GenericDAO(em);

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
    public Biglietti controllaValidità(BigliettoSingolo biglietto) {
        LocalDate scadenza=biglietto.getDataScadenza() ;
        if(scadenza.isBefore(LocalDate.now())){
            System.out.println("Biglietto scaduto");
        }else {
            System.out.println("Biglietto non scaduto, vuoi vidimarlo?");
            System.out.println("1 SI");
            System.out.println("2 NO");
            int scelta = Integer.parseInt(scanner.nextLine());
            switch(scelta){
                case 1:
                    System.out.println("inserisci id mezzo");
                    String input = scanner.nextLine();
                    try {
                        Util.vidimaBiglietto(biglietto.getId().toString(),input);
                    } catch (NotFoundException e) {
                        System.out.println(e.getMessage());
                    }

                    break;
                case 2:
                    System.out.println("Uscita dal programma.");
                    break;
            }
        }
        return null;
    }

}
