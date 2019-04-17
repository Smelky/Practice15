package model.repository;

import model.entity.Project;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class ProjectRepository implements ModelRepository<Project> {
    private EntityManagerFactory emf =
            Persistence.createEntityManagerFactory("homework");
    private EntityManager em;

    public ProjectRepository() {
        em = emf.createEntityManager();
    }

    @Override
    public Project create(Project project) {
        em.getTransaction().begin();
        em.persist(project);
        em.getTransaction().commit();
        return project;
    }

    @Override
    public Project update(Project project) {
        em.getTransaction().begin();
        project = em.merge(project);
        em.getTransaction().commit();
        return project;
    }

    @Override
    public Project read(Long id) {
        em.getTransaction().begin();
        Project project = em.find(Project.class, id);
        em.getTransaction().commit();
        return project;
    }

    @Override
    public void delete(Project project) {
        em.getTransaction().begin();
        em.remove(project);
        em.getTransaction().commit();
    }

    @Override
    public void close() {
        emf.close();
    }
}
