package com.mx.Empleado_B.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mx.Empleado_B.dao.Direccion_Dao;
import com.mx.Empleado_B.modelo.Direccion;

@Service
public class Implement_Direcciones implements Server_Direccion{

	// Inyección de dependencias
	@Autowired
	Direccion_Dao direccionDao;	
	
	@Transactional
	@Override
	public void guardarDireccion(Direccion direccion) {
		// TODO Auto-generated method stub
		direccionDao.save(direccion);
	}

	@Transactional
	@Override
	public void editarDireccion(Direccion direccion) {
		// TODO Auto-generated method stub
		direccionDao.save(direccion);
	}

	@Transactional
	@Override
	public void eliminarDireccion(Direccion direccion) {
		// TODO Auto-generated method stub
		direccionDao.delete(direccion);
	}

	@Transactional(readOnly = true)
	@Override
	public Direccion buscarDireccion(Direccion direccion) {
		// TODO Auto-generated method stub
		return direccionDao.findById(direccion.getId()).orElse(null);
	}

	@Transactional(readOnly = true)
	@Override
	public List<Direccion> listarDireccion() {
		// TODO Auto-generated method stub
		return (List<Direccion>) direccionDao.findAll();
	}

}
