package model.repository;

import model.entity.Customer;
import model.interfaces.CustomerI;
import org.apache.log4j.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class CustomerRepository implements CustomerI<Customer> {
    private static final Logger LOGGER = Logger.getLogger(CompanyRepository.class);

    private EntityManagerFactory emf =
            Persistence.createEntityManagerFactory("homework");
    private EntityManager em;

    public CustomerRepository() {
        em = emf.createEntityManager();
    }

    @Override
    public Customer create(Customer customer) {
        try {
            em.getTransaction().begin();
            em.persist(customer);
            em.getTransaction().commit();
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            em.getTransaction().rollback();
        }
        return customer;
    }

    @Override
    public Customer update(Customer customer) {
        try {
            em.getTransaction().begin();
            customer = em.merge(customer);
            em.getTransaction().commit();
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            em.getTransaction().rollback();
        }
        return customer;
    }

    @Override
    public Customer read(Long id) {
        em.getTransaction().begin();
        Customer customer = em.find(Customer.class, id);
        try {
            em.getTransaction().commit();
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            em.getTransaction().rollback();
        }
        return customer;
    }

    @Override
    public void delete(Customer customer) {
        try {
            em.getTransaction().begin();
            em.remove(customer);
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
