package org.example.exam3.controllers;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.exam3.dao.StudentDao;
import org.example.exam3.dao.StudentScoreDao;
import org.example.exam3.dao.SubjectDao;
import org.example.exam3.dto.req.StudentReq;
import org.example.exam3.entity.Student;
import org.example.exam3.entity.StudentScore;
import org.example.exam3.entity.Subject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@WebServlet(name = "StudentController", value = "/student")
public class StudentController extends HttpServlet {
    private StudentDao studentDao;
    private StudentScoreDao studentScoreDao;
    private SubjectDao subjectDao;

    @Override
    public void init() throws ServletException {
        super.init();
        studentDao = new StudentDao();
        studentScoreDao = new StudentScoreDao();
        subjectDao = new SubjectDao();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null || action.isEmpty()) action = "list";
        switch (action) {
            case "list": showListStudent(req, resp); break;
            case "new": formNewStudent(req, resp); break;
            case "edit": formEditStudent(req, resp); break;
            case "delete": deleteStudent(req, resp); break;
        }
    }

    private String countGrade(double score1, double score2){
        String grade = null;
        double score = score1 * 0.3 + score2 * 0.7;
        if (score >= 8.0 && score <= 10) {
           return grade = "A";
        } else if (score >= 6.0 && score < 8.0) {
           return grade = "B";
        } else if (score >= 4.0 && score < 6.0) {
            return grade = "D";
        } else if (score >= 0.0 && score < 4.0) {
            return grade = "F";
        }
        return "Invalid score";
    }

    private void showListStudent(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Student> students = studentDao.getStudents();
        List<Subject> subjects = subjectDao.getSubjects();
        List<StudentScore> studentScores = studentScoreDao.getAllStudentScore();
        List<StudentReq> studentReqs = new ArrayList<>();

        for (Student student : students) {
            for (StudentScore studentScore : studentScores) {
                if(Objects.equals(studentScore.getStudent().getStudentId(), student.getStudentId())) {
                    Subject subject = studentScore.getSubject();
                    StudentReq studentReq = StudentReq.builder()
                            .studentId(student.getStudentId())
                            .studentCode(student.getStudentCode())
                            .studentName(student.getFullName())
                            .subjectName(subject.getSubjectName())
                            .credit(subject.getCredit())
                            .score1(studentScore.getScore1())
                            .score2(studentScore.getScore2())
                            .grade(countGrade(studentScore.getScore1(), studentScore.getScore2()))
                            .build();
                    studentReqs.add(studentReq);
                }
            }
        }

        req.setAttribute("studentReqs", studentReqs);
        RequestDispatcher rd = req.getRequestDispatcher("student/list.jsp");
        rd.forward(req, resp);
    }

    private void formNewStudent(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Subject> subjects = subjectDao.getSubjects();
        req.setAttribute("subjects", subjects);
        RequestDispatcher rd = req.getRequestDispatcher("student/create.jsp");
        rd.forward(req, resp);
    }

    private void formEditStudent(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int studentId = Integer.parseInt(req.getParameter("studentId"));
        List<Subject> subjects = subjectDao.getSubjects();
        Student student = studentDao.getStudent(studentId);
        StudentScore studentScore = studentScoreDao.getStudentScoreByStudentId(studentId);
        Subject subject = new Subject();
        for (Subject s : subjects) {
            if(Objects.equals(s.getSubjectName(), studentScore.getSubject().getSubjectName())) {
                subject = Subject.builder()
                        .subjectName(s.getSubjectName())
                        .credit(s.getCredit())
                        .build();
            }
        }

        req.setAttribute("subject", subject);
        req.setAttribute("studentScore", studentScore);
        req.setAttribute("student", student);
        req.setAttribute("subjects", subjects);
        RequestDispatcher rd = req.getRequestDispatcher("student/update.jsp");
        rd.forward(req, resp);
    }

    private void deleteStudent(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int studentId = Integer.parseInt(req.getParameter("studentId"));
        studentScoreDao.deleteStudentScore(studentId);
        studentDao.deleteStudent(studentId);

        resp.sendRedirect("student?action=list");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        switch (action) {
            case "insert": addNewStudent(req, resp); break;
            case "update": break;
        }
    }

    private void addNewStudent(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String studentName = req.getParameter("name");
            String studentCode = req.getParameter("studentCode");
            String address = req.getParameter("address");
            int subjectId = Integer.parseInt(req.getParameter("subjectId"));
            double score1 = Double.parseDouble(req.getParameter("score1"));
            double score2 = Double.parseDouble(req.getParameter("score2"));

            Subject subject = Subject.builder()
                    .subjectId(subjectId)
                    .build();

            Student student = Student.builder()
                    .fullName(studentName)
                    .studentCode(studentCode)
                    .address(address)
                    .build();
            studentDao.saveStudent(student);

            StudentScore studentScore = StudentScore.builder()
                    .subject(subject)
                    .score1(score1)
                    .score2(score2)
                    .student(student)
                    .build();
            studentScoreDao.saverStudentScore(studentScore);

            resp.sendRedirect("student?action=list");
        }catch (Exception e) {
            formNewStudent(req, resp);
        }
    }

    private void updateStudent(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            int studentId = Integer.parseInt(req.getParameter("studentId"));
            String studentName = req.getParameter("name");
            String studentCode = req.getParameter("studentCode");
            String address = req.getParameter("address");
            int subjectId = Integer.parseInt(req.getParameter("subjectId"));
            double score1 = Double.parseDouble(req.getParameter("score1"));
            double score2 = Double.parseDouble(req.getParameter("score2"));

            Student student = Student.builder()
                    .fullName(studentName)
                    .address(address)
                    .studentCode(studentCode)
                    .build();
            studentDao.updateStudent(student);

            Subject subject = subjectDao.getSubject(subjectId);
            if (subject == null) {
                throw new IllegalArgumentException("Subject not found");
            }

            StudentScore studentScore = StudentScore.builder()
                    .student(student)
                    .score1(score1)
                    .score2(score2)
                    .subject(subject)
                    .build();
            studentScoreDao.saverStudentScore(studentScore);

            resp.sendRedirect("student?action=list");
        }catch (Exception e) {
            formEditStudent(req, resp);
        }
    }
}
