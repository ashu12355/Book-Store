package com.ashu.book_store.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class FileService {
    @Value("${upload.file.dir}")
    private String IMG_DIR;
    
    public void createDirectory(){
        var file = new File(IMG_DIR);
        if(!file.exists()){
            file.mkdir();
            log.info("File Created to Store Image");
            return;
        }
        // log.info("File Already Exists");
        log.debug("Folder exists with name {}",IMG_DIR);
        log.info("File exists to store images");
    }
    public String uploadImage(MultipartFile bookImage) throws IOException {
      if(!bookImage.isEmpty()){
            String file = bookImage.getOriginalFilename();
            String fileName  = generateImageName(file);
            var fos = new FileOutputStream(IMG_DIR+ File.separator +fileName);
            fos.write(bookImage.getBytes());
            fos.close();
            return fileName;
      }
      log.error("Image Not found");
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
