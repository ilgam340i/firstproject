package ru.vagapov.userapi.dao.Impl;

import jakarta.persistence.Query;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import ru.vagapov.userapi.dao.UserDao;
import ru.vagapov.userapi.entity.UserEntity;
import ru.vagapov.userapi.util.HibernateUtil;
import java.time.*;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    private final HibernateUtil hibernateUtil = new HibernateUtil();

    @Override
    public void createUsersTable() {
        try (Session session = hibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            String sql = "CREATE TABLE IF NOT EXISTS " +
                    "users (id BIGINT GENERATED ALWAYS AS IDENTITY, " +
                    "firstName VARCHAR(255), lastName VARCHAR(255), " +
                    "birthDate DATE," +
                    " birthPlace VARCHAR(255), " +
                    "age SMALLINT)";
            Query query = session.createNativeQuery(sql);
            query.executeUpdate();
            transaction.commit();
        }
    }

    @Override
    public void dropUsersTable() {
        try (Session session = hibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            String sql = "DROP TABLE IF EXISTS users";
            Query query = session.createNativeQuery(sql);
            query.executeUpdate();
            transaction.commit();
        }
    }

    @Override
    public void saveUser(String firstName, String lastName, LocalDate birthDate, String birthPlace, Byte age) {

        try (Session session = hibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            UserEntity user = new UserEntity();
            user.setFirstName(firstName);
            user.setLastName(lastName);
            user.setBirthDate(birthDate);
            user.setBirthPlace(birthPlace);
            user.setAge(age);
            session.persist(user);
            transaction.commit();
        }
    }

    @Override
    public void removeUserById(Long id) {
        try (Session session = hibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            UserEntity user = session.find(UserEntity.class, id);
            session.refresh(user);
            transaction.commit();
        }
    }

    @Override
    public List<UserEntity> getAllUsers() {
//        try (Session session = hibernateUtil.getSessionFactory().openSession()) {
//            return session.createQuery("from users", UserEntity.class).list();
        try (Session session = hibernateUtil.getSessionFactory().openSession()) {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<UserEntity> criteria = builder.createQuery(UserEntity.class);
            criteria.from(UserEntity.class);
            return session.createQuery(criteria).getResultList();
        }
    }

    @Override
    public void cleanUsersTable() {
        try (Session session = hibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            String sql = "TRUNCATE TABLE users";
            Query query = session.createNativeQuery(sql);
            query.executeUpdate();
            transaction.commit();
        }
    }
}