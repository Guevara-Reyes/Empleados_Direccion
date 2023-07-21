/////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////
package com.mx.Empleado_B.controller;

/////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.mx.Empleado_B.modelo.Direccion;
import com.mx.Empleado_B.modelo.Empleado_B;
import com.mx.Empleado_B.service.Implement_Direcciones;
import com.mx.Empleado_B.service.Implement_Empleado;

//Estereotipos o anotaciones
@RestController
@RequestMapping("Empleado_Direcciones_WS")
@CrossOrigin
public class Empleado_Direcciones_WS {

/////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////

	// ZONA DE INYECCIÓN DE DEPENDENCIAS

	// Inyeccion de dependencias Empleado
	@Autowired
	Implement_Empleado impE;
	// Inyeccion de dependencias Direcciones
	@Autowired
	Implement_Direcciones impD;

/////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////

	// OPERACIONES CRUD EMPLEADOS

	// Mostar información del empleado
	// URL: http://localhost:9000/Empleado_Direcciones_WS/mostrarEmpleado
	@GetMapping("mostrarEmpleado")
	public List<Empleado_B> mostrarEmpleado() {
		List<Empleado_B> ListEmpleado = impE.listarEmpleado_B();
		return ListEmpleado;
	}

	// Giardar información del empleado
	// URL: http://localhost:9000/Empleado_Direcciones_WS/guardaEmpleado
	@PostMapping("guardaEmpleado")
	public ResponseEntity<String> guardaEmpleado(@RequestBody Empleado_B empleadoB) {

		// Validar si el nombre y apellido del empleado ya existen en la base de datos
		List<Empleado_B> existingEmployeeds = impE.listarEmpleado_B();
		for (Empleado_B emp : existingEmployeeds) {
			if (emp.getNombre().equalsIgnoreCase(empleadoB.getNombre())
					&& emp.getApp().equalsIgnoreCase(empleadoB.getApp()) && emp.getApm().equals(empleadoB.getApm())) {
				return ResponseEntity.badRequest()
						.body("|| Disculpe se ha generado un error: 400 al guardar el empleado.\n"
								+ "|| Se verifico en la base de datos y el empleado ya existe en la BD.\n"
								+ "|| Por favor ingrese el nuevo empleado que no exista dentro de la base de datos");
			}
			if (emp.getCorreo().equalsIgnoreCase(empleadoB.getCorreo())) {
				return ResponseEntity.badRequest()
						.body("|| Disculpe se ha generado un error: 400 al guardar el empleado.\n"
								+ "|| El correo electrónico ya está en uso por otro empleado en la BD.\n"
								+ "|| Por favor ingrese un nuevo correo electrónico");
			}

		}
		// Guardar el empleado en la base de datos
		impE.guardarEmpleado_B(empleadoB);
		return new ResponseEntity<String>("|| Empleado cumple con todo los requerimientos necesarios. \n "
				+ "|| Se ha registrado y guardado exitosamente al campo laboral.\n"
				+ "**************************BIENVENIDO**************************", HttpStatus.OK);
	}

	// Buscar información del empleado
	// URL: http://localhost:9000/Empleado_Direcciones_WS/buscarEmpleado
	@PostMapping("buscarEmpleado")
	public ResponseEntity<String> buscarEmpleado(@RequestBody Empleado_B empleadoB) {
		try {
			empleadoB = impE.buscarEmpleado_B(empleadoB);

			if (empleadoB != null) {
				// Si se encontraron datos del empleado, retornar la información correspondiente
				return new ResponseEntity<String>("|| Se ha encontrado la información del empleado de forma exitosa",
						HttpStatus.OK);
			} else {
				// Si el empleado no existe, mostrar el mensaje correspondiente
				return ResponseEntity.badRequest().body("|| No se encontró información del empleado");
			}

		} catch (Exception e) {
			// TODO: handle exception
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("|| Ocurrió un error al buscar la información del empleado");
		}
	}

	// Editar información del empleado
	// URL: http://localhost:9000/Empleado_Direcciones_WS/editarEmpleado
	@PostMapping("editarEmpleado")
	public ResponseEntity<String> editarEmpleado(@RequestBody Empleado_B empleadoB) {
		// Obtener el empleado existente de la base de datos por su ID
		Empleado_B empleadoExistente = impE.buscarEmpleado_B(empleadoB); // Asumimos que existe un método
																			// obtenerEmpleadoPorId() que obtiene el
																			// empleado por su ID

		if (empleadoExistente == null) {
			// Si el empleado no existe en la base de datos, devolver un mensaje de error
			return ResponseEntity.badRequest()
					.body("|| No se pudo editar la información del empleado porque el número de empleado no existe.");
		}

		// Validar si el nombre ha sido modificado y si ya existe otro empleado con el
		// mismo nombre
		if (!empleadoExistente.getNombre().equalsIgnoreCase(empleadoB.getNombre())) {
			List<Empleado_B> existingEmployeeds = impE.listarEmpleado_B();
			for (Empleado_B emp : existingEmployeeds) {
				if (emp.getId() != empleadoB.getId() && emp.getNombre().equalsIgnoreCase(empleadoB.getNombre())) {
					return ResponseEntity.badRequest()
							.body("|| No se pudo editar la información del empleado.\n"
									+ "|| Ya existe otro empleado con el mismo nombre en la base de datos.\n"
									+ "|| Por favor, ingrese un nuevo nombre.");
				}
			}
		}

		// Validar si el correo ha sido modificado y si ya existe otro empleado con el
		// mismo correo
		if (!empleadoExistente.getCorreo().equalsIgnoreCase(empleadoB.getCorreo())) {
			List<Empleado_B> existingEmployeeds = impE.listarEmpleado_B();
			for (Empleado_B emp : existingEmployeeds) {
				if (emp.getId() != empleadoB.getId() && emp.getCorreo().equalsIgnoreCase(empleadoB.getCorreo())) {
					return ResponseEntity.badRequest()
							.body("|| No se pudo editar la información del empleado.\n"
									+ "|| El correo electrónico ya está en uso por otro empleado en la base de datos.\n"
									+ "|| Por favor, ingrese un nuevo correo electrónico.");
				}
			}
		}

		// Guardar los cambios en el empleado en la base de datos
		impE.editarEmpleado_B(empleadoB);
		return new ResponseEntity<String>("|| Se ha editado la información del empleado de forma exitosa",
				HttpStatus.OK);
	}

