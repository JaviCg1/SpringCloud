package com.uem.restcoche.controlador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.uem.restcoche.modelo.entidad.Coche;
import com.uem.restcoche.modelo.negocio.GestorCoche;
import com.uem.restcoche.modelo.persistencia.DaoCoche;


//Va a dar de alta un objeto de tipo Controlador persona con capacidad
//de recibir peticiones http

//Dentro de un MVC esta capa se encargaría de recibir la informacion de los
//clientes, traducirla y mandarsela a la capa de negocio para consultar
//o precesar la peticion
@RestController
public class ControladorCoche {
	
	@Autowired
	private GestorCoche gp;
	
	//ResponseEntity nos permite devolver a parte del objeto, tambien 
	//el codigo de respuesta
	@GetMapping("coches")
	public ResponseEntity<List<Coche>> listar(){
		System.out.println("Entrando por get (listar)");
		List<Coche> lista = gp.listar();
		ResponseEntity<List<Coche>> re = 
				new ResponseEntity<List<Coche>>(lista, HttpStatus.OK);
		String S;
		return re;
	}
	
	
	@GetMapping("coches/{id}")//personas/1
	public ResponseEntity<Coche> obtener(@PathVariable("id") int id){//1
		System.out.println("Entrando por get (obtener)");
		Coche p = gp.obtener(id);
		HttpStatus codigoRespuesta = null;
		if(p != null) {
			codigoRespuesta = HttpStatus.OK;
		}else {
			codigoRespuesta = HttpStatus.NOT_FOUND;
		}
		
		ResponseEntity<Coche> re = 
				new ResponseEntity<Coche>(p, codigoRespuesta);
		return re;
	}
	
	//Con @RequestBody le decimos a spring que el json que venga en el body
	//de la request http del cliente lo convierta automaticamente a un 
	//objeto de tipo persona
	@PostMapping("coches")
	public ResponseEntity<Coche> alta(@RequestBody Coche coches){
		System.out.println("Entrando por post");
		Coche pAlta = gp.alta(coches);
		HttpStatus codigoRespuesta = null;
		if(pAlta != null) {
			codigoRespuesta = HttpStatus.CREATED;
		}else {
			codigoRespuesta = HttpStatus.BAD_REQUEST;
		}
		
		ResponseEntity<Coche> re = 
				new ResponseEntity<Coche>(pAlta, codigoRespuesta);
		return re;
	}
	
	@PutMapping("coches/{id}")
	public ResponseEntity<Coche> modificar(
					@RequestBody Coche coche,
					@PathVariable("id") int id){
		System.out.println("Entrando por put");
		coche.setId(id);
		
		Coche pModificado = gp.modificar(coche);
		HttpStatus codigoRespuesta = null;
		if(pModificado != null) {
			codigoRespuesta = HttpStatus.OK;
		}else {
			codigoRespuesta = HttpStatus.BAD_REQUEST;
		}
		
		ResponseEntity<Coche> re = 
				new ResponseEntity<Coche>(pModificado, codigoRespuesta);
		return re;
	}
	
	@DeleteMapping("coches/{id}")
	public ResponseEntity<Integer> borrar(@PathVariable("id") int id){
		System.out.println("Entrando por delete");
		boolean borrado = gp.borrar(id);
		HttpStatus codigoRespuesta = null;
		if(borrado) {
			codigoRespuesta = HttpStatus.OK;
		}else {
			codigoRespuesta = HttpStatus.BAD_REQUEST;
		}

		return new ResponseEntity<Integer>(id,codigoRespuesta);
	}
}



