package org.demo.notesharingapp.service.concretes;

import jakarta.persistence.EntityNotFoundException;
import org.demo.notesharingapp.dao.NoteRepository;
import org.demo.notesharingapp.dao.SharerRepository;
import org.demo.notesharingapp.dao.CourseRepository;
import org.demo.notesharingapp.entity.Note;
import org.demo.notesharingapp.entity.Course;
import org.demo.notesharingapp.entity.Sharer;
import org.demo.notesharingapp.service.abstracts.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

@Service
public class NoteServiceImpl implements NoteService {
    private final NoteRepository noteRepository;
    private final SharerRepository sharerRepository;
    private final CourseRepository courseRepository;
    private final Path fileStorageLocation;

    @Autowired
    public NoteServiceImpl(NoteRepository noteRepository, SharerRepository sharerRepository, CourseRepository courseRepository) {
        this.noteRepository = noteRepository;
        this.sharerRepository = sharerRepository;
        this.courseRepository = courseRepository;
        this.fileStorageLocation = Paths.get("path/to/save").toAbsolutePath().normalize();

        try {
            Files.createDirectories(this.fileStorageLocation);
        } catch (Exception ex) {
            throw new RuntimeException("Dosya depolama dizini oluşturulamadı.", ex);
        }
    }

    @Override
    public Note saveNote(MultipartFile file, Note note, int sharerId, String courseName) {
        if (note == null) {
            throw new IllegalArgumentException("Not boş olamaz");
        }

        if (note.getTitle() == null || note.getContent() == null) {
            throw new IllegalArgumentException("Notun başlığı veya içeriği eksik");
        }

        // Kullanıcıyı bulma
        Sharer sharer = sharerRepository.findById(sharerId)
                .orElseThrow(() -> new IllegalArgumentException("Geçersiz kullanıcı kimliği: " + sharerId));

        // Kursu isme göre bul veya yeni kurs oluştur
        Course course = courseRepository.findByCourseName(courseName);
        if (course == null) {
            course = new Course();
            course.setCourseName(courseName);
            course = courseRepository.save(course);
        }

        // Dosyayı dosya sistemine kaydetme
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        Path targetLocation = this.fileStorageLocation.resolve(fileName);
        try {
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new RuntimeException("Dosya kaydedilirken hata oluştu: " + e.getMessage());
        }

        // Dosya bilgilerini Note entity'sine ekleme
        note.setFileName(fileName);
        note.setFileType(file.getContentType());
        note.setFilePath(targetLocation.toString());
        note.setCourse(course);
        note.setSharer(sharer);
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
        noteRepository.delete(existingNote);
    }

    @Override
    public List<Note> getAllNotes() {
        return noteRepository.findAll();
    }

    @Override
    public Note getNoteById(Integer noteId) {
        return noteRepository.findById(noteId)
                .orElseThrow(() ->
                        new EntityNotFoundException("Şu kimliğe sahip not bulunamadı: " + noteId));

    }

    @Override
    public List<Note> getNotesByTitle(String title) {
        List<Note> notes = noteRepository.findNoteByTitleContains(title);

        if (notes.isEmpty()) {
            throw new RuntimeException("Şu başlıktaki " + title + " notlar bulunamadı");
        }

        return notes;
    }

    @Override
    public Note getNoteByTitleAndCourse_id(String title, Integer courseId) {
        return noteRepository.findNoteByTitleAndCourse_Id(title, courseId);
    }

}
