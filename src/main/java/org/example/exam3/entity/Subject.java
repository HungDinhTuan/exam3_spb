package org.example.exam3.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "subject_t")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "subject_id")
    Integer subjectId;

    @Column(name = "subject_code", nullable = false, length = 20)
    String subjectCode;

    @Column(name = "subject_name", nullable = false, length = 100)
    String subjectName;

    @Column(name = "credit", nullable = false)
    Integer credit;

    @OneToMany(mappedBy = "subject", cascade = CascadeType.ALL)
    List<StudentScore> scores;
}