	// Eliminar información del Empleado
	// URL: http://localhost:9000/Empleado_Direcciones_WS/eliminarEmpleado
	@PostMapping("eliminarEmpleado")
	public ResponseEntity<String> eliminarEmpleado(@RequestBody Empleado_B empleadoB) {
		try {
			// Primero, intentamos obtener el empleado de la base de datos utilizando el ID
			// proporcionado
			Empleado_B empleadoExistente = impE.buscarEmpleado_B(empleadoB); // Aquí asumimos que hay un método
																				// obtenerEmpleadoPorId() que obtiene el
																				// empleado por su ID

			if (empleadoExistente != null) {
				// Si el empleado existe en la base de datos, procedemos a eliminarlo
				impE.eliminarEmpleado_B(empleadoExistente);
				return new ResponseEntity<String>("|| Se eliminó el empleado de forma exitosa.", HttpStatus.OK);
			} else {
				// Si el empleado no existe en la base de datos, mostramos el mensaje
				// correspondiente
				return new ResponseEntity<String>(
						"|| No se eliminó la información del empleado ya que el número de empleado no existe.",
						HttpStatus.INTERNAL_SERVER_ERROR);
			}

		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<String>("|| Ocurrió un error al eliminar la información del empleado.",
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

/////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////

	// OPERACIONES CRUD DIRECCIONES

	// Mostar información de la dirección con el empleado que pertenece
	// URL: http://localhost:9000/Empleado_Direcciones_WS/mostrarDireccion
	@GetMapping("mostrarDireccion")
	public List<Direccion> mostrarDireccion() {
		List<Direccion> ListDireccion = impD.listarDireccion();
		return ListDireccion;
	}

	// Guardar información para agrenar nueva direccion
	// URL: http://localhost:9000/Empleado_Direcciones_WS/guardarDireccion
	@PostMapping("guardarDireccion")
	public ResponseEntity<String> guardarDireccion(@RequestBody Direccion direccion ) {
		impD.guardarDireccion(direccion);
		return new ResponseEntity<String>("|| Se ha guardado la información de la nueva direccion de forma exitosa",
				HttpStatus.OK);
	}

	// Buscar información del empleado
	// URL: http://localhost:9000/Empleado_Direcciones_WS/buscardireccion
	@PostMapping("buscardireccion")
	public ResponseEntity<String> buscardireccion(@RequestBody Direccion direccion) {
		try {
			direccion = impD.buscarDireccion(direccion);

			if (direccion != null) {
				// Si se encontraron datos del empleado, retornar la información correspondiente
				return new ResponseEntity<String>("|| Se ha encontrado la información de la dirección de forma exitosa",
						HttpStatus.OK);
			} else {
				// Si el empleado no existe, mostrar el mensaje correspondiente
				return ResponseEntity.badRequest().body(
						"|| No se encontró información de la dirección, esta no se encuentra en la Base de datos");
			}

		} catch (Exception e) {
			// TODO: handle exception
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("|| Ocurrió un error al buscar la información del empleado");
		}

	}

	// Editar información de alguna direccion
	// URL: http://localhost:9000/Empleado_Direcciones_WS/editarDireccion
	@PostMapping("editarDireccion")
	public ResponseEntity<String> editarDireccion(@RequestBody Direccion direccion) {
		impD.editarDireccion(direccion);
		return new ResponseEntity<String>("|| Se ha editado la información del forma exitosa", HttpStatus.OK);
	}

	// Eliminar información de alguna direccion
	// URL: http://localhost:9000/Empleado_Direcciones_WS/eliminarDireccion
	//@PostMapping("eliminarDireccion")
//	public ResponseEntity<String> eliminarDireccion(@RequestBody Direccion direccion) {
	//	impD.eliminarDireccion(direccion);
		//return new ResponseEntity<String>("|| Se ha eliminado la dirección de forma exitosa", HttpStatus.OK);
	//}
	
	@PostMapping("eliminarDireccion")
	public ResponseEntity<String> eliminarDireccion(@RequestBody Direccion direccion) {
		try {
			// Primero, intentamos obtener el empleado de la base de datos utilizando el ID
			// proporcionado
			Direccion empleadoExistente = impD.buscarDireccion(direccion); // Aquí asumimos que hay un método
																				// obtenerEmpleadoPorId() que obtiene el
																				// empleado por su ID

			if (empleadoExistente != null) {
				// Si el empleado existe en la base de datos, procedemos a eliminarlo
				impD.editarDireccion(empleadoExistente);
				return new ResponseEntity<String>("|| Se eliminó la direccion de forma exitosa.", HttpStatus.OK);
			} else {
				// Si el empleado no existe en la base de datos, mostramos el mensaje
				// correspondiente
				return new ResponseEntity<String>(
						"|| No se eliminó la información del la direccion ya que no existe.",
						HttpStatus.INTERNAL_SERVER_ERROR);
			}

		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<String>("|| Ocurrió un error al eliminar la información del empleado.",
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
