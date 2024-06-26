package org.demo.notesharingapp.controller;

import org.demo.notesharingapp.entity.Course;
import org.demo.notesharingapp.entity.Note;
import org.demo.notesharingapp.service.abstracts.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/courses")
@CrossOrigin
public class CourseController {
    private final CourseService courseService;

    @Autowired
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Course>> getCourses() {
        List<Course> courses = courseService.getAllCourses();
        return new ResponseEntity<>(courses, HttpStatus.OK);
    }

    @GetMapping("/{courseId}")
    public ResponseEntity<Course> getCourseById(@PathVariable int courseId) {
        Course course = courseService.getCourseById(courseId);
        return new ResponseEntity<>(course, HttpStatus.OK);
    }

    @GetMapping("/{courseId}/notes")
    public ResponseEntity<List<Note>> getNotesByCourseId(@PathVariable int courseId) {
        List<Note> notes = courseService.getNotesByCourseId(courseId);
        return new ResponseEntity<>(notes, HttpStatus.OK);
    }
/*
    @GetMapping("/grouped")
    public ResponseEntity<Map<String, List<Note>>> getGroupedCourses() {
        List<Course> courses = courseService.getAllCourses();
        Map<String, List<Note>> groupedCourses = courses.stream()
                .collect(Collectors.groupingBy(Course::getCourseType,
                        Collectors.flatMapping(course -> course.getNotes().stream(), Collectors.toList())));

        return new ResponseEntity<>(groupedCourses, HttpStatus.OK);
    }
    */
}
