package com.blog.services;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

public interface FileService
{
	
	String uploadFile(String path,MultipartFile file) throws IOException;
	
}
