package com.mx.Empleado_B.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.mx.Empleado_B.dao.Empleado_B_Dao;
import com.mx.Empleado_B.modelo.Empleado_B;


@Service
public class Implement_Empleado implements Sercer_Empleado_B{

	// Inyección de dependencias
	@Autowired
	Empleado_B_Dao empleadoDao;
	
	@Transactional
	@Override
	public void guardarEmpleado_B(Empleado_B empleadoB) {
		// TODO Auto-generated method stub
		empleadoDao.save(empleadoB);
	}

	@Override
	public void editarEmpleado_B(Empleado_B empleadoB) {
		// TODO Auto-generated method stub
		empleadoDao.save(empleadoB);
	}

	@Transactional
	@Override
	public void eliminarEmpleado_B(Empleado_B empleadoB) {
		// TODO Auto-generated method stub
		empleadoDao.delete(empleadoB);
	}

	@Transactional(readOnly = true)
	@Override
	public Empleado_B buscarEmpleado_B(Empleado_B empleadoB) {
		// TODO Auto-generated method stub
		return empleadoDao.findById(empleadoB.getId()).orElse(null);
	}

	@Transactional(readOnly = true)
	@Override
	public List<Empleado_B> listarEmpleado_B() {
		// TODO Auto-generated method stub
		return (List<Empleado_B>) empleadoDao.findAll();
	}

}
