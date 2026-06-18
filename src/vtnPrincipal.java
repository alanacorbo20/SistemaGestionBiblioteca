package ProyectoFinal;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

public class vtnPrincipal extends JFrame {

	Gestor logica = new Gestor();

	Object[] columnasLibro = { "ISBN", "Título", "Autor", "Materia", "Estado Físico", "Cantidad de Ejemplares"};
	DefaultTableModel modeloLibro = new DefaultTableModel(null, columnasLibro);

	Object[] columnasPrestamo = {"ID", "CI", "Nombre", "Apellido", "Tipo", "Grupo", "Teléfono", "Libro", "Fecha Préstamo",
			"Fecha Devolución", "Estado" };
	DefaultTableModel modeloPrestamo = new DefaultTableModel(null, columnasPrestamo);

	Object[] columnasPersona = { "CI", "Nombre", "Apellido", "Tipo", "Grupo", "Teléfono" };
	DefaultTableModel modeloPersona = new DefaultTableModel(null, columnasPersona);

	Object[] columnasRegistroCompus = {"ID", "CI", "Nombre", "Apellido", "Teléfono", "Tipo", "Grupo", "Computadora",
			"Fecha Préstamo", "Hora", "Estado" };
	DefaultTableModel modeloRegistroCompus = new DefaultTableModel(null, columnasRegistroCompus);

	Object[] columnasCompu = { "Computadora", "Estado Físico" };
	DefaultTableModel modeloCompus = new DefaultTableModel(null, columnasCompu);

	Object[] columnasDinero = { "Fecha", "Monto Total" };
	DefaultTableModel modeloDinero = new DefaultTableModel(null, columnasDinero);

	Object[] columnasDinero2 = { "Id", "Fecha", "Monto Individual" };
	DefaultTableModel modeloDinero2 = new DefaultTableModel(null, columnasDinero2);

	private CardLayout cardLayout;
	private JPanel pDer;

