package org.arobase.infrastructure.service.api;

import io.vertx.uritemplate.impl.UriTemplateImpl;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.json.Json;
import jakarta.json.JsonObject;
import org.jboss.logging.Logger;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane;
import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@ApplicationScoped
public class ProfileAPIService {

    private final String apiUrl = "http://exercices.codelab.local:8080/api/v1/profile";

    @Inject
    Logger logger;

    public String createProfile(String username) throws IOException, InterruptedException {

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(apiUrl))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(
                    Json.createObjectBuilder().add("username", username).build().toString()
                ))
                .build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            logger.info("Response from create profile: " + response.body());
            JsonObject jsonObject = Json.createReader(new StringReader(response.body())).readObject();
            return jsonObject.getString("profile-id");
        }catch (Exception e){
            throw new RuntimeException("Error while creating profile" + e);
        }

    }

}