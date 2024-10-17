package yurilenzi.dao;

import jakarta.persistence.EntityManager;
import yurilenzi.Util;
import yurilenzi.entities.TipologiaMezzo;
import yurilenzi.exceptions.NothingGenException;

import static yurilenzi.Application.scanner;
import static yurilenzi.Application.em;

public class MenuDAO {
    public final GenericDAO gd = new GenericDAO(em);
    public final MezziDAO mezziDAO = new MezziDAO(em);
    public void opzioniAmministratore() {
        amministratore:
        while(true){
            System.out.println("Opzioni Amministratore");
            System.out.println("1. Controlla distributori attivi");
            System.out.println("2. Controlla distributori fuori servizio");
            System.out.println("3. Controlla Bus in servizio");
            System.out.println("4. Controlla Bus in manutenzione");
            System.out.println("5. Controlla numero di manutenzioni di un mezzo");
            System.out.println("6. Controlla bus in sostituzione");
            System.out.println("7. Aggiungi mezzo in sostituzione");
            System.out.println("0. Esci");

            int scelta = Integer.parseInt(scanner.nextLine());

            switch (scelta) {
                case 1:
                    gd.distributoriAttivi().forEach(System.out::println);
                    break;
                case 2:
                    break;
                case 3:
                    try {
                        System.out.println("------------------Mezzi in Servizio---------------");
                        mezziDAO.mezziInServizio().forEach(System.out::println);
                        System.out.println("---------------Fine mezzi in servizio-------------");
                    } catch (NothingGenException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 4:
                    try {
                        System.out.println("------------------Mezzi in Manuteznione---------------");
                        mezziDAO.dettagliMezziInManutenzione().forEach(System.out::println);
                        System.out.println("---------------Fine mezzi in Manutenzione-------------");
                    } catch (NothingGenException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 5:
                    break;
                case 6:
                    System.out.println("------------------Bus in sostituzione-----------------");
                    try {
                        mezziDAO.mezziDiponibili().forEach(System.out::println);
                    } catch (NothingGenException e) {
                        throw new RuntimeException(e);
                    }
                    System.out.println("-----------------Bus in sostituzione------------------");
                    break;
                case 7:
                    int sceltaMezzo;
                    System.out.println("Quale mezzo vuoi aggiungere?");
                    System.out.println("1.Autobus");
                    System.out.println("2.Tram");
                    sceltaMezzo = Integer.parseInt(scanner.nextLine());
                    switch (sceltaMezzo){
                        case 1:
                            Util.aggiungiMezzi(1, TipologiaMezzo.AUTOBUS);
                            break;
                        case 2:
                            Util.aggiungiMezzi(1, TipologiaMezzo.TRAM);
                            break;
                        default:
                            System.out.println("Scelta non valida");
                    }

                    break;
                case 0:
                    System.out.println("Uscita dal menu amministratore.");
                    break amministratore;
                default:
                    System.out.println("Scelta non valida.");
                    break;
            }
        }
    }
    public void opzioniBiglietti(){
        System.out.println("1.Compra biglietto");
        System.out.println("2.Controlla validità");
        System.out.println("0. Esci");
        int scelta2 = Integer.parseInt(scanner.nextLine());

        switch(scelta2){
            case 1:{}
            case 2:{}
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
        System.out.println("3.Rinnova abbonamento");
        System.out.println("0. Esci");
        int scelta2 = Integer.parseInt(scanner.nextLine());

        switch(scelta2){
            case 1:
            case 2:
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
