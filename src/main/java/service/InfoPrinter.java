package service;


import Dao.MedicineDao;
import model.Medicine;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateSessionFactoryUtil;

import java.util.List;

public class InfoPrinter  {
    private final MedicineDao medicineDao = new MedicineDao();

    public InfoPrinter() {
    }

    public void printMedicinesInformation() {
        Transaction transaction = null;
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            List<Medicine> medicine = medicineDao.findAll();
            medicine.forEach(medicines -> System.out.println(medicine));
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }

    }

}
