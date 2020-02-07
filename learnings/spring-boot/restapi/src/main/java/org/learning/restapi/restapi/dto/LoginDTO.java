package org.learning.restapi.restapi.dto;

import java.time.LocalDateTime;

import org.learning.restapi.restapi.dto.UserDTO.UserDTOBuilder;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoginDTO {
	private String username;
	private String password;
}
