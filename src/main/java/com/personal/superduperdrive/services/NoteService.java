package com.personal.superduperdrive.services;

import com.personal.superduperdrive.mapper.NoteMapper;
import com.personal.superduperdrive.model.Note;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteService {

    @Autowired
    private NoteMapper noteMapper;

    public int addNote(Note note) {
        return noteMapper.addNote(note);
    }

    public List<Note> getNotes(int userId) {
        return noteMapper.getNoteByUser(userId);
    }

    public void updateNote(Note note) {
        noteMapper.updateNote(note);
    }

    public void deleteNote(int noteId) {
        noteMapper.deleteNoteByID(noteId);
    }
}
