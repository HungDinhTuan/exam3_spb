<%@ page import="java.util.List" %>
<%@ page import="org.example.exam3.dto.req.StudentReq" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Class</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
</head>
<body>
<div class="container">
    <h1>List student</h1>
    <a href="?action=new">Create a student</a>
    <table class="table">
        <thead>
        <tr>
            <th scope="col">Id</th>
            <th scope="col">Student Code</th>
            <th scope="col">Full Name</th>
            <th scope="col">Subject name</th>
            <th scope="col">Score 1</th>
            <th scope="col">Score 2</th>
            <th scope="col">Credit</th>
            <th scope="col">Grade</th>
            <th scope="col"></th>
        </tr>
        </thead>
        <tbody>
        <% for (StudentReq srq : (List<StudentReq>)request.getAttribute("studentReqs")){ %>
        <tr>
            <th scope="row"><%= srq.getStudentId() %></th>
            <td><%= srq.getStudentCode() %></td>
            <td><%= srq.getStudentName() %></td>
            <td><%= srq.getSubjectName()%></td>
            <td><%= srq.getScore1() %></td>
            <td><%= srq.getScore2() %></td>
            <td><%= srq.getCredit() %></td>
            <td><%= srq.getGrade() %></td>
            <td></td>
            <td>
                <a href="?action=edit&playerId=<%= srq.getStudentId() %>" class="btn btn-primary">Edit</a>
                <a href="?action=delete&playerId=<%= srq.getStudentId()%>" class="btn btn-danger" onclick="return confirm('Are you sure you want to delete this player?');">Delete</a>
            </td>
        </tr>
        <%}%>
        </tbody>
    </table>
</div>
</body>
</html>



