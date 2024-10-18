package yurilenzi.dao;


import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import yurilenzi.entities.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

import static yurilenzi.Application.scanner;

public class BigliettiDAO {
    public final EntityManager entityManager;
    Scanner scanner = new Scanner(System.in);

    public BigliettiDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public Biglietti sceltaViaggio(Tessere tessera) {
        Biglietti titolo;
        GenericDAO ed = new GenericDAO(entityManager);
        System.out.println("Cosa vuoi acquistare? 1 BIGLIETTO 2 ABBONAMENTO");
        int scelta = scanner.nextInt();
        DistributoriDAO pn = new DistributoriDAO(entityManager);
        if (scelta == 1) {
            titolo = creaBiglietto();
            ed.save(titolo);
        } else {
            titolo = creaAbbonamento(tessera);
            ed.save(titolo);
        }

        return null;
    }
    public Abbonamento checkAbbonamento(UUID idAbbonamento){
        return entityManager.find(Abbonamento.class, idAbbonamento);
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
        TipologiaMezzo tipologiaMezzo;
        LocalDate dataEmissione = LocalDate.now();
        puntoVendita = sceltaVendita();
        System.out.println("Per quale mezzo? 1 AUTOBUS 2 TRAM");
        int scelta = scanner.nextInt();
        if (scelta == 1) {
            tipologiaMezzo =TipologiaMezzo.AUTOBUS;
        } else {
            tipologiaMezzo =TipologiaMezzo.TRAM;
        }
        Biglietti biglietto=new BigliettoSingolo(dataEmissione, puntoVendita,tipologiaMezzo);
        System.out.println("il tuo biglietto è stato emesso");
        return biglietto;

    }
    public Biglietti controllaValidità(Biglietti abbonamento) {
       LocalDate scadenza=abbonamento.getDataScadenza() ;
       if(scadenza.isBefore(LocalDate.now())){
           System.out.println("Abbonamento scaduto");
           System.out.println("Vuoi rinnovarlo?");
           System.out.println("1 SI");
           System.out.println("2 NO");
           int scelta = Integer.parseInt(scanner.nextLine());
           switch(scelta){
               case 1:
                   System.out.println("inserisci l'id del tuo abbonamento");
                   String input = scanner.nextLine();
                   UUID abbonamentoId = UUID.fromString(input);
                   Abbonamento abbonamento1 =checkAbbonamento(abbonamentoId);
                   rinnovoAbbonamento(abbonamento1);
                   break;
               case 2:
                   System.out.println("Uscita dal programma.");
                   break;
           }

       }else {
          System.out.println("L'abbonamento scade il "+abbonamento.getDataScadenza());
       }
        return null;
    }


    public Biglietti creaAbbonamento(Tessere tessera) {
        Abbonamento abbonamentoSalvato=null;
        LocalDate dataEmissione = LocalDate.now();
        Distributori puntoVendita;
        TipologiaAbbonamento tipoAbbonamento;
        puntoVendita = sceltaVendita();
        System.out.println("Che tipo di abbonamento vuoi acquistare? 1 MENSILE 2 ANNUALE");
        int scelta = scanner.nextInt();
        if (scelta == 1) {
            tipoAbbonamento = TipologiaAbbonamento.MENSILE;
        } else {
            tipoAbbonamento = TipologiaAbbonamento.ANNUALE;
        }
        Abbonamento abbonamento=new Abbonamento(dataEmissione, puntoVendita,tessera,tipoAbbonamento);
        GenericDAO ed = new GenericDAO(entityManager);
        ed.save(abbonamento);
        try {
          abbonamentoSalvato= ed.findById(Abbonamento.class,abbonamento.getId());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        System.out.println("il tuo abbonamento con id "+abbonamentoSalvato.getId()+" di tipo "+abbonamentoSalvato.getTipologiaAbbonamento()+" è stato creato con successo!!");
        return abbonamentoSalvato;

    }

    public Biglietti rinnovoAbbonamento (Abbonamento abbonamento){
        if(abbonamento.getTipologiaAbbonamento()==TipologiaAbbonamento.MENSILE){LocalDate rinnovoMese=abbonamento.setDataScadenza(LocalDate.now().plusMonths(1));}else{       LocalDate rinnovo=abbonamento.setDataScadenza(LocalDate.now().plusYears(1));};
        System.out.println("Abbonamento rinnovato fino al "+abbonamento.getDataScadenza());
        return abbonamento;
    }



}
