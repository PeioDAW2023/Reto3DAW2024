package paneles;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.UIManager;

import vista.VistaPrincipal;

public class PanelRegistro extends JPanel{

	private static final long serialVersionUID = 1L;

	public PanelRegistro() {}
	
	public PanelRegistro (VistaPrincipal vp) {
		
		setSize(vp.getSize());
		vp.setIconImage(Toolkit.getDefaultToolkit().getImage(VistaPrincipal.class.getResource("/multimedia/login_icon.png")));
		setLayout(null);
		
		JButton btnRegistrarse = new JButton("Registrarse");
		btnRegistrarse.setBackground(UIManager.getColor("Button.background"));
		btnRegistrarse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				registro(vp);
			}

		});
		btnRegistrarse.setBounds(482, 402, 105, 23);
		add(btnRegistrarse);
		
		
		JButton btnAtras = new JButton("Atras");
		btnAtras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vp.cambiarPanel(1);
			}

		});
		btnAtras.setBounds(397, 402, 65, 23);
		add(btnAtras);
	}
	
	private void registro(VistaPrincipal vp) {
		vp.cambiarPanel(1);
	}
	
	
}
