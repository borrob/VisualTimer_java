package visualTimer;

import java.util.Timer;
import java.util.TimerTask;

public class Vtimer_Timer {
	
	private Timer timer;

	private int DELAY = 0;
	private int UPDATEINTERVAL = 1000;
	private int PERIOD = 5000; //must be integer * UPDATEINTVERVAL
	private int INTERVAL = PERIOD;
	
	public boolean isRunning = false;
	
	/*
	 * GETTER and SETTER
	 */
	public int getDELAY() {
		return DELAY;
	}

	public void setDELAY(int dELAY) {
		DELAY = dELAY;
	}

	public int getUPDATEINTERVAL() {
		return UPDATEINTERVAL;
	}

	public void setUPDATEINTERVAL(int uPDATEINTERVAL) {
		UPDATEINTERVAL = uPDATEINTERVAL;
	}

	public int getPERIOD() {
		return PERIOD;
	}

	public void setPERIOD(int pERIOD) {
		PERIOD = pERIOD;
	}

	public int getINTERVAL() {
		return INTERVAL;
	}

	public void setINTERVAL(int iNTERVAL) {
		INTERVAL = iNTERVAL;
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
		}, this.DELAY, this.UPDATEINTERVAL);
		isRunning=true;
	}
	
	void stop(){
		timer.cancel();
		isRunning=false;
	}
	
	private void timeStep(){
		if (INTERVAL <= 0 || INTERVAL > PERIOD){
			INTERVAL = PERIOD;
		}
		INTERVAL -= UPDATEINTERVAL;
	}
	
}
