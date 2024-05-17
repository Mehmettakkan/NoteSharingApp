package org.demo.notesharingapp.controller;

import org.demo.notesharingapp.entity.Note;
import org.demo.notesharingapp.service.abstracts.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notes")
public class NoteController {
    private final NoteService noteService;

    @Autowired
    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    @PostMapping("/create")
    public ResponseEntity<Note> saveNote(@RequestBody Note note) {
        Note savedNote = noteService.saveNote(note);
        return new ResponseEntity<>(savedNote, HttpStatus.CREATED);
    }

    @PutMapping("/update/{noteId}")
    public ResponseEntity<Note> updateNote(@PathVariable int noteId, @RequestBody Note updateNote) {
        Note updated = noteService.updateNote(noteId, updateNote);
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{noteId}")
    public ResponseEntity<Void> deleteNoteById(@PathVariable int noteId) {
        noteService.deleteNoteById(noteId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Note>> getSharers() {
        List<Note> notes = noteService.getAllNotes();
        return new ResponseEntity<>(notes, HttpStatus.OK);
    }

    @GetMapping("/{noteId}")
    public ResponseEntity<Note> getSharerById(@PathVariable int noteId) {
        Note note = noteService.getNoteById(noteId);
        return new ResponseEntity<>(note, HttpStatus.OK);
    }


}
