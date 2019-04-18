package model.repository;

import model.entity.Company;
import model.interfaces.CompanyI;
import org.apache.log4j.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class CompanyRepository implements CompanyI<Company> {
    private static final Logger LOGGER = Logger.getLogger(CompanyRepository.class);

    private EntityManagerFactory emf =
            Persistence.createEntityManagerFactory("homework");
    private EntityManager em;

    public CompanyRepository() {
        em = emf.createEntityManager();
    }

    @Override
    public Company create(Company company) {
        try {
            em.getTransaction().begin();
            em.persist(company);
            em.getTransaction().commit();
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            em.getTransaction().rollback();
        }
        return company;
    }

    @Override
    public Company update(Company company) {
        try {
            em.getTransaction().begin();
            company = em.merge(company);
            em.getTransaction().commit();
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            em.getTransaction().rollback();
        }
        return company;
    }

    @Override
    public Company read(Long id) {
        em.getTransaction().begin();
        Company company = em.find(Company.class, id);
        try {
            em.getTransaction().commit();
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            em.getTransaction().rollback();
        }
        return company;
    }

    @Override
    public void delete(Company company) {
        try {
            em.getTransaction().begin();
            em.remove(company);
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
