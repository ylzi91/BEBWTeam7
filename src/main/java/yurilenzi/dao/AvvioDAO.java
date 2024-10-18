package yurilenzi.dao;

import yurilenzi.entities.Utenti;

import java.util.UUID;

import static yurilenzi.Application.em;
import static yurilenzi.Application.scanner;


public class AvvioDAO {
    public static GenericDAO gd = new GenericDAO(em);
    public static MenuDAO md = new MenuDAO();
    public static AmministratoreDAO amministratoreDAO = new AmministratoreDAO();
    public static UtentiDAO utentiDAO = new UtentiDAO(em);
    public static void avvio(){
        menu:
        while (true) {
            System.out.println("1 Accedi al menù.");
            System.out.println("0.Esci.");
            int scelta = scanner.nextInt();
            scanner.nextLine();

            switch (scelta){
                case 1:

                    Utenti utente = utentiDAO.logIn();
                    if (utente!=null) {
                        if (utente.isAutorizzazione()) {
                            System.out.println("Benvenut* " + utente.getNome() + " " + utente.getCognome());
                            amministratoreDAO.opzioniAmministratore();
                        } else {
                            System.out.println("Benvenut*" + utente.getNome() + " " + utente.getCognome());
                            md.opzioniUtente();
                        }
                    } else {
                        System.out.println("Utente non trovato.");
                    };
                    break;
                case 0:
                    System.out.println("Uscita dal programma.");
                    break menu;
                default:
                    System.out.println("Inserisci un carattere valido,Riprova");
            }

        }
    }


}
