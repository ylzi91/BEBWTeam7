package yurilenzi;


import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import yurilenzi.dao.*;
import yurilenzi.entities.TipologiaMezzo;
import yurilenzi.entities.Utenti;
import yurilenzi.exceptions.NotFoundException;

import java.util.Scanner;
import java.util.UUID;

import java.util.Random;

public class ApplicationMenu {

    public static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("BEBWTeam7");
    public static EntityManager em = emf.createEntityManager();
    public static Scanner scanner = new Scanner(System.in);
    public static boolean continua = true;

    public static void main(String[] args) {

        GenericDAO gd = new GenericDAO(em);
        MenuDAO md = new MenuDAO(em);
        UtentiDAO ut = new UtentiDAO(em);
        TessereDAO te = new TessereDAO(em);
        BigliettiDAO bi = new BigliettiDAO(em);
        Utenti utente = ut.logIn();


        if (utente.isAutorizzazione()) {
            md.opzioniAmministratore();
        } else {
            md.opzioniUtente(utente);
        }
    }



}
