package yurilenzi.dao;


import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import yurilenzi.entities.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class BigliettiDAO {
    public final EntityManager entityManager;
    Scanner scanner = new Scanner(System.in);

    public BigliettiDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public Biglietti sceltaViaggio(Utenti utente) {
        Biglietti titolo;
        GenericDAO ed = new GenericDAO(entityManager);
        System.out.println("Cosa vuoi acquistare? 1 BIGLIETTO 2 ABBONAMENTO");
        int scelta = scanner.nextInt();
        DistributoriDAO pn = new DistributoriDAO(entityManager);
        if (scelta == 1) {
            titolo = creaBiglietto();
            ed.save(titolo);
        } else {
            titolo = creaAbbonamento(utente);
            ed.save(titolo);
        }

        return null;
    }

    public Distributori sceltaVendita() {
        Distributori puntoVendita;
        GenericDAO ed = new GenericDAO(entityManager);
        System.out.println("Dove vuoi acquistare? 1 RIVENDITORE 2 DISTRIBUTORE");
        int scelta = scanner.nextInt();
        DistributoriDAO pn = new DistributoriDAO(entityManager);
        if (scelta == 1) {
            List<RivenditoreFisico> rivenditoreList = pn.findAllRivenditori();
            System.out.println("Da quale rivenditore? Inserisci ID RIVENDITORE");
            System.out.println(rivenditoreList.toString());
            String rispostaId = scanner.next();
            try {
                puntoVendita = ed.findById(Distributori.class, rispostaId);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }


        } else {
            List<DistributoreAutomatico> distributoreList = pn.findAllDistributore();
            System.out.println("Da quale distributore? Inserisci ID DISTRIBUTORE");
            System.out.println(distributoreList.toString());
            String rispostaId = scanner.next();
            try {
                puntoVendita = ed.findById(Distributori.class, rispostaId);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        return puntoVendita;

    }

    public Biglietti creaBiglietto() {
        Distributori puntoVendita;

        LocalDate dataEmissione = LocalDate.now();
        puntoVendita = sceltaVendita();

        return new BigliettoSingolo(dataEmissione, puntoVendita);

    }

    public Biglietti creaAbbonamento(Utenti utente) {
        LocalDate dataEmissione = LocalDate.now();
        Distributori puntoVendita;
        TipologiaAbbonamento tipoAbbonamento;
        Tessere tessera;
        TessereDAO ed = new TessereDAO(entityManager);
        puntoVendita = sceltaVendita();
        System.out.println("Che tipo di abbonamento vuoi acquistare? 1 MENSILE 2 ANNUALE");
        int scelta = scanner.nextInt();
        if (scelta == 1) {
            tipoAbbonamento = TipologiaAbbonamento.MENSILE;
        } else {
            tipoAbbonamento = TipologiaAbbonamento.ANNUALE;
        }
try{
    tessera = ed.findTesseraByUtente(utente);
   }
    catch (NoResultException e) {
        System.out.println("Tessera non trovata ");
        tessera = ed.creaTessera(utente);
        System.out.println("Tessera creata ");
    }

        return new Abbonamento(dataEmissione,puntoVendita,tessera, tipoAbbonamento );

    }


}
