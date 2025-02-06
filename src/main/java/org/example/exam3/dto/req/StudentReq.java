package org.example.exam3.dto.req;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class StudentReq {
    Integer studentId;
    String studentCode;
    String studentName;
    String subjectName;
    double score1;
    double score2;
    Integer credit;
    String grade;
}
