package yurilenzi.dao;

import yurilenzi.entities.Utenti;

import java.util.UUID;

import static yurilenzi.Application.em;
import static yurilenzi.Application.scanner;


public class AvvioDAO {
    public static GenericDAO gd = new GenericDAO(em);
    public static MenuDAO md = new MenuDAO();
    public static void avvio(){
        menu:
        while (true) {
            System.out.println("1 Accedi al menù.");
            System.out.println("0.Esci.");
            int scelta = scanner.nextInt();
            scanner.nextLine();

            switch (scelta){
                case 1:
                    System.out.println("Inserisci il tuo id");
                    String input = scanner.nextLine();
                    UUID utenteId = UUID.fromString(input);
                    Utenti utente =gd.verificaId(utenteId);
                    if (utente!=null) {
                        if (utente.isAutorizzazione()) {
                            md.opzioniAmministratore();
                        } else {
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
