package yurilenzi.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import yurilenzi.entities.Tratte;
import yurilenzi.exceptions.NothingGenException;

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

    public List<Tratte> tratteDisponibili() throws NothingGenException {
        TypedQuery<Tratte> query = entityManager.createQuery("select t from Tratte t left join Mezzi m on t = m.tratte where m.tratte is null", Tratte.class);
        List<Tratte> mezziDisp = query.getResultList();
        if(mezziDisp.isEmpty()){
            throw new NothingGenException(Tratte.class);
        }
        else return mezziDisp;
    }

}
