package org.demo.notesharingapp.entity;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Entity
@Table(name = "sharers")
@AllArgsConstructor
@NoArgsConstructor
@Data

public class Sharer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @NotBlank(message = "Ad alanı boş bırakılamaz")
    @Column(name = "first_name", nullable = false)
    private String firstName;

    @NotBlank(message = "Soyad alanı boş bırakılamaz")
    @Column(name = "last_name", nullable = false)
    private String lastName;

    @NotBlank(message = "Kullanıcı adı boş bırakılamaz")
    @Column(name = "user_name", nullable = false, unique = true)
    private String userName;

    @Email(message = "Geçerli bir e-posta adresi giriniz")
    @NotBlank(message = "E-posta adresi boş bırakılamaz")
    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @NotBlank(message = "Şifre boş bırakılamaz")
    @Column(name = "password", nullable = false)
    private String password;

    @Pattern(regexp = "^\\d{10}$", message = "Geçerli bir telefon numarası giriniz")
    @NotNull(message = "Telefon numarası boş bırakılamaz")
    @Column(name = "phone")
    private String phone;

    @Min(value = 18, message = "Yaş en az 18 olmalıdır")
    @NotNull(message = "Yaş boş bırakılamaz")
    @Column(name = "age", nullable = false)
    private int age;

    @NotBlank(message = "Cinsiyet alanı boş bırakılamaz")
    @Column(name = "gender", nullable = false)
    private String gender;

    @OneToMany(mappedBy = "sharer", fetch = FetchType.LAZY,
            cascade = {CascadeType.DETACH, CascadeType.MERGE,
                    CascadeType.PERSIST, CascadeType.REFRESH})
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "sharer"})
    @ToString.Exclude
    private List<Note> notes;
}