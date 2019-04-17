package model.repository;

import model.entity.Company;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class CompanyRepository implements ModelRepository<Company> {
    private EntityManagerFactory emf =
            Persistence.createEntityManagerFactory("homework");
    private EntityManager em;

    public CompanyRepository() {
        em = emf.createEntityManager();
    }

    @Override
    public Company create(Company company) {
        em.getTransaction().begin();
        em.persist(company);
        em.getTransaction().commit();
        return company;
    }

    @Override
    public Company update(Company company) {
        em.getTransaction().begin();
        company = em.merge(company);
        em.getTransaction().commit();
        return company;
    }

    @Override
    public Company read(Long id) {
        em.getTransaction().begin();
        Company company = em.find(Company.class, id);
        em.getTransaction().commit();
        return company;
    }

    @Override
    public void delete(Company company) {
        em.getTransaction().begin();
        em.remove(company);
        em.getTransaction().commit();
    }

    @Override
    public void close() {
        emf.close();
    }
}