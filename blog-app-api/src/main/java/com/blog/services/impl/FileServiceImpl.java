package com.blog.services.impl;



import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.blog.services.FileService;

@Service
public class FileServiceImpl implements FileService {

	@Override
	public String uploadFile(String path, MultipartFile file) throws IOException {
		
		//get file Name		
		String fileName = file.getOriginalFilename();
		
		//generate random file name		
		String random = UUID.randomUUID().toString();
		
		String updatedFileName = random.concat(fileName.substring(fileName.lastIndexOf('.')));
		
		// get path		
		String filePath = path+File.separator+updatedFileName;
		
		
		//check if file exists or not , if not create one		
		File file1 = new File(path);
		if(!file1.exists()) {
			file1.mkdir();
		}
		
		//copy file to the absolute path		
		Files.copy(file.getInputStream(),Paths.get(filePath));
		
		return fileName;
	}

}
