package org.example.exam3.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "student_t")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "student_id")
    Integer studentId;

    @Column(name = "student_code", nullable = false, length = 20)
    String studentCode;

    @Column(name = "full_name", nullable = false, length = 100)
    String fullName;

    @Column(name = "address", length = 255)
    String address;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
    List<StudentScore> scores;
}
