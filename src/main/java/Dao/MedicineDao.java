package Dao;

import util.HibernateSessionFactoryUtil;
import model.Medicine;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.Collections;
import java.util.List;

public class MedicineDao {
    public static List<Medicine> findAll() {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            return session.createQuery("From Medicine ").list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }
    public void delete(Medicine medicines) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(medicines);
        tx1.commit();
        session.close();
    }
    public void update(Medicine medicine) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.update(medicine);
        transaction.commit();
        session.close();
    }
}

