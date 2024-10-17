package yurilenzi.dao;

import jakarta.persistence.EntityManager;
import yurilenzi.entities.Utenti;

import java.util.Scanner;

import static yurilenzi.Application.em;
import static yurilenzi.Application.scanner;
import static yurilenzi.Application.continua;

public class MenuDAO {
    public final EntityManager entityManager;


    public MenuDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
    GenericDAO gd=new GenericDAO(em);
    boolean continua1=true;

    public void opzioniAmministratore() {
        menu1:
        while(continua1){
            System.out.println("Opzioni Amministratore");
            System.out.println("1. Controlla distributori attivi");
            System.out.println("2. Controlla distributori fuori servizio");
            System.out.println("3. Controlla Bus in servizio");
            System.out.println("4. Controlla Bus in manutenzione");
            System.out.println("5. Controlla numero di manutenzioni");
            System.out.println("0. Esci");

            int scelta = scanner.nextInt();
            scanner.nextLine();

            switch (scelta) {
                case 1:
                    gd.distributoriAttivi();
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    break;
                case 0:
                    continua1=false;
                    System.out.println("Uscita dal programma.");
                    break menu1;
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
        int scelta2 = scanner.nextInt();
        scanner.nextLine();
        switch(scelta2){
            case 1:{}
            case 2:{}
            case 0:
                continua=false;
                System.out.println("Uscita dal programma.");
                break;
        }}
    public void opzioniTessere(){
        System.out.println("1.Compra tessera");
        System.out.println("2.Controlla validità");
        System.out.println("3. rinnova tessera");
        System.out.println("0. Esci");
        int scelta2 = scanner.nextInt();
        scanner.nextLine();
        switch(scelta2){
            case 1:
            case 2:
            case 3:
            case 0:
                continua=false;
                System.out.println("Uscita dal programma.");
                break;
        }
    }
    public void opzioniAbbonamenti(){
        System.out.println("1.Compra abbonamento");
        System.out.println("2.Controlla validità");
        System.out.println("3.Rinnova abbonamento");
        System.out.println("0. Esci");
        int scelta2 = scanner.nextInt();
        scanner.nextLine();
        switch(scelta2){
            case 1:
            case 2:
            case 3:
            case 0:
                continua=false;
                System.out.println("Uscita dal programma.");
                break;
        }}


    public void opzioniUtente(Utenti utente){

        System.out.println("Opzioni Utente");
        System.out.println("1 Acquista titolo di viaggio");
        System.out.println("2 Controlla validità");
        int scelta3 = scanner.nextInt();
        switch(scelta3){
            case 1:BigliettiDAO ed = new BigliettiDAO(entityManager);
                ed.sceltaViaggio(utente);
                break;
            case 2:BigliettiDAO et = new BigliettiDAO(entityManager);
                et.sceltaControllo(utente);
                break;
            case 0:
                continua=false;
                System.out.println("Uscita dal programma.");
                break;
        }}



    }

