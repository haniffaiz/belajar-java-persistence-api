package programmerzamannow.jpa;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import org.junit.jupiter.api.Test;
import programmerzamannow.jpa.entity.Customer;
import programmerzamannow.jpa.util.JpaUtil;

public class ColumnTest {

    @Test
    void column() {
        EntityManagerFactory entityManagerTest = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerTest.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        Customer customer = new Customer();
        customer.setId("1");
        customer.setName("Hanif");
        customer.setPrimaryEmail("contoh@example.com");
        customer.setFullName("Hanif Faiz Hidayat");

        entityManager.persist(customer);

        entityTransaction.commit();
        entityManager.close();
    }

    @Test
    void transientTest() {
        EntityManagerFactory entityManagerTest = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerTest.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        Customer customer = new Customer();
        customer.setId("4");
        customer.setName("Hanif");
        customer.setPrimaryEmail("contoh@example.com");
        customer.setFullName("Hanif Faiz Hidayat");

        entityManager.persist(customer);

        entityTransaction.commit();
        entityManager.close();
    }
}
