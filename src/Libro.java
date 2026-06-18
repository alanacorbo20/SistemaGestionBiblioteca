package ProyectoFinal;

public class Libro {

	String isbn;
	String autor;
	String titulo;
	String materia;
	String estadoFisico;
	int cantidadEjemplares;

	public Libro(String isbn, String autor, String titulo, String materia, String estadoFisico,
			int cantidadEjemplares) {
		super();
		this.isbn = isbn;
		this.autor = autor;
		this.titulo = titulo;
		this.materia = materia;
		this.estadoFisico = estadoFisico;
		this.cantidadEjemplares = cantidadEjemplares;
	}

	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	public String getAutor() {
		return autor;
	}
	public void setAutor(String autor) {
		this.autor = autor;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getMateria() {
		return materia;
	}
	public void setMateria(String materia) {
		this.materia = materia;
	}
	public String getEstadoFisico() {
		return estadoFisico;
	}
	public void setEstadoFisico(String estadoFisico) {
		this.estadoFisico = estadoFisico;
	}
	public int getCantidadEjemplares() {
		return cantidadEjemplares;
	}
	public void setCantidadEjemplares(int cantidadEjemplares) {
		this.cantidadEjemplares = cantidadEjemplares;
	}


}
