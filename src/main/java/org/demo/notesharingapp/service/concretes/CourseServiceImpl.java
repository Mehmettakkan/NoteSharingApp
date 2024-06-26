package org.demo.notesharingapp.service.concretes;

import jakarta.persistence.EntityNotFoundException;
import org.demo.notesharingapp.dao.CourseRepository;
import org.demo.notesharingapp.dao.NoteRepository;
import org.demo.notesharingapp.entity.Course;
import org.demo.notesharingapp.entity.Note;
import org.demo.notesharingapp.service.abstracts.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {
    private final CourseRepository courseRepository;
    private final NoteRepository noteRepository;

    @Autowired
    public CourseServiceImpl(CourseRepository courseRepository, NoteRepository noteRepository) {
        this.courseRepository = courseRepository;
        this.noteRepository = noteRepository;
    }
    @Override
    public Course addCourse(Course course) {
        return null;
    }

    @Override
    public Course updateCourse(Course course) {
        return null;
    }

    @Override
    public void deleteCourseById(Integer courseId) {

    }

    @Override
    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    @Override
    public Course getCourseById(Integer courseId) {
        return courseRepository.findById(courseId)
                .orElseThrow(() ->
                        new EntityNotFoundException("Şu kimliğe sahip kurs bulunamadı: " + courseId));

    }


    @Override
    public Course getCourseByName(String courseName) {
        return null;
    }


    public List<Note> getNotesByCourseId(int courseId) {
        return noteRepository.findByCourseId(courseId);
    }

}
