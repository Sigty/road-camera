package com.dulik.jnetworks.roadcamera.repository;

import com.dulik.jnetworks.roadcamera.entity.RegisteredCar;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegisteredCarRepository extends PagingAndSortingRepository<RegisteredCar, Integer> {
}
