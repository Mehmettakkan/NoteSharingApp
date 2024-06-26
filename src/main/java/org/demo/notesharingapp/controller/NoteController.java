package org.demo.notesharingapp.controller;

import org.demo.notesharingapp.entity.Note;
import org.demo.notesharingapp.service.abstracts.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/notes")
@CrossOrigin
public class NoteController {
    private final NoteService noteService;

    @Autowired
    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    @PostMapping("/create")
    public ResponseEntity<Note> saveNote(@RequestParam("file") MultipartFile file,
                                         @RequestParam("title") String title,
                                         @RequestParam("content") String content,
                                         @RequestParam("sharerId") int sharerId,
                                         @RequestParam("courseName") String courseName) {
        Note note = new Note();
        note.setTitle(title);
        note.setContent(content);

        Note savedNote = noteService.saveNote(file, note, sharerId, courseName);
        return ResponseEntity.ok(savedNote);
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
