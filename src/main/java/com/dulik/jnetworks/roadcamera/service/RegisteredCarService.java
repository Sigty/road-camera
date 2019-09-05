package com.dulik.jnetworks.roadcamera.service;

import com.dulik.jnetworks.roadcamera.dto.CarCountDto;
import com.dulik.jnetworks.roadcamera.entity.RegisteredCar;
import com.dulik.jnetworks.roadcamera.repository.RegisteredCarRepository;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class RegisteredCarService {

    private final RegisteredCarRepository registeredCarRepository;

    public List<RegisteredCar> getAllCars() {
        List<RegisteredCar> carList = new ArrayList<>();
        registeredCarRepository.findAll().forEach(carList::add);
        return carList;
    }

    public CarCountDto countCars() {
        return CarCountDto.builder()
                .registeredCarsCount(registeredCarRepository.countByIdCars())
                .build();
    }

    public RegisteredCar getRegisteredCarById(Integer id) {
        return registeredCarRepository.findById(id).get();
    }

    public RegisteredCar saveOrUpdate(String carNumber) {
        RegisteredCar car = RegisteredCar.builder()
                .carNumber(carNumber)
                .timestamp(OffsetDateTime.now(ZoneId.systemDefault()))
                .build();
        registeredCarRepository.save(car);
        return car;
    }

}
