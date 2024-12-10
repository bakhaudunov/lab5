package com.example.lab5.controller;

import com.example.lab5.model.Photo;
import com.example.lab5.service.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.ui.Model;

import java.io.IOException;

@Controller
@RequestMapping("/photos")
public class PhotoController {
    private final PhotoService photoService;

    @Autowired
    public PhotoController(PhotoService photoService) {
        this.photoService = photoService;
    }

    @PostMapping("/upload")
    public ResponseEntity<Photo> uploadPhoto(@RequestParam("file") MultipartFile file) {
        try {
            Photo photo = photoService.uploadPhoto(file);
            return new ResponseEntity<>(photo, HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/view/{id}")
    public String viewPhoto(@PathVariable Long id, Model model) {
        Photo photo = photoService.getPhotoById(id);
        model.addAttribute("photo", photo);
        return "viewPhoto";
    }
}
