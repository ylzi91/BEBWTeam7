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
            double randomTrattaTeorica = new Random().nextDouble(10,30);
            int randomNumeroGiri = new Random().nextInt(5,20);
            double randomTrattaEffettiva = new Random().nextDouble((randomTrattaTeorica * randomNumeroGiri)-10,(randomTrattaTeorica * randomNumeroGiri)+10);
            return new Tratte(faker.address().streetAddress(), randomTrattaEffettiva, randomTrattaTeorica, faker.address().streetAddress(), randomNumeroGiri);
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
        BigliettoSingolo bigliettoSingolo = new BigliettoSingolo(LocalDate.now(), genericDAO.findById(Ditributori.class, idDistributore), false, tipologiaMezzo);
        BigliettoSingolo bigliettoSingolo2 = new BigliettoSingolo(LocalDate.now(), genericDAO.findById(Ditributori.class, idDistributore), true, tipologiaMezzo);
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

    public static <T> void aggiungiMezziInSostituzione(int numeroMezzi, EntityManager em,T tipoMezzo){
        GenericDAO genericDAO = new GenericDAO(em);
        for (int i = 0; i < numeroMezzi; i++) {
            Supplier<Mezzi> mezziSupplier = () -> new Mezzi((TipologiaMezzo) tipoMezzo, false, null);
            genericDAO.save(mezziSupplier.get());
        }
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
        mezziDAO.mezzoFuoriServizio(mezzoTrov);

        Supplier<Manutenzioni> manutenzioniSupplier = () -> new Manutenzioni(LocalDate.now(), LocalDate.now().plusDays(random), mezzoTrov);


        genericDAO.save(manutenzioniSupplier.get());
    }


    public static void aggiungiTrattaAdUnMezzo(EntityManager em, String idMezzo, String idTratta) throws NotFoundException {
        GenericDAO genericDAO = new GenericDAO(em);
        MezziDAO mezziDAO = new MezziDAO(em);
        Mezzi mezzoTrov = genericDAO.findById(Mezzi.class, idMezzo);
        Tratte trattaTrov = genericDAO.findById(Tratte.class, idTratta);
        if(mezzoTrov.getTratte() == null){
            mezziDAO.setTratta(mezzoTrov, trattaTrov);
        }
        else System.out.println("Il mezzo ha già una tratta");
    }


    public static void calcolaMediaMezzoById(EntityManager em, String idMezzo) throws NotFoundException {
        GenericDAO genericDAO = new GenericDAO(em);
        MezziDAO mezziDAO = new MezziDAO(em);
        Mezzi mezzoTrovato = genericDAO.findById(Mezzi.class, idMezzo);
        System.out.println(mezziDAO.calcolaTempoMedio(mezzoTrovato));
    }

}
