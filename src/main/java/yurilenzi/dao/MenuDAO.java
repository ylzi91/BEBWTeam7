package yurilenzi.dao;

import jakarta.persistence.EntityManager;
import yurilenzi.Util;
import yurilenzi.entities.Abbonamento;
import yurilenzi.entities.Tessere;
import yurilenzi.entities.TipologiaMezzo;
import yurilenzi.entities.Utenti;
import yurilenzi.exceptions.NothingGenException;

import java.util.Scanner;
import java.util.UUID;

import static yurilenzi.Application.scanner;
import static yurilenzi.Application.em;

public class MenuDAO {
    public final GenericDAO gd = new GenericDAO(em);
    public final MezziDAO mezziDAO = new MezziDAO(em);
    BigliettiDAO bigliettiDAO=new BigliettiDAO(em);
    UtentiDAO utentiDAO = new UtentiDAO(em);
    TessereDAO tessereDAO = new TessereDAO(em);


    public void opzioniBiglietti(){
        System.out.println("1.Compra biglietto");
        System.out.println("2.Controlla validità");
        System.out.println("0. Esci");
        int scelta2 = Integer.parseInt(scanner.nextLine());

        switch(scelta2){
            case 1:try{bigliettiDAO.creaBiglietto();}catch (Exception e){System.out.println(e.getMessage());}
            case 2:
            case 0:
                System.out.println("Uscita dal programma.");
                break;
        }}
    public void opzioniTessere(){
        System.out.println("1.Compra tessera");
        System.out.println("2.Controlla validità");
        System.out.println("3.Rinnova tessera");
        System.out.println("0.Esci");
        int scelta2 = Integer.parseInt(scanner.nextLine());
        switch(scelta2){
            case 1:
            case 2:
            case 3:

            case 0:

                System.out.println("Uscita dal programma.");
                break;
        }
    }
    public void opzioniAbbonamenti(){
        System.out.println("1.Compra abbonamento");
        System.out.println("2.Controlla validità");
        System.out.println("0. Esci");
        int scelta2 = Integer.parseInt(scanner.nextLine());

        switch(scelta2){
            case 1:
                try {
                    System.out.println("inserisci l'id della tua tessera");
                    String input = scanner.nextLine();
                    UUID tessereId = UUID.fromString(input);
                    Tessere tessera =tessereDAO.checkTessera(tessereId);
                    if (tessera!=null) {

                        bigliettiDAO.creaAbbonamento(tessera);

                        } else {
                        System.out.println("Tessera non esistente");
                        System.out.println("inserisci il tuo id");
                        String input2 = scanner.nextLine();
                        UUID utenteId = UUID.fromString(input2);
                        Utenti utente =gd.verificaId(utenteId);
                        tessereDAO.creaTessera(utente);
                        }
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
                break;
            case 2:
                try{
                System.out.println("inserisci l'id del tuo abbonamento");
                String input = scanner.nextLine();
                UUID abbonamentoId = UUID.fromString(input);
                Abbonamento abbonamento =bigliettiDAO.checkAbbonamento(abbonamentoId);
                if (abbonamento!=null){
                    bigliettiDAO.controllaValidità(abbonamento);
                }
                else{
                    opzioniAbbonamenti();
                }
                }catch (Exception e ){    System.out.println(e.getMessage());}

            case 3:
            case 0:

                System.out.println("Uscita dal programma.");
                break;
        }}
    public void opzioniUtente(){
        opzioniUtente:
        while (true){
            System.out.println("Opzioni Utente");
            System.out.println("1. Biglietti");
            System.out.println("2. Tessere");
            System.out.println("3. Abbonamenti");
            System.out.println("0. Esci");
            int scelta = Integer.parseInt(scanner.nextLine());
            switch (scelta){
                case 1:
                    opzioniBiglietti();
                    break;

                case 2:
                    opzioniTessere();
                    break;
                case 3:
                    opzioniAbbonamenti();
                    break;
                case 0:
                    System.out.println("Uscita dal programma.");
                    break opzioniUtente;
            }
        }


    }
}
