package yurilenzi;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import yurilenzi.dao.GenericDAO;
import yurilenzi.entities.TipologiaMezzo;
import yurilenzi.entities.Utenti;
import yurilenzi.exceptions.NotFoundException;

import java.util.Scanner;
import java.util.UUID;

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
    }

}
