package org.example.exam3.dao;

import org.example.exam3.entity.Student;
import org.example.exam3.untils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class StudentDao {
    public List<Student> getStudents() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from Student", Student.class).list();
        }
    }

    public void saveStudent(Student student) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.save(student);
            tx.commit();
        }catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
    }

    public void updateStudent(Student student) {
        Transaction tx = null;
        Session session = HibernateUtil.getSessionFactory().openSession();

        try {
            tx = session.beginTransaction();
            Student existingStudent = session.get(Student.class, student.getStudentId());
            if (existingStudent != null) {
                existingStudent = Student.builder()
                        .studentCode(student.getStudentCode())
                        .address(student.getAddress())
                        .scores(student.getScores())
                        .fullName(student.getFullName())
                        .build();
                session.update(existingStudent);
            }else {
                throw new IllegalArgumentException("Student isn't exist.");
            }
        }catch (Exception e) {
            if (tx != null) tx.rollback();
            throw e;
        }finally {
            session.close();
        }
    }

    public void deleteStudent(Student student) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            Student existingStudent = session.get(Student.class, student.getStudentId());
            if (existingStudent != null) {
                session.delete(existingStudent);
            }
            tx.commit();
        }catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
    }

    public Student getStudent(int studentId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Student.class, studentId);
        }
    }
}
