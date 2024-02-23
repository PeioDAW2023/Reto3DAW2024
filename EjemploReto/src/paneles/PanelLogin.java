package paneles;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import controlador.GestionBD;
import modelo.Cliente;
import vista.VistaPrincipal;

public class PanelLogin extends JPanel {
	private static final long serialVersionUID = 1L;

	public PanelLogin() {
	}

	// Declaracion de las campos y el ArrayList
	private JLabel lblTituloInicioSesion;
	private JLabel lblUsuario;
	private JTextField txtUsuario;
	private JLabel lblcontraseña;
	private JTextField txtContraseña;
	private JLabel lblRegistro;
	private JButton btnRegistro;
	private JButton btnContinuar;
	private JLabel lblImagenIzquierda;
	private JLabel lblImagenDerecha;

	public ArrayList<Cliente> clientes = new ArrayList<Cliente>();

	public PanelLogin(VistaPrincipal vp, GestionBD gestion) {

		// Adaptar el panel al frame
		setSize(vp.getSize());
		setLayout(null);

		// AGREGACION DE CAMPOS
		// Labels del título y campos a rellenar
		
		lblTituloInicioSesion = new JLabel("Iniciar Sesion");
		lblTituloInicioSesion.setHorizontalAlignment(SwingConstants.CENTER);
		lblTituloInicioSesion.setFont(new Font("Bell MT", Font.BOLD | Font.ITALIC, 45));
		lblTituloInicioSesion.setBounds(10, 120, 780, 75);
		add(lblTituloInicioSesion);

		lblUsuario = new JLabel("Usuario:");
		lblUsuario.setFont(new Font("Calisto MT", Font.PLAIN, 25));
		lblUsuario.setBounds(220, 250, 175, 30);
		add(lblUsuario);

		txtUsuario = new JTextField();
		txtUsuario.setBounds(395, 250, 175, 30);
		add(txtUsuario);
		txtUsuario.setColumns(10);

		lblcontraseña = new JLabel("Contraseña:");
		lblcontraseña.setFont(new Font("Calisto MT", Font.PLAIN, 25));
		lblcontraseña.setBounds(220, 310, 175, 30);
		add(lblcontraseña);

		txtContraseña = new JPasswordField(); // Txt especial, aparecerán puntos en lugar de texto
		txtContraseña.setColumns(10);
		txtContraseña.setBounds(395, 310, 175, 30);
		add(txtContraseña);

		lblRegistro = new JLabel("¿No tienes una cuenta?");
		lblRegistro.setFont(new Font("Calisto MT", Font.PLAIN, 13));
		lblRegistro.setBounds(220, 385, 200, 25);
		add(lblRegistro);
		
		// Labels de las Imagenes
		
		lblImagenIzquierda = new JLabel();
		lblImagenIzquierda.setIcon(new ImageIcon(VistaPrincipal.class.getResource("/multimedia/rolloCinePelis1.png")));
		lblImagenIzquierda.setBounds(0, 0, 145, 600);
		add(lblImagenIzquierda);

		lblImagenDerecha = new JLabel();
		lblImagenDerecha.setIcon(new ImageIcon(VistaPrincipal.class.getResource("/multimedia/rolloCinePelis2.png")));
		lblImagenDerecha.setBounds(647, 0, 175, 600);
		add(lblImagenDerecha);

		// Botones
		
		btnContinuar = new JButton("Siguiente");
		btnContinuar.setBackground(UIManager.getColor("Button.background"));
		btnContinuar.setBounds(480, 410, 90, 25);
		add(btnContinuar);
		
		btnRegistro = new JButton("Registrate");
		btnRegistro.setBounds(220, 410, 120, 25);
		add(btnRegistro);

		// ACCIONES DE LOS BOTONES
		// Botón de "Registrate"
		
		btnRegistro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vp.cambiarPanel(2); // Cambia al panel de Registro
			}

		});
		
		// Botón de "Siguiente"
		
		btnContinuar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				// Carga el ArrayList de Clientes para comprobar el usuario
				gestion.queryClientes(txtUsuario.getText()); 
				clientes = gestion.devolverArrayListClientes();
				
				if (clientes.isEmpty()) { // Si el ArrayList resulta estar vacío significa que el Usuario está mal
					JOptionPane.showMessageDialog(null, "Usuario incorrecto, intentelo de nuevo", "ERROR",
							JOptionPane.ERROR_MESSAGE);
				}
				for (int i = 0; i < clientes.size(); i++) { // Recorre el ArrayList
					if (txtContraseña.getText().equals(clientes.get(i).getContrasena()) 
							&& txtUsuario.getText().equals(clientes.get(i).getDni())) { // Compara las contraseñas y el usuario
						if (clientes.get(i).getSexo().equals("H")) { // Una vez comprobado que está correcto, observa el sexo para mostrar un mensaje u otro
							JOptionPane.showMessageDialog(null, "Bienvenido " + clientes.get(i).getNomCliente(),
									"Bienvenido", JOptionPane.PLAIN_MESSAGE);
							vp.cambiarPanel(3); // Cambia al Panel de Cines
						} else if (clientes.get(i).getSexo().equals("M")) {
							JOptionPane.showMessageDialog(null, "Bienvenida " + clientes.get(i).getNomCliente(),
									"Bienvenida", JOptionPane.PLAIN_MESSAGE);
							vp.cambiarPanel(3); // Cambia al Panel de Cines
						}

					} else { // Si la contraseña o el usuario no son iguales a los del ArrayList, se mostrará un mensaje de error
						JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrectos, intentelo de nuevo",
								"ERROR", JOptionPane.ERROR_MESSAGE);
					}
				}
				clientes.clear(); // Al final vacía el ArrayList, para que no se acumulen al hacer nuevas consultas
			}
		});	
	}

}