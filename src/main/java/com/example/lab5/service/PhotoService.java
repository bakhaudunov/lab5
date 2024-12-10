package com.example.lab5.service;

import com.example.lab5.model.Photo;
import com.example.lab5.repository.PhotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class PhotoService {
    private final String UPLOAD_DIR = "src/main/resources/static/images/";
    private final PhotoRepository photoRepository;

    @Autowired
    public PhotoService(PhotoRepository photoRepository) {
        this.photoRepository = photoRepository;
    }

    public Photo uploadPhoto(MultipartFile file) throws IOException {
        String fileName = file.getOriginalFilename();
        Path filePath = Paths.get(UPLOAD_DIR + fileName);
        Files.write(filePath, file.getBytes());

        Photo photo = new Photo();
        photo.setImageLink("/images/" + fileName);
        return photoRepository.save(photo);
    }

    public Photo getPhotoById(Long id) {
        return photoRepository.findById(id).orElse(null);
    }
}