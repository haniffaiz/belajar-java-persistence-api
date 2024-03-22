package programmerzamannow.jpa;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import org.junit.jupiter.api.Test;
import programmerzamannow.jpa.entity.Customer;
import programmerzamannow.jpa.entity.Member;
import programmerzamannow.jpa.entity.Name;
import programmerzamannow.jpa.util.JpaUtil;

import java.util.ArrayList;
import java.util.HashMap;

public class CollectionTest {

    @Test
    void create() {
        EntityManagerFactory entityManagerTest = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerTest.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        Name name = new Name();
        name.setFirstName("Hanif");
        name.setMiddleName("Faiz");
        name.setLastName("Hidayat");

        Member member = new Member();
        member.setEmail("test@example.com");
        member.setName(name);

        member.setHobbies(new ArrayList<>());
        member.getHobbies().add("Coding");
        member.getHobbies().add("Gaming");

        entityManager.persist(member);

        entityTransaction.commit();
        entityManager.close();
    }

    @Test
    void update() {
        /*
        perlu diperhatikan ketika melakukan update collection
        sebelum update, prosedurnya delete terlebih dahulu
        baru update pada db. hindari table ke table lain
         */
        EntityManagerFactory entityManagerTest = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerTest.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        Member member = entityManager.find(Member.class,2);

        member.getHobbies().add("Traveling");

        entityManager.merge(member);

        entityTransaction.commit();
        entityManager.close();
    }

    @Test
    void updateSkills() {
        /*
        perlu diperhatikan ketika melakukan update collection
        sebelum update, prosedurnya delete terlebih dahulu
        baru update pada db. hindari table ke table lain
         */
        EntityManagerFactory entityManagerTest = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerTest.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        Member member = entityManager.find(Member.class,2);
        member.setSkills(new HashMap<>());
        member.getSkills().put("Java", 90);
        member.getSkills().put("Golang", 80);
        member.getSkills().put("PHP", 85);


        entityManager.merge(member);

        entityTransaction.commit();
        entityManager.close();
    }

}
