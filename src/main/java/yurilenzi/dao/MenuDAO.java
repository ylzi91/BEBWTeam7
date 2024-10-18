package yurilenzi.dao;

import jakarta.persistence.EntityManager;
import yurilenzi.Util;
import yurilenzi.entities.*;
import yurilenzi.exceptions.NothingGenException;

import java.util.List;
import java.util.Scanner;
import java.util.UUID;

import static yurilenzi.Application.scanner;
import static yurilenzi.Application.em;

public class MenuDAO {
    public final GenericDAO gd = new GenericDAO(em);
    public final MezziDAO mezziDAO = new MezziDAO(em);
    BigliettoSingoloDAO bsd=new BigliettoSingoloDAO(em);
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
                break;
            case 2:
                try{
                System.out.println("inserisci id biglietto");
                String input = scanner.nextLine();
                BigliettoSingolo biglietto = gd.findById(BigliettoSingolo.class, input);
                bsd.controllaValidità(biglietto);
            }
            catch (Exception e) {
                System.out.println(e.getMessage());
            }
            break;
            case 0:
                System.out.println("Uscita dal programma.");
                break;
        }}
    public void opzioniTessere(Utenti utente){
        System.out.println("1.Compra tessera");
        System.out.println("2.Controlla validità");
        System.out.println("0.Esci");
        int scelta2 = Integer.parseInt(scanner.nextLine());
        switch(scelta2){
            case 1:
                try{
                    tessereDAO.compraTessera(utente);
            }catch(Exception e){   System.out.println(e.getMessage());}
                 break;
            case 2:
                try{
                    tessereDAO.findTesseraByUtente(utente);
                    tessereDAO.controllaValidità(tessereDAO.findTesseraByUtente(utente));

                }catch (Exception e ){
                    System.out.println("Nessuna tessera trovata");
                    opzioniUtente(utente);
                }
                break;

            case 0:
                System.out.println("Uscita dal programma.");
                break;
            default:
                System.out.println("Opzione non valida");
        }
    }
    public void opzioniAbbonamenti(Utenti utente){
        System.out.println("1.Compra abbonamento");
        System.out.println("2.Controlla validità");
        System.out.println("0. Esci");
        int scelta2 = Integer.parseInt(scanner.nextLine());

        switch(scelta2){
            case 1:
                try {
                    Tessere tessera = tessereDAO.checkTessera(tessereDAO.findTesseraByUtente(utente).getIdTessera());
                    bigliettiDAO.creaAbbonamento(tessera);
                }
                catch (Exception e) {
                    System.out.println("Non hai una tessera");
                }
                break;
            case 2:
                try{
                   Abbonamento abbonamento = bigliettiDAO.checkAbbonamento(bigliettiDAO.findAbbonamentoByTessera(tessereDAO.findTesseraByUtente(utente)).getId());
                   bigliettiDAO.controllaValidità(abbonamento);

                }catch (Exception e){
                    System.out.println("L'abbonamento non esiste");
                }
                break;
            case 0:

                System.out.println("Uscita dal programma.");
                break;
        }}
    public void opzioniUtente(Utenti utente){
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
                    opzioniTessere(utente);
                    break;
                case 3:
                    opzioniAbbonamenti(utente);
                    break;
                case 0:
                    System.out.println("Uscita dal programma.");
                    break opzioniUtente;
            }
        }


    }
}
