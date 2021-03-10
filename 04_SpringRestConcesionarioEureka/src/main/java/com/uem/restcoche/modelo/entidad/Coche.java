package com.uem.restcoche.modelo.entidad;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

//Todas las aplicaciones se empiezan siempre por las entidades, que se pueden
//hacer con Javabeans o se pueden hacer como tablas y relaciones de BBDD
@Entity(name = "coches")
public class Coche {
	@Id
	@GeneratedValue
	private int id;
	private String matricula, marca, modelo, potencia;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getMatricula() {
		return matricula;
	}
	public void setMatricula(String nombre) {
		this.matricula = nombre;
	}
	public String getMarca() {
		return marca;
	}
	public void setMarca(String marca) {
		this.marca = marca;
	}
	public String getModelo() {
		return modelo;
	}
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}
	public String getPotencia() {
		return potencia;
	}
	public void setPotencia(String potencia) {
		this.potencia = potencia;
	}
	
	
}
