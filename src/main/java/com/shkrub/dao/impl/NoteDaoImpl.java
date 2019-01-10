package com.shkrub.dao.impl;

import com.shkrub.dao.NoteDao;
import com.shkrub.entities.Note;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;
import static com.shkrub.util.HibernateUtil.*;

public class NoteDaoImpl implements NoteDao {


    public void persist(Note note) {
        try{
            Session session = openCurrentSessionWithTransaction();
            session.save(note);
        } catch (HibernateException e){
            handleException();
        } finally {
            closeCurrentSessionWithTransaction();
        }
    }

    public List<Note> findAll() {
        List<Note> notes = new ArrayList<Note>();
        try{
            Session session = openCurrentSessionWithTransaction();
            Query query = session.createQuery("from Note");
            notes = query.list();
        } catch (HibernateException e){
            handleException();
        } finally {
            closeCurrentSessionWithTransaction();
        }
        return notes;
    }

    public void delete(Note note) {
        try{
            Session session = openCurrentSessionWithTransaction();
            session.delete(note);
        } catch (HibernateException e){
            handleException();
        } finally {
            closeCurrentSessionWithTransaction();
        }
    }

    public Note findById(long id) {
        Note note = new Note();
        try{
            Session session= openCurrentSessionWithTransaction();
            note = session.get(Note.class, id);
        } catch (HibernateException e){
            handleException();
        } finally {
            closeCurrentSessionWithTransaction();
        }
        return note;
    }

    public void update(Note note) {
        try{
            Session session = openCurrentSessionWithTransaction();
            session.update(note);
        } catch (HibernateException e){
            handleException();
        } finally {
            closeCurrentSessionWithTransaction();
        }
    }
}
