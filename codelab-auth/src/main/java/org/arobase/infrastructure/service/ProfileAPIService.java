package org.arobase.infrastructure.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.json.Json;
import jakarta.json.JsonObject;

import java.io.StringReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@ApplicationScoped
public class ProfileAPIService {

    private final String apiUrl = "http://exercice.codelab.local/api/profiles";

    public int createProfile() {

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(apiUrl))
                .build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            JsonObject jsonObject = Json.createReader(new StringReader(response.body())).readObject();
            return jsonObject.getInt("id");
        }catch (Exception e){
            throw new RuntimeException("Error while creating profile");
        }

    }

}
