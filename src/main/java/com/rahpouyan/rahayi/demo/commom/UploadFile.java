package com.rahpouyan.rahayi.demo.commom;

import com.rahpouyan.rahayi.demo.model.entity.Image;
import com.rahpouyan.rahayi.demo.model.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

@Component
public class UploadFile {

    @Autowired
    private ImageService imageService;

    public static String getPrefix(String fileName) {
        int x = fileName.lastIndexOf(".");
        return fileName.substring(x, fileName.length());
    }

    public static File renameFile(File file, String fileName, Long id) {

        File file1 = new File(file, fileName);
        File file2 = new File(file, (id + getPrefix(fileName)));
        if (file2.exists()) {
            file2.delete();
        }
        file1.renameTo(file2);
        return file2;
    }

    public File store(MultipartFile multipartFile, Path path, Long id, String... estention) {
        try {

            File file = new File(path.toString());
            if (!file.exists()) {
                file.mkdirs();
            }

            String filename = multipartFile.getOriginalFilename();

            for (String s : estention) {
                if (getPrefix(filename).equals(s)) {
                    InputStream inputStream = multipartFile.getInputStream();
                    Files.copy(inputStream, path.resolve(String.valueOf(filename)), StandardCopyOption.REPLACE_EXISTING);
                    inputStream.close();
                    File file1 = renameFile(file, filename, id);
                    return file1;
                }
            }

            return null;

        } catch (Exception e) {

            System.out.println("Error : " + e.getMessage());
            e.printStackTrace();
            return null;
        }

    }




}
