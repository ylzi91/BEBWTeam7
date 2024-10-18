package yurilenzi.dao;


import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import yurilenzi.entities.Abbonamento;
import yurilenzi.entities.Biglietti;
import yurilenzi.entities.Tessere;
import yurilenzi.entities.Utenti;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class TessereDAO {
    public final EntityManager entityManager;
    Scanner scanner = new Scanner(System.in);

    public TessereDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public Tessere checkTessera(UUID idTessera){
        return entityManager.find(Tessere.class, idTessera);
}

    public Tessere findTesseraByUtente(Utenti utente) {
        TypedQuery<Tessere> query = entityManager.createQuery("SELECT t FROM Tessere t WHERE t.utenti = :utente", Tessere.class);
        query.setParameter("utente", utente);
        return query.getSingleResult();
    }


    public Tessere creaTessera(Utenti utente) {
        LocalDate dataEmissione = LocalDate.now();
        LocalDate dataRinnovo = dataEmissione.plusYears(1);
        Tessere tessera = new Tessere(utente, dataEmissione, dataRinnovo);
        GenericDAO ed = new GenericDAO(entityManager);
        ed.save(tessera);
        System.out.println("Tessera creata, id tessera: "+tessera.getIdTessera()+" scadenza: "+tessera.getDataScadenza());
        return findTesseraByUtente(utente);

    }
    public Tessere compraTessera(Utenti utente){
        if (utente.getTessere()==null){     creaTessera(utente); }
        else{
            System.out.println("Hai già una tessera");
            System.out.println(utente.getTessere());
        }

        return new Tessere();
    }
    public Tessere controllaValidità(Tessere tessera) {
        LocalDate scadenza=tessera.getDataScadenza() ;
        if(scadenza.isBefore(LocalDate.now())){
            System.out.println("Tessera scaduto");
            System.out.println("Vuoi rinnovarla?");
            System.out.println("1 SI");
            System.out.println("2 NO");
            int scelta = Integer.parseInt(scanner.nextLine());
            switch(scelta){
                case 1:
                    System.out.println("inserisci l'id della tua tessera");
                    String input = scanner.nextLine();
                    UUID tesseraId = UUID.fromString(input);
                    tessera.setDataScadenza(LocalDate.now().plusYears(1));
                    break;
                case 2:
                    System.out.println("Uscita dal programma.");
                    break;
            }

        }else {
            System.out.println("La tessera scade il "+tessera.getDataScadenza());
        }
        return null;
    }




}

