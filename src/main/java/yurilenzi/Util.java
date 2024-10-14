package yurilenzi;

import com.github.javafaker.Faker;
import jakarta.persistence.EntityManager;
import yurilenzi.dao.GenericDAO;
import yurilenzi.entities.Tratte;
import yurilenzi.entities.Utenti;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.function.Supplier;

public class Util {
    public static Faker faker = new Faker(Locale.ITALY);
    public static void saveFakeIUser(int numberOfUser, EntityManager em){

        Supplier<Utenti> utentiSupplier = () -> {
            return new Utenti(faker.name().firstName(), faker.name().lastName(), false);
        };
        GenericDAO genericDAO = new GenericDAO(em);
        for (int i = 0; i < numberOfUser; i++) {
            genericDAO.save(utentiSupplier.get());
        }
    }

    public static void saveFakeTratte(int numberOfTratte, EntityManager em){

        Supplier<Tratte> tratteSupplier = () -> {
            double randomTrattaTeorica = new Random().nextDouble(10,60);
            double randomTrattaEffettiva = new Random().nextDouble(randomTrattaTeorica, randomTrattaTeorica + 20);
            return new Tratte(faker.address().streetAddress(), faker.address().streetAddress(), randomTrattaTeorica, randomTrattaEffettiva);
        };
        GenericDAO genericDAO = new GenericDAO(em);
        for (int i = 0; i < numberOfTratte; i++) {
            genericDAO.save(tratteSupplier.get());
        }
    }

}
