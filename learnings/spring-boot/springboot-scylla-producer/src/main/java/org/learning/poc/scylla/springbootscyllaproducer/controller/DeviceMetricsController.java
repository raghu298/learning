package org.learning.poc.scylla.springbootscyllaproducer.controller;

import org.learning.poc.scylla.springbootscyllaproducer.models.dto.DeviceMetricsDTO;
import org.learning.poc.scylla.springbootscyllaproducer.service.DeviceMetricsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/devices")
public class DeviceMetricsController implements  IDeviceMetricsController {

    @Autowired
    private DeviceMetricsService service;
    @Override
    public List<DeviceMetricsDTO> getDevices() {
        return service.getDevices();
    }

    @Override
    public DeviceMetricsDTO createDevices(DeviceMetricsDTO deviceMetricsDTO) {
        return service.createDevices(deviceMetricsDTO);
    }

    @Override
    public DeviceMetricsDTO updateDevices(DeviceMetricsDTO deviceMetricsDTO, String id) {
        return service.updateDevices(deviceMetricsDTO, UUID.fromString(id));
    }

    @Override
    public void deleteDevices(@PathVariable("id") String id) {
        service.deleteDevices(UUID.fromString(id));

    }
}
