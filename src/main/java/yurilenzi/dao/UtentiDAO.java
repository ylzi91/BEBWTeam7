package yurilenzi.dao;






import jakarta.persistence.EntityManager;
import yurilenzi.entities.Utenti;
import yurilenzi.exceptions.NotFoundException;
import  static yurilenzi.Application.scanner;

import java.util.Scanner;


public class UtentiDAO {

    public final EntityManager entityManager;

    public UtentiDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public Utenti logIn()  {
        Utenti utente;
        GenericDAO ed = new GenericDAO(entityManager);
        System.out.println("Sei già registrato? 1 SI 2 NO");
        int risposta = Integer.parseInt(scanner.nextLine());

        if (risposta == 1) {
            System.out.println("Inserisci l'ID UTENTE");
            String rispostaId = scanner.nextLine();
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
        int abilitazione = Integer.parseInt(scanner.nextLine());
        boolean abilitazioneUtente = false;
        if (abilitazione == 1) {
            abilitazioneUtente = true;
        }
        System.out.println("Inserisci il tuo nome");
        String nome = scanner.nextLine();
        System.out.println("Inserisci il tuo cognome");
        String cognome = scanner.nextLine();
        return new Utenti( nome, cognome,abilitazioneUtente);

    }
}

