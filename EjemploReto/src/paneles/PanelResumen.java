package paneles;

import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import vista.VistaPrincipal;

public class PanelResumen extends JPanel{
	private static final long serialVersionUID = 1L;

	
	public PanelResumen() {}
	
	public PanelResumen(VistaPrincipal vp) {
		setSize(vp.getSize());
		setLayout(null);
		
		JLabel lblCineElorrieta = new JLabel("Crear Cuenta");
		lblCineElorrieta.setHorizontalAlignment(SwingConstants.CENTER);
		lblCineElorrieta.setFont(new Font("Bell MT", Font.BOLD | Font.ITALIC, 45));
		lblCineElorrieta.setBounds(10, 122, 780, 73);
		add(lblCineElorrieta);
		
	}
	
	
	
}
