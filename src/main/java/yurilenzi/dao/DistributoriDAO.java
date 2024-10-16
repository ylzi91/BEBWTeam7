package yurilenzi.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import yurilenzi.entities.DistributoreAutomatico;
import yurilenzi.entities.RivenditoreFisico;

import java.util.List;
import java.util.Scanner;

public class DistributoriDAO {
    public final EntityManager entityManager;
    Scanner scanner = new Scanner(System.in);

    public DistributoriDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<RivenditoreFisico> findAllRivenditori() {
        TypedQuery<RivenditoreFisico> query = entityManager.createQuery("SELECT r FROM RivenditoreFisico r", RivenditoreFisico.class);
        return query.getResultList();
    }

    public List<DistributoreAutomatico> findAllDistributore() {
        TypedQuery<DistributoreAutomatico> query = entityManager.createQuery("SELECT d FROM DistributoreAutomatico d WHERE d.attivo = true", DistributoreAutomatico.class);
        return query.getResultList();
    }
}
