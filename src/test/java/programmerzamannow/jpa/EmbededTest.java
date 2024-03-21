package programmerzamannow.jpa;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import programmerzamannow.jpa.entity.*;
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

    @Test
    void embededId() {
        EntityManagerFactory entityManagerTest = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerTest.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        DepartmentId id = new DepartmentId();
        id.setCompany_id("pzn");
        id.setDepartment_id("tech");

        Department department = new Department();
        department.setId(id);
        department.setName("Teknologi");

        entityManager.persist(department);


        entityTransaction.commit();
        entityManager.close();
    }

    @Test
    void embededIdFind() {
        EntityManagerFactory entityManagerTest = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerTest.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        DepartmentId id = new DepartmentId();
        id.setCompany_id("pzn");
        id.setDepartment_id("tech");

        Department department = entityManager.find(Department.class, id);
        Assertions.assertEquals("Teknologi", department.getName());


        entityTransaction.commit();
        entityManager.close();
    }

}
