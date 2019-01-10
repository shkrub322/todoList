package com.shkrub.services;

import com.shkrub.entities.Note;

import java.util.List;

public interface NoteService {
    List<Note> getAllNotes();
    void updateNote(long id, String content);
    void deleteNote(long id);
    Note createNote(String content);
}
