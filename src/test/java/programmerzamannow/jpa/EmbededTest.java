package programmerzamannow.jpa;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import org.junit.jupiter.api.Test;
import programmerzamannow.jpa.entity.Customer;
import programmerzamannow.jpa.entity.Member;
import programmerzamannow.jpa.entity.Name;
import programmerzamannow.jpa.util.JpaUtil;

public class EmbededTest {

    @Test
    void embeded() {
        EntityManagerFactory entityManagerTest = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerTest.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        Name name = new Name();
        name.setTitle("Mr");
        name.setFirstName("Hanif");
        name.setMiddleName("Faiz");
        name.setLastName("Hidayat");

        Member member = new Member();
        member.setEmail("hanif@example.com");
        member.setName(name);

        entityManager.persist(member);

        entityTransaction.commit();
        entityManager.close();
    }

}
