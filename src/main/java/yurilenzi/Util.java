package yurilenzi;

import com.github.javafaker.Faker;
import jakarta.persistence.EntityManager;
import yurilenzi.dao.BigliettoSingoloDAO;
import yurilenzi.dao.GenericDAO;
import yurilenzi.dao.MezziDAO;
import yurilenzi.dao.TrattaDAO;
import yurilenzi.entities.*;
import yurilenzi.exceptions.NotFoundException;

import java.time.LocalDate;
import java.util.Locale;
import java.util.Random;
import java.util.function.Supplier;

public class Util {
    public static Faker faker = new Faker(Locale.ITALY);
    public static void saveFakeIUser(int numberOfUser, EntityManager em){

        Supplier<Utenti> utentiSupplier = () -> {
            Boolean random = new Random().nextBoolean();
            return new Utenti(faker.name().firstName(), faker.name().lastName(), random);
        };
        GenericDAO genericDAO = new GenericDAO(em);
        for (int i = 0; i < numberOfUser; i++) {
            genericDAO.save(utentiSupplier.get());
        }
    }

    public static void saveFakeTratte(int numberOfTratte, EntityManager em){

        Supplier<Tratte> tratteSupplier = () -> {
            double randomTrattaTeorica = new Random().nextDouble(10,60);
            double randomTrattaEffettiva = new Random().nextDouble(randomTrattaTeorica -10, randomTrattaTeorica + 20);
            return new Tratte(faker.address().streetAddress(), faker.address().streetAddress(), randomTrattaTeorica, randomTrattaEffettiva);
        };
        GenericDAO genericDAO = new GenericDAO(em);
        for (int i = 0; i < numberOfTratte; i++) {
            genericDAO.save(tratteSupplier.get());
        }
    }

    public static void SaveFakeDistributori(int numeroDistributori, EntityManager em){
        Supplier<DistributoreAutomatico> distributoreAutomaticoSupplier = () ->{
            Random random = new Random();
            return new DistributoreAutomatico(random.nextBoolean());
        };

        Supplier<RivenditoreFisico> rivenditoreFisicoSupplier = () -> {
            return new RivenditoreFisico(faker.name().title());
        };
        GenericDAO genericDAO = new GenericDAO(em);
        for (int i = 0; i < numeroDistributori; i++) {
            genericDAO.save(distributoreAutomaticoSupplier.get());
            genericDAO.save(rivenditoreFisicoSupplier.get());

        }
    }

    public static void saveBigliettoSingolo(EntityManager entityManager, String idDistributore, TipologiaMezzo tipologiaMezzo) throws NotFoundException {
        GenericDAO genericDAO = new GenericDAO(entityManager);
        BigliettoSingolo bigliettoSingolo = new BigliettoSingolo(LocalDate.now(), genericDAO.findById(Ditributori.class, idDistributore), tipologiaMezzo);
        genericDAO.save(bigliettoSingolo);
    }

    public static void SaveMezzi(EntityManager em){

        GenericDAO genericDAO = new GenericDAO(em);
        TrattaDAO trattaDAO = new TrattaDAO(em);

        trattaDAO.getListTratte().forEach(tratte -> {
            int random = new Random().nextInt(0,2);

            TipologiaMezzo[] mezzi = TipologiaMezzo.values();
            TipologiaMezzo mezzoTrovato = mezzi[random];

            Supplier<Mezzi> mezziSupplier = () -> new Mezzi(mezzoTrovato, true, tratte);
            genericDAO.save(mezziSupplier.get());
        });
    }

    public static void vidimaBiglietto(EntityManager em , String idMezzo, String idBiglietto) throws NotFoundException {
        GenericDAO genericDAO = new GenericDAO(em);
        BigliettoSingoloDAO bigliettoSingoloDAO = new BigliettoSingoloDAO(em);
        Mezzi mezzoTrov = genericDAO.findById(Mezzi.class, idMezzo);
        BigliettoSingolo bigliettoSingoloTrov = genericDAO.findById(BigliettoSingolo.class, idBiglietto);
        bigliettoSingoloTrov.setGiornoDiVidimatura();
        if(bigliettoSingoloTrov.getTipologiaMezzo() == mezzoTrov.getTipologiaMezzo())
            if(!bigliettoSingoloTrov.isConvalidato())
                bigliettoSingoloDAO.upadateVidimato(bigliettoSingoloTrov, mezzoTrov);
            else
                System.out.println("Hai già timbrato questo biglietto");
        else
            System.out.println("Non puoi salire su questo mezzo");
    }

    public static void salvaNuovaManutenzione(EntityManager em, String idMezzo) throws NotFoundException {
        GenericDAO genericDAO = new GenericDAO(em);

        int random = new Random().nextInt(0,4);
        Mezzi mezzoTrov = genericDAO.findById(Mezzi.class, idMezzo);
        MezziDAO mezziDAO = new MezziDAO(em);
        mezziDAO.mezzoInManutenzione(mezzoTrov);

        Supplier<Manutenzioni> manutenzioniSupplier = () -> new Manutenzioni(LocalDate.now(), LocalDate.now().plusDays(random), mezzoTrov);

        genericDAO.save(manutenzioniSupplier.get());

    }

}
