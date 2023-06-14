package com.prueba.tecnica.services;

import org.springframework.http.ResponseEntity;

import com.prueba.tecnica.models.entity.Producto;

import reactor.core.publisher.Mono;

public interface PeticionService {

	public Integer[] buscaSimilaresById(int id);
	public Mono<ResponseEntity<Producto>> buscaById(int id);
	
}
