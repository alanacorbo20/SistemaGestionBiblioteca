package ProyectoFinal;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.WindowConstants;

public class vtnInicioSesion extends JFrame {

	Gestor logica = new Gestor();
    private vtnPrincipal ventanaPrincipal;

	public vtnInicioSesion() {

		this.setTitle("Inicio de Sesión");
		this.setSize(450, 600);
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setResizable(false);

		// Panel principal
		JPanel p1 = new JPanel();
		p1.setBackground(Color.WHITE);
		p1.setLayout(new BoxLayout(p1, BoxLayout.Y_AXIS));
		p1.setBorder(BorderFactory.createEmptyBorder(75, 0, 0, 0)); // top, left, bottom, right

		this.add(p1);

		// Logo
		ImageIcon im = new ImageIcon(getClass().getResource("/ProyectoFinal/logologo.png"));
		JLabel lbnLogo = new JLabel(new ImageIcon(im.getImage().getScaledInstance(170, 170, Image.SCALE_SMOOTH)));
		lbnLogo.setAlignmentX(Component.CENTER_ALIGNMENT);

		// Título
		JLabel lblTitulo = new JLabel("Biblioteca");
		lblTitulo.setFont(new Font("Verdana", Font.BOLD, 28));
		lblTitulo.setForeground(Colores.verdeOcuro);
		lblTitulo.setAlignmentX(Component.CENTER_ALIGNMENT);

		JLabel lblSub = new JLabel("Administrador");
		lblSub.setFont(new Font("Arial", Font.BOLD, 16));
		lblSub.setForeground(Colores.verdeV);
		lblSub.setAlignmentX(Component.CENTER_ALIGNMENT);

		// Campos
		JPasswordField txtContra = new JPasswordField();
		txtContra.setBackground(Colores.gris);
		txtContra.setForeground(Color.white);
		txtContra.setMaximumSize(new Dimension(250, 35));

		// Botón
		JButton btnInicioSesion = new JButton("Iniciar Sesión");
		btnInicioSesion.setBackground(Colores.verdeOcuro);
		btnInicioSesion.setFont(new Font("Segoe UI", Font.BOLD, 15));
		btnInicioSesion.setForeground(Color.WHITE);
	    btnInicioSesion.setMaximumSize(new Dimension(160, 40));
		btnInicioSesion.setAlignmentX(Component.CENTER_ALIGNMENT);

		String contraseña = "alvaroFigueredo";

		btnInicioSesion.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// System.out.println(txtContra.getPassword().equals(contraseña));
				char[] conIngresada = txtContra.getPassword();
				String con = new String(conIngresada);

				if (con.equals(contraseña)) {

					 vtnPrincipal ventanaPrincipal = new vtnPrincipal();
			            
			            logica.actualizarEstadoRetrasado();
			            ventanaPrincipal.actualizarR();
			            ventanaPrincipal.setVisible(true);
			            
			            dispose();

				} else {
					JOptionPane.showMessageDialog(null, "Contraseña incorrecta", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		p1.add(lbnLogo);
		p1.add(new JLabel(" "));
		p1.add(lblTitulo);
		p1.add(lblSub);
		p1.add(new JLabel(" "));
		p1.add(txtContra);
		p1.add(new JLabel(" "));
		p1.add(btnInicioSesion);

		// --------------------------------------------
		// EVENTOS

	}

}
