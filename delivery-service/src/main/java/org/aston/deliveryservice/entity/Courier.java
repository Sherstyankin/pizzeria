package org.aston.deliveryservice.entity;

import jakarta.persistence.*;
import lombok.*;
import org.aston.deliveryservice.constants.CourierStatus;

@Getter
@Setter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "couriers")
public class Courier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "status")
    private CourierStatus status;
}
