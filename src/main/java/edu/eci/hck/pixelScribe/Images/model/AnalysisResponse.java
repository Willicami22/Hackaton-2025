package edu.eci.hck.pixelScribe.Images.model;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AnalysisResponse {
    private String description;
    private List<String> tags;

    @SerializedName("text_content")
    private String textContent;
}
