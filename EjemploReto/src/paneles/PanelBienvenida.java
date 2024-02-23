package paneles;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import vista.VistaPrincipal;

public class PanelBienvenida extends JPanel {

	private static final long serialVersionUID = 1L;

	public PanelBienvenida(VistaPrincipal vp) {

		setSize(vp.getSize());
		setLayout(null);

		JLabel lblFondoCine = new JLabel("");
		lblFondoCine.setBounds(0, 0, 800, 600);

		add(lblFondoCine);

		JLabel lblBienvenido = new JLabel("CINES SEVENTH");
		lblBienvenido.setForeground(Color.WHITE);
		lblBienvenido.setFont(new Font("Bell MT", Font.ITALIC, 60));
		lblBienvenido.setHorizontalAlignment(SwingConstants.CENTER);
		lblBienvenido.setBounds(115, 150, 545, 205);
		add(lblBienvenido);
		
		ImageIcon foto = new ImageIcon(new ImageIcon(VistaPrincipal.class.getResource("/multimedia/fotoBienvenida.jpg")).getImage()
				.getScaledInstance(vp.getWidth(), vp.getHeight(), Image.SCALE_DEFAULT));
		
		JLabel fondoCine = new JLabel(foto);
		fondoCine.setBounds(0, 0, vp.getWidth(), vp.getHeight());
		add(fondoCine);

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
				while (System.currentTimeMillis() < tiempoDeActivacion) {
				}
				vp.cambiarPanel(1);// hacer algo tras 3000 milisegundos

			}
		});
	}
}