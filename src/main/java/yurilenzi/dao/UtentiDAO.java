package yurilenzi.dao;






import jakarta.persistence.EntityManager;
import yurilenzi.entities.Utenti;
import yurilenzi.exceptions.NotFoundException;

import java.util.Scanner;


public class UtentiDAO {

    public final EntityManager entityManager;
    Scanner scanner = new Scanner(System.in);

    public UtentiDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public Utenti logIn()  {
        Utenti utente;
        GenericDAO ed = new GenericDAO(entityManager);
        System.out.println("Sei già registrato? 1 SI 2 NO");
        int risposta = scanner.nextInt();

        if (risposta == 1) {
            System.out.println("Inserisci l'ID UTENTE");
            String rispostaId = scanner.next();
            try {
                utente = ed.findById(Utenti.class, rispostaId);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } else {
            utente = creaUtente();
            ed.save(utente);
            try {
                utente = ed.findById(Utenti.class, utente.getUtenteId());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

        }

        return utente;
    }


    public Utenti creaUtente() {
        System.out.println("Sei un amministratore? 1 SI 2 NO");
        int abilitazione = scanner.nextInt();
        boolean abilitazioneUtente = false;
        if (abilitazione == 1) {
            abilitazioneUtente = true;
        }
        System.out.println("Inserisci il tuo nome");
        String nome = scanner.next();
        System.out.println("Inserisci il tuo cognome");
        String cognome = scanner.next();
        return new Utenti( nome, cognome,abilitazioneUtente);

    }
}

