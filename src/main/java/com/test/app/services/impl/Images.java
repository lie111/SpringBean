package com.test.app.services.impl;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletContext;
//import javax.validation.Valid;

import org.apache.commons.io.FileUtils;
import org.springframework.validation.BindingResult;
//import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.web.multipart.MultipartFile;

public class Images{
		
	ServletContext servletContext;
	
	public boolean addImage(String titleWithExtension,
			BindingResult bindingResult, MultipartFile image, String savePath, ServletContext sc) {
		
		servletContext = sc;
			 				
		String oldName = image.getOriginalFilename();
		String extension;
		
		if (!image.isEmpty()) {
				
			try {
				validateImage(image);
			 
			} catch (RuntimeException re) {
				bindingResult.reject(re.getMessage());
				return false;
			}
						extension = oldName.substring(oldName.lastIndexOf(".") + 1);
						System.out.println(extension);
			try {
				saveImage(titleWithExtension, image, savePath);
			} catch (IOException e) {
				bindingResult.reject(e.getMessage());
				return false;
			}
		}
		
		return true;
	}
			 
	private void validateImage(MultipartFile image) {
		
		if (!image.getContentType().equals("image/jpeg")) {
			throw new RuntimeException("Only JPG images are accepted");
		}
	}
					 
	private void saveImage(String filename, MultipartFile image, String savePath)
		throws RuntimeException, IOException {
				
		String fullPath = servletContext.getRealPath("/") + savePath;
			try {			
				
				File checkFile = new File(fullPath);
				
				if(!checkFile.exists())
					checkFile.mkdirs();
				
				File file = new File(fullPath + filename);				
					
				FileUtils.writeByteArrayToFile(file, image.getBytes());
				System.out.println("Go to the location:  " + file.toString()
				+ " on your computer and verify that the image has been stored.");
				
			} catch (IOException e) {
				throw e;
			  }
	}
	
	public static void main(String[] args) {
		
		File file = new File("D:/users/user/");		
		try{
			
				file.mkdirs();
			
			file.createTempFile("Heng", ".exe",file);
			System.out.println("successful");
			
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
}