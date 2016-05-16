package gui_componenten;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class GameMain extends JPanel {
	public final static double G = 2500; // Gravitatieconstante
	public final static double WR = 0.45; // Wrijvingscoefficient
	public final static double COR = 0.55; // Coefficient of Restitution
	public final static double DeltaT = 0.01;
	public final static double MOUSE_SPEED_COEFFICIENT = 2.5;
	public final static double MINIMAL_AVERAGE_MOVEMENT = 3.0;
	public final static double MAX_POWER = 400;
	public final static double AIM_TIME = 1;
	public final static int BREEDTE = 1300, HOOGTE = 730;

	private final static JFrame frame = new JFrame("Space-Golf");
	private JPanel activePanel;

	public static void main(String args[]) {
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setFocusable(true);
		frame.setContentPane(new GameMain());
		frame.pack();
		frame.setVisible(true);
	}

	public GameMain() {
		// overgenomen van ProjectTemplate <-- Beerend
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS)); // is nodig zodat paneel heel de ruimte bedekt
		activePanel = new MenuPanel(this); // het eerste paneel dat getoond wordt
		add(activePanel);
		setPreferredSize(activePanel.getPreferredSize());
	}

	public void switchPanel(JPanel toActivate) {
		// overgenomen van ProjectTemplate <-- Beerend
		remove(activePanel);
		activePanel = toActivate;
		add(activePanel);
		setPreferredSize(activePanel.getPreferredSize());
		validate();
		frame.pack();
		repaint(); // we laten het geheel hertekenen
	}

	public void switchPanel() {
		// overgenomen van ProjectTemplate <-- Beerend
		switchPanel(new MenuPanel(this)); // terug naar het begin
	}
}