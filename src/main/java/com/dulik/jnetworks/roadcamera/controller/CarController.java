package com.dulik.jnetworks.roadcamera.controller;

import com.dulik.jnetworks.roadcamera.dto.CarCountDto;
import com.dulik.jnetworks.roadcamera.dto.RegisteredCarsDto;
import com.dulik.jnetworks.roadcamera.dto.FilterCar;
import com.dulik.jnetworks.roadcamera.service.CarService;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class CarController {

    private final CarService carService;

    @GetMapping("/registeredAllCars")
    public List<RegisteredCarsDto> getAllCars() {
        return carService.getAllCars();
    }

    @PostMapping("/registeredCars")
    @ResponseStatus(HttpStatus.CREATED)
    public RegisteredCarsDto saveCar(@RequestBody RegisteredCarsDto newCar) {
        return carService.saveOrUpdateCar(newCar.getCarNumber());
    }

    @GetMapping("/registeredCars/count")
    public CarCountDto getCount() {
        return carService.countCars();
    }

    @GetMapping("/registeredCars")
    public List<RegisteredCarsDto> filterCar(@RequestParam(value = "carNumber", required = false) String carNumber,
                                             @RequestParam(value = "date", required = false) String date,
                                             @RequestParam(value = "page", required = false, defaultValue = "1")
                                                     Integer page,
                                             @RequestParam(value = "records", required = false)
                                                     Optional<Integer> records) {
        OffsetDateTime dateTime = null;
        if (date != null) {
            dateTime = LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyyMMdd"))
                    .atStartOfDay(ZoneId.systemDefault()).toOffsetDateTime();
        }
        return carService.getCarByFilter(FilterCar.builder()
                .carNumber(carNumber)
                .date(dateTime)
                .page(page)
                .records(records.orElse(Integer.MAX_VALUE))
                .build());
    }
}