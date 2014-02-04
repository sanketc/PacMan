package gui;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import common.CommonAPIs;
import common.Globals;
import common.Globals.GameType;

import core.Agent;

@SuppressWarnings("serial")
class ClientFrame extends JFrame {

	private JPanel canvas;
	
	public ClientFrame() {
		setSize(900, 700);
		setTitle("Minimax demo");

		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});

		Container contentPane = getContentPane();
		canvas = new JPanel();
		contentPane.add(canvas, "Center");
		JPanel p = new JPanel();
		
//		addButton(p, "demo1", new ActionListener() {
//			public void actionPerformed(ActionEvent evt) {
//				Globals.mapFile = Globals.demoGameMap1;
//				Globals.gameType = GameType.MINIMAX;
//				// demo map 1
//				Agent pacman = new Agent(3, 1, 0);
//				Agent ghost1 = new Agent(8, 1, 0);
//				Board b = new Board(canvas, pacman, ghost1);
//				b.start();
//				canvas.repaint();
//			}
//		});
//
//		addButton(p, "demo2", new ActionListener() {
//			public void actionPerformed(ActionEvent evt) {
//				Globals.mapFile = Globals.demoGameMap2;
//				Globals.gameType = GameType.MINIMAX;
//				// demo map 2
//				Agent ghost1 = new Agent(8, 3, 0);
//				Agent pacman = new Agent(5, 3, 0);
//				Board b = new Board(canvas, pacman, ghost1);
//				b.start();
//				canvas.repaint();
//			}
//		});
//		
//		addButton(p, "demo3", new ActionListener() {
//			public void actionPerformed(ActionEvent evt) {
//				Globals.mapFile = Globals.demoGameMap3;
//				Globals.gameType = GameType.MINIMAX;
//				// demo map 3
//				Agent pacman = new Agent(1, 1, 0);
//				Agent ghost1 = new Agent(6, 1, 0);
//				Board b = new Board(canvas, pacman, ghost1);
//				b.start();
//				canvas.repaint();
//			}
//		});
		
//		addButton(p, "demo4", new ActionListener() {
//			public void actionPerformed(ActionEvent evt) {
//				Globals.mapFile = Globals.demoGameMap4;
//				Globals.gameType = GameType.MINIMAX;
//				// demo map 4
//				Agent pacman = new Agent(5, 3, 0);
//				Agent ghost1 = new Agent(6, 3, 0);
//				Board b = new Board(canvas, pacman, ghost1);
//				b.start();
//				canvas.repaint();
//			}
//		});
		
