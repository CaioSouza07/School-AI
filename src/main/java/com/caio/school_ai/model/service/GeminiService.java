package com.caio.school_ai.model.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Service
public class GeminiService {

    private final HttpClient httpClient;

    @Value("${gemini.api.key}")
    private String apiKey;

    @Value("${gemini.api.url}")
    private String apiUrl;

    public GeminiService(HttpClient httpClient){
        this.httpClient = httpClient;
    }

    //FIZ UM METODO PARA GERAR UMA RESPOSTA PARA TESTAR A ALPICAÇÃO
    public String gerarResposta(String prompt) throws Exception {

        String body = """
        {
          "contents": [{
            "parts": [{
              "text": "%s"
            }]
          }]
        }
        """.formatted(prompt);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(apiUrl + "?key=" + apiKey))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(body))
                .build();

        HttpResponse<String> response =
                httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        return response.body();
    }

}
