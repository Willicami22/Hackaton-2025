package edu.eci.hck.pixelScribe.Images.repository;

import edu.eci.hck.pixelScribe.Images.model.Image;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepository extends MongoRepository<Image, String> {
}
