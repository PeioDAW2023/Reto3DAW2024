package paneles;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import controlador.GestionBD;
import vista.VistaPrincipal;

public class PanelRegistro extends JPanel {
	private static final long serialVersionUID = 1L;
	
	// Declaracion de campos y Arrays
	
	private JTextField txtNombre; 
	private JTextField txtDNI; 
	private JTextField txtApellido; 
	private JTextField pwdContraseña; 
	private JTextField pwdConfirmarContraseña;	
	private JLabel lblTituloRegistro; 
	private JLabel lblNombre;
	private JLabel lblDNI; 
	private JLabel lblApellido; 
	private JLabel lblContraseña; 
	private JLabel lblConfirmarContraseña; 
	private JLabel lblSexo;
	private JLabel lblIcono1; 
	private JLabel lblIcono2;
	private JButton btnGuardarRegistro; 
	private JButton btnAtras;
	private JComboBox<String> comboBoxSexo;
	
	private String[] sexos = { "H", "M" };
	
	// Constructor del panel
	public PanelRegistro() {
	}

	public PanelRegistro(VistaPrincipal vp, GestionBD gestion) {

		// Adapta el panel al frame
		setSize(vp.getSize());
		setLayout(null);

		// AGREGACION DE CAMPOS
		// Labels y TextFields
		lblTituloRegistro = new JLabel("Crear Cuenta");
		lblTituloRegistro.setHorizontalAlignment(SwingConstants.CENTER);
		lblTituloRegistro.setFont(new Font("Bell MT", Font.BOLD | Font.ITALIC, 45));
		lblTituloRegistro.setBounds(10, 10, 780, 73);
		add(lblTituloRegistro);

		lblNombre = new JLabel("Nombre:");
		lblNombre.setFont(new Font("Calisto MT", Font.PLAIN, 17));
		lblNombre.setBounds(220, 164, 175, 30);
		add(lblNombre);

		txtNombre = new JTextField();
		txtNombre.setBounds(395, 167, 175, 30);
		add(txtNombre);
		txtNombre.setColumns(10);

		lblDNI = new JLabel("DNI:");
		lblDNI.setFont(new Font("Calisto MT", Font.PLAIN, 17));
		lblDNI.setBounds(220, 110, 175, 30);
		add(lblDNI);

		txtDNI = new JTextField();
		txtDNI.setColumns(10);
		txtDNI.setBounds(395, 113, 175, 30);
		add(txtDNI);

		lblApellido = new JLabel("Apellido:");
		lblApellido.setFont(new Font("Calisto MT", Font.PLAIN, 17));
		lblApellido.setBounds(220, 215, 175, 30);
		add(lblApellido);

		txtApellido = new JTextField();
		txtApellido.setColumns(10);
		txtApellido.setBounds(395, 223, 175, 30);
		add(txtApellido);

		lblSexo = new JLabel("Sexo:");
		lblSexo.setFont(new Font("Calisto MT", Font.PLAIN, 17));
		lblSexo.setBounds(220, 273, 175, 30);
		add(lblSexo);

		lblContraseña = new JLabel("Contraseña:");
		lblContraseña.setFont(new Font("Calisto MT", Font.PLAIN, 17));
		lblContraseña.setBounds(220, 351, 175, 30);
		add(lblContraseña);

		pwdContraseña = new JPasswordField();
		pwdContraseña.setColumns(10);
		pwdContraseña.setBounds(395, 354, 175, 30);
		add(pwdContraseña);

		lblConfirmarContraseña = new JLabel("Confirmar contraseña:");
		lblConfirmarContraseña.setFont(new Font("Calisto MT", Font.PLAIN, 17));
		lblConfirmarContraseña.setBounds(220, 402, 175, 30);
		add(lblConfirmarContraseña);

		pwdConfirmarContraseña = new JPasswordField();
		pwdConfirmarContraseña.setColumns(10);
		pwdConfirmarContraseña.setBounds(395, 402, 175, 30);
		add(pwdConfirmarContraseña);

		// Labels con Imagenes

		lblIcono1 = new JLabel();
		lblIcono1.setIcon(new ImageIcon(VistaPrincipal.class.getResource("/multimedia/rolloCinePelis1.png")));
		lblIcono1.setBounds(0, 0, 145, 600);
		add(lblIcono1);

		lblIcono2 = new JLabel();
		lblIcono2.setIcon(new ImageIcon(VistaPrincipal.class.getResource("/multimedia/rolloCinePelis2.png")));
		lblIcono2.setBounds(647, 0, 175, 600);
		add(lblIcono2);

		// ComboBox

		comboBoxSexo = new JComboBox<String>(sexos);
		comboBoxSexo.setBounds(395, 280, 43, 21);
		add(comboBoxSexo);

		// Botones

		btnGuardarRegistro = new JButton("Guardar");
		btnGuardarRegistro.setBackground(UIManager.getColor("Button.background"));
		btnGuardarRegistro.setBounds(490, 486, 80, 25);
		add(btnGuardarRegistro);

		btnAtras = new JButton("Atras");
		btnAtras.setBounds(395, 486, 65, 25);
		add(btnAtras);

		// ACCIONES DE BOTONES

		btnAtras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vp.cambiarPanel(1); // Cambia al Panel de Login
			}

		});

		btnGuardarRegistro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Llama al método para validar los campos rellenados antes de agregralo a la
				// base de datos
				gestion.validarRegistro(vp, txtDNI.getText(), txtNombre.getText(), txtApellido.getText(),
						comboBoxSexo.getSelectedItem().toString(), pwdContraseña.getText(),
						pwdConfirmarContraseña.getText());

			}

		});

	}

}
