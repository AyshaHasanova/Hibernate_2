package service;

import Dao.MedicineDao;
import model.Medicine;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateSessionFactoryUtil;

import java.util.List;

public class NumberOfMedicine {
    private final MedicineDao medicineDao = new MedicineDao();

    public NumberOfMedicine() {
    }

    public void checkAmountOfMedicines() {
        Transaction transaction = null;
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            List<Medicine> medicine = medicineDao.findAll();
            medicine.forEach(medicines -> {
                if (medicines.getQuantity() == 0)
                    System.out.println(medicines.getName());
            });
        } catch (
                Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }

    }
}
