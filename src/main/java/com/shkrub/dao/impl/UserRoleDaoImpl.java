package com.shkrub.dao.impl;

import com.shkrub.dao.UserRoleDao;
import com.shkrub.entities.UserRoles;
import org.hibernate.HibernateException;
import org.hibernate.Session;

import static com.shkrub.util.HibernateUtil.*;

public class UserRoleDaoImpl implements UserRoleDao {
    @Override
    public void persist(UserRoles userRole) {
        try{
            Session session = openCurrentSessionWithTransaction();
            session.save(userRole);
        } catch (HibernateException e){
            handleException();
        }finally {
            closeCurrentSessionWithTransaction();
        }
    }
}
