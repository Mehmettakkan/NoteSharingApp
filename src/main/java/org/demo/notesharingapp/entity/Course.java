package org.demo.notesharingapp.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "courses")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @NotBlank(message = "Kurs tipi boş bırakılamaz")
    @Column(name = "course_type")
    private String courseType;

    @NotBlank(message = "Kurs adı boş bırakılamaz")
    @Column(name = "course_name")
    private String courseName;

    @Column(name = "course_description")
    private String courseDescription;

    @OneToMany(mappedBy = "course", fetch = FetchType.LAZY,
            cascade = {CascadeType.DETACH, CascadeType.MERGE,
                    CascadeType.PERSIST, CascadeType.REFRESH})
    private List<Note> notes;
}