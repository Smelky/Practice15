package model.repository;

import model.entity.Project;
import model.interfaces.ProjectI;
import org.apache.log4j.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class ProjectRepository implements ProjectI<Project> {
    private static final Logger LOGGER = Logger.getLogger(CompanyRepository.class);

    private EntityManagerFactory emf =
            Persistence.createEntityManagerFactory("homework");
    private EntityManager em;

    public ProjectRepository() {
        em = emf.createEntityManager();
    }

    @Override
    public Project create(Project project) {
        try {
            em.getTransaction().begin();
            em.persist(project);
            em.getTransaction().commit();
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            em.getTransaction().rollback();
        }
        return project;
    }

    @Override
    public Project update(Project project) {
        try {
            em.getTransaction().begin();
            project = em.merge(project);
            em.getTransaction().commit();
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            em.getTransaction().rollback();
        }
        return project;
    }

    @Override
    public Project read(Long id) {
        em.getTransaction().begin();
        Project project = em.find(Project.class, id);
        try {
            em.getTransaction().commit();
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            em.getTransaction().rollback();
        }
        return project;
    }

    @Override
    public void delete(Project project) {
        try {
            em.getTransaction().begin();
            em.remove(project);
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
