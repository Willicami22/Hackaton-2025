package edu.eci.hck.pixelScribe.Images.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Document(collection = "images")
public class Image {

    @Id
    private String id;
    private String nombre;

    @Transient
    private byte[] datos;
    private Description descripcion;
}

