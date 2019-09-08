package com.dulik.jnetworks.roadcamera.repository;

import com.dulik.jnetworks.roadcamera.entity.Car;
import java.time.OffsetDateTime;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarRepository extends PagingAndSortingRepository<Car, Integer> {

    @Query("select count(r.id) from Car r ")
    long countByIdCars();

    Optional<Car> findByCarNumber(String carNumber);

    Page<Car> findByCarNumberAndTimestampBetween(String carNumber, OffsetDateTime timeStart, OffsetDateTime endStart,
                                                 Pageable pageable);

    Page<Car> findByTimestampBetween(OffsetDateTime timeStart, OffsetDateTime endStart, Pageable pageable);

    Page<Car> findByCarNumber(String carNumber, Pageable pageable);
}