package org.demo.notesharingapp.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "notes")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Note {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @NotBlank(message = "Başlık alanı boş bırakılamaz")
    @Column(name = "title")
    private String title;

    @NotBlank(message = "İçerik alanı boş bırakılamaz")
    @Column(name = "content")
    private String content;

    @Column(name = "shared_date")
    private LocalDateTime sharedDate = LocalDateTime.now();

    @NotNull(message = "Paylaşan kullanıcı boş bırakılamaz")
    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE,
            CascadeType.PERSIST, CascadeType.REFRESH})

    @JoinColumn(name = "sharer_id")
    private Sharer sharer;

    @NotNull(message = "Kurs boş bırakılamaz")
    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE,
            CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "course_id")
    private Course course;
}