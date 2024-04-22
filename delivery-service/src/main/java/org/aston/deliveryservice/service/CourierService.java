package org.aston.deliveryservice.service;

import org.aston.deliveryservice.constants.CourierStatus;
import org.aston.deliveryservice.entity.Courier;
import org.aston.deliveryservice.repository.CourierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class CourierService {

    @Autowired
    private CourierRepository repository;

    public Courier get() {
        var free = repository.getFree();
        if (free.size() == 0) {
            throw new NoSuchElementException("All couriers are busy");
        } else {
            return free.stream().findAny().get();
        }
    }

    public void changeStatus(Courier courier, CourierStatus status) {
        repository.setStatus(courier.getId(), CourierStatus.IN_PROGRESS);
    }
}
