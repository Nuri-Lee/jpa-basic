package jpabook.jpashop.domain;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

// 주문 목록
@Entity
@Table(name = "ORDERS") // DB 에는 ORDER 가 에약으로 걸려있다. (ex. ORDER BY ) -> ORDER 을 썼을 때 안되는 DB도 있기 때문
public class Order {

    @Id
    @GeneratedValue
    @Column(name = "ORDER_ID")
    private Long id;

//    @Column(name = "MEMBER_ID")
//    private Long memberId;
    @ManyToOne      // Order 입장에서 Member 임.
    @JoinColumn(name = "MEMBER_ID")     // 이것을 양방향 관계로 갖고 싶다면
    private Member member;

    // Order 입장에서는 orderItems 가 비즈니스적으로 충분히 가치 있다.
    // 왜냐하면 주문서를 뽑았는데 주문서와 연관된 어떤 아이템 목록을 가지고 찾는 경우가 제일 많다.
    @OneToMany(mappedBy = "order")  // OrderItem 에 있는 order_id 와 매핑한 게 OrderItem 에 있는 order 임
    private List<OrderItem> orderItems = new ArrayList<>();

    public Member getMember() {
        return member;
    }

    private LocalDateTime orderDate;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    public void addOrderItem(OrderItem orderItem) {
        orderItems.add(orderItem);
        orderItem.setOrder(this);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setMember(Member member) {
        this.member = member;
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
