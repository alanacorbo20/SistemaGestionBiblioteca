package ProyectoFinal;

import javax.swing.table.DefaultTableModel;

public class Gestor {

	MySQL bdd = new MySQL();

	public static void main(String[] args) {
		vtnInicioSesion is = new vtnInicioSesion();
		is.setVisible(true);

	}

	

	// Registro de Libros
	public void registrarLibro(String isbn, String titulo, String autor, String materia, String estadoFisico, int canEjemplares) throws IllegalArgumentException {

		campoVacio(isbn, "El ISBN no puede estar vacío");
		campoVacio(titulo, "El título no puede estar vacío");
		campoVacio(autor, "El autor no puede estar vacío");
		campoVacio(materia, "La materia no puede estar vacío");
		campoVacio(estadoFisico, "El estado físico no puede estar vacío");

		bdd.altaLibrosBDD(isbn, titulo, autor, materia, estadoFisico, canEjemplares);
	}

	public void campoVacio(String campo, String msj) throws IllegalArgumentException {
		if (campo == null || campo.trim().isEmpty()) {
			throw new IllegalArgumentException(msj);
		}
	}

	public void ListarLibro(DefaultTableModel modelo) {
		bdd.listarLibrosBDD(modelo);

	}
	public void modificarLibro(String isbn, String titulo, String autor, String materia, String estadoFisico, int cantidad) {
		campoVacio(isbn, "El ISBN no puede estar vacío");
		campoVacio(titulo, "El título no puede estar vacío");
		campoVacio(autor, "El autor no puede estar vacío");
		campoVacio(materia, "La materia no puede estar vacío");
		campoVacio(estadoFisico, "El estado físico no puede estar vacío");
	    bdd.modificarLibroBDD(isbn, titulo, autor, materia, estadoFisico, cantidad);
	}

	public void bajaLibro(String isbn) {

		bdd.bajaLibrosBDD(isbn);
	}
//Registro de personas
	public void altaPersona(int ci, String nombre, String apellido, String tipo, String grupo, int tel) {
		campoVacio(nombre, "El nombre no puede estar vacío");
		campoVacio(apellido, "El apellido no puede estar vacío");

		bdd.altaPersonaBDD(ci, nombre, apellido, tipo, grupo, tel);

	}

	public void ListarPersona(DefaultTableModel modelo) {
		bdd.listarPersonaBDD(modelo);

	}
	public void modificarPersona(int ci, String nombre, String apellido, String tipo, String grupo, int telefono) {
		campoVacio(nombre, "El nombre no puede estar vacío");
		campoVacio(apellido, "El apellido no puede estar vacío");
		campoVacio(tipo, "El tipo no puede estar vacío");
		campoVacio(grupo, "El grupo no puede estar vacío");
	    bdd.modificarPersonaBDD(ci, nombre, apellido, tipo, grupo, telefono);
	}

	public void bajaPersona(String ci) {

		bdd.bajaPersonaBDD(ci);
	}
//RegistroPrestamo de Libros
	public void altaPrestamo(int ci, String isbn, String fechaD) {

		campoVacio(isbn, "El ISBN no puede estar vacío");
		campoVacio(fechaD, "La fecha de devolución no puede estar vacía");
		bdd.altaPrestamoBDD(ci, isbn, fechaD);
	}

	public void ListarPrestamo(DefaultTableModel modelo) {
		bdd.listarPrestamo(modelo);

	}
	public void listarPrestamosRetrasados(DefaultTableModel modelo) {
		bdd.listarPrestamosRetrasados(modelo);

	}

	public void actualizarEstadoPrestamo(int id) {
		bdd.actualizarEstadoPrestamo(id);

	}

	public void actualizarEstadoRetrasado() {
		bdd.actualizarPrestamosR();

	}
	public void bajaPrestamo(int id) {

		bdd.bajaPrestamoBDD(id);
	}

	//Compus
	public void altaComputadora(int numero, String estadoFisico) {
		bdd.altaCompusBDD(numero, estadoFisico);
	}

	public void listarComputadoras(DefaultTableModel modelo) {
		bdd.listarCompusBDD(modelo);
	}


	public void bajaCompus(int num) {

		bdd.bajaCompusBDD(num);
	}


//Prestamo compus
	public void altaPrestamoCompus(int numero, int ci) {


		bdd.altaPrestamoCompusBDD(numero, ci);
	}

	public void listarPrestamosRetrasadosC(DefaultTableModel modelo) {
		bdd.listarPrestamosRetrasadosC(modelo);

	}

	public void ListarPrestamoCompus(DefaultTableModel modelo) {
		bdd.listarPrestamoCompusBDD(modelo);

	}
	public void actualizarEstadoPrestamoC(int id) {
		bdd.actualizarEstadoPrestamoC(id);

	}


	public void actualizarEstadoRetrasadoCompus() {
		bdd.actualizarPrestamosRCompu();

	}

	public void bajaPrestamoCompus(int id) {

		bdd.bajaPrestamoCompusBDD(id);
	}

	//Dinero
	public void altaDinero(float cobro) {


	    bdd.altaDineroBDD(cobro);
	}

	public void listarDinero(DefaultTableModel modelo) {
	    bdd.listarDinero(modelo);
	}
	public void listarMontoTotal(DefaultTableModel modelo) {
	    bdd.listarMontoTotal(modelo);
	}


	public void bajaDinero(int cobroId) {
		bdd.bajaDinero(cobroId);
    }


}
