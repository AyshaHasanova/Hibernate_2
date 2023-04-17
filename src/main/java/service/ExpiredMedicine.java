package service;

import Dao.MedicineDao;
import model.Medicine;
import org.hibernate.Session;
import org.hibernate.Transaction;

import util.HibernateSessionFactoryUtil;

import java.time.LocalDate;
import java.util.List;

public class ExpiredMedicine {
    private final MedicineDao medicineDao = new MedicineDao();

    public ExpiredMedicine() {
    }

    public void findAndDeletExpiredMedicine() {
        Transaction transaction = null;
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            List<Medicine> medicine = medicineDao.findAll();
            medicine.forEach(medicines -> {
                if (medicines.getDate().isBefore(LocalDate.now())) {
                    session.delete(medicines);
                }
            });
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }

    }

}





