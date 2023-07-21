package com.mx.Empleado_B.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

//Estereotipos o anotaciones--- Indicar que esta clase pertenece a una tabla
@Entity
@Table(name = "DIREECION")
public class Direccion {
	
	/*
	 *
	ID NUMBER PRIMARY KEY NOT NULL,
	NOMBRE_CALLE NVARCHAR2(50) NOT NULL,
	NUMERO_EXT number NOT NULL,
	COLONIA NVARCHAR2(50) NOT NULL,
	ID_EMPLEADO NUMBER NOT NULL,
	FOREIGN KEY(ID_EMPLEADO) REFERENCES EMPLEADO_B (ID)
	 */
	
	// MAPEAR LA TABLA --- Variables o atributos de mi objeto
	@Id
	int id;
	String nombre_Calle;
	int numero_ext;
	String colonia;

	// Cardinadilad
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ID_EMPLEADO")
	Empleado_B empleadoB;

	public Direccion() {
	}

	public Direccion(int id) {
		this.id = id;
	}

	public Direccion(int id, String nombre_Calle, int numero_ext, String colonia, Empleado_B empleadoB) {
		this.id = id;
		this.nombre_Calle = nombre_Calle;
		this.numero_ext = numero_ext;
		this.colonia = colonia;
		this.empleadoB = empleadoB;
	}

	@Override
	public String toString() {
		return "Direccion [id=" + id + ", nombre_Calle=" + nombre_Calle + ", numero_ext=" + numero_ext + ", colonia="
				+ colonia + ", empleadoB=" + empleadoB + "]\n";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre_Calle() {
		return nombre_Calle;
	}

	public void setNombre_Calle(String nombre_Calle) {
		this.nombre_Calle = nombre_Calle;
	}

	public int getNumero_ext() {
		return numero_ext;
	}

	public void setNumero_ext(int numero_ext) {
		this.numero_ext = numero_ext;
	}

	public String getColonia() {
		return colonia;
	}

	public void setColonia(String colonia) {
		this.colonia = colonia;
	}

	public Empleado_B getEmpleadoB() {
		return empleadoB;
	}

	public void setEmpleadoB(Empleado_B empleadoB) {
		this.empleadoB = empleadoB;
	}

	
		

}
