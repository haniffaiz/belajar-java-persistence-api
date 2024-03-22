package programmerzamannow.jpa;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import programmerzamannow.jpa.entity.Credential;
import programmerzamannow.jpa.entity.Customer;
import programmerzamannow.jpa.entity.User;
import programmerzamannow.jpa.entity.Wallet;
import programmerzamannow.jpa.util.JpaUtil;

public class EntityRelationshipTest {

    @Test
    void oneToOnePersist() {
        EntityManagerFactory entityManagerTest = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerTest.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        Credential credential = new Credential();
        credential.setId("faiz");
        credential.setEmail("faiz@example.com");
        credential.setPassword("rahasia");
        entityManager.persist(credential);

        User user = new User();
        user.setId("faiz");
        user.setName("Hanif Faiz Hidayat");
        entityManager.persist(user);

        entityTransaction.commit();
        entityManager.close();
    }

    @Test
    void oneToOneFind() {
        EntityManagerFactory entityManagerTest = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerTest.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        User user = entityManager.find(User.class,"faiz");
        Assertions.assertNotNull(user.getCredential());
        Assertions.assertNotNull(user.getWallet());

        Assertions.assertEquals("faiz@example.com", user.getCredential().getEmail());
        Assertions.assertEquals("rahasia", user.getCredential().getPassword());
        Assertions.assertEquals(1_000_000, user.getWallet().getBalance());

        entityTransaction.commit();
        entityManager.close();
    }

    @Test
    void oneToOneJoinColumn() {
        EntityManagerFactory entityManagerTest = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerTest.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        User user = entityManager.find(User.class,"faiz");

        Wallet wallet = new Wallet();
        wallet.setUser(user);
        wallet.setBalance(1_000_000L);
        entityManager.persist(wallet);

        entityTransaction.commit();
        entityManager.close();
    }

}
