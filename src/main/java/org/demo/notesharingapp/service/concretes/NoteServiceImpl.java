package org.demo.notesharingapp.service.concretes;

import org.demo.notesharingapp.dao.NoteRepository;
import org.demo.notesharingapp.entity.Note;
import org.demo.notesharingapp.service.abstracts.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteServiceImpl implements NoteService {
    // This variable is defined as final, meaning it can only be assigned a value once.
    // It means that it should not have a default value.
    private final NoteRepository noteRepository;

    @Autowired
    public NoteServiceImpl(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
        // When we make the final, we need to assign it directly to a value and
        // we assigned it to the constructor here.
    }

    @Override
    public Note saveNote(Note note) {

        if (note == null) {
            throw new IllegalArgumentException("Not boş olamaz");
        }

        if (note.getTitle() == null || note.getContent() == null) {
            throw new IllegalArgumentException("Notun başlığı, içeriği veya paylaşım tarihi eksik");
        }
        return noteRepository.save(note);
    }


    @Override
    public Note updateNote(int noteId, Note updateNote) {
        Note existingNote = noteRepository.findById(noteId)
                .orElseThrow(() -> new IllegalArgumentException("Şu kimliğe sahip not bulunamadı: " + noteId));

        if (updateNote.getTitle() != null) {
            existingNote.setTitle(updateNote.getTitle());
        }
        if (updateNote.getContent() != null) {
            existingNote.setContent(updateNote.getContent());
        }

        existingNote.setSharedDate(updateNote.getSharedDate());
        existingNote.setCourse(updateNote.getCourse());

        return existingNote;
    }

    @Override
    public void deleteNoteById(int noteId) {
        Note existingNote = noteRepository.
                findById(noteId).orElseThrow(() -> new IllegalArgumentException("Şu kimlipe sahip not bulunamadı: " + noteId));
    }

    @Override
    public List<Note> getAllNotes() {
        return List.of();
    }

    @Override
    public Note getNoteById(Integer id) {
        return null;
    }

    @Override
    public List<Note> getNotesByTitle(String title) {
        return List.of();
    }

    @Override
    public Note getNoteByTitleAndCourse_id(String title, Integer courseId) {
        return null;
    }

    @Override
    public List<Note> getNoteByCourse_CourseType(String courseType, Integer courseId) {
        return List.of();
    }
}
