package paneles;

import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import vista.VistaPrincipal;

public class PanelRegistro extends JPanel {

	private static final long serialVersionUID = 1L;

	public PanelRegistro() {
	}

	private JTextField txtUsuario;
	private JTextField txtContraseña, txtConfirmarContra;
	private JButton btnContinuar;

	public PanelRegistro(VistaPrincipal vp) {

		setSize(vp.getSize());
		vp.setIconImage(
				Toolkit.getDefaultToolkit().getImage(VistaPrincipal.class.getResource("/multimedia/login_icon.png")));
		setLayout(null);

		JLabel lblCineElorrieta = new JLabel("Crear Cuenta");
		lblCineElorrieta.setHorizontalAlignment(SwingConstants.CENTER);
		lblCineElorrieta.setFont(new Font("Bell MT", Font.BOLD | Font.ITALIC, 45));
		lblCineElorrieta.setBounds(10, 122, 780, 73);
		add(lblCineElorrieta);

		JLabel lblUsuario = new JLabel("Nombre:");
		lblUsuario.setFont(new Font("Calisto MT", Font.PLAIN, 17));
		lblUsuario.setBounds(220, 220, 175, 30);
		add(lblUsuario);

		txtUsuario = new JTextField();
		txtUsuario.setBounds(395, 220, 175, 30);
		add(txtUsuario);
		txtUsuario.setColumns(10);

		JLabel lblcontraseña = new JLabel("Contraseña:");
		lblcontraseña.setFont(new Font("Calisto MT", Font.PLAIN, 17));
		lblcontraseña.setBounds(220, 275, 175, 30);
		add(lblcontraseña);

		txtContraseña = new JPasswordField();
		txtContraseña.addKeyListener(new KeyListener() {
			public void keyTyped(KeyEvent e) {
			}

			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					btnContinuar.doClick();
				}
			}

			public void keyReleased(KeyEvent e) {
			}
		});

		txtContraseña.setColumns(10);
		txtContraseña.setBounds(395, 275, 175, 30);
		add(txtContraseña);

		JLabel lblConfirmaContra = new JLabel("Confirmar contraseña:");
		lblConfirmaContra.setFont(new Font("Calisto MT", Font.PLAIN, 17));
		lblConfirmaContra.setBounds(220, 325, 175, 30);
		add(lblConfirmaContra);

		txtConfirmarContra = new JPasswordField();
		txtConfirmarContra.addKeyListener(new KeyListener() {
			public void keyTyped(KeyEvent e) {
			}

			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					btnContinuar.doClick();
				}
			}

			public void keyReleased(KeyEvent e) {
			}
		});

		txtConfirmarContra.setColumns(10);
		txtConfirmarContra.setBounds(395, 325, 175, 30);
		add(txtConfirmarContra);

		JButton btnRegistrarse = new JButton("Guardar");
		btnRegistrarse.setBackground(UIManager.getColor("Button.background"));
		btnRegistrarse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				registro(vp);
			}

		});
		btnRegistrarse.setBounds(490, 410, 80, 25);
		add(btnRegistrarse);

		JButton btnAtras = new JButton("Atras");
		btnAtras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vp.cambiarPanel(1);
			}

		});
		btnAtras.setBounds(395, 410, 65, 25);
		add(btnAtras);

		JLabel lblIcono1 = new JLabel();
		lblIcono1.setIcon(new ImageIcon(VistaPrincipal.class.getResource("/multimedia/rolloCinePelis1.png")));
		lblIcono1.setBounds(0, 0, 145, 600);
		add(lblIcono1);

		JLabel lblIcono2 = new JLabel();
		lblIcono2.setIcon(new ImageIcon(VistaPrincipal.class.getResource("/multimedia/rolloCinePelis2.png")));
		lblIcono2.setBounds(647, 0, 175, 600);
		add(lblIcono2);

	}

	private void registro(VistaPrincipal vp) {
		
		if (txtUsuario.getText().isEmpty() | txtContraseña.getText().isEmpty() | txtConfirmarContra.getText().isEmpty() ) {
			JOptionPane.showMessageDialog(null, "Todos los datos son obligatorios", "ERROR", JOptionPane.ERROR_MESSAGE);
		}
		
		else if (txtContraseña.getText().equals(txtConfirmarContra.getText())) {
			JOptionPane.showMessageDialog(null, "Se ha guardado , Inicia sesion");
			vp.cambiarPanel(1);
		} else {
			JOptionPane.showMessageDialog(null, "Las contraseñas no coinciden", "ERROR", JOptionPane.ERROR_MESSAGE);

		}

	}

}
