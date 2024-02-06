package paneles;

import java.awt.Color;
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

public class PanelLogin extends JPanel {
	private static final long serialVersionUID = 1L;

	public PanelLogin() {
	}
	
	private JTextField txtUsuario;
	private JTextField txtContraseña;
	private JButton btnContinuar;

	public PanelLogin(VistaPrincipal vp) {
		
		setSize(vp.getSize());
		vp.setIconImage(Toolkit.getDefaultToolkit().getImage(VistaPrincipal.class.getResource("/multimedia/login_icon.png")));
		setLayout(null);

		JLabel lblCineElorrieta = new JLabel("Iniciar Sesion");
		lblCineElorrieta.setHorizontalAlignment(SwingConstants.CENTER);
		lblCineElorrieta.setFont(new Font("Bell MT", Font.BOLD | Font.ITALIC, 45));
		lblCineElorrieta.setBounds(10, 122, 780, 73);
		add(lblCineElorrieta);

		JLabel lblUsuario = new JLabel("Usuario:");
		lblUsuario.setFont(new Font("Calisto MT", Font.PLAIN, 25));
		lblUsuario.setBounds(222, 272, 175, 30);
		add(lblUsuario);

		txtUsuario  = new JTextField();
		txtUsuario.setBounds(397, 273, 175, 30);
		add(txtUsuario);
		txtUsuario.setColumns(10);

		JLabel lblcontraseña = new JLabel("Contraseña:");
		lblcontraseña.setFont(new Font("Calisto MT", Font.PLAIN, 25));
		lblcontraseña.setBounds(222, 324, 175, 30);
		add(lblcontraseña);

		txtContraseña = new JPasswordField();
		txtContraseña.addKeyListener (new KeyListener() {
		public void keyTyped(KeyEvent e) {}
		
		public void keyPressed(KeyEvent e) {
			if (e.getKeyCode() == KeyEvent.VK_ENTER) {
				btnContinuar.doClick();
			}
		}
		public void keyReleased(KeyEvent e) {}
	});
		
	
		txtContraseña.setColumns(10);
		txtContraseña.setBounds(397, 325, 175, 30);
		add(txtContraseña);

		

		btnContinuar = new JButton("Continuar");
		btnContinuar.setBackground(UIManager.getColor("Button.background"));
		btnContinuar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				login(vp);
			}

		});
		btnContinuar.setBounds(482, 402, 90, 23);
		add(btnContinuar);
		
		JButton btnRegistro = new JButton("Registrarse");
		btnRegistro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vp.cambiarPanel(2);
			}

		});
		btnRegistro.setBounds(357, 402, 105, 23);
		add(btnRegistro);
		
		JLabel lblIcono1 = new JLabel();
		lblIcono1.setIcon(new ImageIcon(VistaPrincipal.class.getResource("/multimedia/rollocine.png")));
		lblIcono1.setBounds(0, 0, 145, 600);
		add(lblIcono1);
		
		JLabel lblIcono2 = new JLabel();
		lblIcono2.setBackground(new Color(128, 0, 0));
		lblIcono2.setForeground(new Color(128, 0, 0));
		lblIcono2.setIcon(new ImageIcon(VistaPrincipal.class.getResource("/multimedia/rollocine.png")));
		lblIcono2.setBounds(647, 0, 175, 600);
		add(lblIcono2);
		
	}

	private void login(VistaPrincipal vp) {
		if ((txtUsuario.getText().equalsIgnoreCase("cine")) && (txtContraseña.getText().equals("cine"))) {
			JOptionPane.showMessageDialog(null, "Has iniciado sesion");
			vp.cambiarPanel(3);
		}
		
			else {
			JOptionPane.showMessageDialog(null, "USUARIO O CONTRASEÑAS INCORRECTAS", "ERROR", JOptionPane.ERROR_MESSAGE);
		}
	}

}