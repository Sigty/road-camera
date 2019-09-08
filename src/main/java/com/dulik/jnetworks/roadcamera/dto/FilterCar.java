package com.dulik.jnetworks.roadcamera.dto;

import java.time.OffsetDateTime;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FilterCar {

    private String carNumber;
    private OffsetDateTime date;
    private Integer page;
    private Integer records;
}