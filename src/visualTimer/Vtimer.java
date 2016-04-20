package visualTimer;

public class Vtimer{	
	/*
	 * Class: Vtimer
	 * 
	 * This is the main class that acts like a controller class. The
	 * Vtimer_visual is an object that handles the visualisation and screen
	 * layout. The Vtimer_timer keeps track of the actual countdown.
	 */
		
	Vtimer_Visual vv;
	Vtimer_Timer vtt;
	
	public static void main(String[] args) {
		
		Vtimer vt = new Vtimer();
		
		vt.vv = new Vtimer_Visual();
		vt.vtt = new Vtimer_Timer();
		
		//show the default settings in the GUI
		vt.vv.setLABEL(((Integer) (vt.vtt.getInterval()/1000)).toString());
		vt.vv.setFLASH(vt.vtt.getInterval()<=0);
		vt.vv.setIntervalText(Integer.toString(vt.vtt.getCurrentCycleTime()/1000));
		
		vt.vv.init(vt);
		vt.vtt.start(vt);
	}
	
	void doUpdate(){
		//update the GUI
		vv.setLABEL(((Integer) (vtt.getInterval()/1000)).toString());
		vv.setFLASH(vtt.getInterval()<=0);
		vv.setIntervalText(Integer.toString(vtt.getCurrentCycleTime()/1000));
		vv.doPaint();
	}
	
}