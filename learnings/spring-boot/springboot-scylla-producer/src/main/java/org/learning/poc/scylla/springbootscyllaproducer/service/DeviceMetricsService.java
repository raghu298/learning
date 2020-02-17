package org.learning.poc.scylla.springbootscyllaproducer.service;

import com.datastax.driver.core.utils.UUIDs;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.learning.poc.scylla.springbootscyllaproducer.kafka.producer.Producer;
import org.learning.poc.scylla.springbootscyllaproducer.models.dto.DeviceMetricsDTO;
import org.learning.poc.scylla.springbootscyllaproducer.models.entity.DeviceMetircs;
import org.learning.poc.scylla.springbootscyllaproducer.repository.DeviceMetricsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class DeviceMetricsService {

    @Autowired
    private DeviceMetricsRepository repository;

    @Autowired
    private Producer producer;

    public List<DeviceMetricsDTO> getDevices() {
        List<DeviceMetircs> deviceMetrics = repository.findAll();
        return deviceMetrics.stream().map(this::convertToDTO).collect(Collectors.toList());
    }


    public DeviceMetricsDTO createDevices(DeviceMetricsDTO deviceMetricsDTO) {
        DeviceMetircs deviceMetircs = repository.save(convertToEntity(deviceMetricsDTO));
        DeviceMetricsDTO deviceMetricsResult = convertToDTO(deviceMetircs);
        try {
            producer.sendMessage("CREATE_DEVICE",toJosn(deviceMetircs));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return deviceMetricsResult;
    }


    public DeviceMetricsDTO updateDevices(DeviceMetricsDTO deviceMetricsDTO, UUID deviceId) {
        Boolean existsById = repository.existsById(deviceId);
        if (existsById) {
            DeviceMetircs deviceMetircs = repository.save(convertToEntity(deviceMetricsDTO));
            DeviceMetricsDTO deviceMetricsResult = convertToDTO(deviceMetircs);
            try {
                producer.sendMessage("UPDATE_DEVICE", toJosn(deviceMetircs));
            } catch (Exception e) {
                e.printStackTrace();
            }
            return deviceMetricsResult;
        } else {
            throw new RuntimeException(" deviceId Doesn't exist "+deviceId);
        }
    }


    public void deleteDevices(UUID id) {
        repository.deleteById(id);
        try {
            producer.sendMessage("DELETEDEVICE_DEVICE",id.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private  DeviceMetricsDTO convertToDTO(DeviceMetircs deviceMetircs) {
        return DeviceMetricsDTO.builder()
                .deviceId(deviceMetircs.getDeviceId())
                .deviceType(deviceMetircs.getDeviceType())
                .createdOn(deviceMetircs.getCreatedOn())
                .modifiedOn(deviceMetircs.getModifiedOn())
                .build();


    }

    private  DeviceMetircs convertToEntity(DeviceMetricsDTO deviceMetircs) {
        return DeviceMetircs.builder()
                .id(UUIDs.random())
                .deviceId(deviceMetircs.getDeviceId())
                .deviceType(deviceMetircs.getDeviceType())
                .createdOn(deviceMetircs.getCreatedOn())
                .modifiedOn(deviceMetircs.getModifiedOn())
                .build();


    }

    private String toJosn(DeviceMetircs deviceMetircs) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(deviceMetircs);

    }
}
