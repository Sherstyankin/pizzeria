package org.aston.registrationservice.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.Objects;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "DELIVERED_ORDERS")
@NamedQuery(name = "Pizza.findAllPizzaByUserId", query = "SELECT o FROM Pizza o WHERE o.user.id = :userId")
public class Pizza {
    @Id
    @Column(name = "ID")
    private Long id;

    @Column(name = "PIZZA_NAME")
    private List<String> pizza_name;

    @Column(name = "COUNT")
    private Integer count;
    @ManyToOne(cascade = CascadeType.ALL) //, fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    private User user;

}
