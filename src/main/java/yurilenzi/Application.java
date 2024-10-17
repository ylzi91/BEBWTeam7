package yurilenzi;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import yurilenzi.dao.GenericDAO;
import yurilenzi.dao.MenuDAO;
import yurilenzi.dao.MezziDAO;
import yurilenzi.entities.TipologiaMezzo;
import yurilenzi.entities.Utenti;
import yurilenzi.exceptions.NotFoundException;

import java.util.Scanner;
import java.util.UUID;

import java.util.Random;

public class Application {

    public static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("BEBWTeam7");
    public static EntityManager em = emf.createEntityManager();
    public static Scanner scanner=new Scanner(System.in);
    public static boolean continua = true;
    public static void main(String[] args) {


       // Util.saveFakeIUser(20, em);
       // Util.saveFakeTratte(20,em);
       // Util.SaveFakeDistributori(30, em);
        //try {
        //    Util.saveBigliettoSingolo(em, "d613f8d2-45cc-4f2f-8e83-aa22b0398686", TipologiaMezzo.AUTOBUS);
        //} catch (NotFoundException e) {
        //    System.out.println(e.getMessage());
        //}

        GenericDAO gd = new GenericDAO(em);
        MenuDAO md=new MenuDAO(em);
        while (continua) {
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
                        //md.opzioniUtente();
                    }
                } else {
                    System.out.println("Utente non trovato.");
                };
                break;
                case 0:
                    continua = false;
                    System.out.println("Uscita dal programma.");
                    break;
                default:
                    System.out.println("Inserisci un carattere valido,Riprova");

            }

        }
        scanner.close();

//        try {
//            Util.saveBigliettoSingolo(em, "d613f8d2-45cc-4f2f-8e83-aa22b0398686", TipologiaMezzo.AUTOBUS);
//        } catch (NotFoundException e) {
//            System.out.println(e.getMessage());
//        }
//        Util.SaveMezzi(em);

//        try {
//            Util.vidimaBiglietto(em, "07c685ba-be3a-4436-8e69-4663afa78e0e", "b4489c97-63ec-4a2f-863f-e03b9cee0e14");
//        } catch (NotFoundException e) {
//            System.out.println(e.getMessage());
//        }

//        try {
//            Util.salvaNuovaManutenzione(em, "07c685ba-be3a-4436-8e69-4663afa78e0e");
//        } catch (NotFoundException e) {
//            System.out.println(e.getMessage());
//        }
//
//        Util.aggiungiMezziInSostituzione(4, em, TipologiaMezzo.AUTOBUS);


//        try {
//            Util.aggiungiTrattaAdUnMezzo(em,"ec3383b9-a2ae-4ada-ac5d-0aa2d46f0487", "06129651-f2e9-4008-bb33-a33afb0949ac");
//        } catch (NotFoundException e) {
//            throw new RuntimeException(e);
//        }

//        try {
//            Util.salvaNuovaManutenzione(em, "16e4c709-5bbf-449b-b0e3-6f4f28648447");
//        } catch (NotFoundException e) {
//            throw new RuntimeException(e);
//        }

//        try {
//            Util.calcolaMediaMezzoById(em,"046a8a80-58f9-43dc-bc5d-42fed506e2cb");
//        } catch (NotFoundException e) {
//            throw new RuntimeException(e);
//        }
    }
}
