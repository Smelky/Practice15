package model.repository;

import model.entity.Skill;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class SkillRepository implements ModelRepository<Skill> {
    private EntityManagerFactory emf =
            Persistence.createEntityManagerFactory("homework");
    private EntityManager em;

    public SkillRepository() {
        em = emf.createEntityManager();
    }

    @Override
    public Skill create(Skill skill) {
        em.getTransaction().begin();
        em.persist(skill);
        em.getTransaction().commit();
        return skill;
    }

    @Override
    public Skill update(Skill skill) {
        em.getTransaction().begin();
        skill = em.merge(skill);
        em.getTransaction().commit();
        return skill;
    }

    @Override
    public Skill read(Long id) {
        em.getTransaction().begin();
        Skill skill = em.find(Skill.class, id);
        em.getTransaction().commit();
        return skill;
    }

    @Override
    public void delete(Skill skill) {
        em.getTransaction().begin();
        em.remove(skill);
        em.getTransaction().commit();
    }

    @Override
    public void close() {
        emf.close();
    }
}
