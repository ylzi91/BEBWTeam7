/*
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

import static yurilenzi.Application.em;

public class Util {


    public static Faker faker = new Faker(Locale.ITALY);
    public static GenericDAO genericDAO = new GenericDAO();
    public static MezziDAO mezziDAO = new MezziDAO(em);
    public static TrattaDAO trattaDAO = new TrattaDAO(em);
    public static BigliettoSingoloDAO bigliettoSingoloDAO = new BigliettoSingoloDAO(em);


    public static void saveFakeIUser(int numberOfUser){
        Supplier<Utenti> utentiSupplier = () -> {
            Boolean random = new Random().nextBoolean();
            return new Utenti(faker.name().firstName(), faker.name().lastName(), random);
        };

        for (int i = 0; i < numberOfUser; i++) {
            genericDAO.save(utentiSupplier.get());
        }
    }

    public static void saveFakeTratte(int numberOfTratte){

        Supplier<Tratte> tratteSupplier = () -> {
            double randomTrattaTeorica = new Random().nextDouble(10,30);
            int randomNumeroGiri = new Random().nextInt(5,20);
            double randomTrattaEffettiva = new Random().nextDouble((randomTrattaTeorica * randomNumeroGiri)-10,(randomTrattaTeorica * randomNumeroGiri)+10);
            return new Tratte(faker.address().streetAddress(), randomTrattaEffettiva, randomTrattaTeorica, faker.address().streetAddress(), randomNumeroGiri);
        };
        for (int i = 0; i < numberOfTratte; i++) {
            genericDAO.save(tratteSupplier.get());
        }
    }

    public static void SaveFakeDistributori(int numeroDistributori){
        Supplier<DistributoreAutomatico> distributoreAutomaticoSupplier = () ->{
            Random random = new Random();
            return new DistributoreAutomatico(random.nextBoolean());
        };

        Supplier<RivenditoreFisico> rivenditoreFisicoSupplier = () -> {
            return new RivenditoreFisico(faker.name().title());
        };

        for (int i = 0; i < numeroDistributori; i++) {
            genericDAO.save(distributoreAutomaticoSupplier.get());
            genericDAO.save(rivenditoreFisicoSupplier.get());

        }
    }

    public static void saveBigliettoSingolo(EntityManager entityManager, String idDistributore, TipologiaMezzo tipologiaMezzo) throws NotFoundException {
        GenericDAO genericDAO = new GenericDAO(entityManager);
        BigliettoSingolo bigliettoSingolo = new BigliettoSingolo(LocalDate.now(), genericDAO.findById(Distributori.class, idDistributore), tipologiaMezzo);
    public static void saveBigliettoSingolo(String idDistributore, TipologiaMezzo tipologiaMezzo) throws NotFoundException {

        BigliettoSingolo bigliettoSingolo = new BigliettoSingolo(LocalDate.now(), genericDAO.findById(Ditributori.class, idDistributore), false,tipologiaMezzo);
        genericDAO.save(bigliettoSingolo);
    }

    public static void SaveMezzi(){

        trattaDAO.getListTratte().forEach(tratte -> {
            int random = new Random().nextInt(0,2);
            TipologiaMezzo[] mezzi = TipologiaMezzo.values();
            TipologiaMezzo mezzoTrovato = mezzi[random];
            Supplier<Mezzi> mezziSupplier = () -> new Mezzi(mezzoTrovato, true, tratte);
            genericDAO.save(mezziSupplier.get());
        });

    }

    public static <T> void aggiungiMezzi(int numeroMezzi, T tipoMezzo){
        for (int i = 0; i < numeroMezzi; i++) {
            Supplier<Mezzi> mezziSupplier = () -> new Mezzi((TipologiaMezzo) tipoMezzo, false, null);
            genericDAO.save(mezziSupplier.get());
        }
    }

    public static void vidimaBiglietto(String idMezzo, String idBiglietto) throws NotFoundException {
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

    public static void salvaNuovaManutenzione(String idMezzo) throws NotFoundException {
        int random = new Random().nextInt(0,4);
        Mezzi mezzoTrov = genericDAO.findById(Mezzi.class, idMezzo);
        mezziDAO.mezzoFuoriServizio(mezzoTrov);

        Supplier<Manutenzioni> manutenzioniSupplier = () -> new Manutenzioni(LocalDate.now(), LocalDate.now().plusDays(random), mezzoTrov);


        genericDAO.save(manutenzioniSupplier.get());
    }


    public static void aggiungiTrattaAdUnMezzo(String idMezzo, String idTratta) throws NotFoundException {
        Mezzi mezzoTrov = genericDAO.findById(Mezzi.class, idMezzo);
        Tratte trattaTrov = genericDAO.findById(Tratte.class, idTratta);
        if(mezzoTrov.getTratte() == null){
            mezziDAO.setTratta(mezzoTrov, trattaTrov);
        }
        else System.out.println("Il mezzo ha già una tratta");
    }


    public static void calcolaMediaMezzoById(String idMezzo) throws NotFoundException {
        Mezzi mezzoTrovato = genericDAO.findById(Mezzi.class, idMezzo);
        System.out.println(mezziDAO.calcolaTempoMedio(mezzoTrovato));
    }

}
*/
