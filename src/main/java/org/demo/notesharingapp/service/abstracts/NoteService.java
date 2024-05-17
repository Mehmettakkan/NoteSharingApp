package org.demo.notesharingapp.service.abstracts;

import org.demo.notesharingapp.entity.Note;

import java.util.List;

public interface NoteService {
    Note saveNote(Note note);

    Note updateNote(int noteId, Note updateNote);

    void deleteNoteById(int noteId);

    List<Note> getAllNotes();

    Note getNoteById(Integer id);

    List<Note> getNotesByTitle(String title);

    Note getNoteByTitleAndCourse_id(String title, Integer courseId);

    List<Note> getNoteByCourse_CourseType(String courseType, Integer courseId);

}
