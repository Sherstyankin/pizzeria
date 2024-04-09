package org.aston.registrationservice.service;

import jakarta.persistence.EntityNotFoundException;
import org.aston.registrationservice.dto.DeliveredOrdersDto;
import org.aston.registrationservice.entity.DeliveredOrders;
import org.aston.registrationservice.exceptions.ObjectNotFoundException;
import org.aston.registrationservice.mapper.DeliveredOrdersMapper;
import org.aston.registrationservice.repository.DeliveredOrdersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DeliveredOrdersServiceImpl implements DeliveredOrdersService {
    private final DeliveredOrdersRepository deliveredOrdersRepository;
    private final DeliveredOrdersMapper deliveredOrdersMapper;
@Autowired
    public DeliveredOrdersServiceImpl(DeliveredOrdersRepository deliveredOrdersRepository, DeliveredOrdersMapper deliveredOrdersMapper) {
        this.deliveredOrdersRepository = deliveredOrdersRepository;
        this.deliveredOrdersMapper = deliveredOrdersMapper;
    }

    @Override
    public DeliveredOrdersDto getDeliveredOrderById(Long id) {
        validateDeliveredOrderById(id);
        Optional<DeliveredOrders> order = deliveredOrdersRepository.findById(id);
        return order.map(deliveredOrdersMapper::toDeliveredOrdersDto).orElse(null);
    }
    @Override
    public List<DeliveredOrdersDto> findAllDeliveredOrdersByUserId (Long userId){
    return deliveredOrdersRepository.findAllDeliveredOrdersByUserId(userId).stream().map(
            deliveredOrders -> new DeliveredOrdersDto(deliveredOrders.getId(),
                    deliveredOrders.getPizza_name(), deliveredOrders.getCount(),
                    deliveredOrders.getUser())
    ).collect(Collectors.toList());
    }



    @Override
    public List<DeliveredOrdersDto> getAllDeliveredOrders() {
        List<DeliveredOrders> orders = deliveredOrdersRepository.findAll();
        return orders.stream().map(deliveredOrdersMapper::toDeliveredOrdersDto).collect(Collectors.toList());
    }

    @Override
    public DeliveredOrdersDto saveNewDeliveredOrder(DeliveredOrdersDto deliveredOrdersDto) {
        DeliveredOrders order = deliveredOrdersMapper.toDeliveredOrders(deliveredOrdersDto);
        order = deliveredOrdersRepository.save(order);
        return deliveredOrdersMapper.toDeliveredOrdersDto(order);
    }

    @Override
    public DeliveredOrdersDto updateDeliveredOrder(Long id, DeliveredOrdersDto deliveredOrdersDto) {
        validateDeliveredOrderById(id);
        DeliveredOrders order = deliveredOrdersRepository.findById(id).get();

        if (deliveredOrdersDto != null) {
            order.setCount(deliveredOrdersDto.count());
        }
        order = deliveredOrdersRepository.save(order);
        return deliveredOrdersMapper.toDeliveredOrdersDto(order);

    }

    @Override
    public void deleteDeliveredOrder(Long id) {
        validateDeliveredOrderById(id);
        deliveredOrdersRepository.deleteById(id);
    }

    private void validateDeliveredOrderById(Long id) {
        if (!deliveredOrdersRepository.existsById(id)) {
            throw new ObjectNotFoundException("DeliveredOrder with id = " + id + " doesn't exist.");
        }
    }
}
