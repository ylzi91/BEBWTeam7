package yurilenzi.dao;

import yurilenzi.Util;
import yurilenzi.entities.Mezzi;
import yurilenzi.entities.TipologiaMezzo;
import yurilenzi.exceptions.NotFoundException;
import yurilenzi.exceptions.NothingGenException;

import static yurilenzi.Application.scanner;
import static yurilenzi.Application.em;

public class AmministratoreDAO {
    public GenericDAO gd = new GenericDAO(em);
    public MezziDAO mezziDAO = new MezziDAO(em);
    public TrattaDAO trattaDAO = new TrattaDAO(em);

    public void opzioniAmministratore() {
        amministratore:
        while(true){
            System.out.println("Opzioni Amministratore");
            System.out.println("1. Controlla distributori attivi");
            System.out.println("2. Controlla distributori fuori servizio");
            System.out.println("3. Controlla Bus in servizio");
            System.out.println("4. Controlla lo storico manutenzioni");
            System.out.println("5. Controlla numero di manutenzioni di un mezzo");
            System.out.println("6. Controlla bus in sostituzione");
            System.out.println("7. Aggiungi mezzo in sostituzione");
            System.out.println("8. Assegna tratta ad un mezzo");
            System.out.println("9. Manda un mezzo in manutenzione");
            System.out.println("10. Fine della manutenzione");
            System.out.println("11. Calcola il tempo medio di percorrenza di un mezzo");
            System.out.println("12. Aggiungi nuova tratta");
            System.out.println("0. Esci");

            int scelta = Integer.parseInt(scanner.nextLine());

            switch (scelta) {
                case 1:
                    gd.distributoriAttivi().forEach(System.out::println);
                    break;
                case 2:
                    gd.distributoriFuoriServizio().forEach(System.out::println);
                    break;
                case 3:
                    try {
                        System.out.println("------------------Mezzi in Servizio---------------");
                        mezziDAO.mezziInServizio().forEach(System.out::println);
                        System.out.println("---------------Fine mezzi in servizio-------------");
                    } catch (NothingGenException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 4:
                    try {
                        System.out.println("------------------Mezzi in Manuteznione---------------");
                        mezziDAO.dettagliMezziInManutenzione().forEach(System.out::println);
                        System.out.println("---------------Fine mezzi in Manutenzione-------------");
                    } catch (NothingGenException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 5:
                    System.out.println("----------Lista mezzi che sono stati in mautenzione------------");
                    try {
                        mezziDAO.mezziInManutenzione().forEach(System.out::println);

                        System.out.println("Conta il nuemro di manutenzioni con l'id");
                        String idMezzMan = scanner.nextLine();

                        System.out.println("Numro di manutenzioni totali: " + mezziDAO.contaggioManutenzioni(idMezzMan));


                    } catch (NothingGenException | NotFoundException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 6:
                    System.out.println("------------------Bus in sostituzione-----------------");
                    try {
                        mezziDAO.mezziDiponibili().forEach(System.out::println);
                    } catch (NothingGenException e) {
                        throw new RuntimeException(e);
                    }
                    System.out.println("-----------------Bus in sostituzione------------------");
                    break;
                case 7:
                    int sceltaMezzo;
                    System.out.println("Quale mezzo vuoi aggiungere?");
                    System.out.println("1.Autobus");
                    System.out.println("2.Tram");
                    sceltaMezzo = Integer.parseInt(scanner.nextLine());
                    switch (sceltaMezzo){
                        case 1:
                            Util.aggiungiMezzi(1, TipologiaMezzo.AUTOBUS);
                            break;
                        case 2:
                            Util.aggiungiMezzi(1, TipologiaMezzo.TRAM);
                            break;
                        default:
                            System.out.println("Scelta non valida");
                            break;
                    }

                    break;
                case 8:

                    System.out.println("Tratte disponibili");
                    try {
                        trattaDAO.tratteDisponibili().forEach(tratte -> {
                            System.out.println("idTratta: " + tratte.getTratteId());
                            System.out.println("Da " + tratte.getZonaPartenza() + " a " + tratte.getCapolinea());

                        });

                        System.out.println("Scrvi l'id della tratta");
                        String idTratta = scanner.nextLine();

                        System.out.println("Mezzi disponibili");
                        mezziDAO.mezziDiponibili().forEach(mezzi -> {
                            System.out.println("idMezzo: " + mezzi.getMezziId());
                            System.out.println("Tipologia: " + mezzi.getTipologiaMezzo());
                        });

                        System.out.println("Scrivi l'id del mezzo");
                        String idMezzo = scanner.nextLine();
                        Util.aggiungiTrattaAdUnMezzo(idMezzo, idTratta);

                    } catch (NothingGenException | NotFoundException e) {
                        System.out.println(e.getMessage());
                    } catch (Exception e){
                        System.out.println("Errore di inserimento");
                    }
                    break;
                case 9:
                    System.out.println("Lista mezzi");
                    gd.listagenerica(Mezzi.class).forEach(System.out::println);
                    System.out.println("Scrvi l'id del mezzo a cui fare la manutenzione");
                    String idMezzo = scanner.nextLine();
                    try {
                        Util.salvaNuovaManutenzione(idMezzo);
                    } catch (NotFoundException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 10:
                    System.out.println("Lista Mezzi in manutenzione");
                    try {
                        mezziDAO.mezziInManutenzione().forEach(System.out::println);
                        System.out.println("Quale mezzo vuoi far uscire dalla manutanzione?");
                        String idMezzoMan = scanner.nextLine();
                        Util.mezzoEsceDallaManutenzione(idMezzoMan);
                        System.out.println("Vuoi già assegnarlo ad una tratta? S/N");
                        String sceltaMan = scanner.nextLine();
                        switch (sceltaMan.toUpperCase()){
                            case "S":
                                System.out.println("Lista tratte");
                                trattaDAO.tratteDisponibili().forEach(System.out::println);
                                System.out.println("Inserisci l'id di una tratta");
                                Util.aggiungiTrattaAdUnMezzo(idMezzoMan, scanner.nextLine());
                                break;
                            case "N":
                                break;
                        }
                    } catch (NothingGenException | NotFoundException e) {
                        System.out.println(e.getMessage());
                    } catch (Exception e){
                        System.out.println("Errore input");
                    }
                    break;
                case 11:
                    System.out.println("Lista mezzi in servizio");
                    try {
                        mezziDAO.mezziInServizio().forEach(System.out::println);
                        System.out.println("Scrivi l'id del mezzo per cui vuoi calcolare la media");
                        String idMezzoMed = scanner.nextLine();
                        System.out.println("La media del mezzo" + idMezzoMed + " è");
                        Util.calcolaMediaMezzoById(idMezzoMed);
                    } catch (NothingGenException | NotFoundException e) {
                        System.out.println(e.getMessage());
                    }catch (Exception e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case 12:
                    try {
                        System.out.println("Indirizzo di partenza");
                        String partenza = scanner.nextLine();
                        System.out.println("Capolinea");
                        String capolinea = scanner.nextLine();
                        System.out.println("Inserisci il tempo previsto");
                        Double tempoPrevisto = Double.parseDouble(scanner.nextLine());
                        System.out.println("Inserisci il numero di giri");
                        int nGiri = Integer.parseInt(scanner.nextLine());
                        Util.aggiungiTratta(capolinea, tempoPrevisto, partenza, nGiri);
                    } catch (Exception e) {
                        System.out.println("Errore di inserimento");
                    }

                    break;
                case 0:
                    System.out.println("Uscita dal menu amministratore.");
                    break amministratore;
                default:
                    System.out.println("Scelta non valida.");
                    break;
            }
        }
    }

}
