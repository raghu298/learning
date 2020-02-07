package org.learning.restapi.restapi.dto;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class TokenDTO implements Serializable  {


private static final long serialVersionUID = -8091879091924046844L;

private final String jwttoken;
}
