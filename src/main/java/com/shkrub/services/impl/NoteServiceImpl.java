package com.shkrub.services.impl;

import com.shkrub.dao.NoteDao;
import com.shkrub.dao.UserDao;
import com.shkrub.dao.impl.NoteDaoImpl;
import com.shkrub.dao.impl.UserDaoImpl;
import com.shkrub.entities.Note;
import com.shkrub.entities.Users;
import com.shkrub.services.NoteService;

import java.util.List;

public class NoteServiceImpl implements NoteService {
    private final Users user;
    private NoteDao noteDao = new NoteDaoImpl();

    public NoteServiceImpl(String username) {
        UserDao userDao = new UserDaoImpl();
        user = userDao.findById(username);
    }

    public List<Note> getAllNotes() {
        List<Note> notes = noteDao.findAll();
        notes.removeIf(note -> !note.getUser().equals(user));
        return notes;
    }

    public void updateNote(long id, String content) {
        Note note = noteDao.findById(id);
        if (note.getUser().equals(user)){
            note.setContent(content);
            noteDao.update(note);
        }
    }

    public void deleteNote(long id) {
        Note note = noteDao.findById(id);
        if (note.getUser().equals(user)){
            noteDao.delete(note);
        }
    }

    public Note createNote(String content) {
        Note note = new Note(content, user);
        noteDao.persist(note);
        return note;
    }
}
