package edu.eci.hck.pixelScribe.Images.controller;

import edu.eci.hck.pixelScribe.Images.model.Image;
import edu.eci.hck.pixelScribe.Images.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/images")
public class ImageController {

    @Autowired
    private ImageService imageService;

    @GetMapping("/{id}")
    public ResponseEntity<Image> getImageById(@PathVariable String id) {
        Image image = imageService.getImageById(id);
        return ResponseEntity.ok(image);
    }

    @GetMapping
    public ResponseEntity<List<Image>> getAllImages() {
        List<Image> images = imageService.getAllImages();
        return ResponseEntity.ok(images);
    }

    @PostMapping
    public ResponseEntity<String> uploadImage(
            @RequestParam("image") MultipartFile image,
            @RequestParam("descripcion") String descripcionJson) throws IOException {

        imageService.uploadImage(image, descripcionJson);
        return ResponseEntity.ok("Imagen subida correctamente");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteImage(@PathVariable String id) {
        imageService.deleteById(id);
        return ResponseEntity.ok("Imagen eliminada correctamente");
    }
}
