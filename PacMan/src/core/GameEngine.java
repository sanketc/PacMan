package core;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

import common.CommonAPIs;

/**
 * This class contains the code logic/implementation of this game.
 * 
 * @author Sanket Chandorkar
 */
public class GameEngine {
	
	private HashSet<State> visitedStateSet;
	
	private State root; // root node for game tree
	
	private static int stateVisitCount;
	
	public GameEngine(State startState){
		root = startState;
		visitedStateSet = new HashSet<>();
		stateVisitCount = 0;
	}
	
	public void populateGameTree(){
		Queue<State> sQueue = new LinkedList<>();
		State currState = null;
		
		sQueue.add(root);

		while(sQueue.peek() != null){
			currState = sQueue.poll();
			
			// check for terminal state
			if(currState.isTerminalState()){
				currState.setSuccessorStates(null);
				continue;
			}
			
			// check for loop state
			if(isVisitedState(currState)){
				currState.setLoopState(true);
				currState.setSuccessorStates(null);
				continue;
			}
			
			// process state
			visitedStateSet.add(currState);
			
			// Update successor state list
			ArrayList<State> successorStateList = State.findSuccessorState(currState);
			currState.setSuccessorStates(successorStateList);
			
			// add successors in queue
			for(State s : successorStateList){
				sQueue.add(s);
			}
		}
		
		CommonAPIs.dumpGameTree(root);
	}
	
	private boolean isVisitedState(State s2){
		for(State s1: visitedStateSet){
			if(s1.getPacman().getxCordinate() == s2.getPacman().getxCordinate())
				if(s1.getPacman().getyCordinate() == s2.getPacman().getyCordinate())
					if(s1.getGhost1().getxCordinate() == s2.getGhost1().getxCordinate())
						if(s1.getGhost1().getyCordinate() == s2.getGhost1().getyCordinate())
							if(s1.getDotLoc().equals(s2.getDotLoc()))
								return true;
		}
		return false;
	}
	
	// Two player min max
	
	public ArrayList<State> solveMinimax(){
		stateVisitCount = 0;
		ArrayList<State> list = maxValue(root);
//		CommonAPIs.dumpGameTree(root);
		return list;
	}
	
	private ArrayList<State> maxValue(State currState){
		ArrayList<State> gamePlan,successorPlan = null;
		stateVisitCount++;
		
		if(currState.isTerminalState()){
			gamePlan = new ArrayList<>();
			gamePlan.add(currState);
			return gamePlan;
		}
			
		ArrayList<State> maxSuccessorPlan = null;
		for(State s: currState.getSuccessorStates()){
			successorPlan = minValue(s);
			if(maxSuccessorPlan == null)
				maxSuccessorPlan = successorPlan;
			else
				maxSuccessorPlan = getMaxValueState(maxSuccessorPlan, successorPlan);
		}

		int index = maxSuccessorPlan.size() - 1;
		State sEnd = maxSuccessorPlan.get(index);
		currState.setStateValue(sEnd.getStateValue());
		maxSuccessorPlan.add(currState);
		return maxSuccessorPlan;
	}
	
	private ArrayList<State> getMaxValueState(ArrayList<State> list1, ArrayList<State> list2){
		State s1 = list1.get(list1.size() - 1);
		State s2 = list2.get(list2.size() - 1);

		// handle for loop / states with unknown value
		if(s1.getStateValue() == 0)
			return list2;

		if(s2.getStateValue() == 0)
			return list1;
		
		// normal handling
		if(s1.getStateValue() >= s2.getStateValue())
			return list1;
		else
			return list2;
	}
	
	private ArrayList<State> minValue(State currState) {
		ArrayList<State> gamePlan,successorPlan = null;
		stateVisitCount++;
		
		if(currState.isTerminalState()){
			gamePlan = new ArrayList<>();
			gamePlan.add(currState);
			return gamePlan;
		}
			
		ArrayList<State> minSuccessorPlan = null;
		for(State s: currState.getSuccessorStates()){
			successorPlan = maxValue(s);
			if(minSuccessorPlan == null)
				minSuccessorPlan = successorPlan;
			else
				minSuccessorPlan = getMinValueState(minSuccessorPlan, successorPlan);
		}

		int index = minSuccessorPlan.size() - 1;
		State sEnd = minSuccessorPlan.get(index);
		currState.setStateValue(sEnd.getStateValue());
		minSuccessorPlan.add(currState);
		return minSuccessorPlan;
	
	}
	
