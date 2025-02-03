package org.example.exam3.dao;

import org.example.exam3.entity.Subject;
import org.example.exam3.untils.HibernateUtil;
import org.hibernate.Session;

import java.util.List;

public class SubjectDao {
    public List<Subject> getSubjects() {
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from Subject", Subject.class).list();
        }
    }

    public Subject getSubject(int id) {
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Subject.class, id);
        }
    }
}
