<%@ page import="java.util.List" %>
<%@ page import="org.example.exam3.entity.Subject" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
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
    <form method="post" action="?action=insert">
        <div class="mb-3">
            <label class="form-label">Student Name</label>
            <input type="text" name="name" class="form-control">
        </div>
        <div class="mb-3">
            <label class="form-label">Student Code</label>
            <input type="text" name="studentCode" class="form-control">
        </div>
        <div class="mb-3">
            <label class="form-label">Address</label>
            <input type="text" name="address" class="form-control">
        </div>
        <div class="mb-3">
            <label class="form-label">Subject name</label>
            <select class="form-select" name="subjectId">
                <% for (Subject s : (List<Subject>)request.getAttribute("subjects")){%>
                <option value="<%= s.getSubjectId() %>"><%= s.getSubjectName() %></option>
                <%}%>
            </select>
        </div>
        <div class="mb-3">
            <label class="form-label">Score 1</label>
            <input type="number" name="score1" class="form-control">
        </div>
        <div class="mb-3">
            <label class="form-label">Score 2</label>
            <input type="number" name="score2" class="form-control">
        </div>
        <button type="submit" class="btn btn-success">Submit</button>
    </form>
</div>
</body>
</html>


