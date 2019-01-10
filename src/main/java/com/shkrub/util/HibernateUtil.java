package com.shkrub.util;

import com.shkrub.entities.Note;
import com.shkrub.entities.UserRoles;
import com.shkrub.entities.Users;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
    private static SessionFactory sessionFactory = buildSessionFactory();
    private static Session currentSession;
    private static Transaction currentTransaction;

    private static SessionFactory buildSessionFactory() {
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");

        configuration.addAnnotatedClass(Note.class);
        configuration.addAnnotatedClass(Users.class);
        configuration.addAnnotatedClass(UserRoles.class);

        return configuration.buildSessionFactory();
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static Session openCurrentSession() {
        currentSession = getSessionFactory().openSession();
        return currentSession;
    }

    public static Session openCurrentSessionWithTransaction() {
        currentSession = getSessionFactory().openSession();
        currentTransaction = currentSession.beginTransaction();
        return currentSession;
    }

    public static void closeCurrentSession() {
        currentSession.close();
    }

    public static void closeCurrentSessionWithTransaction() {
        currentTransaction.commit();
        currentSession.close();
    }

    public static void handleException(){
        currentTransaction.rollback();
    }
}
