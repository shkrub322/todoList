package com.shkrub.dao.impl;

import com.shkrub.dao.UserDao;
import com.shkrub.entities.Users;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import static com.shkrub.util.HibernateUtil.*;

public class UserDaoImpl implements UserDao {
    public void persist(Users user) {
        try{
            Session session = openCurrentSessionWithTransaction();
            session.save(user);
        } catch (HibernateException e){
            handleException();
        }finally {
            closeCurrentSessionWithTransaction();
        }
    }

    public Users findById(String username) {
        Users user = new Users();
        try{
            Session session = openCurrentSessionWithTransaction();
            user = session.get(Users.class, username);
        } catch (HibernateException e){
            handleException();
        } finally {
            closeCurrentSessionWithTransaction();
        }
        return user;
    }
}
