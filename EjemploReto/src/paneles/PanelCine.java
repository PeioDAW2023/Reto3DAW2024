package paneles;

import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;

import vista.VistaPrincipal;

public class PanelCine extends JPanel {
	private static final long serialVersionUID = 1L;

	public PanelCine() {
		setLayout(null);
	}

	
	
	public PanelCine(VistaPrincipal vp) {
		setSize(vp.getSize());
		vp.setIconImage(
				Toolkit.getDefaultToolkit().getImage(VistaPrincipal.class.getResource("/multimedia/seventh.jpg")));
		setLayout(null);
		
		
		JLabel lblCines = new JLabel("Elige un cine:");
		lblCines.setFont(new Font("Bell MT", Font.BOLD | Font.ITALIC, 45));
		lblCines.setBounds(275, 125, 780, 75);
		add(lblCines);
		
		JComboBox<String> comBoxCines = new JComboBox<String>();
		comBoxCines.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
			
		});
		comBoxCines.setBounds(250, 300, 300, 30);
		add(comBoxCines);
		
		 

		JButton btnSiguiente = new JButton("Siguiente");
		btnSiguiente.setBackground(UIManager.getColor("Button.background"));
		btnSiguiente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getSource()==comBoxCines) {
		           //String seleccionado=(String)comBoxCines.getSelectedItem();
		        }
				vp.cambiarPanel(4);
			}

		});
		btnSiguiente.setBounds(480, 400, 105, 25);
		add(btnSiguiente);
	}
}
