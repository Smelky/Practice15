package model.repository;

import model.entity.Developer;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class DeveloperRepository implements ModelRepository<Developer>{
    private EntityManagerFactory emf =
            Persistence.createEntityManagerFactory("homework");
    private EntityManager em;

    public DeveloperRepository() {
        em = emf.createEntityManager();
    }

    @Override
    public Developer create(Developer developer) {
        em.getTransaction().begin();
        em.persist(developer);
        em.getTransaction().commit();
        return developer;
    }

    @Override
    public Developer read(Long id) {
        em.getTransaction().begin();
        Developer developer = em.find(Developer.class, id);
        em.getTransaction().commit();
        return developer;
    }

    @Override
    public Developer update(Developer developer) {
        em.getTransaction().begin();
        developer = em.merge(developer);
        em.getTransaction().commit();
        return developer;
    }


    @Override
    public void delete(Developer developer) {
        em.getTransaction().begin();
        em.remove(developer);
        em.getTransaction().commit();
    }

    @Override
    public void close() {
        emf.close();
    }
}
