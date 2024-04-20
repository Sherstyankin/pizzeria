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
public class Pizza {
    @Id
    @Column(name = "ID")
    private Long id;

    @Column(name = "PIZZA_NAME")
    private String pizza_name;

    @Column(name = "COUNT")
    private Integer count;
    @ManyToOne(cascade = CascadeType.ALL) //, fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    private User user;

}
