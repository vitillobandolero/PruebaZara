package com.prueba.tecnica.services;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import com.prueba.tecnica.models.entity.Producto;

import reactor.core.publisher.Mono;


@Service
public class PeticionServiceImpl implements PeticionService{

	@Override
	public Integer[] buscaSimilaresById(int id) {
        String url = "http://localhost:3001/product/" + id + "/similarids";

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Integer[]> response = restTemplate.getForEntity(url, Integer[].class);

        return response.getBody();
    }

	@Override	
	public Mono<ResponseEntity<Producto>> buscaById(int id) {
	    String url = "http://localhost:3001/product/" + id;

	    WebClient webClient = WebClient.create();
	    Mono<ResponseEntity<Producto>> response = webClient.get()
	            .uri(url)
	            .retrieve()
	            .toEntity(Producto.class);

	    return response;
	}

}
