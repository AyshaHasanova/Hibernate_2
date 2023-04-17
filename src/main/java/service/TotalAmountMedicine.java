package service;

import Dao.MedicineDao;
import model.Medicine;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateSessionFactoryUtil;


import java.util.List;


public class TotalAmountMedicine {
    private final MedicineDao medicineDao = new MedicineDao();

    public TotalAmountMedicine() {

    }
    public void ShowResult(){
        Transaction transaction = null;
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            System.out.println(getSum());
        } catch (
                Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }

    }

public Integer getSum() {
    List<Medicine> medicine = medicineDao.findAll();
    Integer sum = 0;
    for (Medicine medicines : medicine) {
        sum += medicines.getPrice();
    }
    return sum;
}
}


