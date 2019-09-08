package com.dulik.jnetworks.roadcamera.service;

import com.dulik.jnetworks.roadcamera.converter.CarToRegisteredCarsDot;
import com.dulik.jnetworks.roadcamera.dto.CarCountDto;
import com.dulik.jnetworks.roadcamera.dto.RegisteredCarsDto;
import com.dulik.jnetworks.roadcamera.entity.Car;
import com.dulik.jnetworks.roadcamera.dto.FilterCar;
import com.dulik.jnetworks.roadcamera.exception.EntityNotFoundException;
import com.dulik.jnetworks.roadcamera.exception.NoUniqException;
import com.dulik.jnetworks.roadcamera.repository.CarRepository;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CarService {

    private final CarRepository carRepository;
    private final CarToRegisteredCarsDot carToRegisteredCarsDot;

    public List<RegisteredCarsDto> getAllCars() {
        List<Car> carList = new ArrayList<>();
        carRepository.findAll().forEach(carList::add);
        return carList
                .stream()
                .map(carToRegisteredCarsDot::convert)
                .collect(Collectors.toList());
    }

    public CarCountDto countCars() {
        return CarCountDto.builder()
                .registeredCarsCount(carRepository.countByIdCars())
                .build();
    }

    public List<RegisteredCarsDto> getCarByFilter(FilterCar filterCar) {
        if (filterCar.getCarNumber() == null) {
            Page<Car> byTimestampBetween = carRepository.findByTimestampBetween(filterCar.getDate(),
                    filterCar.getDate().plusDays(1).minusNanos(1),
                    PageRequest.of(filterCar.getPage() - 1, filterCar.getRecords()));
            return byTimestampBetween.stream().map(carToRegisteredCarsDot::convert).collect(Collectors.toList());
        } else if (filterCar.getDate() == null) {
            Page<Car> byCarNumber = carRepository.findByCarNumber(filterCar.getCarNumber(),
                    PageRequest.of(filterCar.getPage() - 1, filterCar.getRecords()));
            return byCarNumber
                    .stream()
                    .map(carToRegisteredCarsDot::convert)
                    .collect(Collectors.toList());
        }
        Page<Car> byCarNumberAndTimestampBetween = carRepository
                .findByCarNumberAndTimestampBetween(filterCar.getCarNumber(), filterCar.getDate(),
                        filterCar.getDate().plusDays(1).minusNanos(1),
                        PageRequest.of(filterCar.getPage() - 1, filterCar.getRecords()));
        return byCarNumberAndTimestampBetween
                .stream()
                .map(carToRegisteredCarsDot::convert)
                .collect(Collectors.toList());
    }

    @Transactional
    public RegisteredCarsDto saveOrUpdateCar(String carNumber) {
        try {
            carRepository.save(Car.builder()
                    .carNumber(carNumber)
                    .timestamp(OffsetDateTime.now(ZoneId.systemDefault()))
                    .build());
        } catch (DataIntegrityViolationException ex) {
            throw new NoUniqException(carNumber);
        }
        return carToRegisteredCarsDot.convert(carRepository.findByCarNumber(carNumber)
                .orElseThrow(() -> new EntityNotFoundException("Not found : " + carNumber)));
    }
}