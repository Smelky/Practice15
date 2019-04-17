package model;

import org.hibernate.SessionFactory;

public class DBRunner {
    public static void main(String[] args) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    }
}
