package yurilenzi;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import yurilenzi.dao.*;
import yurilenzi.entities.*;
import yurilenzi.exceptions.NotFoundException;
import yurilenzi.exceptions.NothingTratteException;

import java.util.List;
import java.util.Scanner;
import java.util.UUID;

import java.util.Random;

public class Application {

    public static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("BEBWTeam7");
    public static EntityManager em = emf.createEntityManager();
    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Hello World!");


        TrattaDAO trattaDAO = new TrattaDAO(em);
        try {
            trattaDAO.tratteDisponibili().forEach(System.out::println);
        } catch (NothingTratteException e) {
            System.out.println(e.getMessage());
        }


    }
}
