package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import common.CommonAPIs;
import common.Globals;
import common.Globals.Direction;
import common.Globals.GameType;
import common.Globals.MapEnum;

import core.Agent;
import core.GameEngine;
import core.Map;


public class Board extends Thread {
	
	private Image BNK_image, CD0_image, CL0_image, CR0_image, CU0_image,
	DOT_image, GH1_image, GH2_image, HOR_image, LD0_image, LT0_image,
	RD0_image, RT0_image, TD0_image, TU0_image, TL0_image, TR0_image,
	VER_image, PACL1_image, PACL2_image, PACL3_image, PACR1_image,
	PACR2_image, PACR3_image, PACU1_image, PACU2_image, PACU3_image,
	PACD1_image, PACD2_image, PACD3_image;
	
	private JPanel board;

	Agent pacman, ghost1;
	
	public Board(JPanel b, Agent _pacman, Agent _ghost1) {
		pacman = _pacman;
		ghost1 = _ghost1;
		board = b;
	}

	public void run() {
		
		Map map = new Map(Globals.mapFile);
    	Globals.setMap(map);
    	
    	core.State startState = new core.State(pacman, ghost1);
    	
    	GameEngine eng = new GameEngine(startState);
    	eng.populateGameTree();
    	
    	ArrayList<core.State> gamePlan = null;
    	if(Globals.gameType == GameType.MINIMAX)
    		gamePlan = eng.solveMinimax();
    	else // for alpha beta
    		gamePlan = eng.solveAlphaBeta();
    	
    	Globals.badEval = false;
    	CommonAPIs.logMsg("Nodes traversed = " + eng.getStateVistCount());
    	CommonAPIs.logMsg("");
    	CommonAPIs.dumpGamePlan(gamePlan);
		
    	loadImages();
    	
    	Graphics g = board.getGraphics();
		playGame(g, gamePlan);
    	g.dispose();
	}
	
