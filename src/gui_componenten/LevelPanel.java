package gui_componenten;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.Timer;

import tools.Vector;
import spelelementen.Bal;
import spelelementen.Level;
import spelelementen.OutOfBoundsBox;
import spelelementen.Planeet;
import spelelementen.Satelliet;
import spelelementen.Traject;

@SuppressWarnings("serial")
public class LevelPanel extends JPanel implements MouseListener, ActionListener, MouseMotionListener {
	private Level level;
	private Timer timer = new Timer((int) (GameMain.DeltaT * 1000), this);
	private Vector muis_positie = new Vector(0, 0);
	final static String IMAGE_FOLDER = "images/";
	private Image[] planeet_afbeeldingen;
	private Image[] satelliet_afbeeldingen;
	private Image Background;

	public final JTextArea information = new JTextArea(1, 1);
	public final JButton Next = new JButton("Next");

	public LevelPanel(Level level) {
		this.level = level;
		setPreferredSize(new Dimension(GameMain.BREEDTE, GameMain.HOOGTE));
		setFocusable(true);
		addMouseListener(this);
		addMouseMotionListener(this);
		planeet_afbeeldingen = new Image[level.getPlaneten().length];
		satelliet_afbeeldingen = new Image[level.getSatellieten().length];
		setBackground(Color.BLACK);
		Background = new ImageIcon(getClass().getResource(IMAGE_FOLDER + "space_11.jpg")).getImage();
		timer.start();
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		g.drawImage(Background, 0, 0, getWidth(), getHeight(), this);
		
		for(Planeet planeet : level.getPlaneten())
			planeet.drawme(g, this);
		level.getHole().drawme(g, this);
		
		for(Satelliet satelliet : level.getSatellieten())
			satelliet.drawme(g, this);
		
		if (!level.getHole().getScored()) {
			if (level.getGolfbal().outOfBounds(GameMain.BREEDTE, GameMain.HOOGTE)) {
				OutOfBoundsBox.drawBox(g, level.getGolfbal(), GameMain.BREEDTE, GameMain.HOOGTE);
			}
			level.getGolfbal().drawme(g, this);
			if (level.getGolfbal().isStationary()) {
				Traject.Aim(g, level.getGolfbal(), level.getPlaneten(), muis_positie, level.getHemellichamen());
			}
		}
	}

	public Level getLevel() {
		return level;
	}

	public void setLevel(Level level) {
		this.level = level;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		level.turn();
		repaint();
		information.setText(
				"Strokes: " + level.getNr_strokes() + ", Par: " + (level.getPar() > 0 ? level.getPar() : "N/A"));
		if (level.getHole().getScored())
			Next.setVisible(true);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		Bal b = level.getGolfbal();
		if (!level.getHole().getScored()) {
			if (b.isStationary()) {
				b.setSnelheid(b.InitialSpeed(muis_positie));
				b.setStationary(false);
				level.incrementStrokes();
			}
		}

	}

	@Override
	public void mouseMoved(MouseEvent e) {
		muis_positie = new Vector(e.getX(), e.getY());
		repaint();

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}
