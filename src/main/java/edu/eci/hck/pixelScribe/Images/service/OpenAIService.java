package edu.eci.hck.pixelScribe.Images.service;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonArray;
import edu.eci.hck.pixelScribe.Images.model.AnalysisResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Base64;

@Service
public class OpenAIService {

    @Value("${openai.api.key}")
    private String apiKey;

    private final Gson gson = new Gson();
    private final RestTemplate restTemplate = new RestTemplate();

    private static final String OPENAI_API_URL = "https://api.openai.com/v1/chat/completions";
    private static final String MODEL = "gpt-4o";

    public AnalysisResponse analyzeImage(byte[] imageData, String imageFormat) {
        String base64Image = Base64.getEncoder().encodeToString(imageData);

        String mediaType = getMediaType(imageFormat);

        JsonObject payload = new JsonObject();
        payload.addProperty("model", MODEL);
        payload.addProperty("max_tokens", 1000);

        JsonArray messages = new JsonArray();
        JsonObject message = new JsonObject();
        message.addProperty("role", "user");

        JsonArray content = new JsonArray();

        JsonObject imageContent = new JsonObject();
        imageContent.addProperty("type", "image_url");
        JsonObject imageUrl = new JsonObject();
        imageUrl.addProperty("url", "data:" + mediaType + ";base64," + base64Image);
        imageContent.add("image_url", imageUrl);
        content.add(imageContent);

        JsonObject textContent = new JsonObject();
        textContent.addProperty("type", "text");
        textContent.addProperty("text", "Analiza esta imagen y proporciona una respuesta en JSON con exactamente estas tres propiedades:\n" +
                "1. 'description': Una descripción detallada de lo que ves en la imagen (máximo 500 caracteres)\n" +
                "2. 'tags': Un array de palabras clave o etiquetas que describan los elementos principales de la imagen (máximo 10 tags)\n" +
                "3. 'text_content': Todo el texto visible en la imagen (OCR). Si no hay texto, devuelve una cadena vacía\n" +
                "\n" +
                "Responde SOLO con el JSON, sin explicaciones adicionales.");
        content.add(textContent);

        message.add("content", content);
        messages.add(message);
        payload.add("messages", messages);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("Authorization", "Bearer " + apiKey);

        HttpEntity<String> request = new HttpEntity<>(payload.toString(), headers);

        try {
            String response = restTemplate.postForObject(OPENAI_API_URL, request, String.class);

            JsonObject responseJson = gson.fromJson(response, JsonObject.class);
            String responseContent = responseJson
                    .getAsJsonArray("choices")
                    .get(0)
                    .getAsJsonObject()
                    .getAsJsonObject("message")
                    .get("content")
                    .getAsString();

            responseContent = responseContent.replaceAll("```json", "").replaceAll("```", "").trim();

            AnalysisResponse analysisResponse = gson.fromJson(responseContent, AnalysisResponse.class);

            return analysisResponse;
        } catch (Exception e) {
            throw new RuntimeException("Error analyzing image with OpenAI: " + e.getMessage(), e);
        }
    }

    private String getMediaType(String imageFormat) {
        return switch (imageFormat.toLowerCase()) {
            case "jpg", "jpeg" -> "image/jpeg";
            case "png" -> "image/png";
            case "gif" -> "image/gif";
            case "webp" -> "image/webp";
            default -> "image/jpeg";
        };
    }
}
