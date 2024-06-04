package com.josemaba.springboot.client.controller;

import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class ApiPokemonController {
    private final RestTemplate restTemplate;

    public ApiPokemonController() {
        this.restTemplate = new RestTemplate();
    }

    @GetMapping("/consume-api-pokemon")
    @ResponseBody
    public ResponseEntity<String> consumeApi() {
        String url = "https://pokeapi.co/api/v2/pokemon/ditto";
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, null, String.class);
        return ResponseEntity.ok()
                .header("Content-Type", "application/json")
                .body(response.getBody());
    }
}
