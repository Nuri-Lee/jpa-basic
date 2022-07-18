package jpabook.jpashop;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.domain.Order;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {

            //## 001 >> Order.java 의 ## 001 과 이어보기
            Order order = em.find(Order.class, 1L);
            // 주문한 회원을 알고 싶다. Order 객체에서 memberId 를 얻어옴.
            Long memberId = order.getMemberId();

            // Order 객체에서 얻어온 memberId 로 Member 객체에서 memberId 를 또 찾아야한다.
            Member member = em.find(Member.class, memberId);

            // -> 객체지향스럽지 못하다.
            // 객체지향스러우려면 Member 을 바로 끄집어낼 수 있어야 한다.
            // 객체는 객체그래프로 참조로 쭉쭉 찾아갈 수 있어야 한다.
            Member findMember = order.getMember();
            findMember.getId();

        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }
}
