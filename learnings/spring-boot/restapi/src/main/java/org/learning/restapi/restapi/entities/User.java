package org.learning.restapi.restapi.entities;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="tbl_user")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(Include.NON_NULL)
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@Column(name="user_name")
	@NotNull
	@NotEmpty(message = "name must not be empty")
	private String name;
	
	@Column(name="user_dob")
	@Past
	private LocalDateTime dob;
	
	@Column(name="user_age")
	@Digits(fraction = 0, integer = 2,message="Please enter two digit number")
	private Integer age;
	
	@Column(name="user_address")
	private String addess;
	
	public User(String name, Integer id) {
		this.name = name;
		this.id = id;
	}
	
	
	
	
	

}
