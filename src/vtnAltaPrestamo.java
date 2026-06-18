package ProyectoFinal;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.border.LineBorder;
public class vtnAltaPrestamo extends JFrame {

	private vtnPrincipal ventanaPrincipal;
	Gestor logica = new Gestor();

	public vtnAltaPrestamo(vtnPrincipal principal) {
        this.ventanaPrincipal = principal;

		this.setTitle("Registrar Préstamo");
		this.setSize(500, 400);
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);

		  setUndecorated(true);


		GridBagConstraints gbc = new GridBagConstraints();

		JPanel panel1 = new JPanel();
		 panel1.setBorder(BorderFactory.createCompoundBorder(
	                new LineBorder(Color.GRAY, 1, true),
	                BorderFactory.createEmptyBorder(5, 25, 5, 25)
	        ));
		this.add(panel1);
		panel1.setBackground(Color.WHITE);
		panel1.setLayout(new GridBagLayout());
//		                        filas,columnas,
		gbc.insets = new Insets(10,10,10,10);
		gbc.fill	 = GridBagConstraints.HORIZONTAL;
		gbc.weightx=1;

		  //Boton x
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
	    gbc.gridwidth = 1;
	    gbc.gridy = 0;
	    gbc.gridx = 3;
	    panel1.add(pSup, gbc);
		// panel1.setLayout(new BoxLayout(panel1, BoxLayout.Y_AXIS));


		JLabel lab0 = new JLabel("Registrar Préstamo");
		lab0.setFont(new Font("Arial", Font.BOLD, 22));
		lab0.setForeground(Colores.verdeOcuro);

		//Botton regitarar
        JButton btnRegistrar = new JButton("Registrar");
        btnRegistrar.setForeground(Color.WHITE);
        btnRegistrar.setBackground(Colores.verdeOcuro);
        btnRegistrar.setFont(new Font("Arial", Font.BOLD, 13));

        JPanel pRegistrar = new JPanel(new FlowLayout(FlowLayout.RIGHT, 0, 10));
        pRegistrar.setOpaque(false);
        pRegistrar.add(btnRegistrar);

        gbc.gridx = 0; gbc.gridy = 4;
        gbc.gridwidth = 4;
        panel1.add(pRegistrar, gbc);




		JTextField tex3 = new PlaceholderTextField("Fecha de Devolución");
		JTextField txt4 = new PlaceholderTextField("CI Persona");
		JTextField txt5 = new PlaceholderTextField("ISBN");

		JButton btnPersona = new JButton("Agregar Persona");

		btnRegistrar.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        String Libro = txt5.getText().trim().replace(" ", "");
		        String fechaDev = tex3.getText().trim();

		        try {
		            int ci = Integer.parseInt(txt4.getText().trim().replace(" ", ""));

		            // Validación CI
		            if (ci < 10000000 || ci > 99999999) {
		                JOptionPane.showMessageDialog(null, "La CI debe tener 8 dígitos", "Error", JOptionPane.ERROR_MESSAGE);
		                return;
		            }

		            // Validación ISBN
		            if (!(Libro.length() == 10 || Libro.length() == 13)) {
		                JOptionPane.showMessageDialog(null, "El ISBN debe tener 10 o 13 caracteres", "Error", JOptionPane.ERROR_MESSAGE);
		                return;
		            }

		            if (!fechaDev.matches("\\d{4}-\\d{2}-\\d{2}")) {
		                JOptionPane.showMessageDialog(null, "La fecha debe escribirse como YYYY-MM-DD", "Error", JOptionPane.ERROR_MESSAGE);
		                return;
		            }

		            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		            sdf.setLenient(false);
		            Date fechaIngresada = sdf.parse(fechaDev);

		            Date fechaHoy = sdf.parse(sdf.format(new Date()));

		          if (fechaIngresada.before(fechaHoy)) {
		                JOptionPane.showMessageDialog(null, "La fecha de devolución no puede ser anterior a hoy", "Error", JOptionPane.ERROR_MESSAGE);
		                return;
		            }

		            logica.altaPrestamo(ci, Libro, fechaDev);
		            ventanaPrincipal.actualizar3();
		            dispose();

		        } catch (NumberFormatException ex) {
		            JOptionPane.showMessageDialog(null, "La CI debe ser un número válido", "Error", JOptionPane.ERROR_MESSAGE);
		        } catch (Exception ex) {
		            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		        }
		    }
		});


        gbc.gridx = 3; gbc.gridy = 1;
        gbc.gridwidth = 1;
        panel1.add(btnPersona, gbc);

//		FILLA 0:
		gbc.gridwidth = 3;
		 gbc.gridx = 0;  // columna
	     gbc.gridy = 0;  // fila
		panel1.add(lab0,gbc);
	 //FILLA 1:
		 gbc.gridwidth = 3;
		 gbc.gridx = 0;  // columna
	     gbc.gridy = 1;  // fila
		panel1.add(txt4, gbc);

	  //FILLA 2:

		 gbc.gridwidth = 4;
		 gbc.gridx = 0;  // columna
	     gbc.gridy = 2;  // fila
	     panel1.add(txt5,gbc);

	     gbc.gridwidth = 4;
		 gbc.gridx = 1;
		 gbc.gridy = 3;
		panel1.add(tex3,gbc);

		//-----------------------------
		//EVENTOS

		btnPersona.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				vtnAltaPersona alta = new vtnAltaPersona(principal);
				alta.setVisible(true);

			}
		});
	   	}}
