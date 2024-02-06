package paneles;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import vista.VistaPrincipal;

public class PanelPeliculas extends JPanel{
	private static final long serialVersionUID = 1L;

	public PanelPeliculas() {
		
	}
	
	public PanelPeliculas (VistaPrincipal vp) {
		
		setSize(vp.getSize());
		vp.setIconImage(Toolkit.getDefaultToolkit().getImage(VistaPrincipal.class.getResource("/multimedia/seventh.jpg")));
		setLayout(null);
		
		JButton btnResumen = new JButton("Resumen");
		btnResumen.setBackground(UIManager.getColor("Button.background"));
		btnResumen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vp.cambiarPanel(4);
			}

		});
		btnResumen.setBounds(482, 402, 105, 23);
		add(btnResumen);
		
		
	}
	
}
