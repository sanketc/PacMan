package core;

import java.util.ArrayList;
import java.util.HashSet;

import common.Globals;

/**
 * Stores state related information information.
 * 
 * @author Sanket Chandorkar
 */
public class State {
	
	private Agent pacman;

	private Agent ghost1;
	
	private Agent turn;

	private HashSet<String> dotLoc;
	
	private boolean winState = false;

	private boolean looseState = false;

	private boolean loopState = false;

	private int value = 0;
	
	private ArrayList<State> successors = null;
	
//	private int alpha = Integer.MIN_VALUE;
//	
//	private int beta = Integer.MAX_VALUE;
	
	/**
	 * Constructor for initial state
	 */
	public State(Agent pac, Agent gh1){
		pacman = pac;
		ghost1 = gh1;
		turn = pacman;
		dotLoc = new HashSet<>();

		// populate dots from map
		Map map = Globals.getMap();
		for(int x = 0; x < map.getColumns() ; x++){
			for(int y = 0; y < map.getRows(); y++){
				if(map.hasDot(x, y))
					dotLoc.add(getCode(x, y));
			}
		}
		
		processTerminalState();
	}
	
	public static ArrayList<State> findSuccessorState(State currState){
		ArrayList<State> stateList = new ArrayList<State>(4);
		Map map = Globals.getMap();
		
		for(Pair move: map.getLegalMoves(currState.getTurn())){
			stateList.add(new State(currState, move));
		}
		return stateList;
	}
	
	/**
	 * Constructor for successive state
	 * @param s current state
	 * @param move next move for which the state is to be made
	 */
	public State(State currState, Pair move) {
		
		Agent currTurn = currState.getTurn();
		Agent updatedAgent = new Agent(move.getxCordinate(), move.getyCordinate(), currTurn.getStepCount() + 1);

		/* update turn and agents */
		if(currTurn == currState.getPacman()){
			pacman = updatedAgent;
			ghost1 = currState.getGhost1();
			turn = ghost1;
		}
		else{
			ghost1 = updatedAgent;
			pacman = currState.getPacman();
			turn = pacman;
		}
		
		/* update dot count */
		dotLoc = new HashSet<>(currState.getDotLoc());
		
		// remove dot if PAC Man
		if(Globals.getMap().hasDot(pacman.getxCordinate(), pacman.getyCordinate())){
			dotLoc.remove(getCode(move.getxCordinate(), move.getyCordinate()));
		}
		
		processTerminalState();
	}

	private void processTerminalState() {
		// is game won
		if( dotLoc.size() == 0){
			winState = true;
			value = Globals.PAC_STEP_VALUE * pacman.getStepCount() +
					Globals.DOT_SCORE_VALUE * Globals.getMap().getMaxDotCount() +
					Globals.WIN_POINTS +
					calculateSLD();
		}
		
		// is game lost
		if(pacman.getxCordinate() == ghost1.getxCordinate() && 
				pacman.getyCordinate() == ghost1.getyCordinate()){
			looseState = true;
			value = Globals.PAC_STEP_VALUE * pacman.getStepCount() +
					Globals.DOT_SCORE_VALUE * (Globals.getMap().getMaxDotCount() - dotLoc.size()) -
					Globals.WIN_POINTS;
					
		}
	}
	
	private int calculateSLD(){
//		System.out.println(ghost1.getxCordinate() + " | " + ghost1.getyCordinate());
//		System.out.println(pacman.getxCordinate() + " | " + pacman.getyCordinate());
//		System.out.println(findMinPath(new Pair(ghost1.getxCordinate(), ghost1.getyCordinate()), new HashSet<String>()));
		if(Globals.badEval)
			return 0;
		else
			return findMinPath(new Pair(ghost1.getxCordinate(), ghost1.getyCordinate()), new HashSet<String>());
	}
	
	private Integer findMinPath(Pair move, HashSet<String> oldVisited){
		// breaking condition
		if(move.getxCordinate() == pacman.getxCordinate() && move.getyCordinate() == pacman.getyCordinate())
			return 0;
		if(oldVisited.contains(getCode(move.getxCordinate(), move.getyCordinate())))
			return null;
		
		HashSet<String> newVisited = new HashSet<>();
		newVisited.addAll(oldVisited);
		newVisited.add(getCode(move.getxCordinate(), move.getyCordinate()));
		
		// bifurccate
		Integer minVal = null;
		for(Pair newMove: Globals.getMap().getLegalMoves(move)){
			Integer value = findMinPath(newMove, newVisited);
			if(value == null)
				continue;
			if(minVal == null){
				minVal = value;
				continue;
			}
			if (minVal > value)
				minVal = value;
		}
		if(minVal == null)
			return null;
		return minVal + 1;
	}

	private String getCode(int x, int y){
		return "x" + x + "y" + y;
	}

	public Agent getPacman() {
		return pacman;
	}
	
	public Agent getGhost1() {
		return ghost1;
	}
	
	public Agent getTurn() {
		return turn;
	}
	
	public HashSet<String> getDotLoc() {
		return dotLoc;
	}
	
	public boolean hasDot(int x, int y) {
		return dotLoc.contains(getCode(x, y));
	}
	
	public boolean isLoopState() {
		return loopState;
	}
	
	public void setLoopState(boolean value) {
		loopState = value;
	}
	
	public boolean isTerminalState() {
		return winState || looseState || loopState;
	}
	
	public boolean isGameWinState() {
		return winState;
	}
	
	public void setGameWinState(boolean value) {
		winState = value;
	}
	
	public ArrayList<State> getSuccessorStates(){
		return successors;
	}
	
	public void setSuccessorStates(ArrayList<State> list){
		successors = list;
	}

	public boolean isGameLoseState() {
		return looseState;
	}

	public void setGameLoseState(boolean looseState) {
		this.looseState = looseState;
	}
	
	public int getStateValue() {
		return value;
	}

	public void setStateValue(int value) {
		this.value = value;
	}

//	public int getAlpha() {
//		return alpha;
//	}
//
//	public void setAlpha(int alpha) {
//		this.alpha = alpha;
//	}
//
//	public int getBeta() {
//		return beta;
//	}
//
//	public void setBeta(int beta) {
//		this.beta = beta;
//	}

}