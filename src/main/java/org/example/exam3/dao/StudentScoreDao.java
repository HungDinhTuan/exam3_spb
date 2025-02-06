package org.example.exam3.dao;

import org.example.exam3.entity.StudentScore;
import org.example.exam3.untils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class StudentScoreDao {
    public List<StudentScore> getAllStudentScore() {
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from StudentScore", StudentScore.class).list();
        }
    }

    public StudentScore getStudentScoreByStudentId(int studentScoreId) {
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(StudentScore.class, studentScoreId);
        }
    }

    public void saverStudentScore(StudentScore studentScore) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.save(studentScore);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
    }

    public void updateStudentScore(StudentScore studentScore) {
        Transaction tx = null;
        Session session = HibernateUtil.getSessionFactory().openSession();

        try {
            tx = session.beginTransaction();
            StudentScore existingStudentScore = session.get(StudentScore.class, studentScore.getStudentScoreId());
            if (existingStudentScore != null) {
                existingStudentScore = StudentScore.builder()
                        .student(studentScore.getStudent())
                        .score1(studentScore.getScore1())
                        .score2(studentScore.getScore2())
                        .subject(studentScore.getSubject())
                        .build();
                session.update(existingStudentScore);
            }else {
                throw new IllegalArgumentException("Student Score isn't exist");
            }
        }catch (Exception e) {
            if (tx != null) tx.rollback();
            throw e;
        }finally {
            session.close();
        }
    }

    public void deleteStudentScore(int studentScoreId) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            StudentScore existingStudentScore = session.get(StudentScore.class, studentScoreId);
            if (existingStudentScore != null) {
                session.delete(existingStudentScore);
            }
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
    }
}
