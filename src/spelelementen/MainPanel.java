package spelelementen;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

import tools.Vector;

@SuppressWarnings("serial")
public class MainPanel extends JPanel implements MouseListener, KeyListener, ActionListener, MouseMotionListener {
	private final Level level;
	private Timer timer = new Timer((int) (MainFrame.DeltaT * 1000), this);
	private Vector muis_positie = new Vector(0, 0);
	final static String IMAGE_FOLDER = "images/";
	private Image[] afbeeldingen;
	private Image Background;

	public MainPanel(Level level) {
		this.level = level;
		setPreferredSize(new Dimension(MainFrame.BREEDTE,MainFrame.HOOGTE));
		setFocusable(true);
		addMouseListener(this);
		addKeyListener(this);
		addMouseListener(this);
		addMouseMotionListener(this);
		afbeeldingen = new Image[level.getPlaneten().length];
		setBackground(Color.BLACK);
		for (int i = 0; i< level.getPlaneten().length; i++){
			afbeeldingen[i] = new ImageIcon(getClass().getResource(IMAGE_FOLDER + i+".png")).getImage();
		}
		Background = new ImageIcon(getClass().getResource(IMAGE_FOLDER + "space_11.jpg")).getImage();
		timer.start();
	}

	public void paint(Graphics g) {
		super.paint(g);
		g.drawImage(Background, 0, 0, getWidth(), getHeight(), this);
		for (Planeet planeet : level.getPlaneten()) {
			planeet.paintme(g);
		}
		for (int i=0;i<level.getPlaneten().length;i++){
			int uitwijking = level.getPlaneten()[i].getStraal();
			int x = (int) level.getPlaneten()[i].getPlaats().getX()-uitwijking;
			int y = (int) level.getPlaneten()[i].getPlaats().getY()-uitwijking;
			g.drawImage(afbeeldingen[i], x, y, 2*uitwijking, 2*uitwijking, this);

		}
		level.getGolfbal().paintme(g);
		level.getHole().paintme(g);
		for (Satelliet satelliet : level.getSatellieten()) {
			satelliet.paintme(g);
		}
		if (level.getGolfbal().isStationary()) {
			Traject.Aim(g, level.getGolfbal(), level.getPlaneten(), muis_positie, level.getHemellichamen());
		}
		if (level.getGolfbal().outOfBounds(MainFrame.BREEDTE,MainFrame.HOOGTE)){
			OutOfBoundsBox.drawBox(g,level.getGolfbal(),MainFrame.BREEDTE, MainFrame.HOOGTE);
		}

	}

	public Level getLevel() {
		return level;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		level.turn();
		repaint();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		Bal b = level.getGolfbal();
		if (b.isStationary()) {
			b.setSnelheid(b.InitialSpeed(muis_positie));
			b.setStationary(false);
			System.out.println("GO");
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_R:
			level.ResetBall();
			// satellieten ook terug op startpositie?
			repaint();
			break;
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
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}
