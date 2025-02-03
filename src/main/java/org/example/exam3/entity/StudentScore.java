package org.example.exam3.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

@Entity
@Table(name = "student_score_t")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class StudentScore {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "student_score_id")
    Integer studentScoreId;

    @ManyToOne
    @JoinColumn(name = "student_id", nullable = false, foreignKey = @ForeignKey(name = "fk_student_id"))
    Student student;

    @ManyToOne
    @JoinColumn(name = "subject_id", nullable = false, foreignKey = @ForeignKey(name = "fk_subject_id"))
    Subject subject;

    @Column(name = "score1", precision = 5, scale = 2)
    double score1;

    @Column(name = "score2", precision = 5, scale = 2)
    double score2;
}
