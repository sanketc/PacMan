package core;

/**
 * This class stores agent related information
 * 
 * @author Sanket Chandorkar
 */
public class Agent extends Pair{

	private int stepCount;
	
	public Agent(int x, int y,int stepCount) {
		super(x, y);
		this.stepCount = stepCount;
	}

	public int getStepCount() {
		return stepCount;
	}

	public void setStepCount(int stepCount) {
		this.stepCount = stepCount;
	}

	public boolean overlapsWith(Agent agent) {
		if(getxCordinate() == agent.getxCordinate() &&
				getyCordinate() == agent.getyCordinate())
			return true;
		return false;
	}

}
