package edu.eci.hck.pixelScribe.Images.service;

import edu.eci.hck.pixelScribe.Images.model.AnalysisResponse;
import edu.eci.hck.pixelScribe.Images.model.Description;
import edu.eci.hck.pixelScribe.Images.model.Image;
import edu.eci.hck.pixelScribe.Images.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
public class ImageService {

    @Autowired
    private ImageRepository imageRepository;

    @Autowired
    private OpenAIService openAIService;

    public Image uploadImage(MultipartFile file) {
        try {
            if (!Objects.requireNonNull(file.getContentType()).startsWith("image/")) {
                throw new IllegalArgumentException("El archivo debe ser una imagen");
            }

            byte[] imageData = file.getBytes();

            String contentType = file.getContentType();
            String imageFormat = contentType != null ? contentType.split("/")[1] : "jpeg";

            AnalysisResponse analysisResponse = openAIService.analyzeImage(imageData, imageFormat);

            Description description = new Description();
            description.setDescripcion(analysisResponse.getDescription());
            description.setTags(analysisResponse.getTags());
            description.setTextContent(analysisResponse.getTextContent());

            Image image = new Image();
            image.setId(UUID.randomUUID().toString());
            image.setNombre(file.getOriginalFilename());
            image.setDatos(imageData);
            image.setDescripcion(description);

            return imageRepository.save(image);

        } catch (IOException e) {
            throw new RuntimeException("Error al procesar la imagen: " + e.getMessage(), e);
        }
    }

    public Image getImageById(String id) {
        return imageRepository.findById(id).orElse(null);
    }

    public List<Image> getAllImages() {
        return imageRepository.findAll();
    }
}
