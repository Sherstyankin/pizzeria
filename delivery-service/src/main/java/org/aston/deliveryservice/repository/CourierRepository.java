package org.aston.deliveryservice.repository;

import java.util.List;

import org.aston.deliveryservice.entity.Courier;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface CourierRepository extends CrudRepository<Courier, Long> {
    @Query(value = "SELECT * FROM couriers WHERE status = 0", nativeQuery = true)
    List<Courier> getFreeCouriers();
}
