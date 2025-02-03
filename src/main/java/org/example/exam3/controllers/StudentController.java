package org.example.exam3.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.exam3.dao.StudentDao;
import org.example.exam3.dao.StudentScoreDao;
import org.example.exam3.dao.SubjectDao;

import java.io.IOException;

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
            case "list": break;
            case "new": break;
            case "edit": break;
            case "delete": break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        switch (action) {
            case "insert": break;
            case "update": break;
        }
    }
}
