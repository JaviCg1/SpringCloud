package com.uem.restcoche.modelo.negocio;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uem.restcoche.modelo.entidad.Coche;
import com.uem.restcoche.modelo.persistencia.DaoCoche;

@Service
public class GestorCoche {

	//En esta parte vamos a crear un servicio rest que proporcione
	//un CRUD basico de personas a nuestros posibles clientes
	//(app android, app iOS, app java)
	@Autowired
	private DaoCoche daoCoche;
	
	public List<Coche> listar(){
		//aqui podria poner toda la  logica de negocio de mi app
		//ejempolos:
		//1- puede el usuario consultar las personas
		//2- Tengo que mostrar todas las personas o solo algunas?
		List<Coche> lista = daoCoche.findAll();
		return lista;
	}
	
	/**
	 * Metodo que busca en la bbdd un objeto persona dado un id
	 * 
	 * @param id representa el id a buscar
	 * @return el objeto persona en caso de que exista en la bbdd y null
	 * en caso de que no
	 */
	public Coche obtener(int id) {
		Optional<Coche> opt = daoCoche.findById(id);
		//si no encuentra el objeto el optional estaria vacio
		if(opt.isPresent()) {
			return opt.get();//findById() devuelve un optional
		}else {
			return null;
		}		
	}
	
	/**
	 * Metodo que da de alta una persona en la bbdd, no debemos pasarle
	 * id
	 * @param persona el objeto que vamos a dar de alta en la bbdd
	 * @return la persona dada de alta en la bbdd o null en caso de que 
	 * el nombre de la persona tenga menos de 3 caracteres
	 */
	public Coche alta(Coche coche) {
		//podriamos poner toda la logica de la aplicacion
		//En caso de que tengamos una regla de negocio de que el nombre
		//tenga que tener al menos 3 caracteres, la podriamos aqui
		if(coche.getMatricula().length() < 3) {
			return null;
		}

		Coche p = daoCoche.save(coche);
		return p;
	}
	
	public Coche modificar(Coche coche) {
		if(coche.getMatricula().length() < 3) {
			return null;
		}
		//si ejecutamos el save con un id, modificamos dicho registro
		//si no le pasamos id, como en el caso de arriba, lo damos de alta
		Coche p = daoCoche.save(coche);
		return p;
	}
	
	/**
	 * Metodo que borra una persona a partir de una id de la bbdd
	 * @param id de la persona a borrar
	 * @return true en caso que la persona exista en la bbdd false
	 * en caso de que no exista
	 */
	public boolean borrar(int id) {
		if(daoCoche.findById(id).isPresent()) {
			daoCoche.deleteById(id);
			return true;
		}else {
			return false;
		}
		
	}
}
