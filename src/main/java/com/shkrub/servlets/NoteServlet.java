package com.shkrub.servlets;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.shkrub.entities.Note;
import com.shkrub.services.NoteService;
import com.shkrub.services.impl.NoteServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "NoteServlet", urlPatterns = "/note")
public class NoteServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        NoteService noteService = new NoteServiceImpl(req.getRemoteUser());
        List<Note> notes = noteService.getAllNotes();
        resp.setContentType("application/json");
        resp.getWriter().write(createJson(notes));
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException{
        long id = Long.parseLong(req.getParameter("id"));
        NoteService noteService = new NoteServiceImpl(req.getRemoteUser());
        noteService.deleteNote(id);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException{
        long id = Long.parseLong(req.getParameter("id"));
        String content = req.getParameter("content");
        NoteService noteService = new NoteServiceImpl(req.getRemoteUser());
        noteService.updateNote(id, content);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        NoteService noteService = new NoteServiceImpl(req.getRemoteUser());
        Note note = noteService.createNote(req.getParameter("content"));
        resp.setContentType("application/json");
        resp.getWriter().write(createJson(note));
    }

    private <T>String createJson(T t) throws JsonProcessingException {
        ObjectWriter objectWriter = new ObjectMapper().writer().withDefaultPrettyPrinter();
        return objectWriter.writeValueAsString(t);
    }
}
