package org.aston.registrationservice.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "DELIVERED_ORDERS")
@NamedQuery(name = "DeliveredOrders.findAllDeliveredOrdersByUserId", query = "SELECT o FROM DeliveredOrders o WHERE o.user.id = :userId")
public class DeliveredOrders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "PIZZA_NAME")
    private String pizza_name;

    @Column(name = "COUNT")
    private Integer count;
    @ManyToOne(cascade = CascadeType.ALL) //, fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    private User user;

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof DeliveredOrders orders)) return false;
        return Objects.equals(getId(), orders.getId()) && Objects.equals(getPizza_name(), orders.getPizza_name()) && Objects.equals(getCount(), orders.getCount()) && Objects.equals(getUser(), orders.getUser());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getPizza_name(), getCount(), getUser());
    }

}
