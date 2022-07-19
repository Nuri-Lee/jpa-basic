package helloJpa;

import javax.persistence.*;
import java.util.List;

public class JpaMain {

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

       EntityTransaction tx = em.getTransaction();
       tx.begin();

       try {
           Member member1 = new Member();
           member1.setUsername("A");

           Member member2 = new Member();
           member2.setUsername("B");

           Member member3 = new Member();
           member3.setUsername("C");

           System.out.println("========================");

           // call next value for MEMBER_SEQ 이 2번 호출되는데,
           // 처음 호출하면 call next value for MEMBER_SEQ 는 1이 된다.
           // 그런데 지금 성능 최적화를 하려고 했기 때문에
           // 두 번째 호출을 하게 되는데 이때는 call next value for MEMBER_SEQ 가 51이다.
           // -> 50개씩 호출 해야하는데, 처음 호출시 1이어서 문제가 있다 싶어 또 호출한 것이다.
           // 그 다음 호출되는 값은 51일이지만, 현재 내 호출된 SEQUENCE 값은 2이다.
           // 즉, DB SEQ | 현재 SEQ
           //       1       1
           //       51      2
           //       51      3
           // 이렇게 되는 것이다.
           // 2번의 call next 가 이뤄지지만, 1,2,3,, 씩 증가하다가 51을 만나면 또 50씩 증가해야하기 때문에
           // 이때 또 call next 가 호출된다.

           em.persist(member1); // DB 호출 :: 1, 51
//           em.persist(member2);   // MEMORY 호출
//           em.persist(member3);   // MEMORY 호출

           System.out.println("member1 = " + member1.getId());
           System.out.println("member2 = " + member2.getId());
           System.out.println("member3 = " + member3.getId());

           System.out.println("===========================");
           tx.commit();
       } catch (Exception e){
           tx.rollback();
       } finally {
           em.close();
       }
        emf.close();
    }
}