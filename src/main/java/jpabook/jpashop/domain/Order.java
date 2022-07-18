package jpabook.jpashop.domain;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "ORDERS") // DB 에는 ORDER 가 에약으로 걸려있다. (ex. ORDER BY ) -> ORDER 을 썼을 때 안되는 DB도 있기 때문
public class Order {

    @Id
    @GeneratedValue
    @Column(name = "ORDER_ID")
    private Long id;

    @Column(name = "MEMBER_ID")
    private Long memberId;

    /*
    * @Column(name = "MEMBER_ID")
    * private Long memberId;
    * -> 객체지향스럽지 못한 부분이다.
    * 왜냐하면 Order 코드를 사용한다고 가정해봤을 때
    * ## 001 >> JpaMain.java 의 ## 001 과 이어보기
    * 이런 방식의 설계는 관계형 DB 에 맞춘 설계이다.
    *
    * */

    // 객체지향스러우려먼 이렇게 해야한다.
    private Member member;

    public Member getMember() {
        return member;
    }

    private LocalDateTime orderDate;

    // JPA 나 Hibernate 를 사용하면 zipcode 그대로 DB 에 나간다.
    // Spring Boot 에서는 Hibernate 설정을 원하는대로 overriding 할 수 있다.
    // ex. orderDate 를 기본으로 가면 orderDate 그대로 가는데
    // DB 에서 원하는 건 order_date 또는 ORDER_DATE 이다.
    // Spring Boot 는 java 의 orderDate 를 자동으로 order_date 로 바꿔준다.

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }
}
