package com.zakir.blog.execptions;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.zakir.blog.paylods.ApiResponse;

@RestControllerAdvice
public class GolbalExceptionHandler {
    
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ApiResponse> resourceNotfoundException(ResourceNotFoundException ex)
	{
		String message=ex.getMessage();
		ApiResponse apiresponse=new ApiResponse(message,false);
		return new ResponseEntity<ApiResponse>(apiresponse,HttpStatus.NOT_FOUND);
		
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, String>>handleMethodArugmentException(MethodArgumentNotValidException ex)
	
	{
		
		Map<String, String> resp=new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach((error)->{
			
			String fieldnmae=((FieldError)error).getField();
			String message=error.getDefaultMessage();
			resp.put(fieldnmae, message);
		});
		return  new ResponseEntity<Map<String,String>>(resp,HttpStatus.BAD_REQUEST);
		
	}
	
	
}
