package edu.eci.hck.pixelScribe.Images.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "images")
public class Image {

    @Id
    private String id;
    private String nombre;

    @Transient
    private byte[] datos;
    private Description descripcion;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public byte[] getDatos() {
        return datos;
    }

    public void setDatos(byte[] datos) {
        this.datos = datos;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Description getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(Description descripcion) {
        this.descripcion = descripcion;
    }

}

