package org.aston.deliveryservice.repository;

import java.util.List;

import org.aston.deliveryservice.constants.CourierStatus;
import org.aston.deliveryservice.entity.Courier;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import jakarta.transaction.Transactional;

public interface CourierRepository extends CrudRepository<Courier, Long> {
    @Query(value = "SELECT * FROM couriers WHERE status = 0", nativeQuery = true)
    List<Courier> getFree();

    @Modifying
    @Transactional
    @Query(value = "UPDATE couriers SET status = :status WHERE id = :id", nativeQuery = true)
    void setStatus(@Param("id") long id, @Param("status") CourierStatus status);
}
