package yurilenzi.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import yurilenzi.exceptions.NotFoundException;

import java.util.UUID;
import static yurilenzi.Application.em;

public class GenericDAO {

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


}
