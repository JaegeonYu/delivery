package com.example.bio.domain;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Delivery {
    @Id @GeneratedValue
    @Column(name = "delivery_id")
    private Long id;
    @OneToOne(mappedBy = "delivery", fetch = FetchType.LAZY)
    private Order order;

    @Embedded
    private Address address;

    @Enumerated(EnumType.STRING)
    private DeliveryStatus deliveryStatus;
    @Builder
    public Delivery(Order order, Address address, DeliveryStatus deliveryStatus) {
        this.order = order;
        this.address = address;
        this.deliveryStatus = deliveryStatus;
    }

    public void beOrder(Order order){
        this.order = order;
    }

}