//		addButton(p, "demo5", new ActionListener() {
//			public void actionPerformed(ActionEvent evt) {
//				Globals.mapFile = Globals.demoGameMap5;
//				Globals.gameType = GameType.MINIMAX;
//				// demo map 5
//				Agent pacman = new Agent(2, 1, 0);
//				Agent ghost1 = new Agent(6, 1, 0);
//				Board b = new Board(canvas, pacman, ghost1);
//				b.start();
//				canvas.repaint();
//			}
//		});
//
//		addButton(p, "demo6", new ActionListener() {
//			public void actionPerformed(ActionEvent evt) {
//				Globals.mapFile = Globals.demoGameMap6;
//				Globals.gameType = GameType.MINIMAX;
//				// demo map 4
//				Agent pacman = new Agent(5, 3, 0);
//				Agent ghost1 = new Agent(7, 3, 0);
//				Board b = new Board(canvas, pacman, ghost1);
//				b.start();
//				canvas.repaint();
//			}
//		});
		
		addButton(p, "demo1 (MM)", new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				Globals.mapFile = Globals.demoGameMap8;
				Globals.gameType = GameType.MINIMAX;
				CommonAPIs.logMsg("Map = " + Globals.mapFile);
				CommonAPIs.logMsg("Game type = MINIMAX");
				Globals.badEval = false;
				// full map 2
				Agent pacman = new Agent(2, 1, 0);
				Agent ghost1 = new Agent(6, 1, 0);
				Board b = new Board(canvas, pacman, ghost1);
				b.start();
				canvas.repaint();
			}
		});
		
		addButton(p, "demo1 (AB)", new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				Globals.mapFile = Globals.demoGameMap8;
				Globals.gameType = GameType.ALPHA_BETA;
				CommonAPIs.logMsg("Map = " + Globals.mapFile);
				CommonAPIs.logMsg("Game type = ALPHA_BETA");
				Globals.badEval = false;
				// full map 2
				Agent pacman = new Agent(2, 1, 0);
				Agent ghost1 = new Agent(6, 1, 0);
				Board b = new Board(canvas, pacman, ghost1);
				b.start();
				canvas.repaint();
			}
		});
		
		addButton(p, "demo2 (MM)", new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				Globals.mapFile = Globals.demoGameMap4;
				Globals.gameType = GameType.MINIMAX;
				CommonAPIs.logMsg("Map = " + Globals.mapFile);
				CommonAPIs.logMsg("Game type = MINIMAX");
				Globals.badEval = false;
				// demo map 4
				Agent pacman = new Agent(5, 3, 0);
				Agent ghost1 = new Agent(6, 3, 0);
				Board b = new Board(canvas, pacman, ghost1);
				b.start();
				canvas.repaint();
			}
		});
		
		addButton(p, "demo2 (MM):bad eval", new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				Globals.mapFile = Globals.demoGameMap4;
				Globals.gameType = GameType.MINIMAX;
				CommonAPIs.logMsg("Map = " + Globals.mapFile);
				CommonAPIs.logMsg("Game type = MINIMAX");
				Globals.badEval = true;
				// demo map 4
				Agent pacman = new Agent(5, 3, 0);
				Agent ghost1 = new Agent(6, 3, 0);
				Board b = new Board(canvas, pacman, ghost1);
				b.start();
				canvas.repaint();
			}
		});
		
		addButton(p, "demo2 (AB)", new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				Globals.mapFile = Globals.demoGameMap4;
				Globals.gameType = GameType.ALPHA_BETA;
				CommonAPIs.logMsg("Map = " + Globals.mapFile);
				CommonAPIs.logMsg("Game type = ALPHA_BETA");
				Globals.badEval = false;
				// demo map 4
				Agent pacman = new Agent(5, 3, 0);
				Agent ghost1 = new Agent(6, 3, 0);
				Board b = new Board(canvas, pacman, ghost1);
				b.start();
				canvas.repaint();
			}
		});
		
		addButton(p, "demo3 (MM)", new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				Globals.mapFile = Globals.demoGameMap7;
				Globals.gameType = GameType.MINIMAX;
				CommonAPIs.logMsg("Map = " + Globals.mapFile);
				CommonAPIs.logMsg("Game type = MINIMAX");
				Globals.badEval = false;
				// full map 2
				Agent pacman = new Agent(5, 5, 0);
				Agent ghost1 = new Agent(6, 3, 0);
				Board b = new Board(canvas, pacman, ghost1);
				b.start();
				canvas.repaint();
			}
		});
		
		addButton(p, "demo3 (AB)", new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				Globals.mapFile = Globals.demoGameMap7;
				Globals.gameType = GameType.ALPHA_BETA;
				CommonAPIs.logMsg("Map = " + Globals.mapFile);
				CommonAPIs.logMsg("Game type = ALPHA_BETA");
				Globals.badEval = false;
				// full map 2
				Agent pacman = new Agent(5, 5, 0);
				Agent ghost1 = new Agent(6, 3, 0);
				Board b = new Board(canvas, pacman, ghost1);
				b.start();
				canvas.repaint();
			}
		});
		
		addButton(p, "Close", new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				canvas.setVisible(false);
				System.exit(0);
			}
		});
		contentPane.add(p, "South");
	}

	public void addButton(Container c, String title, ActionListener a) {
		JButton b = new JButton(title);
		c.add(b);
		b.addActionListener(a);
	}

}