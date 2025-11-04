package edu.eci.hck.pixelScribe.Images.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.eci.hck.pixelScribe.Images.model.Description;
import edu.eci.hck.pixelScribe.Images.model.Image;
import edu.eci.hck.pixelScribe.Images.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class ImageService {

    @Autowired
    private ImageRepository imageRepository;

    @Autowired
    private ObjectMapper objectMapper;

    public Image getImageById(String id) {
        return imageRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Imagen no encontrada " + id));
    }

    public List<Image> getAllImages() {
        return imageRepository.findAll();
    }

    public void uploadImage(MultipartFile file, String descripcionJson) throws IOException {
        Description descripcion = objectMapper.readValue(descripcionJson, Description.class);

        Image image = new Image();
        image.setNombre(file.getOriginalFilename());
        image.setDatos(file.getBytes());
        image.setDescripcion(descripcion);

        imageRepository.save(image);
    }

    public void deleteById(String id) {
        if (!imageRepository.existsById(id)) {
            throw new RuntimeException("No existe imagen con id: " + id);
        }
        imageRepository.deleteById(id);
    }
}
