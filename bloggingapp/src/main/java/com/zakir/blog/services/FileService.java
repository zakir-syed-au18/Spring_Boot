package com.zakir.blog.services;



import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.springframework.web.multipart.MultipartFile;

public interface FileService {

	
	String uplodImage(String path,MultipartFile file) throws IOException;
	
	InputStream getSource(String path,String filename) throws FileNotFoundException;
}
