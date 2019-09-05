package com.dulik.jnetworks.roadcamera.controller;

import com.dulik.jnetworks.roadcamera.dto.CarCountDto;
import com.dulik.jnetworks.roadcamera.entity.RegisteredCar;
import com.dulik.jnetworks.roadcamera.service.RegisteredCarService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class RegisteredCarController {

    private final RegisteredCarService registeredCarService;

    @GetMapping("/registeredAllCars")
    private List<RegisteredCar> getAllCars() {
        return registeredCarService.getAllCars();
    }

    @PostMapping("/registeredCars")
    private RegisteredCar saveCars() {

        return null;
    }


    @GetMapping("/registeredCars/count")
    private CarCountDto getCount() {
        return registeredCarService.countCars();
    }
}
