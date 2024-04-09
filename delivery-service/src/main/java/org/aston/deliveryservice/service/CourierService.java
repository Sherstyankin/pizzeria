package org.aston.deliveryservice.service;

import java.util.List;

import org.aston.deliveryservice.constants.CourierStatus;
import org.aston.deliveryservice.entity.Courier;
import org.aston.deliveryservice.repository.CourierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;

@Service
public class CourierService {
    @Autowired
    private CourierRepository repository;

    public List<Courier> getFreeCouriers() {
        return repository.getFreeCouriers();
    }
    
    @PostConstruct
    private void init() {
        repository.save(Courier.builder().name("John").status(CourierStatus.SLEEP).build());
        repository.save(Courier.builder().name("Katrine").status(CourierStatus.SLEEP).build());
        repository.save(Courier.builder().name("Stephen").status(CourierStatus.SLEEP).build());
    }
}
