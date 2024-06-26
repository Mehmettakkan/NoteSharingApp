package org.demo.notesharingapp.entity;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

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
    @Column(name = "title", nullable = false)
    private String title;

    @NotBlank(message = "İçerik alanı boş bırakılamaz")
    @Column(name = "content", nullable = false)
    private String content;

    @Column(name = "shared_date")
    private LocalDateTime sharedDate = LocalDateTime.now();

    @Column(name = "file_name")
    private String fileName;

    @Column(name = "file_type")
    private String fileType;

    @Column(name = "file_path")
    private String filePath;

    @NotNull(message = "Paylaşan kullanıcı boş bırakılamaz")
    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE,
            CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "sharer_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "notes"})
    private Sharer sharer;

    @NotNull(message = "Kurs boş bırakılamaz")
    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE,
            CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "course_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "notes"})
    @ToString.Exclude
    private Course course;
}