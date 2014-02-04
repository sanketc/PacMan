package common;

import java.io.File;

import core.Map;

/**
 * Global constants and important flags.
 * 
 * @author Sanket Chandorkar
 */
public class Globals {
	
	/**
	 * Constants
	 * */
	
	public static enum GameType{MINIMAX, ALPHA_BETA};

	public static enum Direction{UP, DOWN, LEFT, RIGHT};
	
	public static enum MapEnum{BNK,CD0,CL0,CR0,CU0,DOT,GH1,GH2,HOR,LD0,LT0,PAC,RD0,RT0,TD0,TU0,TL0,TR0,VER}; 
	
	public static GameType gameType;
	
	/**
	 * Represents the size of the one object square in the board grid.
	 * Keep max size as 0 < x <= 70
	 */
	public static final short MAP_OBJ_SIZE = 70;	
	
	public static final short scoreBoardHeight = MAP_OBJ_SIZE;
	
	public static final short toolBarEndCorrection = 0;
	
	public static final short PAC_STEP_VALUE = -1;

	public static final short GH_STEP_VALUE = 1;
	
	public static final short DOT_SCORE_VALUE = 100;
	
	public static final short WIN_POINTS = 5000;
	
	private static Map currMap;
	
	// display delay for animation
	public static final int PAC_MAN_DELAY = 100;

	// display delay for animation
	public static final int GHOST_DELAY = 800;

	// set true to print debug values.
	public static final boolean DEBUG = true;
	
	// for testing bad effects of evaluation function
	public static boolean badEval = false;
	
	/**
	 * Images file paths
	 * */
	
	public static final String workingDir = (new File(".").getAbsolutePath());

	public static final String dataDir = workingDir + File.separator + "Data";

	public static final String configDir = workingDir + File.separator + "Config";

	public static final String logDir = workingDir + File.separator + "log";

	public static final String icoFilePath = dataDir + File.separator + "pacman.jpg";

	public static final String mainGifImageFilePath = dataDir + File.separator + "pacman.gif";

	public static String mapFile = null;	
	
	// map names
	public static final String demoGameMap1 = configDir + File.separator + "demoGameMap1.map";	
	public static final String demoGameMap2 = configDir + File.separator + "demoGameMap2.map";	
	public static final String demoGameMap3 = configDir + File.separator + "demoGameMap3.map";	
	public static final String demoGameMap4 = configDir + File.separator + "demoGameMap4.map";	
	public static final String demoGameMap5 = configDir + File.separator + "demoGameMap5.map";	
	public static final String demoGameMap6 = configDir + File.separator + "demoGameMap6.map";	
	public static final String demoGameMap7 = configDir + File.separator + "demoGameMap7.map";	
	public static final String demoGameMap8 = configDir + File.separator + "demoGameMap8.map";	

	public static final String fullGameMap1 = configDir + File.separator + "fullGameMap1.map";	
	public static final String fullGameMap2 = configDir + File.separator + "fullGameMap2.map";	

	public static final String gamePlanLogFile = logDir + File.separator + "gamePlan.log";	
	public static final String gameTreeLogFile = logDir + File.separator + "gameTree.log";	
	public static final String gameLogFile = logDir + File.separator + "gameLog.log";	
	
	
	public static final String BNK_File = dataDir + File.separator + "blank.jpg";
	public static final String CD0_File = dataDir + File.separator + "close_down.jpg";
	public static final String CL0_File = dataDir + File.separator + "close_left.jpg";
	public static final String CR0_File = dataDir + File.separator + "close_right.jpg";
	public static final String CU0_File = dataDir + File.separator + "close_up.jpg";
	public static final String DOT_File = dataDir + File.separator + "dot.jpg";
	public static final String GH1_File = dataDir + File.separator + "ghost1.jpg";
	public static final String GH2_File = dataDir + File.separator + "ghost2.jpg";
	public static final String HOR_File = dataDir + File.separator + "horizontal.jpg";
	public static final String LD0_File = dataDir + File.separator + "left_down.jpg";
	public static final String LT0_File = dataDir + File.separator + "left_top.jpg";
	public static final String PACD1_File = dataDir + File.separator + "pac_down1.jpg";
	public static final String PACD2_File = dataDir + File.separator + "pac_down2.jpg";
	public static final String PACD3_File = dataDir + File.separator + "pac_down3.jpg";
	public static final String PACL1_File = dataDir + File.separator + "pac_left1.jpg";
	public static final String PACL2_File = dataDir + File.separator + "pac_left2.jpg";
	public static final String PACL3_File = dataDir + File.separator + "pac_left3.jpg";
	public static final String PACR1_File = dataDir + File.separator + "pac_right1.jpg";
	public static final String PACR2_File = dataDir + File.separator + "pac_right2.jpg";
	public static final String PACR3_File = dataDir + File.separator + "pac_right3.jpg";
	public static final String PACU1_File = dataDir + File.separator + "pac_up1.jpg";
	public static final String PACU2_File = dataDir + File.separator + "pac_up2.jpg";
	public static final String PACU3_File = dataDir + File.separator + "pac_up3.jpg";
	public static final String RD0_File = dataDir + File.separator + "right_down.jpg";
	public static final String RT0_File = dataDir + File.separator + "right_top.jpg";
	public static final String TD0_File = dataDir + File.separator + "tri_down.jpg";
	public static final String TL0_File = dataDir + File.separator + "tri_left.jpg";
	public static final String TR0_File = dataDir + File.separator + "tri_right.jpg";
	public static final String TU0_File = dataDir + File.separator + "tri_up.jpg";
	public static final String VER_File = dataDir + File.separator + "verticle.jpg";
	
	
	public static void setMap(Map map) {
		currMap = map;		
	}
	
	public static Map getMap(){
		return currMap;
	}
}