package edu.eci.hck.pixelScribe.Images.controller;

import edu.eci.hck.pixelScribe.Images.model.Image;
import edu.eci.hck.pixelScribe.Images.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public class ImageController {

    @Autowired
    private ImageService imageService;

    @GetMapping("/{id}")
    public Image getImageById(@PathVariable Long id) {
        return imageService.getImageById(id);
    }

    @GetMapping
    public List<Image> getAllImages() {
        return imageService.getAllImages();
    }

    @PostMapping
    public void uploadImage(@RequestParam("image") MultipartFile image) {
        imageService.uploadImage(image);
    }

}
