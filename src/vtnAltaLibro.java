package ProyectoFinal;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.border.LineBorder;

public class vtnAltaLibro extends JFrame {

	Gestor logica = new Gestor();
	private vtnPrincipal ventanaPrincipal;

	public vtnAltaLibro(vtnPrincipal principal) {
		this.ventanaPrincipal = principal;

		this.setTitle("Agregar Libro");
		this.setSize(500, 400);
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setUndecorated(true);

		GridBagConstraints gbc = new GridBagConstraints();

		JPanel panel1 = new JPanel();
		panel1.setBorder(BorderFactory.createCompoundBorder(new LineBorder(Color.GRAY, 1, true),
				BorderFactory.createEmptyBorder(5, 25, 5, 25)
		));
		this.add(panel1);
		panel1.setBackground(Color.WHITE);
		panel1.setLayout(new GridBagLayout());
		gbc.insets = new Insets(10, 10, 10, 10);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.weightx = 1;

		// Boton x
		JLabel btnX = new JLabel("X", SwingConstants.CENTER);
		btnX.setFont(new Font("Arial", Font.BOLD, 18));
		btnX.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnX.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
			}
		});

		JPanel pSup = new JPanel(new BorderLayout());
		pSup.setOpaque(false);
		pSup.add(btnX, BorderLayout.EAST);
		gbc.gridwidth = 3;
		gbc.gridy = 0;
		gbc.gridx = 3;
		panel1.add(pSup, gbc);
		// panel1.setLayout(new BoxLayout(panel1, BoxLayout.Y_AXIS));

		JLabel lab0 = new JLabel("Registrar Libro");
		lab0.setFont(new Font("Arial", Font.BOLD, 22));
		lab0.setForeground(Colores.verdeOcuro);

		// Panel del botón Registrar
		JButton bot1 = new JButton("Registrar");
		bot1.setForeground(Color.WHITE);
		bot1.setBackground(Colores.verdeOcuro);
		bot1.setPreferredSize(new Dimension(150, 40));

		JPanel panelBoton = new JPanel(new FlowLayout(FlowLayout.RIGHT, 0, 0));
		panelBoton.setOpaque(false);
		panelBoton.add(bot1);

		gbc.gridx = 0;
		gbc.gridy = 6;
		gbc.gridwidth = 4;
		panel1.add(panelBoton, gbc);

		String[] elemento = { "Materia", "Literatura", "Historia", "Filosofía", "Biología", "Matemáticas",
				"Informática", "Arte", "Inglés", "Recreativo", "Química", "Idioma Español", "Otros" };
		String[] elemento2 = { "Estado Físico", "Nuevo", "Bueno", "Dañado" };

		JTextField tex1 = new PlaceholderTextField("ISBN");
		JTextField tex2 = new PlaceholderTextField("Autor");
		JTextField tex3 = new PlaceholderTextField("Título");
		JTextField tex5 = new PlaceholderTextField("Cantidad de Ejemplares");
		JComboBox box8 = new JComboBox(elemento);
		JComboBox box9 = new JComboBox(elemento2);

		bot1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String isbn = tex1.getText().trim().replace(" ", "");
				String autor = tex2.getText().trim();
				String titulo = tex3.getText().trim();
				String materia = (String) box8.getSelectedItem();
				String estado = (String) box9.getSelectedItem();

				if (!(isbn.length() == 10 || isbn.length() == 13)) {
					JOptionPane.showMessageDialog(null, "El ISBN debe tener 10 o 13 caracteres y no debe contener guiones", "Error",
							JOptionPane.ERROR_MESSAGE);
					return;
				}

				if (!isbn.matches("[a-zA-Z0-9]+")) {
		            JOptionPane.showMessageDialog(null, "El ISBN solo puede contener números y letras", "Error",
		                    JOptionPane.ERROR_MESSAGE);
		            return;
		        }

				if (materia.equals("Materia")) {
					JOptionPane.showMessageDialog(null, "Debe seleccionar una materia válida", "Error",
							JOptionPane.ERROR_MESSAGE);
					return;
				} else if (estado.equals("Estado Físico")) {

					JOptionPane.showMessageDialog(null, "Debe seleccionar una estado válida", "Error",
							JOptionPane.ERROR_MESSAGE);
					return;
				}
				try {

					int cantEjemplares = Integer.parseInt(tex5.getText().replace(" ", ""));
					if (cantEjemplares < 0) {
						JOptionPane.showMessageDialog(null, "La cantidad de ejemplares no puede ser negativa", "Error",
								JOptionPane.ERROR_MESSAGE);
						return;
					}

					logica.registrarLibro(isbn, titulo, autor, materia, estado, cantEjemplares);

					ventanaPrincipal.actualizar();
					logica.actualizarEstadoRetrasado();
					dispose();

				} catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(null, "La cantidad de ejemplares debe ser un número válido", "Error",
							JOptionPane.ERROR_MESSAGE);

					// } catch (IllegalArgumentException ex) {
					// JOptionPane.showMessageDialog(null, ex.getMessage(), "Error",
					// JOptionPane.ERROR_MESSAGE);
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});

//	FILLA 0:
		gbc.gridwidth = 0;
		gbc.gridx = 0; // columna
		gbc.gridy = 0; // fila
		panel1.add(lab0, gbc);
		// FILLA 1:
		gbc.gridwidth = 1;
		gbc.gridx = 0; // columna
		gbc.gridy = 1; // fila
		panel1.add(tex1, gbc);

		gbc.gridwidth = 3;
		gbc.gridx = 1; // columna
		gbc.gridy = 1; // fila
		panel1.add(tex2, gbc);
		// FILLA 2:
		gbc.gridwidth = 4;
		gbc.gridx = 0; // columna
		gbc.gridy = 2; // fila
		panel1.add(tex3, gbc);

		// FILLA 3:

		gbc.gridwidth = 4;
		gbc.gridx = 0; // columna
		gbc.gridy = 3; // fila
		panel1.add(box8, gbc);
		// FILLA 4:
		gbc.gridwidth = 2;
		gbc.gridx = 0; // columna
		gbc.gridy = 4; // fila
		panel1.add(box9, gbc);

		gbc.gridwidth = 2;
		gbc.gridx = 2; // columna
		gbc.gridy = 4; // fila
		panel1.add(tex5, gbc);
//     PANEL 3:

	}
}