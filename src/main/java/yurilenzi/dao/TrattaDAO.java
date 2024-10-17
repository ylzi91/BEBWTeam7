package yurilenzi.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import yurilenzi.entities.Mezzi;
import yurilenzi.entities.Tratte;
import yurilenzi.exceptions.NothingTratteException;

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

    public List<Mezzi> tratteDisponibili() throws NothingTratteException {
        TypedQuery<Mezzi> query = entityManager.createQuery("select m from Mezzi m where m.tratte is null", Mezzi.class);
        List<Mezzi> mezziDisp = query.getResultList();
        if(mezziDisp.isEmpty()){
            throw new NothingTratteException();
        }
        else return mezziDisp;

    }
}
