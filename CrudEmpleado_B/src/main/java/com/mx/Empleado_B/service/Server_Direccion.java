package com.mx.Empleado_B.service;

import java.util.List;

import com.mx.Empleado_B.modelo.Direccion;


public interface Server_Direccion {

	public void guardarDireccion(Direccion direccion);

	public void editarDireccion(Direccion direccion);

	public void eliminarDireccion(Direccion direccion);

	public Direccion buscarDireccion(Direccion direccion);

	public List<Direccion> listarDireccion();
}
