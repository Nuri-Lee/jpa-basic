package jpabook.jpashop.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

// 상품
@Entity
public class Item {

    // Item 입장에서 필요하면 양방향을 만들 수 있다.
    // 하나의 item 을 보고 ORDER_ITEM 을 추적하고 싶을 때 그럴 수 있는데,
    // 이렇게 할 일이 거의 없다.
    // ITEM 입장에서 보면 ORDER_ITEM (주문) 입장에서 ITEM (상품) 이
    // 뭐가 들어왔는지 제일 중요한데
    // ITEM 입장에서 보면 어떤 주문에 의해서 상품되었는지는 연관관계를 찾아갈 만큼 중요하지 않다.
    // 주문서를 보고 아이템을 찾지, 아이템을 보고 어떤 주문서들에 주문됐는지 찾을 일이 별로 없다.
    @Id
    @GeneratedValue
    @Column(name = "ITEM_ID")
    private Long id;

    private String name;
    private int price;
    private int stockQuantity;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(int stockQuantity) {
        this.stockQuantity = stockQuantity;
    }
}
