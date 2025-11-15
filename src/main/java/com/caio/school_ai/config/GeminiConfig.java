package com.caio.school_ai.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.http.HttpClient;

@Configuration
public class GeminiConfig {

    @Value("${gemini.api.key}")
    private String apiKey;

    @Bean
    public String geminiApiKey(){
        return apiKey;
    }

    @Bean
    public HttpClient httpClient(){
        return HttpClient.newHttpClient();
    }

}
