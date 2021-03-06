package gui_componenten;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import tools.LevelQueue;

public class MainMenuPanel extends JPanel implements ActionListener {
	private static final long serialVersionUID = -3801890339594484316L;
	private final JButton Play = new JButton("Play");
	private final JButton PlayLevel = new JButton("Level");
	private final JButton PlayRandom = new JButton("Random Level");
	private GameMain window;

	public MainMenuPanel(GameMain window) {
		this.window = window;
		Play.addActionListener(this);
		PlayLevel.addActionListener(this);
		PlayRandom.addActionListener(this);
		add(Play);
		add(PlayLevel);
		add(PlayRandom);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		//Play = begin vanaf level 1; PlayLevel = kies een level; Playrandom = geeft random level
		if (e.getSource() == Play) {
			GameMain.totalstrokes = 0;
			PlayPanel p = new PlayPanel(window,new LevelQueue(LevelQueue.REALGAME));
			window.switchPanel(p);
		} else if (e.getSource() == PlayLevel) {
			GameMain.totalstrokes = -1;
			LevelMenuPanel p = new LevelMenuPanel(window);
			window.switchPanel(p);
		} else if (e.getSource() == PlayRandom) {
			GameMain.totalstrokes = -1;
			PlayPanel p = new PlayPanel(window,new LevelQueue(LevelQueue.RANDOMLEVEL));
			window.switchPanel(p);
		}
	}

}
