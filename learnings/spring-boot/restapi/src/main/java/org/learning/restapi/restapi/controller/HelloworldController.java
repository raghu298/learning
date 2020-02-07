package org.learning.restapi.restapi.controller;

import org.learning.restapi.restapi.dto.Response;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;


@RestController
@RequestMapping("/restapi/v1")
public class HelloworldController {
	
	@GetMapping(value="/hello", produces="application/json" )
	@ApiOperation(value = "GET HELLO RESPONSE", response = Response.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message="Returned on success along with the response body "),
            @ApiResponse(code = 400,message="Returned when the period parameter is missing or is not formatted correctly"),
            @ApiResponse(code=401,message="Returned when caller is not authorized to make the API call"),
            @ApiResponse(code=404, message="Returned when the usage report for the month is not available")
    })
	public Response printHello() {
		return Response.builder().status("OK").build();
	}
	
	@PostMapping(value="/hello", produces="application/json" )
	@ApiOperation(value = "POST HELLO RESPONSE", response = Response.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message="Returned on success along with the response body "),
            @ApiResponse(code = 400,message="Returned when the period parameter is missing or is not formatted correctly"),
            @ApiResponse(code=401,message="Returned when caller is not authorized to make the API call"),
            @ApiResponse(code=404, message="Returned when the usage report for the month is not available")
    })
	public Response postHello(@RequestBody Response response) {

		response.setStatus("Hello"+response.getStatus());
		
		return response;
	}
 
	@PutMapping(value="/hello", produces="application/json" )
	@ApiOperation(value = "POST HELLO RESPONSE", response = Response.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message="Returned on success along with the response body "),
            @ApiResponse(code = 400,message="Returned when the period parameter is missing or is not formatted correctly"),
            @ApiResponse(code=401,message="Returned when caller is not authorized to make the API call"),
            @ApiResponse(code=404, message="Returned when the usage report for the month is not available")
    })
	public Response putHello(@RequestBody Response response) {
	
		response.setStatus("Put Hello"+response.getStatus());
		
		return response;
	}
	
}
