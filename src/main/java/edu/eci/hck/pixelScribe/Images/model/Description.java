package edu.eci.hck.pixelScribe.Images.model;

import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
public class Description {
    private String descripcion;
    private List<String> tags;
    private String textContent;
}
