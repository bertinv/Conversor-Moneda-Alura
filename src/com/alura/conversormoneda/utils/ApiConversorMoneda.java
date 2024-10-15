package com.alura.conversormoneda.utils;

import com.alura.conversormoneda.dto.MonedaDto;
import com.google.gson.Gson;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ApiConversorMoneda {

    protected static final String API_KEY = "04e5789ad56fd9aff66a9546";
    public MonedaDto convertirMoneda(String base_code, String target_code, double mount) {

        URI uri = URI.create("https://v6.exchangerate-api.com/v6/" + API_KEY + "/pair/" + base_code + "/" + target_code + "/" + mount);

        try (HttpClient client = HttpClient.newHttpClient()) {

            HttpRequest request = HttpRequest.newBuilder().uri(URI.create(String.valueOf(uri))).build();

            try {
                HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
                return new Gson().fromJson(response.body(), MonedaDto.class);

            } catch (Exception e) {
                throw new RuntimeException("Moneda no valida.");
            }
        }
    }
}
