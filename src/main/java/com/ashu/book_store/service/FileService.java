package com.ashu.book_store.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileService {
    public String uploadImage(MultipartFile bookImage) throws IOException {
      if(!bookImage.isEmpty()){
            String file = bookImage.getOriginalFilename();
            String fileName  = generateImageName(file);
            var fos = new FileOutputStream("images"+ File.separator +fileName);
            fos.write(bookImage.getBytes());
            fos.close();
            return fileName;
      }
      throw new RuntimeException("Image Not Found");
    }
    private String generateImageName(String file) {
        String extensionName = file.substring(file.lastIndexOf('.'));
        String fileName = UUID.randomUUID().toString();
        return fileName + extensionName;
    }
    public byte[] getImage(String imageName) throws IOException {
        var fis = new FileInputStream("images"+File.separator+imageName);
        byte [] image = fis.readAllBytes();
        fis.close();
        return image;
    }
     
    
}
