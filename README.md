![Duoc UC](https://www.duoc.cl/wp-content/uploads/2022/09/logo-0.png)
# 🧠 Biblioteca Escolar – Desarrollo Orientado a Objetos II

## 👤 Autor del proyecto
- **Nombre completo:** [Arturo Ignacio Sandoval Gonzalez]
- **Carrera:** Analista programador computacional
- **Sede:** [Concepcion]

---

## 📘 Descripcion general del sistema
Biblioteca Escolar es una aplicación de escritorio desarrollada en Java con Swing, JDBC y MySQL, utilizando Apache NetBeans. Permite gestionar libros, estudiantes y prestamos de una biblioteca escolar, consultar reportes y acceder mediante inicio de sesion con permisos de roles.

---

## 🧱 Estructura general del proyecto

```plaintext
📁src/

	-app/
		Main.java

	-conexion/
		DatabaseConnection.java

	-controlador/
		BibliotecaController.java

	-dao/
		CategoriaDAO.java
		EstudianteDAO.java
		LibroDAO.java
		PrestamoDAO.java
		UsuarioDAO.java

	-interfaces/
		Administrable.java
		
	-modelo/
		Bibliotecario.java
		Categoria.java
		Estudiante.java
		Libro.java
		Prestamo.java
		Usuario.java

	-vista/
		EstudiantesForm.java
		EstudiantesForm.form
		LibrosForm.java
		LibrosForm.form
		LoginForm.java
		LoginForm.form
		PrestamosForm.java
		PrestamosForm.form
		PrincipalForm.java
		PrincipalForm.form
		ReportesForm.java
		ReportesForm.form
	



    /lib/
        mysql-connector-j-26.7.0.jar

    /sql/
        PRY2203_EFT_S9_Script_crea_tablas_biblioteca.sql
	PRY2203_EFT_S9_Script_poblado_tablas_biblioteca.sql


````


## ⚙️ Instrucciones de uso y funciones:

1. Abrir el proyecto BibliotecaEscolar en NetBeans.

2. Ejecutar la clase app.Main.

3. Inicio de sesion y navegacion entre ventanas.

4. Registro, consulta, actualizacion y eliminacion de libros y estudiantes.

5. Registro y devolucion de prestamos, incluida la modificacion del stock.

6. Prevencion de una segunda devolucion del mismo prestamo.

7. Actualizacion de los indicadores de reportes.

8. Permisos visuales para bibliotecarios y estudiantes.


---
## 📘 Tecnologias utilizadas:

-Java y programación orientada a objetos (clases, herencia, abstracción e interfaces).

-Java Swing y diseñador gráfico de Apache NetBeans.

-MySQL, JDBC, PreparedStatement y patrón DAO.

-Patrón Singleton para la conexión a la base de datos.

-Transacciones SQL para registrar préstamos y devoluciones.

-SwingWorker para trabajo en segundo plano.

---

**Repositorio GitHub:** https://github.com/artusand/BibliotecaEscolar
**Fecha de entrega:** \[11/10/2026]

---

© Duoc UC | Escuela de Informática y Telecomunicaciones | Semana 9 Evaluacion Final Transversal



