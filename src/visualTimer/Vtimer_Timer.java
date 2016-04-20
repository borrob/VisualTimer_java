package visualTimer;

import java.util.Timer;
import java.util.TimerTask;

public class Vtimer_Timer {
	/*
	 * Class: Vtimer_Timer
	 * 
	 * This class implements a countdown timer with the functionality to start,
	 * stop and resume the timer. The countdown time is set with an integer
	 * array (time in miliseconds). The timer counts these times down one by
	 * one and starts with the first one after the last one is done.
	 */
	
	private Timer timer;

	private int delay = 0;
	private int updateInterval = 1000;
	private int[] cycleTimes = new int[]{5000};
	private int interval = cycleTimes[0]; //the interval is the time left over in the current cycleTime
	private int currentCycleTimeIndex = 0;
	
	public boolean isRunning = false;
	
	/*
	 * GETTER and SETTER
	 */
	public int getInterval() {
		return interval;
	}

	public void setInterval(int iv) {
		interval = iv;
	}
	
	public int[] getCycleTimes(){
		return cycleTimes;
	}
	
	public void setCycleTimes(int[] ct){
		cycleTimes = ct;
	}
	
	public int getCurrentCycleTime(){
		return cycleTimes[currentCycleTimeIndex];
	}
	/*
	 * END GETTER AND SETTER
	 */

	void start(Vtimer vt){
		currentCycleTimeIndex=0;
		interval = cycleTimes[currentCycleTimeIndex];
		
		resume(vt);
	}
	
	void resume(Vtimer vt){
		timer = new Timer();
		timer.scheduleAtFixedRate(
			new TimerTask(){
				public void run(){
					timeStep();
					vt.doUpdate();
				}
			},
			this.delay,
			this.updateInterval
		);
		isRunning=true;
	}
	
	void stop(){
		timer.cancel();
		isRunning=false;
	}
	
	private void timeStep(){
		/*
		 * Helper method to update the time left over in the current run.
		 * 
		 * Reset interval to the next cycleTime.
		 */
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
