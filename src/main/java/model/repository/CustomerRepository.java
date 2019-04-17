package model.repository;

import model.entity.Customer;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class CustomerRepository implements ModelRepository<Customer> {
    private EntityManagerFactory emf =
            Persistence.createEntityManagerFactory("homework");
    private EntityManager em;

    public CustomerRepository() {
        em = emf.createEntityManager();
    }

    @Override
    public Customer create(Customer customer) {
        em.getTransaction().begin();
        em.persist(customer);
        em.getTransaction().commit();
        return customer;
    }

    @Override
    public Customer update(Customer customer) {
        em.getTransaction().begin();
        customer = em.merge(customer);
        em.getTransaction().commit();
        return customer;
    }

    @Override
    public Customer read(Long id) {
        em.getTransaction().begin();
        Customer customer = em.find(Customer.class, id);
        em.getTransaction().commit();
        return customer;
    }

    @Override
    public void delete(Customer customer) {
        em.getTransaction().begin();
        em.remove(customer);
        em.getTransaction().commit();
    }

    @Override
    public void close() {
        emf.close();
    }
}
