package common;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

import common.Globals.Direction;

import core.Agent;
import core.State;

/**
 * Contains Common APIs.
 * 
 * @author Sanket Chandorkar
 */
public class CommonAPIs {

	public static void logMsg(String msg){
		PrintWriter pw  = null;
		try {
			pw = new PrintWriter(new BufferedWriter(new FileWriter(Globals.gameLogFile,true/*append*/)));
			pw.println("LOG : " + msg);			
		} catch (IOException e) {
			System.err.println("Unable to open log file: " + Globals.gameLogFile);
		}
		finally{
				try {
					if(pw != null)
						pw.close();
				} catch (Exception e) {
					System.err.println("Unable to close log file");
				}
		}
	}
	
	public static Direction findDirection(State prevState, State nextState){

		Direction dir = Direction.RIGHT;
		Agent prev = prevState.getTurn();
		Agent next;
		
		if(prev == prevState.getPacman())
			next = nextState.getPacman();
		else
			next = nextState.getGhost1();
		
		if(next.getxCordinate() > prev.getxCordinate())
			dir = Direction.RIGHT;
		else if(next.getxCordinate() < prev.getxCordinate())
			dir = Direction.LEFT;
		else if(next.getyCordinate() < prev.getyCordinate())
			dir = Direction.UP;
		else
			dir = Direction.DOWN;
		
		return dir;
	}
	
	public static Agent findMovingAgent(State prevState, State nextState){
		Agent prev = prevState.getTurn();
		if(prev == prevState.getPacman())
			return nextState.getPacman();
		else
			return nextState.getGhost1();
	}
	
	public static void dumpGamePlan(ArrayList<State> list){
		PrintWriter pw  = null;
		try {
			pw = new PrintWriter(new BufferedWriter(new FileWriter(Globals.gamePlanLogFile,false/*append*/)));
			for(int i = list.size() - 1; i >= 0 ; i--){
				State s = list.get(i);
				pw.println(" ******************************** ");
				pw.println("PAC     " + s.getPacman().getxCordinate() + "\t" + s.getPacman().getyCordinate());
				pw.println("GH1     " + s.getGhost1().getxCordinate() + "\t" + s.getGhost1().getyCordinate());
				pw.println("isloop  " + s.isLoopState());
				pw.println("isWin   " + s.isGameWinState());
				pw.println("isLoose " + s.isGameLoseState());
				pw.println("value   " + s.getStateValue());
				pw.println();
			}
		} catch (IOException e) {
			System.err.println("Unable to open log file");
		}
		finally{
				try {
					if(pw != null)
						pw.close();
				} catch (Exception e) {
					System.err.println("Unable to close log file");
				}
		}
		
	}
	
	public static void dumpGameTree(State state){
		Queue<State> qList = new LinkedList<State>();
		PrintWriter pw  = null;
		int StateCount = 0;
		try {
			pw = new PrintWriter(new BufferedWriter(new FileWriter(Globals.gameTreeLogFile,false/*append*/)));
			qList.add(state);
			
			State currState = null;
			while(qList.peek() != null){
				currState = qList.poll();
				
				StateCount++;
				pw.println(" ******************************** ");
				pw.println("PAC     " + currState.getPacman().getxCordinate() + "\t" + currState.getPacman().getyCordinate());
				pw.println("GH1     " + currState.getGhost1().getxCordinate() + "\t" + currState.getGhost1().getyCordinate());
				pw.println("isloop  " + currState.isLoopState());
				pw.println("isWin   " + currState.isGameWinState());
				pw.println("isLoose " + currState.isGameLoseState());
				pw.println("value   " + currState.getStateValue());
				pw.println();
				
				if(currState.getSuccessorStates() == null)
					continue;
					
				for(State s: currState.getSuccessorStates()){
					qList.add(s);
					pw.println(" -> Successor");
					pw.println("PAC     " + s.getPacman().getxCordinate() + "\t" + s.getPacman().getyCordinate());
					pw.println("GH1     " + s.getGhost1().getxCordinate() + "\t" + s.getGhost1().getyCordinate());
					pw.println();
				}
			}
			
		} catch (IOException e) {
			System.err.println("Unable to open log file");
		}
		finally{
				try {
					CommonAPIs.logMsg("Number of nodes in game = " + StateCount);
					if(pw != null)
						pw.close();
				} catch (Exception e) {
					System.err.println("Unable to close log file");
				}
		}
		
	}
	
	public static void printState(State s){
		System.out.println(" ******************************** ");
		System.out.println("PAC     " + s.getPacman().getxCordinate() + "\t" + s.getPacman().getyCordinate());
		System.out.println("GH1     " + s.getGhost1().getxCordinate() + "\t" + s.getGhost1().getyCordinate());
		System.out.println("isloop  " + s.isLoopState());
		System.out.println("isWin   " + s.isGameWinState());
		System.out.println("isLoose " + s.isGameLoseState());
		System.out.println("value   " + s.getStateValue());
		System.out.println();
	}
}