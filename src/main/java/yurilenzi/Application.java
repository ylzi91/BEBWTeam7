package yurilenzi;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import yurilenzi.dao.AvvioDAO;
import yurilenzi.dao.GenericDAO;
import yurilenzi.dao.MenuDAO;
import yurilenzi.dao.MezziDAO;
import yurilenzi.entities.Ditributori;
import yurilenzi.entities.Mezzi;
import yurilenzi.entities.TipologiaMezzo;
import yurilenzi.entities.Utenti;
import yurilenzi.exceptions.NotFoundException;

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
        GenericDAO genericDAO = new GenericDAO();
        List<Mezzi> mezzis = genericDAO.listagenerica(Mezzi.class);

        for (int i = 0; i < mezzis.size(); i++) {
            System.out.println(i+1 + ")" + mezzis.get(i));
        }
        AvvioDAO.avvio();

    }
}
