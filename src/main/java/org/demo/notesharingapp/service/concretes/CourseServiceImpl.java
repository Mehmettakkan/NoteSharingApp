package org.demo.notesharingapp.service.concretes;

import org.demo.notesharingapp.dao.CourseRepository;
import org.demo.notesharingapp.entity.Course;
import org.demo.notesharingapp.service.abstracts.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {
    private CourseRepository courseRepository;

    @Autowired
    public CourseServiceImpl(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
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
    public void deleteCourseById(Integer id) {

    }

    @Override
    public List<Course> getAllCourses() {
        return List.of();
    }

    @Override
    public Course getCourseById(Integer id) {
        return null;
    }

    @Override
    public Course getCourseByName(String courseName) {
        return null;
    }

    @Override
    public Course getCourseByType(String courseType) {
        return null;
    }

    @Override
    public List<Course> getCoursesByCourseTypeAndCourseName(String courseType, String courseName) {
        return List.of();
    }
}