	public vtnPrincipal() {
		this.setTitle("Biblioteca");
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		// this.setSize(900, 500);
		this.setLocationRelativeTo(null);
		this.setExtendedState(Frame.MAXIMIZED_BOTH);

		Color cPizq = Color.decode("#009282");
		Color cTitBarra = Color.decode("#8f643e");

		JPanel pIzq = new JPanel();
		pIzq.setLayout(new BorderLayout());
		pIzq.setBackground(cPizq);
		pIzq.setPreferredSize(new Dimension(240, 0));

		JPanel pTituloIzq = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 30));
		//pTituloIzq.setOpaque(false);
		pTituloIzq.setBackground(Colores.tit);
		ImageIcon im = new ImageIcon(getClass().getResource("/ProyectoFinal/logologo.png"));
		JLabel lbnLogo = new JLabel(new ImageIcon(im.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH)));

		JLabel titulo = new JLabel("Biblioteca");
		titulo.setForeground(Color.WHITE);
		titulo.setFont(new Font("SansSerif", Font.BOLD, 30));

		pTituloIzq.add(lbnLogo);
		pTituloIzq.add(titulo);
		pIzq.add(pTituloIzq, BorderLayout.NORTH);

		JPanel jpMenu = new JPanel();
		jpMenu.setOpaque(false);
		jpMenu.setLayout(new BoxLayout(jpMenu, BoxLayout.Y_AXIS));

		// Botones con iconos
		// En el constructor, cambiar TODOS los botones a RoundedButton
		JButton btnInicio = new RoundedButton("Inicio", imagen("/ProyectoFinal/inicio.png"));
		JButton btnLibros = new RoundedButton("Libros", imagen("/ProyectoFinal/libro.png"));
		JButton btnPersonas = new RoundedButton("Personas", imagen("/ProyectoFinal/persona.png"));
		JButton btnPrestamosLibros = new RoundedButton("Préstamo Libros", imagen("/ProyectoFinal/prestamo.png"));
		JButton btnPrestamosCompus = new RoundedButton("Préstamo Compus", imagen("/ProyectoFinal/computadora.png"));
		JButton btnCompus = new RoundedButton("Computadoras", imagen("/ProyectoFinal/computadora.png"));
		JButton btnDinero = new RoundedButton("Dinero", imagen("/ProyectoFinal/dinero.png"));

		jpMenu.add(Box.createVerticalStrut(20));
		jpMenu.add(btnInicio);
		Botones(btnInicio);
		jpMenu.add(Box.createVerticalStrut(10));

		jpMenu.add(btnLibros);
		Botones(btnLibros);
		jpMenu.add(Box.createVerticalStrut(10));

		jpMenu.add(btnPersonas);
		Botones(btnPersonas);
		jpMenu.add(Box.createVerticalStrut(10));

		jpMenu.add(btnCompus);
		Botones(btnCompus);
		jpMenu.add(Box.createVerticalStrut(10));

		jpMenu.add(btnPrestamosLibros);
		Botones(btnPrestamosLibros);
		jpMenu.add(Box.createVerticalStrut(10));

		jpMenu.add(btnPrestamosCompus);
		Botones(btnPrestamosCompus);
		jpMenu.add(Box.createVerticalStrut(10));


		jpMenu.add(btnDinero);
		Botones(btnDinero);

		pIzq.add(jpMenu, BorderLayout.CENTER);

		cardLayout = new CardLayout();
		JPanel pDer = new JPanel(cardLayout);

		JPanel panelInicio = panelInicio();
		JPanel panelLibros = panelLibros();
		JPanel panelPersonas = panelPersonas();
		JPanel panelCompus = panelCompus();
		JPanel panelPrestamosLibros = panelPrestamos();
		JPanel panelPrestamosCompus = panelRegistroCompus();
		JPanel panelDinero = panelDinero();

		// AGREGAR AL CARDLAYOUT
		pDer.add(panelInicio, "Inicio");
		pDer.add(panelLibros, "Libros");
		pDer.add(panelPersonas, "Personas");
		pDer.add(panelCompus, "Computadoras");
		pDer.add(panelPrestamosLibros, "Prestamos Libros");
		pDer.add(panelPrestamosCompus, "Prestamos Compu");
		pDer.add(panelDinero, "Dinero");
		this.add(pIzq, BorderLayout.WEST);
		this.add(pDer, BorderLayout.CENTER);

		// --------------------------------------------------------------------------------------------------------------

		// EVENTOS

		btnInicio.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				cardLayout.show(pDer, "Inicio");
				logica.listarPrestamosRetrasados(modeloPrestamo);
				logica.listarPrestamosRetrasadosC(modeloRegistroCompus);


			}
		});

		btnLibros.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				cardLayout.show(pDer, "Libros");

				logica.ListarLibro(modeloLibro);

				// modelo.addColumn(columnas);
			}
		});

		btnPersonas.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				cardLayout.show(pDer, "Personas");
				logica.ListarPersona(modeloPersona);

			}
		});

		btnCompus.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				cardLayout.show(pDer, "Computadoras");
				logica.listarComputadoras(modeloCompus);

			}
		});

		btnPrestamosLibros.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				cardLayout.show(pDer, "Prestamos Libros");
				logica.actualizarEstadoRetrasado();
				logica.ListarPrestamo(modeloPrestamo);



			}
		});

		btnPrestamosCompus.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				cardLayout.show(pDer, "Prestamos Compu");
				logica.actualizarEstadoRetrasadoCompus();
				logica.ListarPrestamoCompus(modeloRegistroCompus);
			}
		});


		btnDinero.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				cardLayout.show(pDer, "Dinero");
				logica.listarMontoTotal(modeloDinero);
				logica.listarDinero(modeloDinero2);
			}
		});

	}

	// ... (el resto del código permanece igual)

	private void Botones(JButton btn) {
	    btn.setFont(new Font("SansSerif", Font.BOLD, 16));
	    btn.setForeground(Color.WHITE);
	    btn.setBackground(Colores.verdeOcuro);
	    btn.setBorderPainted(false); // Sin borde visible
	    btn.setBorder(BorderFactory.createEmptyBorder(15, 17, 15, 12));
	    btn.setMaximumSize(new Dimension(225, 45));
	    btn.setFocusable(false);
	    btn.setHorizontalAlignment(SwingConstants.LEFT);
	}
	public void actualizarR() {
		logica.listarPrestamosRetrasados(modeloPrestamo);
		logica.listarPrestamosRetrasadosC(modeloRegistroCompus);
		
	}

	private static class RoundedButton extends JButton {
	    private int radius = 15;

	    public RoundedButton(String text, Icon icon) {
	        super(text, icon);
	        setOpaque(false);
	        setBorderPainted(false);
	        setFocusPainted(false);
	    }

	    @Override
	    protected void paintComponent(Graphics g) {
	        Graphics2D g2 = (Graphics2D) g.create();
	        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

	        Color bgColor = getBackground();
	        if (getModel().isPressed()) {
	            bgColor = bgColor.darker();
	        } else if (getModel().isRollover()) {
	            bgColor = bgColor.brighter();
	        }

	        g2.setColor(bgColor);
	        g2.fillRoundRect(0, 0, getWidth(), getHeight(), radius, radius);
	        super.paintComponent(g2);
	        g2.dispose();
	    }
	}


	// Funcion imagenes
	private ImageIcon imagen(String ruta) {
		java.net.URL url = getClass().getResource(ruta);
		if (url == null) {
			return null;
		}
		ImageIcon icon = new ImageIcon(url);
		Image img = icon.getImage().getScaledInstance(35, 35, Image.SCALE_SMOOTH);
		return new ImageIcon(img);
	}

	private JPanel panelInicio() {
	    JPanel p1 = new JPanel(new BorderLayout());

	    // p superior
	    JPanel pSup = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 35));
	    pSup.setBackground(Color.WHITE);

		ImageIcon inicio = new ImageIcon(new ImageIcon(getClass().getResource("/ProyectoFinal/incioinicio.png"))
		        .getImage().getScaledInstance(45, 45, Image.SCALE_SMOOTH));
		JLabel lblInicio = new JLabel(inicio);
	    JLabel lblTit = new JLabel("Inicio");
	    lblTit.setFont(new Font("SansSerif", Font.BOLD, 30));
	    lblTit.setForeground(Colores.marron);
	    pSup.add(lblInicio);
	    pSup.add(lblTit);
	    p1.add(pSup, BorderLayout.NORTH);

	    JTable tabla1 = new JTable(modeloPrestamo);
	    tabla1.setFillsViewportHeight(true);
	    tabla1.getTableHeader().setReorderingAllowed(false);
	    JScrollPane scr1 = new JScrollPane(tabla1);
	    scr1.setPreferredSize(new Dimension(1200, 250));
		tabla1.setDefaultEditor(Object.class, null);


	    // tabla 2
	    JTable tabla2 = new JTable(modeloRegistroCompus);
	    tabla2.setFillsViewportHeight(true);
	    tabla2.getTableHeader().setReorderingAllowed(false);
	    JScrollPane scr2 = new JScrollPane(tabla2);
	    scr2.setPreferredSize(new Dimension(1200, 250));
		tabla2.setDefaultEditor(Object.class, null);

	    // panel central
	    JPanel pCentro = new JPanel(new GridBagLayout());
	    GridBagConstraints gbc = new GridBagConstraints();
	    gbc.gridx = 0;
	    gbc.gridy = 0;
	    gbc.anchor = GridBagConstraints.WEST;
	    gbc.insets = new Insets(0, 0, 10, 0);

	    // Titulo tabla 1
	    JLabel lblTabla1 = new JLabel("Préstamos Retrasados de Libros");
	    lblTabla1.setFont(new Font("SansSerif", Font.BOLD, 19));
	    lblTabla1.setForeground(Colores.marron);
	    pCentro.add(lblTabla1, gbc);

	    gbc.gridy = 1;
	    gbc.anchor = GridBagConstraints.CENTER;
	    pCentro.add(scr1, gbc);

	    // Titulo tabla 2
	    gbc.gridy = 2;
	    gbc.anchor = GridBagConstraints.WEST;
	    JLabel lblTabla2 = new JLabel("Préstamos Retrasados de Computadoras");
	    lblTabla2.setFont(new Font("SansSerif", Font.BOLD, 19));
	    lblTabla2.setForeground(Colores.marron);
	    pCentro.add(lblTabla2, gbc);


	    tabla1.getColumnModel().getColumn(10).setCellRenderer(new javax.swing.table.DefaultTableCellRenderer() {
			@Override
			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
					boolean hasFocus, int row, int column) {

				Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

				if (value != null) {
					String estado = value.toString();
					if(estado.equals("Retrasado")) {
						c.setBackground(Colores.retrasado);
					}
					else {
						c.setBackground(Color.WHITE);
					}
				}

				return c;
			}
		});

	    tabla2.getColumnModel().getColumn(10).setCellRenderer(new javax.swing.table.DefaultTableCellRenderer() {
			@Override
			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
					boolean hasFocus, int row, int column) {

				Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

				if (value != null) {
					String estado = value.toString();
					if(estado.equals("Retrasado")) {
						c.setBackground(Colores.retrasado);
					}
					else {
						c.setBackground(Color.WHITE);
					}
				}

				return c;
			}
		});
	    gbc.gridy = 3;
	    gbc.anchor = GridBagConstraints.CENTER;
	    pCentro.add(scr2, gbc);

	    p1.add(pCentro, BorderLayout.CENTER);

	    return p1;
	}

	private JPanel panelLibros() {
		JPanel p1 = new JPanel(new BorderLayout());

		// Barra Superior
		JPanel pSup = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 35));
		pSup.setBackground(Color.WHITE);
		ImageIcon inicio = new ImageIcon(new ImageIcon(getClass().getResource("/ProyectoFinal/librolibro.png"))
		        .getImage().getScaledInstance(45, 45, Image.SCALE_SMOOTH));
		JLabel lblInicio = new JLabel(inicio);
		JLabel lblTit = new JLabel("Libros");
		lblTit.setFont(new Font("SansSerif", Font.BOLD, 26));
		lblTit.setForeground(Colores.marron);
		pSup.add(lblInicio);
		pSup.add(lblTit);

		// Panel Botones
		JPanel pBotones = new JPanel(new FlowLayout(FlowLayout.RIGHT, 20, 35));

		// Panel barra
				JPanel barra = new JPanel(new BorderLayout());
				barra.setBackground(Color.WHITE);
				barra.setPreferredSize(new Dimension(1028, 50));
				barra.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5));
				barra.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));

				// JTextField barra
				JTextField txtBusqueda = new JTextField();
				txtBusqueda.setPreferredSize(new Dimension(1028, 50));
				txtBusqueda.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
				txtBusqueda.setFont(new Font("Arial", Font.PLAIN, 16));
				txtBusqueda.setBackground(Color.WHITE);

				ImageIcon lupa = new ImageIcon(new ImageIcon(getClass().getResource("/ProyectoFinal/33.png"))
				        .getImage().getScaledInstance(23, 23, Image.SCALE_SMOOTH));
				JLabel lblLupa = new JLabel(lupa);
				lblLupa.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 15));


				barra.add(txtBusqueda, BorderLayout.CENTER);
				barra.add(lblLupa, BorderLayout.EAST);


		// Botones
		JButton btnAlta = new JButton("+");
		btnAlta.setToolTipText("Agregar");
		btnAlta.setFont(new Font("Arial", Font.BOLD, 28));
		btnAlta.setForeground(Colores.marron);

		JButton btnModificar = new JButton();
		btnModificar.setToolTipText("Modificar");
		JButton btnBaja = new JButton();
		btnBaja.setToolTipText("Eliminar");

		// Botones con imagenes
		ImageIcon iconModificar = new ImageIcon(new ImageIcon(getClass().getResource("/ProyectoFinal/modificar.png"))
				.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH));
		btnModificar.setIcon(iconModificar);

		ImageIcon iconEliminar = new ImageIcon(new ImageIcon(getClass().getResource("/ProyectoFinal/eliminar.png"))
				.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH));
		btnBaja.setIcon(iconEliminar);

		// Estilo de botones
		btnAlta.setBackground(Color.WHITE);
		btnAlta.setFocusable(false);
		btnModificar.setBackground(Color.WHITE);
		btnModificar.setFocusable(false);
		btnBaja.setBackground(Color.WHITE);
		btnBaja.setFocusable(false);

		botonesPaneles(btnAlta);
		botonesPaneles(btnModificar);
		botonesPaneles(btnBaja);

		pBotones.add(barra);
		pBotones.add(btnAlta);
		pBotones.add(btnModificar);
		pBotones.add(btnBaja);

		// Panel barra + botones
		JPanel pSuperior = new JPanel(new BorderLayout());
		pSuperior.add(pSup, BorderLayout.NORTH);
		pSuperior.add(pBotones, BorderLayout.SOUTH);

		// Tabla
		// modelo = new DefaultTableModel();

		JTable tabla = new JTable(modeloLibro);
		tabla.setDefaultEditor(Object.class, null);

		JScrollPane scr = new JScrollPane(tabla);
		tabla.setFillsViewportHeight(true);
		tabla.getTableHeader().setReorderingAllowed(false);

		JPanel pCentro = new JPanel(new GridBagLayout());
		scr.setPreferredSize(new Dimension(1200, 550));
		pCentro.add(scr);

		p1.add(pSuperior, BorderLayout.NORTH);
		p1.add(pCentro, BorderLayout.CENTER);

		// ------------------------------------------------------
		// EVENTOS

		btnAlta.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				vtnAltaLibro ventanaRegistrar = new vtnAltaLibro(vtnPrincipal.this);
				ventanaRegistrar.setVisible(true);

			}
		});

		btnBaja.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				int fila = tabla.getSelectedRow();

				if (fila != -1) {

					String isbn = tabla.getValueAt(fila, 0).toString();
		            int op = JOptionPane.showConfirmDialog(
		                    null,
		                    "¿Está seguro que desea eliminar este libro?","",JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE
		            );

		            if (op == JOptionPane.YES_OPTION) {
		                logica.bajaLibro(isbn);
		                logica.ListarLibro(modeloLibro);
		            }
		        } else {
		            JOptionPane.showMessageDialog(null, "Seleccione un libro para eliminar", "Atención", JOptionPane.INFORMATION_MESSAGE);
		        }
		    }
		});
		btnModificar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				int fila = tabla.getSelectedRow();

				if (fila != -1) {
					String isbn = tabla.getValueAt(fila, 0).toString();
					String titulo = tabla.getValueAt(fila, 1).toString();
					String autor = tabla.getValueAt(fila, 2).toString();
					String materia = tabla.getValueAt(fila, 3).toString();
					String estado = tabla.getValueAt(fila, 4).toString();
					int cantidad = Integer.parseInt(tabla.getValueAt(fila, 5).toString());

					EditarLibro vtnEditar = new EditarLibro(isbn, titulo, autor, materia, estado, cantidad,
							vtnPrincipal.this);
					vtnEditar.setVisible(true);
				} else {
					JOptionPane.showMessageDialog(null, "Seleccione un libro para modificar");
				}

			}
		});

		return p1;
	}

	public void actualizar() {
		logica.ListarLibro(modeloLibro);
	}

	private JPanel panelPrestamos() {
		JPanel p1 = new JPanel(new BorderLayout());

		// Barra Superior
		JPanel pSup = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 35));
		pSup.setBackground(Color.WHITE);
		ImageIcon pres = new ImageIcon(new ImageIcon(getClass().getResource("/ProyectoFinal/prespres.png"))
		        .getImage().getScaledInstance(45, 45, Image.SCALE_SMOOTH));
		JLabel lblpres = new JLabel(pres);
		JLabel lblTit = new JLabel("Préstamo Libros");
		lblTit.setFont(new Font("SansSerif", Font.BOLD, 26));
		lblTit.setForeground(Colores.marron);
		pSup.add(lblpres);
		pSup.add(lblTit);

		// Panel Botones
		JPanel pBotones = new JPanel(new FlowLayout(FlowLayout.RIGHT, 20, 35));

		// Panel barra
		JPanel barra = new JPanel(new BorderLayout());
		barra.setBackground(Color.WHITE);
		barra.setPreferredSize(new Dimension(1028, 50));
		barra.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5));
		barra.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));

		// JTextField barra
		JTextField txtBusqueda = new JTextField();
		txtBusqueda.setPreferredSize(new Dimension(1028, 50));
		txtBusqueda.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
		txtBusqueda.setFont(new Font("Arial", Font.PLAIN, 16));
		txtBusqueda.setBackground(Color.WHITE);

		ImageIcon lupa = new ImageIcon(new ImageIcon(getClass().getResource("/ProyectoFinal/33.png"))
		        .getImage().getScaledInstance(23, 23, Image.SCALE_SMOOTH));
		JLabel lblLupa = new JLabel(lupa);
		lblLupa.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 15));


		barra.add(txtBusqueda, BorderLayout.CENTER);
		barra.add(lblLupa, BorderLayout.EAST);

		// Botones
		JButton btnAlta = new JButton("+");
		btnAlta.setToolTipText("Agregar");
		btnAlta.setFont(new Font("Arial", Font.BOLD, 28));
		btnAlta.setForeground(Colores.marron);

		JButton btnModificar = new JButton();
		btnModificar.setToolTipText("Devolver");
		JButton btnBaja = new JButton();
		btnBaja.setToolTipText("Eliminar");

		// Botones con imagenes
		ImageIcon iconModificar = new ImageIcon(new ImageIcon(getClass().getResource("/ProyectoFinal/devolver.png"))
		        .getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH));
		btnModificar.setIcon(iconModificar);

		ImageIcon iconEliminar = new ImageIcon(new ImageIcon(getClass().getResource("/ProyectoFinal/eliminar.png"))
		        .getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH));
		btnBaja.setIcon(iconEliminar);

		// Estilo de botones
		btnAlta.setBackground(Color.WHITE);
		btnAlta.setFocusable(false);
		btnModificar.setBackground(Color.WHITE);
		btnModificar.setFocusable(false);
		btnBaja.setBackground(Color.WHITE);
		btnBaja.setFocusable(false);

		botonesPaneles(btnAlta);
		botonesPaneles(btnModificar);
		botonesPaneles(btnBaja);

		pBotones.add(barra);
		pBotones.add(btnAlta);
		pBotones.add(btnModificar);
		pBotones.add(btnBaja);

		// Panel barra + botones
		JPanel pSuperior = new JPanel(new BorderLayout());
		pSuperior.add(pSup, BorderLayout.NORTH);
		pSuperior.add(pBotones, BorderLayout.SOUTH);

		// Tabla
		JTable tabla = new JTable(modeloPrestamo);

		JScrollPane scr = new JScrollPane(tabla);
		tabla.setShowGrid(true);
		tabla.setFillsViewportHeight(true);
		tabla.getTableHeader().setReorderingAllowed(false);
		tabla.getColumnModel().getColumn(0).setMinWidth(0);
		tabla.getColumnModel().getColumn(0).setMaxWidth(0);
		tabla.setDefaultEditor(Object.class, null);

		JPanel pCentro = new JPanel(new GridBagLayout());
		scr.setPreferredSize(new Dimension(1200, 550));
		pCentro.add(scr);

		p1.add(pSuperior, BorderLayout.NORTH);
		p1.add(pCentro, BorderLayout.CENTER);
		// Poner colores al estado
		tabla.getColumnModel().getColumn(10).setCellRenderer(new javax.swing.table.DefaultTableCellRenderer() {
			@Override
			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
					boolean hasFocus, int row, int column) {

				Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

				if (value != null) {
					String estado = value.toString();
					if (estado.equals("Prestado")) {
						c.setBackground(Colores.prestado);
					} else if (estado.equals("Devuelto")) {
						c.setBackground(Colores.devuelto);
					} else if(estado.equals("Retrasado")) {
						c.setBackground(Colores.retrasado);
					}
					else {
						c.setBackground(Color.WHITE);
					}
				}

				return c;
			}
		});

		// ------------------------------------------------------
		// EVENTOS

		btnAlta.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				vtnAltaPrestamo alta = new vtnAltaPrestamo(vtnPrincipal.this);
				alta.setVisible(true);

			}
		});

		btnBaja.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int fila = tabla.getSelectedRow();


				if (fila != -1) {

					int id = Integer.parseInt(tabla.getValueAt(fila, 0).toString().trim());
					   int op = JOptionPane.showConfirmDialog(
			                    null,
			                    "¿Está seguro que desea eliminar este préstamo?","",JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE
			            );

			            if (op == JOptionPane.YES_OPTION) {
							logica.bajaPrestamo(id);
							logica.ListarPrestamo(modeloPrestamo);
			            }
			        } else {
			            JOptionPane.showMessageDialog(null, "Seleccione un préstamo para eliminar", "Atención", JOptionPane.INFORMATION_MESSAGE);
			        }
			    }
			});


		btnModificar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int filaSeleccionada = tabla.getSelectedRow();

				if (filaSeleccionada != -1) {
					int id = Integer.parseInt(tabla.getValueAt(filaSeleccionada, 0).toString());

					logica.actualizarEstadoPrestamo(id);
					logica.ListarPrestamo(modeloPrestamo);

					JOptionPane.showMessageDialog(null, "Estado actualizado a 'devuelto'");
				} else {
					JOptionPane.showMessageDialog(null, "Selecciona un préstamo para devolver");
				}
			}
		});

		return p1;
	}

	public void actualizar3() {
		logica.ListarPrestamo(modeloPrestamo);
	}

	private JPanel panelPersonas() {
		JPanel p1 = new JPanel(new BorderLayout());

		// Barra Superior
		JPanel pSup = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 35));
		pSup.setBackground(Color.WHITE);
		ImageIcon per = new ImageIcon(new ImageIcon(getClass().getResource("/ProyectoFinal/personas2.png"))
		        .getImage().getScaledInstance(45, 45, Image.SCALE_SMOOTH));
		JLabel lblPer = new JLabel(per);
		JLabel lblTit = new JLabel("Personas");
		lblTit.setFont(new Font("SansSerif", Font.BOLD, 26));
		lblTit.setForeground(Colores.marron);
		pSup.add(lblPer);
		pSup.add(lblTit);

		// Panel Botones
		JPanel pBotones = new JPanel(new FlowLayout(FlowLayout.RIGHT, 20, 35));

		// Panel barra
				JPanel barra = new JPanel(new BorderLayout());
				barra.setBackground(Color.WHITE);
				barra.setPreferredSize(new Dimension(1028, 50));
				barra.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5));
				barra.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));

				// JTextField barra
				JTextField txtBusqueda = new JTextField();
				txtBusqueda.setPreferredSize(new Dimension(1028, 50));
				txtBusqueda.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
				txtBusqueda.setFont(new Font("Arial", Font.PLAIN, 16));
				txtBusqueda.setBackground(Color.WHITE);

				ImageIcon lupa = new ImageIcon(new ImageIcon(getClass().getResource("/ProyectoFinal/33.png"))
				        .getImage().getScaledInstance(23, 23, Image.SCALE_SMOOTH));
				JLabel lblLupa = new JLabel(lupa);
				lblLupa.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 15));


				barra.add(txtBusqueda, BorderLayout.CENTER);
				barra.add(lblLupa, BorderLayout.EAST);


		// Botones
		JButton btnAlta = new JButton("+");
		btnAlta.setToolTipText("Agregar");

		btnAlta.setFont(new Font("Arial", Font.BOLD, 28));
		btnAlta.setForeground(Colores.marron);

		JButton btnModificar = new JButton();
		btnModificar.setToolTipText("Modificar");
		JButton btnBaja = new JButton();
		btnBaja.setToolTipText("Eliminar");

		// Botones con imagenes
		ImageIcon iconModificar = new ImageIcon(new ImageIcon(getClass().getResource("/ProyectoFinal/modificar.png"))
				.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH));
		btnModificar.setIcon(iconModificar);

		ImageIcon iconEliminar = new ImageIcon(new ImageIcon(getClass().getResource("/ProyectoFinal/eliminar.png"))
				.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH));
		btnBaja.setIcon(iconEliminar);

		// Estilo de botones
		btnAlta.setBackground(Color.WHITE);
		btnAlta.setFocusable(false);
		btnModificar.setBackground(Color.WHITE);
		btnModificar.setFocusable(false);
		btnBaja.setBackground(Color.WHITE);
		btnBaja.setFocusable(false);

		botonesPaneles(btnAlta);
		botonesPaneles(btnModificar);
		botonesPaneles(btnBaja);

		pBotones.add(barra);
		pBotones.add(btnAlta);
		pBotones.add(btnModificar);
		pBotones.add(btnBaja);

		// Panel barra + botones
		JPanel pSuperior = new JPanel(new BorderLayout());
		pSuperior.add(pSup, BorderLayout.NORTH);
		pSuperior.add(pBotones, BorderLayout.SOUTH);

		// Tabla

		JTable tabla = new JTable(modeloPersona);

		JScrollPane scr = new JScrollPane(tabla);
		tabla.setShowGrid(true);
		tabla.setFillsViewportHeight(true);
		tabla.getTableHeader().setReorderingAllowed(false);
		tabla.setDefaultEditor(Object.class, null);

		JPanel pCentro = new JPanel(new GridBagLayout()); // centra lo que tenga adentro
		scr.setPreferredSize(new Dimension(1200, 550)); // tamaño fijo del rectángulo
		pCentro.add(scr);

		p1.add(pSuperior, BorderLayout.NORTH);
		p1.add(pCentro, BorderLayout.CENTER);
		// ------------------------------------------------------
		// EVENTOS

		btnAlta.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				vtnAltaPersona alta = new vtnAltaPersona(vtnPrincipal.this);
				alta.setVisible(true);

			}
		});

		btnBaja.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				int fila = tabla.getSelectedRow();

				if (fila != -1) {

					String ci = tabla.getValueAt(fila, 0).toString();
					   int op = JOptionPane.showConfirmDialog(
			                    null,
			                    "¿Está seguro que desea eliminar esta persona","",JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE
			            );

			            if (op == JOptionPane.YES_OPTION) {
							logica.bajaPersona(ci);
							logica.ListarPersona(modeloPersona);
			            }
			        } else {
			            JOptionPane.showMessageDialog(null, "Seleccione una persona para eliminar", "Atención", JOptionPane.INFORMATION_MESSAGE);
			        }
			    }
			});


		btnModificar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int fila = tabla.getSelectedRow();

				if (fila != -1) {
					int ci = Integer.parseInt(tabla.getValueAt(fila, 0).toString());
					String nombre = tabla.getValueAt(fila, 1).toString();
					String apellido = tabla.getValueAt(fila, 2).toString();
					String tipo = tabla.getValueAt(fila, 3).toString();
					String grupo = tabla.getValueAt(fila, 4).toString();
					int tel = Integer.parseInt(tabla.getValueAt(fila, 5).toString());


					vtnEditarPersona vtnEditar = new vtnEditarPersona(ci, nombre, apellido, tipo, grupo, tel, vtnPrincipal.this);
					vtnEditar.setVisible(true);
				} else {
					JOptionPane.showMessageDialog(null, "Seleccione una persona para modificar");
				}
			}
		});
		return p1;
	}

	public void actualizar2() {
		logica.ListarPersona(modeloPersona);
	}

	private JPanel panelRegistroCompus() {
		JPanel p1 = new JPanel(new BorderLayout());

		// Barra Superior
		JPanel pSup = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 35));
		pSup.setBackground(Color.WHITE);
		ImageIcon pre = new ImageIcon(new ImageIcon(getClass().getResource("/ProyectoFinal/presPres.png"))
		        .getImage().getScaledInstance(45, 45, Image.SCALE_SMOOTH));
		JLabel lblPre = new JLabel(pre);
		JLabel lblTit = new JLabel("Préstamo Computadoras");
		lblTit.setFont(new Font("SansSerif", Font.BOLD, 24));
		lblTit.setForeground(Colores.marron);
		pSup.add(lblPre);
		pSup.add(lblTit);

		// Panel Botones
		JPanel pBotones = new JPanel(new FlowLayout(FlowLayout.RIGHT, 20, 35));

		// Panel barra
				JPanel barra = new JPanel(new BorderLayout());
				barra.setBackground(Color.WHITE);
				barra.setPreferredSize(new Dimension(1028, 50));
				barra.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5));
				barra.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));

				// JTextField barra
				JTextField txtBusqueda = new JTextField();
				txtBusqueda.setPreferredSize(new Dimension(1028, 50));
				txtBusqueda.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
				txtBusqueda.setFont(new Font("Arial", Font.PLAIN, 16));
				txtBusqueda.setBackground(Color.WHITE);

				ImageIcon lupa = new ImageIcon(new ImageIcon(getClass().getResource("/ProyectoFinal/33.png"))
				        .getImage().getScaledInstance(23, 23, Image.SCALE_SMOOTH));
				JLabel lblLupa = new JLabel(lupa);
				lblLupa.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 15));


				barra.add(txtBusqueda, BorderLayout.CENTER);
				barra.add(lblLupa, BorderLayout.EAST);

		// Botones
		JButton btnAlta = new JButton("+");
		btnAlta.setToolTipText("Agregar Préstamo");
		btnAlta.setFont(new Font("Arial", Font.BOLD, 28));
		btnAlta.setForeground(Colores.marron);

		JButton btnModificar = new JButton();
		btnModificar.setToolTipText("Devolver");
		JButton btnBaja = new JButton();
		btnBaja.setToolTipText("Eliminar");

		// Botones con imagenes
		ImageIcon iconModificar = new ImageIcon(new ImageIcon(getClass().getResource("/ProyectoFinal/devolver.png"))
				.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH));
		btnModificar.setIcon(iconModificar);

		ImageIcon iconEliminar = new ImageIcon(new ImageIcon(getClass().getResource("/ProyectoFinal/eliminar.png"))
				.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH));
		btnBaja.setIcon(iconEliminar);

		// Estilo de botones
		btnAlta.setBackground(Color.WHITE);
		btnAlta.setFocusable(false);
		btnModificar.setBackground(Color.WHITE);
		btnModificar.setFocusable(false);
		btnBaja.setBackground(Color.WHITE);
		btnBaja.setFocusable(false);

		botonesPaneles(btnAlta);
		botonesPaneles(btnModificar);
		botonesPaneles(btnBaja);

		pBotones.add(barra);
		pBotones.add(btnAlta);
		pBotones.add(btnModificar);
		pBotones.add(btnBaja);

		// Panel barra + botones

		JPanel pSuperior = new JPanel(new BorderLayout());
		pSuperior.add(pSup, BorderLayout.NORTH);
		pSuperior.add(pBotones, BorderLayout.SOUTH);

		// TAbla
		// modelo = new DefaultTableModel();
		// modelo.setColumnIdentifiers(new String[] { "CI", "Nombre", "Apellido",
		// "Télefono", "Tipo", "Computadora", "Estado"});
		JTable tabla = new JTable(modeloRegistroCompus);

		JScrollPane scr = new JScrollPane(tabla);
		tabla.setShowGrid(true);
		tabla.setFillsViewportHeight(true);
		tabla.getTableHeader().setReorderingAllowed(false);
		tabla.getColumnModel().getColumn(0).setMinWidth(0);
		tabla.getColumnModel().getColumn(0).setMaxWidth(0);
		tabla.setDefaultEditor(Object.class, null);

		JPanel pCentro = new JPanel(new GridBagLayout());
		scr.setPreferredSize(new Dimension(1200, 550));
		pCentro.add(scr);

		p1.add(pSuperior, BorderLayout.NORTH);
		p1.add(pCentro, BorderLayout.CENTER);

		// Poner colores al estado
		tabla.getColumnModel().getColumn(10).setCellRenderer(new javax.swing.table.DefaultTableCellRenderer() {
			@Override
			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
					boolean hasFocus, int row, int column) {

				Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

				if (value != null) {
					String estado = value.toString();
					if (estado.equals("Prestado")) {
						c.setBackground(Colores.prestado);
					} else if (estado.equals("Devuelto")) {
						c.setBackground(Colores.devuelto);
					}  else if(estado.equals("Retrasado")) {
						c.setBackground(Colores.retrasado);
					}else {
						c.setBackground(Color.WHITE);
					}
				}

				return c;
			}
		});

		// EVENTOS

		btnAlta.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				vtnAltaPrestamoComputadora alta = new vtnAltaPrestamoComputadora(vtnPrincipal.this);
				alta.setVisible(true);

			}
		});

		btnModificar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int filaSeleccionada = tabla.getSelectedRow();

				if (filaSeleccionada != -1) {
					int id = Integer.parseInt(tabla.getValueAt(filaSeleccionada, 0).toString());

					logica.actualizarEstadoPrestamoC(id);
					logica.ListarPrestamoCompus(modeloRegistroCompus);

					JOptionPane.showMessageDialog(null, "Estado actualizado a 'Devuelto'");
				} else {
					JOptionPane.showMessageDialog(null, "Selecciona un préstamo para devolver");
				}
			}
		});
		btnBaja.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				 int fila = tabla.getSelectedRow();

			        if (fila != -1) {
			            try {
			                int id = Integer.parseInt(tabla.getValueAt(fila, 0).toString().trim());

			                int opcion = JOptionPane.showConfirmDialog(
			                        null,
			                        "¿Está seguro que desea eliminar este préstamo?",
			                        "Confirmar eliminación",
			                        JOptionPane.YES_NO_OPTION,
			                        JOptionPane.WARNING_MESSAGE
			                );

			                if (opcion == JOptionPane.YES_OPTION) {
			                    logica.bajaPrestamoCompus(id);
			                    logica.ListarPrestamoCompus(modeloRegistroCompus);
			                }

			            } catch (NumberFormatException ex) {
			                JOptionPane.showMessageDialog(null, "Error: CI o número de computadora inválido.");
			            }
			        } else {
			            JOptionPane.showMessageDialog(null, "Seleccione un préstamo para eliminar.");
			        }
			    }
		});

		return p1;
	}

	public void actualizar5() {
		logica.ListarPrestamoCompus(modeloRegistroCompus);
	}

	private JPanel panelCompus() {
		JPanel p1 = new JPanel(new BorderLayout());

		// Barra Superior
		JPanel pSup = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 35));
		pSup.setBackground(Color.WHITE);
		ImageIcon compu = new ImageIcon(new ImageIcon(getClass().getResource("/ProyectoFinal/compus.png"))
		        .getImage().getScaledInstance(45, 45, Image.SCALE_SMOOTH));
		JLabel lblCompu = new JLabel(compu);
		JLabel lblTit = new JLabel("Computadoras");
		lblTit.setFont(new Font("SansSerif", Font.BOLD, 26));
		lblTit.setForeground(Colores.marron);
		pSup.add(lblCompu);
		pSup.add(lblTit);

		// Panel Botones
		JPanel pBotones = new JPanel(new FlowLayout(FlowLayout.RIGHT, 20, 35));

		// Panel barra
				JPanel barra = new JPanel(new BorderLayout());
				barra.setBackground(Color.WHITE);
				barra.setPreferredSize(new Dimension(1090, 50));
				barra.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5));
				barra.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));

				// JTextField barra
				JTextField txtBusqueda = new JTextField();
				txtBusqueda.setPreferredSize(new Dimension(1090, 50));
				txtBusqueda.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
				txtBusqueda.setFont(new Font("Arial", Font.PLAIN, 16));
				txtBusqueda.setBackground(Color.WHITE);

				ImageIcon lupa = new ImageIcon(new ImageIcon(getClass().getResource("/ProyectoFinal/33.png"))
				        .getImage().getScaledInstance(23, 23, Image.SCALE_SMOOTH));
				JLabel lblLupa = new JLabel(lupa);
				lblLupa.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 15));


				barra.add(txtBusqueda, BorderLayout.CENTER);
				barra.add(lblLupa, BorderLayout.EAST);


		// Botones
		JButton btnAlta = new JButton("+");
		btnAlta.setToolTipText("Agregar");
		btnAlta.setFont(new Font("Arial", Font.BOLD, 28));
		btnAlta.setForeground(Colores.marron);

		JButton btnBaja = new JButton();
		btnBaja.setToolTipText("Eliminar");

		// Botones con imagenes

		ImageIcon iconEliminar = new ImageIcon(new ImageIcon(getClass().getResource("/ProyectoFinal/eliminar.png"))
				.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH));
		btnBaja.setIcon(iconEliminar);

		// Estilo de botones
		btnAlta.setBackground(Color.WHITE);
		btnAlta.setFocusable(false);

		btnBaja.setBackground(Color.WHITE);
		btnBaja.setFocusable(false);

		botonesPaneles(btnAlta);
		botonesPaneles(btnBaja);

		pBotones.add(barra);
		pBotones.add(btnAlta);
		pBotones.add(btnBaja);

		// Panel barra + botones

		JPanel pSuperior = new JPanel(new BorderLayout());
		pSuperior.add(pSup, BorderLayout.NORTH);
		pSuperior.add(pBotones, BorderLayout.SOUTH);

		// TAbla

		JTable tabla = new JTable(modeloCompus);

		JScrollPane scr = new JScrollPane(tabla);
		tabla.setShowGrid(true);
		tabla.setFillsViewportHeight(true);
		tabla.getTableHeader().setReorderingAllowed(false);
		tabla.setDefaultEditor(Object.class, null);

		JPanel pCentro = new JPanel(new GridBagLayout());
		scr.setPreferredSize(new Dimension(1200, 550));
		pCentro.add(scr);

		p1.add(pSuperior, BorderLayout.NORTH);
		p1.add(pCentro, BorderLayout.CENTER);

		// EVENTOS

		btnAlta.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				vtnAltaComputadora alta = new vtnAltaComputadora(vtnPrincipal.this);
				alta.setVisible(true);

			}
		});

		btnBaja.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				int fila = tabla.getSelectedRow();

				if (fila != -1) {

					int num = (int) tabla.getValueAt(fila, 0);

					   int op = JOptionPane.showConfirmDialog(
			                    null,
			                    "¿Está seguro que desea eliminar esta Computadora?","",JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE
			            );

			            if (op == JOptionPane.YES_OPTION) {
							logica.bajaCompus(num);
							logica.listarComputadoras(modeloCompus);
			            }
			        } else {
			            JOptionPane.showMessageDialog(null, "Seleccione una computadora para eliminar", "Atención", JOptionPane.INFORMATION_MESSAGE);
			        }
			    }
			});

		return p1;
	}

	public void actualizar4() {
		logica.listarComputadoras(modeloCompus);
	}

	private JPanel panelDinero() {
		JPanel p1 = new JPanel(new BorderLayout());

		// Barra Superior
		JPanel pSup = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 35));
		pSup.setBackground(Color.WHITE);
		ImageIcon dinero = new ImageIcon(new ImageIcon(getClass().getResource("/ProyectoFinal/dinero2.png"))
		        .getImage().getScaledInstance(45, 45, Image.SCALE_SMOOTH));
		JLabel lblDinero = new JLabel(dinero);
		JLabel lblTit = new JLabel("Dinero");
		lblTit.setFont(new Font("SansSerif", Font.BOLD, 26));
		lblTit.setForeground(Colores.marron);
		pSup.add(lblDinero);
		pSup.add(lblTit);

		// Botón con imagen
		JPanel pBotones = new JPanel(new FlowLayout(FlowLayout.RIGHT, 50, 35));
		JButton btnBaja = new JButton();
		btnBaja.setToolTipText("Eliminar");
		ImageIcon iconEliminar = new ImageIcon(new ImageIcon(getClass().getResource("/ProyectoFinal/eliminar.png"))
				.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH));
		btnBaja.setIcon(iconEliminar);

		btnBaja.setBackground(Color.WHITE);
		btnBaja.setFocusable(false);

		// Panel barra
				JPanel barra = new JPanel(new BorderLayout());
				barra.setBackground(Color.WHITE);
				barra.setPreferredSize(new Dimension(1125, 50));
				barra.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5));
				barra.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));

				// JTextField barra
				JTextField txtBusqueda = new JTextField();
				txtBusqueda.setPreferredSize(new Dimension(1125, 50));
				txtBusqueda.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
				txtBusqueda.setFont(new Font("Arial", Font.PLAIN, 16));
				txtBusqueda.setBackground(Color.WHITE);

				ImageIcon lupa = new ImageIcon(new ImageIcon(getClass().getResource("/ProyectoFinal/33.png"))
				        .getImage().getScaledInstance(23, 23, Image.SCALE_SMOOTH));
				JLabel lblLupa = new JLabel(lupa);
				lblLupa.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 15));


				barra.add(txtBusqueda, BorderLayout.CENTER);
				barra.add(lblLupa, BorderLayout.EAST);


		botonesPaneles(btnBaja);
		pBotones.add(barra);
		pBotones.add(btnBaja);

		// Panel barra + botones
		JPanel pSuperior = new JPanel(new BorderLayout());
		pSuperior.add(pSup, BorderLayout.NORTH);
		pSuperior.add(pBotones, BorderLayout.SOUTH);

		// Tabla principal
		JTable tabla = new JTable(modeloDinero);
		tabla.setFillsViewportHeight(true);
		JScrollPane scr = new JScrollPane(tabla);
		scr.setPreferredSize(new Dimension(800, 500));
		tabla.getTableHeader().setReorderingAllowed(false);
		tabla.setDefaultEditor(Object.class, null);

		JTableHeader tit = tabla.getTableHeader();
		Font fuente = new Font(Font.SANS_SERIF, Font.PLAIN, 15);
		tit.setFont(fuente);
		// Tabla abajo

		JTable tablaFechaMonto = new JTable(modeloDinero2);
		tablaFechaMonto.setFillsViewportHeight(true);
		JScrollPane scr2 = new JScrollPane(tablaFechaMonto);
		scr2.setPreferredSize(new Dimension(250, 200));
		// tablaFechaMonto.getTableHeader().setReorderingAllowed(false);
		tablaFechaMonto.getColumnModel().getColumn(0).setMinWidth(0);
		tablaFechaMonto.getColumnModel().getColumn(0).setMaxWidth(0);
		// tablaFechaMonto.getColumnModel().getColumn(0).setWidth(0);
		tablaFechaMonto.setDefaultEditor(Object.class, null);

		JTableHeader tit2 = tablaFechaMonto.getTableHeader();
		tit2.setFont(fuente);


		// Panel derecho¿
		JPanel panelDerecho = new JPanel(new GridBagLayout());
		panelDerecho.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		GridBagConstraints gbc = new GridBagConstraints();

		gbc.insets = new Insets(10, 10, 10, 10);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.weightx = 1;

		// TextField y boton
		JTextField txtMonto = new PlaceholderTextField("$" + "Monto");
		txtMonto.setOpaque(true);
		txtMonto.setBackground(Color.WHITE);
		txtMonto.setFont(new Font("SansSerif", Font.PLAIN, 14));
		txtMonto.setPreferredSize(new Dimension(250, 60));
		gbc.gridx = 0;
		gbc.gridy = 0;
		panelDerecho.add(txtMonto, gbc);

		JButton btnCargar = new JButton("Cargar");
		btnCargar.setFont(new Font("SansSerif", Font.PLAIN, 14));
		btnCargar.setPreferredSize(new Dimension(100, 60));
		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.weightx = 0;
		panelDerecho.add(btnCargar, gbc);

		btnCargar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String texto = txtMonto.getText().replace("$", "").trim().replace(",", ".");

				if (texto.isEmpty()) {
					JOptionPane.showMessageDialog(null, "Ingrese un monto válido", "Error", JOptionPane.ERROR_MESSAGE);
					return;
				}

				try {
					float monto = Float.parseFloat(texto);


					if(monto <= 0) {
						JOptionPane.showMessageDialog(null, "El monto no puede ser negativo o 0", "Error", JOptionPane.ERROR_MESSAGE);
						return;

					}
					logica.altaDinero(monto);
					logica.listarDinero(modeloDinero2);
					logica.listarMontoTotal(modeloDinero);


				} catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(null, "Monto inválido", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnBaja.addActionListener(e -> {
			int fila = tablaFechaMonto.getSelectedRow();

			if (fila != -1) {
				int id = Integer.parseInt(tablaFechaMonto.getValueAt(fila, 0).toString());

		            	logica.bajaDinero(id);

						logica.listarDinero(modeloDinero2);
						logica.listarMontoTotal(modeloDinero);

		        } else {
		            JOptionPane.showMessageDialog(null, "Seleccione un monto para eliminar", "Atención", JOptionPane.INFORMATION_MESSAGE);
		        }
		});

		// Tabla abajo de textfield
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.gridwidth = 2;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weightx = 1;
		gbc.weighty = 1;
		panelDerecho.add(scr2, gbc);

		// Panel central, tabla izquierda + panel derecho
		JPanel pCentro = new JPanel(new BorderLayout());
		pCentro.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		pCentro.add(scr, BorderLayout.WEST);
		pCentro.add(panelDerecho, BorderLayout.CENTER);

		p1.add(pSuperior, BorderLayout.NORTH);
		p1.add(pCentro, BorderLayout.CENTER);

		return p1;
	}

	private void botonesPaneles(JButton btn) {

		btn.setPreferredSize(new Dimension(50, 50));

	}

}