    public void playGame(Graphics g,ArrayList<core.State> gamePlan) {
		Graphics2D g2d = (Graphics2D) g;
		showMaze(g2d);
		try {
			sleep(4000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
//		showScoreBoard(g2d); 

		/* Display the gamePlan */
		if(gamePlan == null)
			return;
		
		core.State prevState = null;
		Direction dir = Direction.RIGHT;
		Agent currAgent = null, prevAgent = null;
		for(int i = gamePlan.size() - 1; i >= 0 ; i--){
			core.State currState = gamePlan.get(i);
			if(prevState == null){
				prevState = currState;
				continue;
			}
			dir = CommonAPIs.findDirection(prevState, currState);
			currAgent = CommonAPIs.findMovingAgent(prevState, currState);
			prevAgent = prevState.getTurn();
			
			try {
				displayAgent(g2d,currAgent, currState,prevAgent ,dir);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			prevState = currState;
		}
//		JOptionPane.showMessageDialog(null, "Simulation Complete","INFORMATION_MESSAGE", 0);
			
	}

	
	private void displayAgent(Graphics2D g2d, Agent currAgent, core.State currState, Agent prevAgent, Direction dir) throws InterruptedException {
		int y_correction = Globals.toolBarEndCorrection + Globals.scoreBoardHeight + 1;
		int prevNewx = prevAgent.getxCordinate() * Globals.MAP_OBJ_SIZE;
		int prevNewy = prevAgent.getyCordinate() * Globals.MAP_OBJ_SIZE + y_correction;
		
		/* Display previous location blank  */
		if(currState.hasDot(prevAgent.getxCordinate(), prevAgent.getyCordinate()) )
			g2d.drawImage(DOT_image,prevNewx, prevNewy, Globals.MAP_OBJ_SIZE, Globals.MAP_OBJ_SIZE, Color.black, null);
		else
			g2d.drawImage(BNK_image,prevNewx, prevNewy, Globals.MAP_OBJ_SIZE, Globals.MAP_OBJ_SIZE, Color.black, null);
		
		/* Display new location  */
		int newx = currAgent.getxCordinate() * Globals.MAP_OBJ_SIZE;
		int newy = currAgent.getyCordinate() * Globals.MAP_OBJ_SIZE + y_correction;
		if(currAgent == currState.getPacman()){
			if(dir == Direction.RIGHT){
				g2d.drawImage(PACR3_image, newx, newy, Globals.MAP_OBJ_SIZE, Globals.MAP_OBJ_SIZE, Color.black, null);
				sleep(Globals.PAC_MAN_DELAY);
				g2d.drawImage(PACR2_image, newx, newy, Globals.MAP_OBJ_SIZE, Globals.MAP_OBJ_SIZE, Color.black, null);
				sleep(Globals.PAC_MAN_DELAY);
				g2d.drawImage(PACR1_image, newx, newy, Globals.MAP_OBJ_SIZE, Globals.MAP_OBJ_SIZE, Color.black, null);
				sleep(Globals.PAC_MAN_DELAY);
				g2d.drawImage(PACR2_image, newx, newy, Globals.MAP_OBJ_SIZE, Globals.MAP_OBJ_SIZE, Color.black, null);
				sleep(Globals.PAC_MAN_DELAY);
				g2d.drawImage(PACR3_image, newx, newy, Globals.MAP_OBJ_SIZE, Globals.MAP_OBJ_SIZE, Color.black, null);
				sleep(Globals.PAC_MAN_DELAY);
			}
			if(dir == Direction.LEFT){
				g2d.drawImage(PACL3_image, newx, newy, Globals.MAP_OBJ_SIZE, Globals.MAP_OBJ_SIZE, Color.black, null);
				sleep(Globals.PAC_MAN_DELAY);
				g2d.drawImage(PACL2_image, newx, newy, Globals.MAP_OBJ_SIZE, Globals.MAP_OBJ_SIZE, Color.black, null);
				sleep(Globals.PAC_MAN_DELAY);
				g2d.drawImage(PACL1_image, newx, newy, Globals.MAP_OBJ_SIZE, Globals.MAP_OBJ_SIZE, Color.black, null);
				sleep(Globals.PAC_MAN_DELAY);
				g2d.drawImage(PACL2_image, newx, newy, Globals.MAP_OBJ_SIZE, Globals.MAP_OBJ_SIZE, Color.black, null);
				sleep(Globals.PAC_MAN_DELAY);
				g2d.drawImage(PACL3_image, newx, newy, Globals.MAP_OBJ_SIZE, Globals.MAP_OBJ_SIZE, Color.black, null);
				sleep(Globals.PAC_MAN_DELAY);
			}
			if(dir == Direction.UP){
				g2d.drawImage(PACU3_image, newx, newy, Globals.MAP_OBJ_SIZE, Globals.MAP_OBJ_SIZE, Color.black, null);
				sleep(Globals.PAC_MAN_DELAY);
				g2d.drawImage(PACU2_image, newx, newy, Globals.MAP_OBJ_SIZE, Globals.MAP_OBJ_SIZE, Color.black, null);
				sleep(Globals.PAC_MAN_DELAY);
				g2d.drawImage(PACU1_image, newx, newy, Globals.MAP_OBJ_SIZE, Globals.MAP_OBJ_SIZE, Color.black, null);
				sleep(Globals.PAC_MAN_DELAY);
				g2d.drawImage(PACU2_image, newx, newy, Globals.MAP_OBJ_SIZE, Globals.MAP_OBJ_SIZE, Color.black, null);
				sleep(Globals.PAC_MAN_DELAY);
				g2d.drawImage(PACU3_image, newx, newy, Globals.MAP_OBJ_SIZE, Globals.MAP_OBJ_SIZE, Color.black, null);
				sleep(Globals.PAC_MAN_DELAY);
			}
			if(dir == Direction.DOWN){
				g2d.drawImage(PACD3_image, newx, newy, Globals.MAP_OBJ_SIZE, Globals.MAP_OBJ_SIZE, Color.black, null);
				sleep(Globals.PAC_MAN_DELAY);
				g2d.drawImage(PACD2_image, newx, newy, Globals.MAP_OBJ_SIZE, Globals.MAP_OBJ_SIZE, Color.black, null);
				sleep(Globals.PAC_MAN_DELAY);
				g2d.drawImage(PACD1_image, newx, newy, Globals.MAP_OBJ_SIZE, Globals.MAP_OBJ_SIZE, Color.black, null);
				sleep(Globals.PAC_MAN_DELAY);
				g2d.drawImage(PACD2_image, newx, newy, Globals.MAP_OBJ_SIZE, Globals.MAP_OBJ_SIZE, Color.black, null);
				sleep(Globals.PAC_MAN_DELAY);
				g2d.drawImage(PACD3_image, newx, newy, Globals.MAP_OBJ_SIZE, Globals.MAP_OBJ_SIZE, Color.black, null);
				sleep(Globals.PAC_MAN_DELAY);
			}
			
		}
		else if(currAgent == currState.getGhost1()){
			g2d.drawImage(GH1_image, newx, newy, Globals.MAP_OBJ_SIZE, Globals.MAP_OBJ_SIZE, Color.black, null);
			sleep(Globals.GHOST_DELAY);
		}
	}

	private void showScoreBoard(Graphics2D g2d) {
		g2d.setColor(Color.GREEN);
		g2d.setFont(new Font(Font.MONOSPACED, Font.BOLD, Globals.MAP_OBJ_SIZE /* Size */));
		g2d.drawString("Score: ", 1, Globals.MAP_OBJ_SIZE + 5);
	}

	private void showMaze(Graphics2D g2d) {
		Image img;
		int y_correction = Globals.toolBarEndCorrection + Globals.scoreBoardHeight + 1;
		
		for(int r = 0, x = 1 ; r < Globals.getMap().getColumns(); r++, x = x + Globals.MAP_OBJ_SIZE ){
			for(int c = 0, y = 1 ; c < Globals.getMap().getRows(); c++, y = y + Globals.MAP_OBJ_SIZE){
				img = getImage(r,c);
				g2d.drawImage(img, x, y + y_correction, Globals.MAP_OBJ_SIZE, Globals.MAP_OBJ_SIZE, Color.black, null);
			}
		}
	}

	private Image getImage(int x, int y){
		MapEnum value = Globals.getMap().getMapping(x, y);
		if( value == MapEnum.BNK )   return BNK_image;
		if( value == MapEnum.CD0 )   return CD0_image;
		if( value == MapEnum.CL0 )   return CL0_image;
		if( value == MapEnum.CR0 )   return CR0_image;
		if( value == MapEnum.CU0 )   return CU0_image;
		if( value == MapEnum.DOT )   return DOT_image;
		if( value == MapEnum.GH1 )   return GH1_image;
		if( value == MapEnum.GH2 )   return GH2_image;
		if( value == MapEnum.HOR )   return HOR_image;
		if( value == MapEnum.LD0 )   return LD0_image;
		if( value == MapEnum.LT0 )   return LT0_image;
		if( value == MapEnum.RD0 )   return RD0_image;
		if( value == MapEnum.RT0 )   return RT0_image;
		if( value == MapEnum.TD0 )   return TD0_image;
		if( value == MapEnum.TU0 )   return TU0_image;
		if( value == MapEnum.TL0 )   return TL0_image;
		if( value == MapEnum.TR0 )   return TR0_image;
		if( value == MapEnum.VER )   return VER_image;
		if( value == MapEnum.PAC )   return PACR1_image;
		return BNK_image;
	}
	

	private void loadImages(){
		BNK_image = new ImageIcon(Globals.BNK_File).getImage();
		CD0_image = new ImageIcon(Globals.CD0_File).getImage();
		CL0_image = new ImageIcon(Globals.CL0_File).getImage();
		CR0_image = new ImageIcon(Globals.CR0_File).getImage();
		CU0_image = new ImageIcon(Globals.CU0_File).getImage();
		DOT_image = new ImageIcon(Globals.DOT_File).getImage();
		GH1_image = new ImageIcon(Globals.GH1_File).getImage();
		GH2_image = new ImageIcon(Globals.GH2_File).getImage();
		HOR_image = new ImageIcon(Globals.HOR_File).getImage();
		LD0_image = new ImageIcon(Globals.LD0_File).getImage();
		LT0_image = new ImageIcon(Globals.LT0_File).getImage();
		RD0_image = new ImageIcon(Globals.RD0_File).getImage();
		RT0_image = new ImageIcon(Globals.RT0_File).getImage();
		TD0_image = new ImageIcon(Globals.TD0_File).getImage();
		TU0_image = new ImageIcon(Globals.TU0_File).getImage();
		TL0_image = new ImageIcon(Globals.TL0_File).getImage();
		TR0_image = new ImageIcon(Globals.TR0_File).getImage();
		VER_image = new ImageIcon(Globals.VER_File).getImage();
		PACL1_image = new ImageIcon(Globals.PACL1_File).getImage();
		PACL2_image = new ImageIcon(Globals.PACL2_File).getImage();
		PACL3_image = new ImageIcon(Globals.PACL3_File).getImage();
		PACR1_image = new ImageIcon(Globals.PACR1_File).getImage();
		PACR2_image = new ImageIcon(Globals.PACR2_File).getImage();
		PACR3_image = new ImageIcon(Globals.PACR3_File).getImage();
		PACU1_image = new ImageIcon(Globals.PACU1_File).getImage();
		PACU2_image = new ImageIcon(Globals.PACU2_File).getImage();
		PACU3_image = new ImageIcon(Globals.PACU3_File).getImage();
		PACD1_image = new ImageIcon(Globals.PACD1_File).getImage();
		PACD2_image = new ImageIcon(Globals.PACD2_File).getImage();
		PACD3_image = new ImageIcon(Globals.PACD3_File).getImage();
	}
	
}
