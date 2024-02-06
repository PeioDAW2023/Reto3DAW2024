package paneles;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import vista.VistaPrincipal;

public class PanelBienvenida extends JPanel{

	private static final long serialVersionUID = 1L;
	


	public PanelBienvenida(VistaPrincipal vp) {
        
		setSize(vp.getSize());
		setLayout(null);
		
		setBackground(Color.GRAY);
		
     
        
        
        JLabel lblBienvenido = new JLabel("CINE SEVENTH");
        lblBienvenido.setBackground(Color.WHITE);
        lblBienvenido.setForeground(Color.BLUE);
        lblBienvenido.setFont(new Font("Bell MT", Font.ITALIC, 60));
        lblBienvenido.setHorizontalAlignment(SwingConstants.CENTER);
        lblBienvenido.setBounds(115, 110, 545, 205);
        add(lblBienvenido);
        
		addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				long tiempoDeActivacion = System.currentTimeMillis() + 350;
	               while (System.currentTimeMillis() < tiempoDeActivacion) {}
	               vp.cambiarPanel(1);//hacer algo tras 3000 milisegundos

			}
		});
	}
}