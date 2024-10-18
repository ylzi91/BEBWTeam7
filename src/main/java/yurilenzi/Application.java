package yurilenzi;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import yurilenzi.dao.*;
import yurilenzi.entities.TipologiaMezzo;
import yurilenzi.entities.Tratte;
import yurilenzi.exceptions.NotFoundException;
import yurilenzi.exceptions.NothingGenException;

import java.util.Scanner;

public class Application {

    public static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("BEBWTeam7");
    public static EntityManager em = emf.createEntityManager();
    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Hello World!");
        AvvioDAO.avvio();







    }
}
