package com.dulik.jnetworks.roadcamera.converter;

import com.dulik.jnetworks.roadcamera.dto.RegisteredCarsDto;
import com.dulik.jnetworks.roadcamera.entity.Car;
import java.time.format.DateTimeFormatter;
import org.springframework.stereotype.Component;

@Component
public class CarToRegisteredCarsDot implements Converter<RegisteredCarsDto, Car> {

    @Override
    public RegisteredCarsDto convert(Car car) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssZ");
        return RegisteredCarsDto.builder()
                .carNumber(car.getCarNumber())
                .timestamp(formatter.format(car.getTimestamp()))
                .build();
    }
}