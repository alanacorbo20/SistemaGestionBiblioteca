package ProyectoFinal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class MySQL {

	private final String DB_URL = "jdbc:mysql://170.249.219.114/program1_equipo3?useSSL=false";
	private final String DB_USER = "program1_estudiantes";
	private final String DB_PASS = "estudiantesarrayanes";
	
	/*private final String DB_URL = "jdbc:mysql://127.0.0.1/biblioteca2025?useSSL=false";
	private final String DB_USER = "root";
	private final String DB_PASS = "";*/

	private Connection getConnection() throws SQLException {
		return DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);

	}

	// Libros
	public void altaLibrosBDD(String isbn, String titulo, String autor, String materia, String estadoFisico,
			int canEjemplares) {

		String sentencia = "INSERT INTO libros (isbn, titulo, autor, estadoFisico, materia, canEjemplares) "
				+ "VALUES (?, ?, ?, ?, ?, ?)";

		try {
			Connection conexion = getConnection();
			PreparedStatement ps = conexion.prepareStatement(sentencia);

			ps.setString(1, isbn);
			ps.setString(2, titulo);
			ps.setString(3, autor);
			
			ps.setString(4, estadoFisico);
			ps.setString(5, materia);
			ps.setInt(6, canEjemplares);

			ps.executeUpdate();

		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
		}
	}

	/*public void altaLibrosBDD(String isbn, String titulo, String autor, String materia, String estadoFisico,
			int canEjemplares) {

		String sentencia = "INSERT INTO libros (isbn, titulo, autor, estadoFisico, materia, canEjemplares) "
				+ "VALUES ('" + isbn + "', '" + titulo + "', '" + autor + "', '" + estadoFisico + "', '" + materia
				+ "', " + canEjemplares + ")";

		try {
			Connection conexion = getConnection();
			PreparedStatement ps = conexion.createStatement();
			ps.executeUpdate(sentencia);

		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
		}
	}*/

	public void listarLibrosBDD(DefaultTableModel modelo) {
		String sentencia = "SELECT isbn, titulo, autor, estadoFisico, materia, canEjemplares FROM libros";

		try {
			Connection conexion = getConnection();
			PreparedStatement declaracionSQL = conexion.prepareStatement(sentencia);
			ResultSet rs = declaracionSQL.executeQuery();

			modelo.setRowCount(0);

			while (rs.next()) {
				String isbn = rs.getString(1);
				String titulo = rs.getString(2);
				String autor = rs.getString(3);
				String estadoFisico = rs.getString(4);
				String materia = rs.getString(5);
				int canEjemplares = rs.getInt(6);

				Object[] fila = { isbn, titulo, autor, materia, estadoFisico, canEjemplares };
				modelo.addRow(fila);
			}

		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
		}
	}

	public void bajaLibrosBDD(String isbn) {

		String sentencia = "DELETE FROM libros WHERE isbn = '" + isbn + "'";

		try {
			Connection conexion = getConnection();
			PreparedStatement declaracionSQL = conexion.prepareStatement(sentencia);
			declaracionSQL.executeUpdate();

		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
		}
	}

	public void modificarLibroBDD(String isbn, String titulo, String autor, String materia, String estadoFisico,
			int cantidad) {

		// String sentencia = "UPDATE libros SET titulo=?, autor=?, materia=?,
		// estadoFisico=?, canEjemplares=? WHERE isbn=?";
		String sentencia = "UPDATE libros SET titulo='" + titulo + "', autor='" + autor + "', materia='" + materia
				+ "', estadoFisico='" + estadoFisico + "', canEjemplares=" + cantidad + " WHERE isbn='" + isbn + "'";
		try {
			Connection conexion = getConnection();
			PreparedStatement declaracionSQL = conexion.prepareStatement(sentencia);
			/*
			 * declaracionSQL.setString(1, titulo); declaracionSQL.setString(2, autor);
			 * declaracionSQL.setString(3, materia); declaracionSQL.setString(4,
			 * estadoFisico); declaracionSQL.setInt(5, cantidad);
			 * declaracionSQL.setString(6, isbn);
			 */

			declaracionSQL.executeUpdate();
			JOptionPane.showMessageDialog(null, "Libro modificado correctamente");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error al modificar libro: " + e.getMessage());
		}
	}

	// Personas
	public void altaPersonaBDD(int ci, String nombre, String apellido, String tipo, String grupo, int telefono) {

		String sentencia = "INSERT INTO personas VALUES ('" + ci + "','" + nombre + "','" + apellido + "','" + tipo
				+ "','" + grupo + "')";

		String sentencia2 = "INSERT INTO telefonos VALUES (" + ci + ", " + telefono + ")";

		try {
			Connection conexion = getConnection();
			PreparedStatement declaracionSQL = conexion.prepareStatement(sentencia);
			declaracionSQL.executeUpdate();

			PreparedStatement declaracionSQL2 = conexion.prepareStatement(sentencia2);
			declaracionSQL2.executeUpdate();

		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
		}

	}

	public void listarPersonaBDD(DefaultTableModel modelo) {

		String sentencia = "SELECT ci, nombre, apellido, tipo, grupo FROM personas";
		String sentencia2 = "SELECT telefono FROM telefonos " + "INNER JOIN personas ON personas.ci=telefonos.ci";

		try {
			Connection conexion = getConnection();
			PreparedStatement declaracionSQL = conexion.prepareStatement(sentencia);
			PreparedStatement declaracionSQL2 = conexion.prepareStatement(sentencia2);
			ResultSet rs = declaracionSQL.executeQuery();
			ResultSet rs2 = declaracionSQL2.executeQuery();

			modelo.setRowCount(0); // Borra cont tabla

			while (rs.next() && rs2.next()) {
				int ci = rs.getInt(1);
				String nombre = rs.getString(2);
				String apellido = rs.getString(3);
				String tipo = rs.getString(4);
				String grupo = rs.getString(5);
				int tel = rs2.getInt(1);

				Object[] fila = { ci, nombre, apellido, tipo, grupo, tel };
				modelo.addRow(fila);

			}

		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
		}

	}

	public void bajaPersonaBDD(String ci) {
		String sentecia1 = "DELETE FROM telefonos WHERE ci = " + ci;

		String sentencia = "DELETE FROM personas WHERE ci = " + ci;


		try {
			Connection conexion = getConnection();
			PreparedStatement declaracionSQL1 = conexion.prepareStatement(sentecia1);

			declaracionSQL1.executeUpdate();

			PreparedStatement declaracionSQL = conexion.prepareStatement(sentencia);
			declaracionSQL.executeUpdate();

		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
		}
	}

	public void modificarPersonaBDD(int ci, String nombre, String apellido, String tipo, String grupo, int telefono) {

		// String sentencia = "UPDATE libros SET titulo=?, autor=?, materia=?,
		// estadoFisico=?, canEjemplares=? WHERE isbn=?";
		String sentencia = "UPDATE personas SET ci='" + ci + "', nombre='" + nombre + "', apellido='" + apellido
				+ "', tipo='" + tipo + "', grupo='" + grupo + "' WHERE ci='" + ci + "'";

		String sentencia2 = "UPDATE telefonos SET telefono='" + telefono + "' WHERE ci='" + ci + "'";

		try {
			Connection conexion = getConnection();
			PreparedStatement declaracionSQL = conexion.prepareStatement(sentencia);
			PreparedStatement declaracionSQL2 = conexion.prepareStatement(sentencia2);

			/*
			 * declaracionSQL.setString(1, titulo); declaracionSQL.setString(2, autor);
			 * declaracionSQL.setString(3, materia); declaracionSQL.setString(4,
			 * estadoFisico); declaracionSQL.setInt(5, cantidad);
			 * declaracionSQL.setString(6, isbn);
			 */

			declaracionSQL.executeUpdate();
			declaracionSQL2.executeUpdate();

			JOptionPane.showMessageDialog(null, "Persona modificada correctamente");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error al modificar Persona: " + e.getMessage());
		}
	}

	/*
	 * public void altaTelefonoBDD(int ci, int telefono) { String sentencia =
	 * "INSERT INTO telefonos VALUES ('" + ci + "', '" + telefono + "')";
	 *
	 * try { Connection conexion = getConnection(); PreparedStatement declaracionSQL
	 * = conexion.prepareStatement(sentencia); declaracionSQL.executeUpdate();
	 *
	 * } catch (SQLException ex) { JOptionPane.showMessageDialog(null, "Error: " +
	 * ex.getMessage()); } }
	 */

	/*
	 * public void listarTelBDD(DefaultTableModel modelo) { String sentencia =
	 * "SELECT ci, telefono FROM telefonos";
	 *
	 * try { Connection conexion = getConnection(); PreparedStatement declaracionSQL
	 * = conexion.prepareStatement(sentencia); ResultSet rs =
	 * declaracionSQL.executeQuery();
	 *
	 * modelo.setRowCount(0);
	 *
	 * while (rs.next()) { int ci = rs.getInt(1); String tel = rs.getString(2);
	 *
	 * Object[] fila = { ci, tel }; modelo.addRow(fila); }
	 *
	 * } catch (SQLException ex) { JOptionPane.showMessageDialog(null, "Error: " +
	 * ex.getMessage()); } }
	 */

	// Prestamos de Libros
	public void altaPrestamoBDD(int ci, String isbn, String fechaD) {

		String sentencia = "INSERT INTO solicitan (isbn, ci, fechaP, fechaD, estado) VALUES ('" + isbn + "','" + ci
				+ "', CURRENT_DATE, '" + fechaD + "', 'Prestado')";

		try {
			Connection conexion = getConnection();
			PreparedStatement declaracionSQL = conexion.prepareStatement(sentencia);
			declaracionSQL.executeUpdate();

		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
		}
	}

	public void listarPrestamo(DefaultTableModel modelo) {
		String sentencia = "SELECT id, ci, isbn, fechaP, fechaD, estado FROM solicitan";
		String sentencia2 = "SELECT nombre, apellido, tipo, grupo FROM personas "
				+ "INNER JOIN solicitan ON solicitan.ci=personas.ci";
		String sentencia3 = "SELECT titulo FROM libros " + "INNER JOIN solicitan ON solicitan.isbn=libros.isbn";
		String sentencia4 = "SELECT telefono FROM telefonos " + "INNER JOIN solicitan ON solicitan.ci=telefonos.ci";

		try {
			Connection conexion = getConnection();
			PreparedStatement declaracionSQL = conexion.prepareStatement(sentencia);
			PreparedStatement declaracionSQL2 = conexion.prepareStatement(sentencia2);
			PreparedStatement declaracionSQL3 = conexion.prepareStatement(sentencia3);
			PreparedStatement declaracionSQL4 = conexion.prepareStatement(sentencia4);
			ResultSet rs = declaracionSQL.executeQuery();
			ResultSet rs2 = declaracionSQL2.executeQuery();
			ResultSet rs3 = declaracionSQL3.executeQuery();
			ResultSet rs4 = declaracionSQL4.executeQuery();

			modelo.setRowCount(0); // Borra cont tabla

			while (rs.next() && rs2.next() && rs3.next() && rs4.next()) {
				int id = rs.getInt(1);
				int ci = rs.getInt(2);
				String isbn = rs.getString(3);
				String fechaP = rs.getString(4);
				String fechaD = rs.getString(5);
				String nombre = rs2.getString(1);
				String apellido = rs2.getString(2);
				String tipo = rs2.getString(3);
				String grupo = rs2.getString(4);
				String telefono = rs4.getString(1);
				String libro = rs3.getString(1);
				String estado = rs.getString(6);
				Object[] fila = { id, ci, nombre, apellido, tipo, grupo, telefono, libro, fechaP, fechaD, estado };
				modelo.addRow(fila);
			}

		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
		}
	}

	public void actualizarEstadoPrestamo(int id) {
		String sentencia = "UPDATE solicitan SET estado = 'Devuelto' WHERE id = ?";
		try (Connection conexion = getConnection();
				PreparedStatement declaracionSQL = conexion.prepareStatement(sentencia)) {
			declaracionSQL.setInt(1, id);
			declaracionSQL.executeUpdate();

		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, "Error al actualizar estado: " + ex.getMessage());
		}
	}

	public void actualizarPrestamosR() {
		String sentencia = "UPDATE solicitan SET estado = 'Retrasado' WHERE fechaD < CURDATE() AND estado != 'Devuelto'";
		try (Connection conexion = getConnection(); PreparedStatement ps = conexion.prepareStatement(sentencia)) {
			ps.executeUpdate();
		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, "Error al actualizar retrasados: " + ex.getMessage());
		}
	}

	public void listarPrestamosRetrasados(DefaultTableModel modelo) {

		String sentencia = "SELECT id, ci, isbn, fechaP, fechaD, estado FROM solicitan "
				+ "WHERE fechaD < CURDATE() AND estado != 'Devuelto'";
		String sentencia2 = "SELECT nombre, apellido, tipo, grupo FROM personas "
				+ "INNER JOIN solicitan ON solicitan.ci = personas.ci";
		String sentencia3 = "SELECT titulo FROM libros " + "INNER JOIN solicitan ON solicitan.isbn = libros.isbn";
		String sentencia4 = "SELECT telefono FROM telefonos " + "INNER JOIN solicitan ON solicitan.ci = telefonos.ci";

		try {
			Connection conexion = getConnection();
			PreparedStatement declaracionSQL = conexion.prepareStatement(sentencia);
			PreparedStatement declaracionSQL2 = conexion.prepareStatement(sentencia2);
			PreparedStatement declaracionSQL3 = conexion.prepareStatement(sentencia3);
			PreparedStatement declaracionSQL4 = conexion.prepareStatement(sentencia4);
			ResultSet rs = declaracionSQL.executeQuery();
			ResultSet rs2 = declaracionSQL2.executeQuery();
			ResultSet rs3 = declaracionSQL3.executeQuery();
			ResultSet rs4 = declaracionSQL4.executeQuery();

			modelo.setRowCount(0); // Borra el contenido de la tabla

			while (rs.next() && rs2.next() && rs3.next() && rs4.next()) {
				int id = rs.getInt(1);
				int ci = rs.getInt(2);
				String isbn = rs.getString(3);
				String fechaP = rs.getString(4);
				String fechaD = rs.getString(5);
				String nombre = rs2.getString(1);
				String apellido = rs2.getString(2);
				String tipo = rs2.getString(3);
				String grupo = rs2.getString(4);
				String telefono = rs4.getString(1);
				String libro = rs3.getString(1);
				String estado = rs.getString(6);
				Object[] fila = { id, ci, nombre, apellido, tipo, grupo, telefono, libro, fechaP, fechaD, estado };
				modelo.addRow(fila);
			}

		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
		}
	}

	public void bajaPrestamoBDD(int id) {

		String sentencia = "DELETE FROM solicitan WHERE id = " + id;

		try {
			Connection conexion = getConnection();
			PreparedStatement declaracionSQL = conexion.prepareStatement(sentencia);
			declaracionSQL.executeUpdate();

		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, "Error al eliminar préstamo: " + ex.getMessage());
		}
	}
	/*
	 * public void modificarPrestamoBDD(int ci, String isbn, String fechaD) {
	 *
	 * //String sentencia =
	 * "UPDATE libros SET titulo=?, autor=?, materia=?, estadoFisico=?, canEjemplares=? WHERE isbn=?"
	 * ; String sentencia = "UPDATE personas SET ci='" + ci + "', nombre='" + nombre
	 * + "', apellido='" + apellido + "', tipo='" + tipo + "', grupo='" + grupo +
	 * "' WHERE ci='" + ci + "'";
	 *
	 * String sentencia2 = "UPDATE telefonos SET telefono='" + telefono +
	 * "' WHERE ci='" + ci + "'";
	 *
	 * try { Connection conexion = getConnection(); PreparedStatement declaracionSQL
	 * = conexion.prepareStatement(sentencia); PreparedStatement declaracionSQL2 =
	 * conexion.prepareStatement(sentencia2);
	 *
	 * /*declaracionSQL.setString(1, titulo); declaracionSQL.setString(2, autor);
	 * declaracionSQL.setString(3, materia); declaracionSQL.setString(4,
	 * estadoFisico); declaracionSQL.setInt(5, cantidad);
	 * declaracionSQL.setString(6, isbn);
	 */

	/*
	 * declaracionSQL.executeUpdate(); declaracionSQL2.executeUpdate();
	 *
	 * JOptionPane.showMessageDialog(null, "Libro actualizado correctamente"); }
	 * catch (Exception e) { JOptionPane.showMessageDialog(null,
	 * "Error al actualizar libro: " + e.getMessage()); } }
	 */

	public String consultarNombre(String nombreLibro) {
		String sentencia = "SELECT isbn FROM libros WHERE titulo = '" + nombreLibro + "'";
		String isbn = "";

		try {
			Connection conexion = getConnection();
			PreparedStatement declaracionSQL = conexion.prepareStatement(sentencia);
			ResultSet rs = declaracionSQL.executeQuery();

			if (rs.next()) {
				isbn = rs.getString("isbn");
			}

		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
		}

		return isbn;
	}

	// Prestamos de computadoras
	public void altaPrestamoCompusBDD(int numero, int ci) {

		String sentencia = "INSERT INTO piden (numero, ci, fechaP, hora, estado) VALUES ('" + numero + "','" + ci
				+ "', CURRENT_DATE, CURRENT_TIME, 'Prestado')";

		try {
			Connection conexion = getConnection();
			PreparedStatement declaracionSQL = conexion.prepareStatement(sentencia);
			declaracionSQL.executeUpdate();

		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
		}
	}

	public void listarPrestamoCompusBDD(DefaultTableModel modelo) {
		String sentencia = "SELECT id, ci, numero, fechaP, hora, estado FROM piden";

		String sentencia2 = "SELECT nombre, apellido, tipo, grupo FROM personas "
				+ "INNER JOIN piden ON piden.ci = personas.ci";

		String sentencia3 = "SELECT telefono FROM telefonos " + "INNER JOIN piden ON piden.ci = telefonos.ci";

		String sentencia4 = "SELECT computadoras.numero FROM computadoras "
				+ "INNER JOIN piden ON piden.numero = computadoras.numero";

		try {
			Connection conexion = getConnection();
			PreparedStatement ps1 = conexion.prepareStatement(sentencia);
			PreparedStatement ps2 = conexion.prepareStatement(sentencia2);
			PreparedStatement ps3 = conexion.prepareStatement(sentencia3);
			PreparedStatement ps4 = conexion.prepareStatement(sentencia4);

			ResultSet rs = ps1.executeQuery();
			ResultSet rs2 = ps2.executeQuery();
			ResultSet rs3 = ps3.executeQuery();
			ResultSet rs4 = ps4.executeQuery();

			modelo.setRowCount(0);

			while (rs.next() && rs2.next() && rs3.next() && rs4.next()) {
				int id = rs.getInt(1);
				int ci = rs.getInt(2);
				String nombre = rs2.getString(1);
				String apellido = rs2.getString(2);
				String tipo = rs2.getString(3);
				String grupo = rs2.getString(4);
				String telefono = rs3.getString(1);
				String computadora = rs4.getString(1);
				String fechaP = rs.getString(4);
				String hora = rs.getString(5);
				String estado = rs.getString(6);

				Object[] fila = { id, ci, nombre, apellido, telefono, tipo, grupo, computadora, fechaP, hora, estado };
				modelo.addRow(fila);

			}

		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
		}
	}

	public void actualizarEstadoPrestamoC(int id) {
		String sentencia = "UPDATE piden SET estado = 'Devuelto' WHERE id = ?";
		try (Connection conexion = getConnection();
				PreparedStatement declaracionSQL = conexion.prepareStatement(sentencia)) {
			declaracionSQL.setInt(1, id);
			declaracionSQL.executeUpdate();

		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, "Error al actualizar estado: " + ex.getMessage());
		}
	}

	public void actualizarPrestamosRCompu() {
	    String sentencia = "UPDATE piden SET estado = 'Retrasado' " + 
	                       "WHERE CONCAT(fechaP, ' ', hora) < NOW() AND estado = 'Prestado'";
	    try (Connection conexion = getConnection(); 
	         PreparedStatement ps = conexion.prepareStatement(sentencia)) {
	        ps.executeUpdate();
	    } catch (SQLException ex) {
	        JOptionPane.showMessageDialog(null, "Error al actualizar retrasados: " + ex.getMessage());
	    }
	}

	public void listarPrestamosRetrasadosC(DefaultTableModel modelo) {

		String sentencia = "SELECT id, numero, ci, fechaP, hora, estado FROM piden "
				+ "WHERE fechaP < CURDATE() AND estado != 'Devuelto'";
		String sentencia2 = "SELECT nombre, apellido, tipo, grupo FROM personas "
				+ "INNER JOIN solicitan ON solicitan.ci = personas.ci";
		String sentencia3 = "SELECT titulo FROM libros " + "INNER JOIN solicitan ON solicitan.isbn = libros.isbn";
		String sentencia4 = "SELECT telefono FROM telefonos " + "INNER JOIN solicitan ON solicitan.ci = telefonos.ci";

		try {
			Connection conexion = getConnection();
			PreparedStatement declaracionSQL = conexion.prepareStatement(sentencia);
			PreparedStatement declaracionSQL2 = conexion.prepareStatement(sentencia2);
			PreparedStatement declaracionSQL3 = conexion.prepareStatement(sentencia3);
			PreparedStatement declaracionSQL4 = conexion.prepareStatement(sentencia4);
			ResultSet rs = declaracionSQL.executeQuery();
			ResultSet rs2 = declaracionSQL2.executeQuery();
			ResultSet rs3 = declaracionSQL3.executeQuery();
			ResultSet rs4 = declaracionSQL4.executeQuery();

			modelo.setRowCount(0); // Borra el contenido de la tabla

			while (rs.next() && rs2.next() && rs3.next() && rs4.next()) {
				int id = rs.getInt(1);
				int num = rs.getInt(2);
				String isbn = rs.getString(3);
				String fechaP = rs.getString(4);
				String hora = rs.getString(5);
				String nombre = rs2.getString(1);
				String apellido = rs2.getString(2);
				String tipo = rs2.getString(3);
				String grupo = rs2.getString(4);
				String telefono = rs4.getString(1);
				String libro = rs3.getString(1);
				String estado = rs.getString(6);
				Object[] fila = { id, num, nombre, apellido, tipo, grupo, telefono, libro, fechaP, hora, estado };
				modelo.addRow(fila);
			}

		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
		}
	}

	public void bajaPrestamoCompusBDD(int id) {
		String sentencia = "DELETE FROM piden WHERE id = " + id;

		try (Connection conexion = getConnection();
				PreparedStatement declaracionSQL = conexion.prepareStatement(sentencia)) {
			declaracionSQL.executeUpdate();

		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, "Error al eliminar préstamo: " + ex.getMessage());
		}
	}

	// Computadoras
	public void altaCompusBDD(int numero, String estadoFisico) {
		String sentencia = "INSERT INTO computadoras VALUES (" + numero + ",'" + estadoFisico + "')";

		try {
			Connection conexion = getConnection();
			PreparedStatement declaracionSQL = conexion.prepareStatement(sentencia);
			declaracionSQL.executeUpdate();

		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
		}
	}

	public void listarCompusBDD(DefaultTableModel modelo) {

		String sentencia = "SELECT numero, estadoFisico FROM computadoras";

		try {
			Connection conexion = getConnection();
			PreparedStatement declaracionSQL = conexion.prepareStatement(sentencia);
			ResultSet rs = declaracionSQL.executeQuery();

			modelo.setRowCount(0); // Borra cont tabla

			while (rs.next()) {
				int numero = rs.getInt(1);
				String estadoFisico = rs.getString(2);

				Object[] fila = { numero, estadoFisico };
				modelo.addRow(fila);
			}

		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
		}
	}

	public void bajaCompusBDD(int num) {

		String sentencia = "DELETE FROM computadoras WHERE numero = '" + num + "'";

		try {
			Connection conexion = getConnection();
			PreparedStatement declaracionSQL = conexion.prepareStatement(sentencia);
			declaracionSQL.executeUpdate();

		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
		}
	}

	/*
	 * public void altaDineroBDD(int cobro) {
	 *
	 * String sentencia = "INSERT INTO cobros VALUES ('" + cobro +
	 * "', CURRENT_DATE)"; try { Connection conexion = getConnection();
	 * PreparedStatement declaracionSQL = conexion.prepareStatement(sentencia);
	 * declaracionSQL.executeUpdate();
	 *
	 * } catch (SQLException ex) { JOptionPane.showMessageDialog(null, "Error: " +
	 * ex.getMessage()); } }
	 *
	 * public void listarDinero(DefaultTableModel modelo) { String sentencia =
	 * "SELECT cobro, fecha, dinero, montoTotal, codigoAdmin FROM cobros";
	 *
	 * try { Connection conexion = getConnection(); PreparedStatement declaracionSQL
	 * = conexion.prepareStatement(sentencia); ResultSet rs =
	 * declaracionSQL.executeQuery();
	 *
	 * modelo.setRowCount(0);
	 *
	 * while (rs.next()) { int cobro = rs.getInt(1); int fecha = rs.getInt(2);
	 *
	 * Object[] fila = {fecha, cobro}; modelo.addRow(fila); }
	 *
	 * } catch (SQLException ex) { JOptionPane.showMessageDialog(null, "Error: " +
	 * ex.getMessage()); } }
	 */

	// Dinero
	public void altaDineroBDD(float cobro) {

		String sentencia = "INSERT INTO cobros (dinero, fecha) VALUES (" + cobro + ", CURRENT_DATE)";

		// String sentencia = "INSERT INTO cobros (dinero, fecha) VALUES (?,
		// CURRENT_DATE)";

		try (Connection conexion = getConnection();
				PreparedStatement declaracionSQL = conexion.prepareStatement(sentencia)) {

			// declaracionSQL.setInt(1, cobro);
			declaracionSQL.executeUpdate();

		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
		}
	}

	public void listarDinero(DefaultTableModel modelo) {
		String sentencia = "SELECT cobro, fecha, dinero FROM cobros ORDER BY fecha DESC, cobro ASC";

		try (Connection conexion = getConnection();
				PreparedStatement ps = conexion.prepareStatement(sentencia);
				ResultSet rs = ps.executeQuery()) {

			modelo.setRowCount(0);

			while (rs.next()) {

				// Columnas con nombres para evitar errores
				int id = rs.getInt("cobro");
				Date fecha = rs.getDate("fecha");
				double monto = rs.getDouble("dinero");

				Object[] fila = { id, fecha, monto };
				modelo.addRow(fila);
			}

		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
		}
	}

	public void listarMontoTotal(DefaultTableModel modelo) {

		// Comentarios de como funciona la sentencia
		// SUM(dinero) = función agregada
		// primero selecciona la columna fecha, calcula la suma de la columna dinero
		// para cada grupo de fechas y le pone de nombre monto de la tabla cobros
		// GROUP By agrupa los registros con la misma fecha
		// ORDER BY ordena lad fechas de la mas reciente a la mas antigua

		String sentencia = "SELECT fecha, SUM(dinero) AS montoTotal FROM cobros GROUP BY fecha ORDER BY fecha DESC";

		try {
			Connection conexion = getConnection();
			PreparedStatement declaracionSQL = conexion.prepareStatement(sentencia);
			ResultSet rs = declaracionSQL.executeQuery();

			modelo.setRowCount(0);

			while (rs.next()) {
				Date fecha = rs.getDate("fecha");
				double total = rs.getDouble("montoTotal");

				Object[] fila = { fecha, total };
				modelo.addRow(fila);
			}

		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
		}
	}

	public void bajaDinero(int id) {
		String sentencia = "DELETE FROM cobros WHERE cobro = " + id + " AND fecha = CURDATE()";

		try {
			Connection conexion = getConnection();
			PreparedStatement declaracionSQL = conexion.prepareStatement(sentencia);

			int filasAfectadas = declaracionSQL.executeUpdate();

			if (filasAfectadas == 0) {
				JOptionPane.showMessageDialog(null, "Solo se pueden eliminar cobros del día actual o el ID no existe",
						"Error", JOptionPane.ERROR_MESSAGE);

			}
		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, "Error al eliminar dinero: " + ex.getMessage());
		}
	}

}
