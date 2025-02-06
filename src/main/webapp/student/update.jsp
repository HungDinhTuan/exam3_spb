<%@ page import="java.util.List" %>
<%@ page import="org.example.exam3.entity.Subject" %>
<%@ page import="org.example.exam3.entity.Student" %>
<%@ page import="org.example.exam3.entity.StudentScore" %>
<%@ page import="java.util.Objects" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    Subject subject = (Subject) request.getAttribute("subject");
    Student student = (Student) request.getAttribute("student");
    StudentScore studentScore = (StudentScore) request.getAttribute("studentScore");
%>
<!DOCTYPE html>
<html>
<head>
    <title>Player</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
</head>
<body>
<div class="container">
    <h1>Create a student</h1>
    <form method="post" action="?action=update">
        <div class="mb-3">
            <input type="hidden" name="studentId" class="form-control" value="<%= student.getStudentId() %>">
        </div>
        <div class="mb-3">
            <label class="form-label">Student Name</label>
            <input type="text" name="name" class="form-control" value="<%= student.getFullName() %>">
        </div>
        <div class="mb-3">
            <label class="form-label">Student Code</label>
            <input type="text" name="studentCode" class="form-control" value="<%= student.getStudentCode() %>">
        </div>
        <div class="mb-3">
            <label class="form-label">Address</label>
            <input type="text" name="address" class="form-control" value="<%= student.getAddress() %>">
        </div>
        <div class="mb-3">
            <label class="form-label">Subject name</label>
            <select class="form-select" name="subjectId">
                <%
                    List<Subject> subjects = (List<Subject>) request.getAttribute("subjects");
                    Integer selectedSubjectId = studentScore != null && studentScore.getSubject().getSubjectId() != null
                            ? studentScore.getSubject().getSubjectId()
                            : null;
                    for (Subject s : subjects) {
                        boolean isSelected = selectedSubjectId != null && Objects.equals(s.getSubjectId(), selectedSubjectId);
                %>
                <option value="<%= s.getSubjectId() %>" <%= isSelected ? "selected" : "" %>><%= s.getSubjectName() %></option>
                <%
                    }
                %>
            </select>
        </div>
        <div class="mb-3">
            <label class="form-label">Score 1</label>
            <input type="number" name="score1" class="form-control" value="<%= studentScore.getScore1() %>">
        </div>
        <div class="mb-3">
            <label class="form-label">Score 2</label>
            <input type="number" name="score2" class="form-control" value="<%= studentScore.getScore2() %>">
        </div>
        <button type="submit" class="btn btn-success">Submit</button>
    </form>
</div>
</body>
</html>



