package model.repository;

import model.entity.Skill;
import model.interfaces.SkillI;
import org.apache.log4j.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class SkillRepository implements SkillI<Skill> {
    private static final Logger LOGGER = Logger.getLogger(CompanyRepository.class);

    private EntityManagerFactory emf =
            Persistence.createEntityManagerFactory("homework");
    private EntityManager em;

    public SkillRepository() {
        em = emf.createEntityManager();
    }

    @Override
    public Skill create(Skill skill) {
        try {
            em.getTransaction().begin();
            em.persist(skill);
            em.getTransaction().commit();
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            em.getTransaction().rollback();
        }
        return skill;
    }

    @Override
    public Skill update(Skill skill) {
        try {
            em.getTransaction().begin();
            skill = em.merge(skill);
            em.getTransaction().commit();
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            em.getTransaction().rollback();
        }
        return skill;
    }

    @Override
    public Skill read(Long id) {
        em.getTransaction().begin();
        Skill skill = em.find(Skill.class, id);
        try {
            em.getTransaction().commit();
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            em.getTransaction().rollback();
        }
        return skill;
    }

    @Override
    public void delete(Skill skill) {
        try {
            em.getTransaction().begin();
            em.remove(skill);
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
