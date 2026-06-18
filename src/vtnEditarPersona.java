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

public class vtnEditarPersona extends JFrame {
	Gestor logica = new Gestor();
	private vtnPrincipal ventanaPrincipal;
	public vtnEditarPersona(int ci, String nombre, String apellido, String tipo, String grupo, int telefono, vtnPrincipal principal) {
		this.ventanaPrincipal = principal;


	this.setTitle("Editar Persona");
	this.setSize(500, 400);
	this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	this.setLocationRelativeTo(null);
	this.setUndecorated(true);


	GridBagConstraints gbc = new GridBagConstraints();

	JPanel panel1 = new JPanel();
	panel1.setBorder(BorderFactory.createCompoundBorder(
             new LineBorder(Color.GRAY, 1, true),
             BorderFactory.createEmptyBorder(5, 25, 5, 25)
     ));
	this.add(panel1);
	panel1.setBackground(Color.WHITE);
	panel1.setLayout(new GridBagLayout());
//	                        filas,columnas,
	gbc.insets = new Insets(10,10,10,10);
	gbc.fill	 = GridBagConstraints.HORIZONTAL;
	gbc.weightx=1;

	  //Boton x
    JLabel btnX = new JLabel("X", SwingConstants.CENTER);
    btnX.setFont(new Font("Arial", Font.BOLD, 18));
    btnX.setCursor(new Cursor(Cursor.HAND_CURSOR));
    btnX.addMouseListener(new MouseAdapter() { //Evento para cerrar la ventana
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



	JLabel lab0 = new JLabel("Editar Persona");
	lab0.setFont(new Font("Arial", Font.BOLD, 22));
	lab0.setForeground(Colores.verdeOcuro);


	 // Panel del botón Registrar
    JButton bot1 = new JButton("Editar");
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

	String[] elemento = {"Tipo" , "Estudiante", "Funcionario", "Externo"};


    JComboBox cmb1 = new JComboBox(elemento);
	JTextField tex2 = new PlaceholderTextField("Cédula");
	JTextField tex3 = new PlaceholderTextField("Nombre");
	JTextField tex5 = new PlaceholderTextField("Apellido");
	JTextField box8 = new PlaceholderTextField("Télefono");
	JTextField box9 = new PlaceholderTextField("Grupo");

	tex2.setEnabled(false);


    tex2.setText(String.valueOf(ci));
    tex3.setText(nombre);
    tex5.setText(apellido);
    cmb1.setSelectedItem(tipo);
    box9.setText(grupo);
    box8.setText(String.valueOf(telefono));

    bot1.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
        	String nombre = tex3.getText().trim();
			String apellido = tex5.getText().trim();
			String tipo = (String) cmb1.getSelectedItem();
			String grupo = box9.getText().trim();

			try {
				int ci = Integer.parseInt(tex2.getText().replace(" ",""));

				if (ci < 10000000 || ci > 99999999) {
					JOptionPane.showMessageDialog(null, "La CI debe tener 8 dígitos", "Error",JOptionPane.ERROR_MESSAGE);
					return;
				}

				//Otar froma de evaluar la longituda pero que no existe en un int
				String textoTel = box8.getText().replace(" ", " ");

				if (!textoTel.matches("\\d{9}")) {
				    JOptionPane.showMessageDialog(null, "El teléfono debe tener exactamente 9 dígitos numéricos","Error",JOptionPane.ERROR_MESSAGE);
				    return;
				}

				int tel = Integer.parseInt(textoTel);

				if (tel <= 0) {
					JOptionPane.showMessageDialog(null, "El teléfono debe ser un número válido", "Error",
							JOptionPane.ERROR_MESSAGE);
					return;
				}



				if (tipo.equals("Tipo")) {
					JOptionPane.showMessageDialog(null, "Debe seleccionar una Tipo válido", "Error", JOptionPane.ERROR_MESSAGE);
					return;
				}
				 if ("Estudiante".equals(tipo)) {
		                if (grupo.isEmpty()) {
		                    JOptionPane.showMessageDialog(null,"El grupo es obligatorio para los estudiantes","Error", JOptionPane.ERROR_MESSAGE);
		                    return;
		                }
		            } else {
		                grupo = null;
		            }
				logica.altaPersona(ci, nombre, apellido, tipo, grupo, tel);
				ventanaPrincipal.actualizar2();
				dispose();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Los números deben ser válidos");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Error al modificar: " + ex.getMessage());
                System.out.println("Excepción: " + ex.getMessage());
            }
        }
    });

//	FILLA 0:
	 gbc.gridwidth = 0;
	 gbc.gridx = 0;  // columna
     gbc.gridy = 0;  // fila
	panel1.add(lab0,gbc);
 //FILLA 1:
	 gbc.gridwidth = 1;
	 gbc.gridx = 0;
     gbc.gridy = 1;
	panel1.add(cmb1,gbc);

	 gbc.gridwidth = 3;
	 gbc.gridx = 1;
     gbc.gridy = 1;
     panel1.add(tex2,gbc);
  // FILA 2
     gbc.gridwidth = 2;
     gbc.gridx = 0;
     gbc.gridy = 2;
     panel1.add(tex3, gbc);

     gbc.gridwidth = 2;
     gbc.gridx = 2;
     gbc.gridy = 2;
     panel1.add(tex5, gbc);

     // FILA 3
     gbc.gridwidth = 2;
     gbc.gridx = 0;
     gbc.gridy = 3;
     panel1.add(box8, gbc);

     gbc.gridwidth = 2;
     gbc.gridx = 2;
     gbc.gridy = 3;
     panel1.add(box9, gbc);




}}