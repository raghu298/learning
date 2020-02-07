package org.learning.restapi.restapi.controller;

import java.util.List;

import javax.validation.Valid;

import org.learning.restapi.restapi.dto.UserDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

public interface IUserController {
	
	@ApiOperation(value = "Create New UserDTO")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message="Users created successfully"),
            @ApiResponse(code = 400,message="Bad request"),
            @ApiResponse(code=401,message="Un-autorized"),
            @ApiResponse(code=404, message="Not FOund")
    })
	@PostMapping(value ="/users")
	public void createUser(@Valid @RequestBody  UserDTO UserDTO);
	
	@ApiOperation(value = "Updated UserDTO")
    @ApiResponses(value = {
            @ApiResponse(code = 202, message="Users updated successfully"),
            @ApiResponse(code = 400,message="Bad request"),
            @ApiResponse(code=401,message="Un-autorized"),
            @ApiResponse(code=404, message="Not FOund")
    })
	@PutMapping(value ="/users/{id}")
	public void updateUser(@RequestBody UserDTO UserDTO,@PathVariable int id);
	
	@ApiOperation(value = "Read UserDTO by id", response= UserDTO.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message="Users updated successfully"),
            @ApiResponse(code = 400,message="Bad request"),
            @ApiResponse(code=401,message="Un-autorized"),
            @ApiResponse(code=404, message="Not FOund")
    })
	@GetMapping(value ="/users/{id}")
	public UserDTO readUser(@PathVariable int id);
	
	@ApiOperation(value = "Read Users", response= UserDTO.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message="Users read successfully"),
            @ApiResponse(code = 400,message="Bad request"),
            @ApiResponse(code=401,message="Un-autorized"),
            @ApiResponse(code=404, message="Not FOund")
    })
	@GetMapping(value ="/users/")
	public List<UserDTO> readUsers();
	
	@ApiOperation(value = "Delete UserDTO by id", response= UserDTO.class)
    @ApiResponses(value = {
            @ApiResponse(code = 203, message="Users deleted successfully"),
            @ApiResponse(code = 400,message="Bad request"),
            @ApiResponse(code=401,message="Un-autorized"),
            @ApiResponse(code=404, message="Not FOund")
    })
	@DeleteMapping(value ="/users/{id}")
	public void deleteUser(@PathVariable int id);
	
	
	@ApiOperation(value = "Search Users", response= UserDTO.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message="Users read successfully"),
            @ApiResponse(code = 400,message="Bad request"),
            @ApiResponse(code=401,message="Un-autorized"),
            @ApiResponse(code=404, message="Not FOund")
    })
	@GetMapping(value ="/users/search")
	public List<UserDTO> searchUSers(@RequestParam("name") String name);
	
	@ApiOperation(value = "Search Users by Age", response= UserDTO.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message="Users read successfully"),
            @ApiResponse(code = 400,message="Bad request"),
            @ApiResponse(code=401,message="Un-autorized"),
            @ApiResponse(code=404, message="Not FOund")
    })
	@GetMapping(value ="/users/searchAge")
	public List<UserDTO> searchUSersAge(@Valid @RequestParam("age") int age);
	
	
	@ApiOperation(value = "Search Users by less than Age", response= UserDTO.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message="Users read successfully"),
            @ApiResponse(code = 400,message="Bad request"),
            @ApiResponse(code=401,message="Un-autorized"),
            @ApiResponse(code=404, message="Not FOund")
    })
	@GetMapping(value ="/users/searchLtAge")
	public List<UserDTO> searchUSersLtAge(@RequestParam("age") int age);
	
	@ApiOperation(value = "Search Users address", response= UserDTO.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message="Users read successfully"),
            @ApiResponse(code = 400,message="Bad request"),
            @ApiResponse(code=401,message="Un-autorized"),
            @ApiResponse(code=404, message="Not FOund")
    })
	@GetMapping(value ="/users/search-address")
	public List<UserDTO> searchByAddress(@RequestParam("address") String address);
	
	@ApiOperation(value = "Search Users address", response= UserDTO.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message="Users read successfully"),
            @ApiResponse(code = 400,message="Bad request"),
            @ApiResponse(code=401,message="Un-autorized"),
            @ApiResponse(code=404, message="Not FOund")
    })
	@GetMapping(value ="/users/searchBetDob")
	public List<UserDTO> searchBetDob(@RequestParam("user_dob") int year1,int year2);
	
	
	@ApiOperation(value = "Search Users address", response= UserDTO.class)
	@ApiImplicitParams({
	    @ApiImplicitParam(name = "page", dataType = "integer", paramType = "query",
	            value = "Results page you want to retrieve (0..N)"),
	    @ApiImplicitParam(name = "size", dataType = "integer", paramType = "query",
	            value = "Number of records per page."),
	    @ApiImplicitParam(name = "sort", allowMultiple = true, dataType = "string", paramType = "query",
	            value = "Sorting criteria in the format: property(,asc|desc). " +
	                    "Default sort order is ascending. " +
	                    "Multiple sort criteria are supported.")
	})
	@ApiResponses(value = {
            @ApiResponse(code = 200, message="Users read successfully"),
            @ApiResponse(code = 400,message="Bad request"),
            @ApiResponse(code=401,message="Un-autorized"),
            @ApiResponse(code=404, message="Not FOund")
    })
	@GetMapping(value ="/users/findByAgeDetails")
	public Page<UserDTO> searchByAgeDetails(@RequestParam("age") int age,@PageableDefault
			(page = 0, size = 1)Pageable page);
	
	@ApiOperation(value = "sort by name ascending", response= UserDTO.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message="Users read successfully"),
            @ApiResponse(code = 400,message="Bad request"),
            @ApiResponse(code=401,message="Un-autorized"),
            @ApiResponse(code=404, message="Not FOund")
    })
	@GetMapping(value ="/users/findBySortName")
	public List<UserDTO> searchDetailsBySortName(@RequestParam("user_name") String name,@RequestParam(defaultValue = "id") String sortBy);
	
}
