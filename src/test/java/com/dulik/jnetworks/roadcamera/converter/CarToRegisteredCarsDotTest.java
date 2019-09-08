package com.dulik.jnetworks.roadcamera.converter;

import com.dulik.jnetworks.roadcamera.dto.RegisteredCarsDto;
import com.dulik.jnetworks.roadcamera.entity.Car;
import java.time.OffsetDateTime;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CarToRegisteredCarsDotTest {

    @Autowired
    private CarToRegisteredCarsDot carToRegisteredCarsDot;

    @Test
    public void convert() {
        Car car = Car.builder()
                .id(1)
                .carNumber("partNumber")
                .timestamp(OffsetDateTime.parse("2019-09-04T12:34:56+00:00"))
                .build();

        RegisteredCarsDto regCar = RegisteredCarsDto.builder()
                .carNumber("partNumber")
                .timestamp("2019-09-04T12:34:56+0000")
                .build();
        assertEquals(carToRegisteredCarsDot.convert(car), regCar);
    }
}