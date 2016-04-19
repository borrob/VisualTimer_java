package visualTimer;

import java.util.Timer;
import java.util.TimerTask;

public class Vtimer_Timer {
	
	private Timer timer;

	private int delay = 0;
	private int updateInterval = 1000;
	private int[] cycleTimes = new int[]{5000};
	private int interval = cycleTimes[0];
	private int currentCycleTimeIndex = 0;
	;
	
	public boolean isRunning = false;
	
	/*
	 * GETTER and SETTER
	 */
	public int getDelay() {
		return delay;
	}

	public void setDelay(int d) {
		delay = d;
	}

	public int getupdateInterval() {
		return updateInterval;
	}

	public void setupdateInterval(int updateinterval) {
		updateInterval = updateinterval;
	}

	public int getInterval() {
		return interval;
	}

	public void setInterval(int iv) {
		interval = iv;
	}
	
	public int getCurrentCycleTime(){
		return cycleTimes[currentCycleTimeIndex];
	}
	
	public void setCurrentCycleTime (int ct){
		cycleTimes[currentCycleTimeIndex]=ct;
	}
	
	public int[] getCycleTimes(){
		return cycleTimes;
	}
	
	public void setCycleTimes(int[] ct){
		cycleTimes = ct;
	}
	/*
	 * END GETTER AND SETTER
	 */

	void start(Vtimer vt){
		timer = new Timer();
		timer.scheduleAtFixedRate(new TimerTask(){ //todo take definition out of loop
			public void run(){
				timeStep();
				vt.doUpdate();
			}
		}, this.delay, this.updateInterval);
		isRunning=true;
	}
	
	void stop(){
		timer.cancel();
		isRunning=false;
	}
	
	private void timeStep(){
		if (interval <= 0 || interval > cycleTimes[currentCycleTimeIndex]){	
			currentCycleTimeIndex ++;
			if (currentCycleTimeIndex >= cycleTimes.length){
				currentCycleTimeIndex = 0;
			}
			interval = cycleTimes[currentCycleTimeIndex];
		}
		interval -= updateInterval;
	}
	
}
