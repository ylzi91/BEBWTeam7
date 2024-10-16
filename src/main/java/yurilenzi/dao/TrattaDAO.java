package yurilenzi.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import yurilenzi.entities.Tratte;

import java.util.List;


public class TrattaDAO {
    public final EntityManager entityManager;

    public TrattaDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<Tratte> getListTratte(){
        TypedQuery<Tratte> query = entityManager.createQuery("select t from Tratte t", Tratte.class);
        return query.getResultList();
    }
}
