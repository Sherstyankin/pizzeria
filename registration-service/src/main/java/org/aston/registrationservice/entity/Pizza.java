package org.aston.registrationservice.entity;

import jakarta.persistence.*;
import lombok.*;


@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "PIZZAS")
@NamedQuery(name = "Pizza.findByUserIdAndPizzaName",
        query = "SELECT p FROM Pizza p WHERE p.user.id = :userId AND p.pizzaName = :pizzaName")
public class Pizza {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "PIZZA_NAME")
    private String pizzaName;

    @Column(name = "COUNT")
    private Integer count;
    @ManyToOne(cascade = CascadeType.ALL) //, fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    private User user;

}
