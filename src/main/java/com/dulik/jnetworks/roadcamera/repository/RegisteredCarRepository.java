package com.dulik.jnetworks.roadcamera.repository;

import com.dulik.jnetworks.roadcamera.entity.RegisteredCar;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegisteredCarRepository extends PagingAndSortingRepository<RegisteredCar, Integer> {

    @Query("select count(r.id) from RegisteredCar r ")
    long countByIdCars();
}
