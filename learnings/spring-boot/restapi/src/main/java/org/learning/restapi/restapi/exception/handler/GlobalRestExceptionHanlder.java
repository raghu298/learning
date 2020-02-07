package org.learning.restapi.restapi.exception.handler;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.ConstraintViolationException;

import org.learning.restapi.restapi.exception.custom.APIError;
import org.learning.restapi.restapi.exception.custom.AddressNotFoundException;
import org.learning.restapi.restapi.exception.custom.RecordNotFoundException;
import org.learning.restapi.restapi.exception.custom.UserNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * 
 * @author Biradar
 *
 */

@ControllerAdvice
public class GlobalRestExceptionHanlder extends ResponseEntityExceptionHandler  {

	private static final Logger log = LoggerFactory.getLogger(GlobalRestExceptionHanlder.class);
	
	
    @Override
	public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatus status, WebRequest request) {


      

        //Get all errors
        List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(x -> x.getDefaultMessage())
                .collect(Collectors.toList());

       
        APIError error= APIError.builder()
                .status(HttpStatus.BAD_REQUEST)
                .timestamp(LocalDateTime.now())
                .message(errors.toString())
                .developerMessage(errors.toString()).build();

        return new ResponseEntity<>(error, status);
        

    }
	@ExceptionHandler({ConstraintViolationException.class,DisabledException.class,BadCredentialsException
		.class,RuntimeException.class, IllegalArgumentException.class, UserNotFoundException.class,RecordNotFoundException.class,AddressNotFoundException.class})
    public final ResponseEntity<APIError> handleAllExceptions(Exception ex, WebRequest request) {
        log.error("Exception raised for request [{}] with query_param [{}] and exception message [{}]", ((ServletWebRequest) request).getRequest().getRequestURI(), ((ServletWebRequest) request).getRequest().getQueryString(), ex.getMessage());

       if (ex instanceof UserNotFoundException) {
    	   return buildResponseEntity(APIError.builder()
                   .status(HttpStatus.NOT_FOUND)
                   .timestamp(LocalDateTime.now())
                   .message("USer Not Found"+ ((ServletWebRequest) request).getRequest().getQueryString())
                   .developerMessage(ex.getLocalizedMessage()).build());
    	   
       }else if (ex instanceof RecordNotFoundException) {
    	   return buildResponseEntity(APIError.builder()
                   .status(HttpStatus.NOT_FOUND)
                   .timestamp(LocalDateTime.now())
                   .message("Record Not Found "+ ((ServletWebRequest) request).getRequest().getQueryString())
                   .developerMessage(ex.getLocalizedMessage()).build());
    	   
       }else if (ex instanceof AddressNotFoundException) {
    	   return buildResponseEntity(APIError.builder()
                   .status(HttpStatus.NOT_FOUND)
                   .timestamp(LocalDateTime.now())
                   .message("Record Not Found "+ ((ServletWebRequest) request).getRequest().getQueryString())
                   .developerMessage(ex.getLocalizedMessage()).build());
    	     
       }
       else if (ex instanceof MethodArgumentNotValidException ) {
    	   return buildResponseEntity(APIError.builder()
                   .status(HttpStatus.BAD_REQUEST)
                   .timestamp(LocalDateTime.now())
                   .message("Request not allowed "+ ((ServletWebRequest) request).getRequest().getQueryString())
                   .developerMessage(ex.getLocalizedMessage()).build());
    	   
       }
       else if (ex instanceof ConstraintViolationException) {
    	   return buildResponseEntity(APIError.builder()
                   .status(HttpStatus.BAD_REQUEST)
                   .timestamp(LocalDateTime.now())
                   .message("BAD Request "+ ex.getMessage())
                   .developerMessage(ex.getLocalizedMessage()).build());
    	   
       }
       
       System.out.println("EXCEPTION:"+ex.getMessage().toString());
       
       
       return buildResponseEntity(APIError.builder()
               .status(HttpStatus.INTERNAL_SERVER_ERROR)
               .timestamp(LocalDateTime.now())
               .message(ex.getMessage())
               .developerMessage(ex.getLocalizedMessage()).build());
   }
    
  
  /*  @ExceptionHandler(ConstraintViolationException.class)
    public void constraintViolationException(HttpServletResponse response) throws IOException {
        response.sendError(HttpStatus.BAD_REQUEST.value());
    }    
   */


private ResponseEntity<APIError> buildResponseEntity(APIError apiError) {
    return new ResponseEntity<>(apiError, apiError.getStatus());
}
}
