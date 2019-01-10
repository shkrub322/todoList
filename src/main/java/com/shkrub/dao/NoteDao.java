package com.shkrub.dao;

import com.shkrub.entities.Note;

import java.util.List;

public interface NoteDao {
    void persist(Note note);
    List<Note> findAll();
    void delete(Note note);
    Note findById(long id);
    void update(Note note);
}
