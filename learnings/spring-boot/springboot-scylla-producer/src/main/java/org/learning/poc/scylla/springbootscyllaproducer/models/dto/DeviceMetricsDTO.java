package org.learning.poc.scylla.springbootscyllaproducer.models.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DeviceMetricsDTO {

    private String deviceId;
    private  String deviceType;
    private LocalDateTime createdOn;
    private LocalDateTime modifiedOn;
}
