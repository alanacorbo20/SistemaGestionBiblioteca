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
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

public class vtnAltaPrestamoComputadora extends JFrame {

	private vtnPrincipal ventanaPrincipal;
	Gestor logica = new Gestor();


	public vtnAltaPrestamoComputadora(vtnPrincipal principal) {
		this.ventanaPrincipal = principal;

        this.setTitle("Registrar Préstamo");
        this.setSize(400, 300);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setUndecorated(true);

        //Panel Principal
        JPanel panelPrincipal = new JPanel(new GridBagLayout());
        panelPrincipal.setBackground(Color.WHITE);
        panelPrincipal.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(Color.GRAY, 1, true),
                BorderFactory.createEmptyBorder(5, 25, 5, 25) // margen interno
        ));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1;

        //Barra Titulo y x
        JLabel btnX = new JLabel("X", SwingConstants.CENTER);
        btnX.setFont(new Font("Arial", Font.BOLD, 18));
        btnX.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnX.addMouseListener(new MouseAdapter() { //Evento para cerrar la ventana
            @Override
            public void mouseClicked(MouseEvent e) {
                dispose();
            }
        });

        JLabel lblTitulo = new JLabel("Registrar Préstamo");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 22));
        lblTitulo.setForeground(Colores.verdeOcuro);

        JPanel pSup = new JPanel(new BorderLayout());
        pSup.setOpaque(false);
        pSup.add(lblTitulo, BorderLayout.WEST);
        pSup.add(btnX, BorderLayout.EAST);

        gbc.gridx = 0; gbc.gridy = 0;
        gbc.gridwidth = 4;
        panelPrincipal.add(pSup, gbc);

        // Persona
		JTextField txt1 = new PlaceholderTextField("CI Persona");
        txt1.setBackground(Color.WHITE);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 3;
        gbc.weightx = 1.0;
        panelPrincipal.add(txt1, gbc);

        //Boton
        JButton btnPersona = new JButton("Agregar Persona");
        btnPersona.setPreferredSize(new Dimension(120, 25));
        gbc.gridx = 3;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.weightx = 0;
        panelPrincipal.add(btnPersona, gbc);

        //Computadora
        JTextField txtComputadora = new PlaceholderTextField("Número Computadora");

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 4;
        gbc.weightx = 1.0;
        panelPrincipal.add(txtComputadora, gbc);


        //Botton regitarar
        JButton btnRegistrar = new JButton("Registrar");
        btnRegistrar.setForeground(Color.WHITE);
        btnRegistrar.setBackground(Colores.verdeOcuro);
        btnRegistrar.setFont(new Font("Arial", Font.BOLD, 13));

        JPanel pRegistrar = new JPanel(new FlowLayout(FlowLayout.RIGHT, 0, 10));
        pRegistrar.setOpaque(false);
        pRegistrar.add(btnRegistrar);

        gbc.gridx = 0;  gbc.gridy = 3;
        gbc.gridwidth = 4;
        panelPrincipal.add(pRegistrar, gbc);


        //------------------------------------------------

        //EVENTOS

		btnRegistrar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {



				 try {
					 int ci = Integer.parseInt(txt1.getText().replace(" ", ""));

				        if (ci < 10000000 || ci > 99999999) {
				            JOptionPane.showMessageDialog(null, "La CI debe tener 8 dígitos", "Error", JOptionPane.ERROR_MESSAGE);
				            return;
				        }

				        int num = Integer.parseInt(txtComputadora.getText().replace(" ", ""));

				        if (num < 0) {
				            JOptionPane.showMessageDialog(null, "El número de computadora no puede ser negativo", "Error", JOptionPane.ERROR_MESSAGE);
				            return;
				        }

				        if (num > 99) {
				            JOptionPane.showMessageDialog(null, "El número de computadora no puede tener 3 dígitos", "Error", JOptionPane.ERROR_MESSAGE);
				            return;
				        }

				        logica.altaPrestamoCompus(num, ci);
				        ventanaPrincipal.actualizar5();
				        dispose();

				} catch (NumberFormatException ex) {
			        JOptionPane.showMessageDialog(null, "CI o número de computadora inválido. No pueden estar vacios y solo pueden contener números", "Error", JOptionPane.ERROR_MESSAGE);

		       // } catch (IllegalArgumentException ex) {
		          //  JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		        }catch (Exception ex) {
		            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		        }


			}
		});

      btnPersona.addActionListener(new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			vtnAltaPersona alta = new vtnAltaPersona(principal);
			alta.setVisible(true);

		}
	});
        this.add(panelPrincipal);
    }



}
