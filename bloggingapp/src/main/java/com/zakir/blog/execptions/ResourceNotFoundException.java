package com.zakir.blog.execptions;




import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class ResourceNotFoundException extends  RuntimeException  {
	

	String resourcename;
	
	 String fieldname;
	
      long  fieldvalue;

	public ResourceNotFoundException(String resourcename, String fieldname, long fieldvalue) {
		super(String.format( " %s not found %s : %d" ,resourcename,fieldname,fieldvalue ));
		this.resourcename = resourcename;
		this.fieldname = fieldname;
		this.fieldvalue = fieldvalue;
	}

	
	
	

	
	
	
	
 
}
