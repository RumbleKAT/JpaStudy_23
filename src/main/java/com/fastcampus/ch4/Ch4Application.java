package com.fastcampus.ch4;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import java.util.Date;

@SpringBootApplication
public class Ch4Application implements CommandLineRunner {
    @Autowired
    EntityManagerFactory emf;

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(Ch4Application.class);
        app.setWebApplicationType(WebApplicationType.NONE);
        app.run(args);
    }

    @Override
    public void run(String... args) throws Exception {
        EntityManager em = emf.createEntityManager();
        System.out.println("emf = " + emf);
        EntityTransaction tx = em.getTransaction();

        User user = new User();
        user.setId("aaa");
        user.setPassword("1234");
        user.setName("Lee");
        user.setEmail("aaa@aaa.com");
        user.setInDate(new Date());
        user.setUpDate(new Date());

        tx.begin();
        em.persist(user); //같은 엔티티를 여러번 해도 한번만 insert한다.

        //2. 변경
        user.setPassword("4321");
        user.setEmail("bbb@bbb.com");
        tx.commit();

        //3. 조회
        User user2 = em.find(User.class, "aaa");
        System.out.println("user=user2 = " + (user == user2)); // 영속 상태에서 비교한다.
        // 없는 경우, select 해서 가져온다.
        User user3 = em.find(User.class, "bbb");
        System.out.println("user3 = " + user3);

        //4. 삭제
        tx.begin();
        em.remove(user);
        tx.commit();
    }
}
