package core;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import common.Globals;
import common.Globals.MapEnum;

/**
 * Stores the map of the game.
 * 
 * @author Sanket Chandorkar
 */
public class Map {
	
	private int rows;
	
	private int columns;
	
	private MapEnum map[][] = null;
	
	private int maxDotCount; 
	
	public Map(String mapFileName) {
		rows = 0;
		columns = 0;
		maxDotCount = 0;
		map = new MapEnum[100][100];	// TODO: Remove this hard coding
		BufferedReader br = null;
		String tokStr;
		try {
			br = new BufferedReader(new FileReader(mapFileName));
			String line, tokens[];
			while((line = br.readLine()) != null){
				
				line = line.replace('[', ' ');
				line = line.replace(']', ' ');
				tokens = line.split("  ");
				columns = tokens.length;
				
				for(int c = 0; c < tokens.length ; c++){
					tokStr = tokens[c].trim();
					processToken(tokStr,c);
				}
				rows++;
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(-1);
		}
		finally{
				try {
					if(br != null)
						br.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		
	}
	
	public MapEnum getMapping(int x, int y){
		return map[y][x];
	}
	
	private void processToken(String tokStr, int columnNo) {
		switch(tokStr){
			case "BNK": map[rows][columnNo] = MapEnum.BNK; break;
			case "CD0": map[rows][columnNo] = MapEnum.CD0; break;
			case "CL0": map[rows][columnNo] = MapEnum.CL0; break;
			case "CR0": map[rows][columnNo] = MapEnum.CR0; break;
			case "CU0": map[rows][columnNo] = MapEnum.CU0; break;
			case "DOT": map[rows][columnNo] = MapEnum.DOT; maxDotCount++; break;
			case "GH1": map[rows][columnNo] = MapEnum.GH1; break;
			case "GH2": map[rows][columnNo] = MapEnum.GH2; break;
			case "HOR": map[rows][columnNo] = MapEnum.HOR; break;
			case "LD0": map[rows][columnNo] = MapEnum.LD0; break;
			case "LT0": map[rows][columnNo] = MapEnum.LT0; break;
			case "PAC": map[rows][columnNo] = MapEnum.PAC; break;
			case "RD0": map[rows][columnNo] = MapEnum.RD0; break;
			case "RT0": map[rows][columnNo] = MapEnum.RT0; break;
			case "TD0": map[rows][columnNo] = MapEnum.TD0; break;
			case "TU0": map[rows][columnNo] = MapEnum.TU0; break;
			case "TL0": map[rows][columnNo] = MapEnum.TL0; break;
			case "TR0": map[rows][columnNo] = MapEnum.TR0; break;
			case "VER": map[rows][columnNo] = MapEnum.VER; break;
		}
	}
	
	public boolean isWall(int x, int y){
		if(map[y][x] == MapEnum.CD0) return true;
		if(map[y][x] == MapEnum.CU0) return true;
		if(map[y][x] == MapEnum.CL0) return true;
		if(map[y][x] == MapEnum.CR0) return true;
		if(map[y][x] == MapEnum.TU0) return true;
		if(map[y][x] == MapEnum.TD0) return true;
		if(map[y][x] == MapEnum.TL0) return true;
		if(map[y][x] == MapEnum.TR0) return true;
		if(map[y][x] == MapEnum.HOR) return true;
		if(map[y][x] == MapEnum.VER) return true;
		if(map[y][x] == MapEnum.LD0) return true;
		if(map[y][x] == MapEnum.LT0) return true;
		if(map[y][x] == MapEnum.RD0) return true;
		if(map[y][x] == MapEnum.RT0) return true;
		return false;
	}

	public int getRows() {
		return rows;
	}

	public int getColumns() {
		return columns;
	}

	public int getWidth() {
		return columns * Globals.MAP_OBJ_SIZE;
	}

	int getMaxDotCount() {
		return maxDotCount;
	}

	public int getHeight() {
		return rows * Globals.MAP_OBJ_SIZE + Globals.scoreBoardHeight + Globals.toolBarEndCorrection;
	}
	
	public ArrayList<Pair> getLegalMoves(Agent agent){
		ArrayList<Pair> moves= new ArrayList<>(4);
		if(!isWall(agent.getxCordinate(), agent.getyCordinate() + 1))  // down
			moves.add(new Pair(agent.getxCordinate(),agent.getyCordinate() + 1));
		if(!isWall(agent.getxCordinate(), agent.getyCordinate() - 1))  // up
			moves.add(new Pair(agent.getxCordinate(),agent.getyCordinate() - 1));
		if(!isWall(agent.getxCordinate() + 1, agent.getyCordinate()))  // right
			moves.add(new Pair(agent.getxCordinate() + 1,agent.getyCordinate()));
		if(!isWall(agent.getxCordinate() - 1, agent.getyCordinate()))  // left
			moves.add(new Pair(agent.getxCordinate() - 1,agent.getyCordinate()));
		return moves;
	}
	
	public ArrayList<Pair> getLegalMoves(Pair agent){
		ArrayList<Pair> moves= new ArrayList<>(4);
		if(!isWall(agent.getxCordinate(), agent.getyCordinate() + 1))  // down
			moves.add(new Pair(agent.getxCordinate(),agent.getyCordinate() + 1));
		if(!isWall(agent.getxCordinate(), agent.getyCordinate() - 1))  // up
			moves.add(new Pair(agent.getxCordinate(),agent.getyCordinate() - 1));
		if(!isWall(agent.getxCordinate() + 1, agent.getyCordinate()))  // right
			moves.add(new Pair(agent.getxCordinate() + 1,agent.getyCordinate()));
		if(!isWall(agent.getxCordinate() - 1, agent.getyCordinate()))  // left
			moves.add(new Pair(agent.getxCordinate() - 1,agent.getyCordinate()));
		return moves;
	}
	public boolean hasDot(int x, int y){
		if(map[y][x] == MapEnum.DOT)
			return true;
		return false;
	}
}
