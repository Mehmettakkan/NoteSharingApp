package org.demo.notesharingapp.dao;

import org.demo.notesharingapp.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Integer> {

    Course findByCourseName(String courseName);

    // Course findCourseByCourseName(String courseName);
    // Course findCourseByCourseType(String courseType);
    // List<Course> findCoursesByCourseTypeAndCourseName(String courseType, String courseName);
}