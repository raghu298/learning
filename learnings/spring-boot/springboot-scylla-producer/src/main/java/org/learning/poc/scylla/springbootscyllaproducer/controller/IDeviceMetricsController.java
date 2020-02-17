package org.learning.poc.scylla.springbootscyllaproducer.controller;

import org.learning.poc.scylla.springbootscyllaproducer.models.dto.DeviceMetricsDTO;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.UUID;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

public interface IDeviceMetricsController {

    /**
     * Get Devices
     *
     * @return the response of incident sync sucessfully or Unsucessfully
     */
    @ApiOperation(value = "Get devices", response = DeviceMetricsDTO.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message="Returned on success along with the response body "),
            @ApiResponse(code = 400,message="Returned when the period parameter is missing or is not formatted correctly"),
            @ApiResponse(code=401,message="Returned when caller is not authorized to make the API call"),
            @ApiResponse(code=404, message="Returned when the devices  is not available")
    })
    @GetMapping
    public List<DeviceMetricsDTO> getDevices();

    /**
     * Create Devices
     * @return the response of Create Devices  sucessfully or Unsucessfully
     */
    @ApiOperation(value = "Create devices", response = DeviceMetricsDTO.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message="Returned on success along with the response body "),
            @ApiResponse(code = 400,message="Returned when the period parameter is missing or is not formatted correctly"),
            @ApiResponse(code=401,message="Returned when caller is not authorized to make the API call"),
            @ApiResponse(code=404, message="Returned when the devices  is not available")
    })
    @PostMapping
    public DeviceMetricsDTO createDevices(@RequestBody DeviceMetricsDTO deviceMetricsDTO);

    /**
     * Update Device
     *
     * @return the response of Update Device sucessfully or Unsucessfully
     */
    @ApiOperation(value = "Update devices by Id", response = DeviceMetricsDTO.class)
    @ApiResponses(value = {
            @ApiResponse(code = 202, message="Returned on success along with the response body "),
            @ApiResponse(code = 400,message="Returned when the period parameter is missing or is not formatted correctly"),
            @ApiResponse(code=401,message="Returned when caller is not authorized to make the API call"),
            @ApiResponse(code=404, message="Returned when the devices  is not available")
    })
    @PutMapping("/{id}")
    public DeviceMetricsDTO updateDevices(@RequestBody DeviceMetricsDTO deviceMetricsDTO, @PathVariable("id") String id);

    /**
     * Get Devices for all partners
     *
     * @return the response of incident sync sucessfully or Unsucessfully
     */
    @ApiOperation(value = "Delete devices", response = void.class)
    @ApiResponses(value = {
            @ApiResponse(code = 203, message="Returned on success along with the response body "),
            @ApiResponse(code = 400,message="Returned when the period parameter is missing or is not formatted correctly"),
            @ApiResponse(code=401,message="Returned when caller is not authorized to make the API call"),
            @ApiResponse(code=404, message="Returned when the devices  is not available")
    })
    @DeleteMapping("/{id}")
    public void deleteDevices(@PathVariable("id") String id);


}
