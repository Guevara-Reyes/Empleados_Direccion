package com.mx.Empleado_B.service;

import java.util.List;

import com.mx.Empleado_B.modelo.Empleado_B;


public interface Sercer_Empleado_B {

	public void guardarEmpleado_B(Empleado_B empleadoB);

	public void editarEmpleado_B(Empleado_B empleadoB);

	public void eliminarEmpleado_B(Empleado_B empleadoB);

	public Empleado_B buscarEmpleado_B(Empleado_B empleadoB);

	public List<Empleado_B> listarEmpleado_B();
}