	private ArrayList<State> getMinValueState(ArrayList<State> list1, ArrayList<State> list2){
		State s1 = list1.get(list1.size() - 1);
		State s2 = list2.get(list2.size() - 1);

		// handle for loop / states with unknown value
		if(s1.getStateValue() == 0)
			return list2;

		if(s2.getStateValue() == 0)
			return list1;
		
		// normal handling
		if(s1.getStateValue() <= s2.getStateValue())
			return list1;
		else
			return list2;
	}

	// ======================================================================================
	// Alpha beta
	// ======================================================================================
	
	public ArrayList<core.State> solveAlphaBeta() {
		stateVisitCount = 0;
		ArrayList<State> list = abMaxValue(root,Integer.MIN_VALUE,Integer.MAX_VALUE);
//		CommonAPIs.dumpGameTree(root);
		return list;
	}
	
	private ArrayList<State> abMaxValue(State currState, int alpha, int beta){
		ArrayList<State> gamePlan,successorPlan = null;
		int value = Integer.MIN_VALUE;
		stateVisitCount++;
		
		if(currState.isTerminalState()){
			gamePlan = new ArrayList<>();
			gamePlan.add(currState);
			return gamePlan;
		}
			
		ArrayList<State> maxSuccessorPlan = null;
		for(State s: currState.getSuccessorStates()){
			
			successorPlan = abMinValue(s,value,beta);
			State last = getLast(successorPlan);
			if(!last.isLoopState())
			{	
				value = last.getStateValue();
				if(value >= beta){
					currState.setStateValue(value);
					successorPlan.add(currState);
					System.out.println("****** prunned at max *******");
					CommonAPIs.printState(currState);
					return successorPlan;
				}
			}
			
			
			if(maxSuccessorPlan == null)
				maxSuccessorPlan = successorPlan;
			else
				maxSuccessorPlan = getMaxValueState(maxSuccessorPlan, successorPlan);
			
			// update value of alpha
			alpha = maxOf(last.getStateValue(), alpha);
		}

		State sEnd = getLast(maxSuccessorPlan);
		currState.setStateValue(sEnd.getStateValue());
		maxSuccessorPlan.add(currState);
		return maxSuccessorPlan;
	}
	
	private ArrayList<State> abMinValue(State currState, int alpha, int beta) {
		ArrayList<State> gamePlan,successorPlan = null;
		int value = Integer.MAX_VALUE;
		stateVisitCount++;
		
		if(currState.isTerminalState()){
			gamePlan = new ArrayList<>();
			gamePlan.add(currState);
			return gamePlan;
		}
			
		ArrayList<State> minSuccessorPlan = null;
		for(State s: currState.getSuccessorStates()){
			
			successorPlan = abMaxValue(s, alpha, value);
			State last = getLast(successorPlan);
			if(!last.isLoopState())
			{	
				value = last.getStateValue();
				if(value <= alpha){
					currState.setStateValue(value);
					successorPlan.add(currState);
					System.out.println("****** prunned at min *******");
					CommonAPIs.printState(currState);
					return successorPlan;
				}
			}
			
			if(minSuccessorPlan == null)
				minSuccessorPlan = successorPlan;
			else
				minSuccessorPlan = getMinValueState(minSuccessorPlan, successorPlan);
			
			// update value of beta
			beta = minOf(last.getStateValue(), beta);
		}

		State sEnd = getLast(minSuccessorPlan);
		currState.setStateValue(sEnd.getStateValue());
		minSuccessorPlan.add(currState);
		return minSuccessorPlan;
	}
	
	private int maxOf(int a, int b){
		if(a>=b)
			return a;
		else
			return b;
	}

	private int minOf(int a, int b){
		if(a<=b)
			return a;
		else
			return b;
	}
	
	private State getLast(ArrayList<State> list){
		return list.get(list.size() -1);
	}

	public int getStateVistCount() {
		return stateVisitCount;
	}

	public void setStateVistCount(int stateVistCount) {
		this.stateVisitCount = stateVistCount;
	}
}
