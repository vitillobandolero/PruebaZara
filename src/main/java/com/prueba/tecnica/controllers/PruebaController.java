package com.prueba.tecnica.controllers;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.prueba.tecnica.models.entity.Producto;
import com.prueba.tecnica.services.PeticionService;

import reactor.core.publisher.Mono;

@RestController
public class PruebaController {

	private static final Logger log = LogManager.getLogger(PruebaController.class);

	@Autowired
	PeticionService service;

	@GetMapping("/product/{id}/similar")
	public ResponseEntity<?> asignarAlumnos(@PathVariable int id) {
		
		Integer[] similares;
		List<Producto> productos = new ArrayList<>();
		
		try {
			similares = service.buscaSimilaresById(id);
		} catch (Exception e) {
			return new ResponseEntity<>("Product Not Found", HttpStatus.NOT_FOUND);
		}	

		for (int s : similares) {
			try {
				Mono<ResponseEntity<Producto>> response = service.buscaById(s);
				Producto producto = response.block().getBody();
				productos.add(producto);
			} catch (Exception e) {
				log.error("No se pudo obtener el producto con ID: " + s);
				// Aquí podemos omitirlo o meter un objeto de fallo por ejemplo:
				Producto errorProducto = new Producto();
				errorProducto.setId(String.valueOf(s));
				errorProducto.setName("Not Found");
				productos.add(errorProducto);
			}

		}

		return new ResponseEntity<>(productos, HttpStatus.OK);
	}	

}
