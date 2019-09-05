package com.dulik.jnetworks.roadcamera;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan("com.dulik")
@EnableJpaRepositories("com.dulik")
public class RoadCameraApplication {

    public static void main(String[] args) {
        SpringApplication.run(RoadCameraApplication.class, args);
    }

}
