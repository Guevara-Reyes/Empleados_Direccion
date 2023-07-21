package com.mx.Empleado_B.modelo;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

//Estereotipos o anotaciones--- Indicar que esta clase pertenece a una tabla
@Entity
@Table(name = "EMPLEADO_B")
public class Empleado_B {
	
	/*
	 ID NUMBER PRIMARY KEY NOT NULL,
	NOMBRE NVARCHAR2(50) NOT NULL,
	APP NVARCHAR2(50) NOT NULL,
	APM NVARCHAR2(50) NOT NULL,
	CORREO NVARCHAR2(50) NOT NULL,
	ESTATUS NUMBER NOT NULL
	 */

	// MAPEAR LA TABLA --- Variables o atributos de mi objeto
	@Id
	int id;
	String nombre;
	String app;
	String apm;
	String correo;
	int estatus;
	
	@OneToOne(mappedBy = "empleadoB", cascade = CascadeType.ALL)
	Direccion direccion;

	public Empleado_B() {
	}

	public Empleado_B(int id) {
		this.id = id;
	}

	public Empleado_B(int id, String nombre, String app, String apm, String correo, int estatus) {
		this.id = id;
		this.nombre = nombre;
		this.app = app;
		this.apm = apm;
		this.correo = correo;
		this.estatus = estatus;
	}

	@Override
	public String toString() {
		return "Empleado_B [id=" + id + ", nombre=" + nombre + ", app=" + app + ", apm=" + apm + ", correo=" + correo
				+ ", estatus=" + estatus + "]\n";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApp() {
		return app;
	}

	public void setApp(String app) {
		this.app = app;
	}

	public String getApm() {
		return apm;
	}

	public void setApm(String apm) {
		this.apm = apm;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public int getEstatus() {
		return estatus;
	}

	public void setEstatus(int estatus) {
		this.estatus = estatus;
	}

	
	
}

