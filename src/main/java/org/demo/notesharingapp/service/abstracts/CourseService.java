package org.demo.notesharingapp.service.abstracts;

import org.demo.notesharingapp.entity.Course;

import java.util.List;

public interface CourseService {
    Course addCourse(Course course);

    Course updateCourse(Course course);

    void deleteCourseById(Integer id);

    List<Course> getAllCourses();

    Course getCourseById(Integer id);

    Course getCourseByName(String courseName);

    Course getCourseByType(String courseType);

    List<Course> getCoursesByCourseTypeAndCourseName(String courseType, String courseName);

}
