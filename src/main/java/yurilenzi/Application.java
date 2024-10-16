package yurilenzi;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import yurilenzi.dao.GenericDAO;
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
    public static void main(String[] args) {
        System.out.println("Hello World!");
        //Util.saveFakeIUser(20, em);
        //Util.saveFakeTratte(20,em);
        //Util.SaveFakeDistributori(30, em);
        //try {
        //    Util.saveBigliettoSingolo(em, "d613f8d2-45cc-4f2f-8e83-aa22b0398686", TipologiaMezzo.AUTOBUS);
        //} catch (NotFoundException e) {
        //    System.out.println(e.getMessage());
        //}
        Scanner scanner=new Scanner(System.in);
        GenericDAO gd = new GenericDAO(em);
        System.out.print("Inserisci il tuo ID: ");
        String input = scanner.nextLine();
        UUID utenteId = UUID.fromString(input);
        Utenti utente =gd.verificaId(utenteId);

        if (utente!=null) {
            if (utente.isAutorizzazione()) {
                System.out.println("Opzioni Amministratore");
                System.out.println("1.Controlla distributori attivi");
                System.out.println("2. Controlla distributori fuori servizio");
                System.out.println("3. Controlla Bus in servizio");
                System.out.println("4. Controlla Bus in manutenzione");
                System.out.println("5.Controlla numero di manutenzioni");
                int scelta = scanner.nextInt();
                scanner.nextLine();
                switch (scelta){
                    case 1:{
                        gd.distributoriAttivi();

                    };
                    case 2:{

                    };
                    case 3:{

                    };
                    case 4:{

                    };
                    case 5:{

                    };
                }
            } else {
                System.out.println("Opzioni Utente");
                System.out.println("1. Biglietti");
                System.out.println("2. Tessere");
                System.out.println("3. Abbonamenti");
                int scelta = scanner.nextInt();
                scanner.nextLine();
                switch (scelta){
                    case 1:{
                        System.out.println("1.Compra biglietto");
                        System.out.println("2.Controlla validità");
                        int scelta2 = scanner.nextInt();
                        scanner.nextLine();
                        switch(scelta2){
                            case 1:{}
                            case 2:{}
                        }

                    };
                    case 2:{
                        System.out.println("1.Compra tessera");
                        System.out.println("2.Controlla validità");
                        System.out.println("3. rinnova tessera");
                        int scelta2 = scanner.nextInt();
                        scanner.nextLine();
                        switch(scelta2){
                            case 1:{}
                            case 2:{}
                            case 3:{}
                        }

                    };
                    case 3:{
                        System.out.println("1.Compra abbonamento");
                        System.out.println("2.Controlla validità");
                        System.out.println("3.Rinnova abbonamento");
                        int scelta2 = scanner.nextInt();
                        scanner.nextLine();
                        switch(scelta2){
                            case 1:{}
                            case 2:{}
                            case 3:{}
                        }

                    };
                }
            }
        } else {
            System.out.println("Utente non trovato.");
        }

        scanner.close();
//        Util.saveFakeIUser(20, em);
//        Util.saveFakeTratte(20,em);
//        Util.SaveFakeDistributori(30, em);
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

        try {
            Util.calcolaMediaMezzoById(em,"046a8a80-58f9-43dc-bc5d-42fed506e2cb");
        } catch (NotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
