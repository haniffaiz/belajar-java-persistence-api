package programmerzamannow.jpa;

import jakarta.persistence.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import programmerzamannow.jpa.entity.Brand;
import programmerzamannow.jpa.entity.Member;
import programmerzamannow.jpa.entity.Product;
import programmerzamannow.jpa.entity.SimpleBrand;
import programmerzamannow.jpa.util.JpaUtil;

import java.util.List;

public class JpaQueryLanguageTest {

    @Test
    void select() {
        EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        TypedQuery<Brand> query = entityManager.createQuery("select b from Brand b", Brand.class);
        List<Brand> brands = query.getResultList();

        for (Brand brand : brands){
            System.out.println(brand.getId()+" : "+brand.getName());
        }


        entityTransaction.commit();
        entityManager.close();
    }

    @Test
    void whereClause() {
        EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        TypedQuery<Member> query = entityManager.createQuery("select m from Member m where m.name.firstName = :firstName and m.name.lastName = :lastName", Member.class);
        query.setParameter("firstName","Hanif");
        query.setParameter("lastName","Hidayat");
        List<Member> members = query.getResultList();

        for (Member member : members){
            System.out.println(member.getId()+" : "+member.getFullName());
        }


        entityTransaction.commit();
        entityManager.close();
    }

    @Test
    void joinClause() {
        EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        TypedQuery<Product> query = entityManager.createQuery("select p from Product p join p.brand b where b.name = :brand", Product.class);
        query.setParameter("brand","Samsung");

        List<Product> products = query.getResultList();

        for (Product product : products){
            System.out.println(product.getId()+" : "+product.getName()+" : "+product.getBrand().getName());
        }


        entityTransaction.commit();
        entityManager.close();
    }

    @Test
    void joinFetchClause() {
        EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        TypedQuery<Product> query = entityManager.createQuery("select p from Product p join fetch p.brand b where b.name = :brand", Product.class);
        query.setParameter("brand","Samsung");

        List<Product> products = query.getResultList();

        for (Product product : products){
            System.out.println(product.getId()+" : "+product.getName()+" : "+product.getBrand().getName());
        }


        entityTransaction.commit();
        entityManager.close();
    }

    @Test
    void orderClause() {
        EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        TypedQuery<Brand> query = entityManager.createQuery("select b from Brand b order by b.name desc", Brand.class);
        List<Brand> brands = query.getResultList();

        for (Brand brand : brands){
            System.out.println(brand.getId()+" : "+brand.getName());
        }


        entityTransaction.commit();
        entityManager.close();
    }

    @Test
    void insertRandomBrand() {
        EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        for (int i = 0; i < 100; i++) {
            Brand brand = new Brand();
            brand.setId(String.valueOf(i));
            brand.setName("Brand " + i);
            entityManager.persist(brand);
        }


        entityTransaction.commit();
        entityManager.close();
    }

    @Test
    void namedQuery() {
        EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        TypedQuery<Brand> query = entityManager.createNamedQuery("Brand.findAllByName", Brand.class);
        query.setParameter("name", "Xiaomi");
        List<Brand> brands = query.getResultList();

        for (Brand brand : brands){
            System.out.println(brand.getId()+" : "+brand.getName());
        }


        entityTransaction.commit();
        entityManager.close();
    }

    @Test
    void selectSomeFields() {
        EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        TypedQuery<Object[]> query = entityManager.createQuery("select b.id, b.name from Brand b where b.name=:name", Object[].class);
        query.setParameter("name", "Xiaomi");
        List<Object[]> objects = query.getResultList();

        for (Object[] object : objects){
            System.out.println(object[0]+" : "+object[1]);
        }


        entityTransaction.commit();
        entityManager.close();
    }

    @Test
    void selectNewConstructor() {
        EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        TypedQuery<SimpleBrand> query = entityManager.createQuery("select new programmerzamannow.jpa.entity.SimpleBrand(b.id, b.name) from Brand b where b.name=:name", SimpleBrand.class);
        query.setParameter("name", "Xiaomi");
        List<SimpleBrand> simpleBrands = query.getResultList();

        for (SimpleBrand simpleBrand : simpleBrands){
            System.out.println(simpleBrand.getId()+" : "+simpleBrand.getName());
        }


        entityTransaction.commit();
        entityManager.close();
    }

    @Test
    void aggregateQuery() {
        EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        TypedQuery<Object[]> query = entityManager.createQuery("select min(p.price), max(p.price), avg(p.price) from Product p", Object[].class);

        Object[] result = query.getSingleResult();

        System.out.println("Min : "+ result[0]);
        System.out.println("Max : "+ result[1]);
        System.out.println("Avg : "+ result[2]);


        entityTransaction.commit();
        entityManager.close();
    }

    @Test
    void aggregateQueryGroupBy() {
        EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        TypedQuery<Object[]> query = entityManager.createQuery("select b.id, min(p.price), max(p.price), avg(p.price) from Product p join p.brand b group by b.id having min(p.price) > :min", Object[].class);
        query.setParameter("min",500_000L);
        List<Object[]> objects = query.getResultList();
        for (Object[] object : objects){
            System.out.println("Brand : "+ object[0]);
            System.out.println("Min : "+ object[1]);
            System.out.println("Max : "+ object[2]);
            System.out.println("Avg : "+ object[3]);
        }


        entityTransaction.commit();
        entityManager.close();
    }

    @Test
    void nativeQuery() {
        EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        Query query = entityManager.createNativeQuery("select * from brands where brands.created_at is not null", Brand.class);

        List<Brand> brands = query.getResultList();
        for (Brand brand : brands){
            System.out.println(brand.getId()+" : "+brand.getName());

        }


        entityTransaction.commit();
        entityManager.close();
    }

    @Test
    void namedNativeQuery() {
        EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        Query query = entityManager.createNamedQuery("Brand.native.findAll", Brand.class);

        List<Brand> brands = query.getResultList();
        for (Brand brand : brands){
            System.out.println(brand.getId()+" : "+brand.getName());

        }


        entityTransaction.commit();
        entityManager.close();
    }

    @Test
    void nonQuery() {
        EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        Query query = entityManager.createQuery("update Brand b set b.name =:name where b.id = :id");
        query.setParameter("name","Samsung Updated");
        query.setParameter("id","samsung");
        int impactedRecords = query.executeUpdate();
        System.out.println("Success update "+impactedRecords+" records");


        entityTransaction.commit();
        entityManager.close();
    }

}
