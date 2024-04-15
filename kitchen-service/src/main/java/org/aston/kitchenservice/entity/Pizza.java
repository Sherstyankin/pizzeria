package org.aston.kitchenservice.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;

@Builder
@Entity
@Table(name = "pizzas")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Pizza {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Override
    public String toString() {
        return "Pizza: " +
                "\nid = " + id +
                "\nname = " + name +
                "\ndescription = " + description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pizza pizza = (Pizza) o;
        return id == pizza.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
