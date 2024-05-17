package org.demo.notesharingapp.dao;

import org.demo.notesharingapp.entity.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoteRepository extends JpaRepository<Note, Integer> {

    List<Note> findNoteByTitleContainingIgnoreCase(String title);

    List<Note> findNoteByTitleStartingWithIgnoreCase(String title);

    List<Note> findNoteByTitleEndingWithIgnoreCase(String title);

    Note findNoteByTitleAndCourse_Id(String noteTitle, int courseId);

    List<Note> findNoteByTitleOrCourse_Id(String noteTitle, int courseId);

    List<Note> findNoteByCourse_CourseType(String courseType);

    List<Note> findNoteBySharer_UserName(String userName);

    List<Note> findNoteByTitleContains(String noteTitle);
}