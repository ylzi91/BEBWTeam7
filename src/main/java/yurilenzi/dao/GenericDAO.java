package yurilenzi.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import yurilenzi.entities.Biglietti;
import yurilenzi.entities.DistributoreAutomatico;
import yurilenzi.entities.Utenti;
import yurilenzi.exceptions.NotFoundException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;
import static yurilenzi.Application.em;

public class GenericDAO {
    public final EntityManager entityManager;

    public GenericDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
    public <T> void save(T objectToSave){
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.persist(objectToSave);
        transaction.commit();
        System.out.println("L'elemento è stato salvato nel DB");
    }

    public <T> T findById(Class<T> myClass, String toSearch) throws NotFoundException {
        T found = em.find(myClass, UUID.fromString(toSearch));
        if (found == null) throw new NotFoundException(toSearch);
        return found;
    }

    public <T> T findById(Class<T> myClass, UUID toSearch) throws NotFoundException {
        T found = entityManager.find(myClass, toSearch);
        if (found == null) throw new NotFoundException(toSearch.toString());
        return found;
    }

    public <T> T findById(Class<T> myClass, long toSearch) throws NotFoundException {
        T found = entityManager.find(myClass, (toSearch));
        if (found == null) throw new NotFoundException(toSearch);
        return found;
    }

    public <T> void deleteById(Class<T> myClass, String toDelete){
        T found = null;
        try {
            found = findById(myClass, toDelete);
        } catch (NotFoundException e) {
            System.out.println(e.getMessage());
        }
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.remove(found);
        transaction.commit();
        System.out.println("L'elemento è stato eliminato");
    }

    public List<DistributoreAutomatico> distributoriAttivi() {
        TypedQuery<DistributoreAutomatico> query = em.createQuery("SELECT d FROM DistributoreAutomatico d WHERE d.attivo=true", DistributoreAutomatico.class);
        return query.getResultList();
    }

    public List<DistributoreAutomatico> distributoriFuoriServizio() {
        TypedQuery<DistributoreAutomatico> query = em.createQuery("SELECT d FROM DistribureAutomatico d WHERE d.attivo=false", DistributoreAutomatico.class);
        return query.getResultList();
    }

    public List<Biglietti> filtraBiglietti() {

        Scanner scanner= new Scanner(System.in);
        UUID rivenditioreId= UUID.fromString(scanner.next());
        System.out.println("Inserisci la data di inizio (formato YYYY-MM-DD): ");
        LocalDate startDate = LocalDate.parse(scanner.next());
        System.out.println("Inserisci la data di fine (formato YYYY-MM-DD): ");
        LocalDate endDate = LocalDate.parse(scanner.next());

        TypedQuery<Biglietti> query = em.createQuery("SELECT b FROM Biglietti b WHERE b.distributore.id = :distributoreId AND b.dataEmissione BETWEEN :startDate AND :endDate", Biglietti.class);
        query.setParameter("rivenditoreId", rivenditioreId);
        query.setParameter("startDate", startDate);
        query.setParameter("endDate", endDate);
        return query.getResultList();
    }
    public Utenti  verificaId(UUID id) {
        return em.find(Utenti.class, id);
    }

    public <T> List<T> listagenerica(Class<T>classeGenerica){
        TypedQuery<T> query = em.createQuery("select g from " + classeGenerica.getSimpleName() + " g", classeGenerica);
        return query.getResultList();
}

}
