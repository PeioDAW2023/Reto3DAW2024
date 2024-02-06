package paneles;

import javax.swing.JPanel;

import vista.VistaPrincipal;

public class PanelResumen extends JPanel{
	private static final long serialVersionUID = 1L;

	
	public PanelResumen() {}
	
	public PanelResumen(VistaPrincipal vp) {
		setSize(vp.getSize());
		setLayout(null);
	}
	
}
