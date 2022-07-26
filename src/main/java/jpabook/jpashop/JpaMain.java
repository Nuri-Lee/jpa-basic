package jpabook.jpashop;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.domain.Order;
import jpabook.jpashop.domain.OrderItem;

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
            // 주문을 해야 한다면 ,,
            // 주문 객체 생성
            Order order = new Order();
            // 원하는 아이템 추가
//            order.addOrderItem(new OrderItem());

            // 사실 Member 의 orders 와 Order 의 orderItems 는 없어도 상관없다.  (있으면 잘못된 코드라 생각함)
            // 왜냐하면 연관관계 매핑하는 것 자체가 아무 문제 없는 것이다.
            // order.addOrderItem(new OrderItem()); 를 주석처리 하고 아래와 같이 코드를 짜도 된다.
            em.persist(order);
            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(order);

            em.persist(orderItem);

            // 즉, 양방향 연관관계가 아니어도 FK 를 개발하는 데 아무 문제가 없다.
            // 중요한 건 단방향 연관관계만 되도 애플리케이션 중 커맨드성은 다 개발할 수 있다.
            // 그럼에도 양방향 연관관게를 만든 이유는
            // 1.개발성 편의와 2.연관관계 주인이 아닌 것은 조회성이어서 바로 조회하고 싶을 때가 있다.
            // JPQL 을 작성하면
            // Order 을 조회할 때 orderItems 를 끌고 오고 싶을 때 뭐라도 있어야 한다.
            // 실무에서 JPQL 를 복잡하게 짜기 때문에
            // 양방향 연관관계를 걸게 됨
            // 핵심은 가능한 단방향으로 하는 것이다.


        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }
}
