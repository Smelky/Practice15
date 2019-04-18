package model.repository;

import model.entity.Developer;
import model.interfaces.DeveloperI;
import org.apache.log4j.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class DeveloperRepository implements DeveloperI<Developer> {
    private static final Logger LOGGER = Logger.getLogger(CompanyRepository.class);

    private EntityManagerFactory emf =
            Persistence.createEntityManagerFactory("homework");
    private EntityManager em;

    public DeveloperRepository() {
        em = emf.createEntityManager();
    }

    @Override
    public Developer create(Developer developer) {
        try {
            em.getTransaction().begin();
            em.persist(developer);
            em.getTransaction().commit();
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            em.getTransaction().rollback();
        }
        return developer;
    }

    @Override
    public Developer read(Long id) {
        em.getTransaction().begin();
        Developer developer = em.find(Developer.class, id);
        try {
            em.getTransaction().commit();
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            em.getTransaction().rollback();
        }
        return developer;
    }

    @Override
    public Developer update(Developer developer) {
        try {
            em.getTransaction().begin();
            developer = em.merge(developer);
            em.getTransaction().commit();
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            em.getTransaction().rollback();
        }
        return developer;
    }


    @Override
    public void delete(Developer developer) {
        try {
            em.getTransaction().begin();
            em.remove(developer);
            em.getTransaction().commit();
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            em.getTransaction().rollback();
        }
    }

    @Override
    public void close() {
        emf.close();
    }
}
