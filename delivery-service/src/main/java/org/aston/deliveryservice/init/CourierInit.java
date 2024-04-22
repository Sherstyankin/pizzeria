package org.aston.deliveryservice.init;

import jakarta.annotation.PostConstruct;
import org.aston.deliveryservice.constants.CourierStatus;
import org.aston.deliveryservice.entity.Courier;
import org.aston.deliveryservice.repository.CourierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CourierInit {
    @Autowired
    private CourierRepository repository;

    @PostConstruct
    private void init() {
        repository.save(Courier.builder().name("John").status(CourierStatus.SLEEP).build());
        repository.save(Courier.builder().name("Katrine").status(CourierStatus.SLEEP).build());
        repository.save(Courier.builder().name("Stephen").status(CourierStatus.SLEEP).build());
    }
}
