package edu.eci.hck.pixelScribe.Images.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RequestCompletionOpenIA {
    private String model;
    private String prompt;
    private float temperature;
    @JsonProperty("max_tokens")
    private short maxTokens;
}
