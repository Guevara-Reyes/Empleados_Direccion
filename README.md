# Empleados_Direccion

Cuenta con 2 metodos POST crea cliente y GET Obtiene todos los clientes  Que debo de hacer:     
    * Compile y que corra el proyecto
    * Agregar validaciones a los campos   
    * Agregar mas datos a cliente (correo, número de empleado, etc)   
    * Agregar tabla de direcciones    
    * Relacionar direcciones con cliente


    +------------------+       +----------------------+
|     EMPLEADO_B   |       |       DIRECCION      |
+------------------+       +----------------------+
| ID (PK)          |       | ID (PK)              |
| NOMBRE           |       | NOMBRE_CALLE         |
| APP              |       | NUMERO_EXT           |
| APM              |       | COLONIA              |
| CORREO           |       | ID_EMPLEADO (FK)     |
| ESTATUS          |       +----------------------+
+------------------+



Agregar metodos:

  * Actualiza cliente
  * Crea direcciones
  * Relaciona direcciones a cliente
  * Obtiene clientes con dirección
