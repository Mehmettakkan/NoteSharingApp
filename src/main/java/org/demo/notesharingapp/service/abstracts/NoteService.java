package org.demo.notesharingapp.service.abstracts;

import org.demo.notesharingapp.entity.Note;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface NoteService {
    public Note saveNote(MultipartFile file, Note note, int sharerId, String courseName);

    Note updateNote(int noteId, Note updateNote);

    void deleteNoteById(int noteId);

    List<Note> getAllNotes();

    Note getNoteById(Integer noteId);

    List<Note> getNotesByTitle(String title);

    Note getNoteByTitleAndCourse_id(String title, Integer courseId);

}
