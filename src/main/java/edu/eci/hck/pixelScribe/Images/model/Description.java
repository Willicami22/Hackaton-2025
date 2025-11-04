package edu.eci.hck.pixelScribe.Images.model;

import lombok.Getter;
import lombok.Setter;
import java.util.List;


public class Description {
    private String descripcion;
    private List<String> tags;
    private String textContent;

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public String getTextContent() {
        return textContent;
    }

    public void setTextContent(String textContent) {
        this.textContent = textContent;
    }
}
