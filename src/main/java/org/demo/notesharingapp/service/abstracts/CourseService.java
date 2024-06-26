package org.demo.notesharingapp.service.abstracts;

import org.demo.notesharingapp.entity.Course;
import org.demo.notesharingapp.entity.Note;

import java.util.List;

public interface CourseService {
    Course addCourse(Course course);

    Course updateCourse(Course course);

    void deleteCourseById(Integer courseId);

    List<Course> getAllCourses();

    Course getCourseById(Integer courseId);

    Course getCourseByName(String courseName);

    List<Note> getNotesByCourseId(int courseId);
}
