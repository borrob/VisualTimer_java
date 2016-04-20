package visualTimer;

public class Vtimer{	
		
	Vtimer_Visual vv;
	Vtimer_Timer vtt;
	
	public static void main(String[] args) {
		
		Vtimer vt = new Vtimer();
		
		vt.vv = new Vtimer_Visual();
		vt.vtt = new Vtimer_Timer();
		
		vt.vv.setLABEL(((Integer) (vt.vtt.getInterval()/1000)).toString());
		vt.vv.setFLASH(vt.vtt.getInterval()<=0);
		vt.vv.setIntervalText(Integer.toString(vt.vtt.getCurrentCycleTime()/1000));
		
		vt.vv.init(vt);
		vt.vtt.start(vt);
	}
	
	void doUpdate(){		
		vv.setLABEL(((Integer) (vtt.getInterval()/1000)).toString());
		vv.setFLASH(vtt.getInterval()<=0);
		vv.setIntervalText(Integer.toString(vtt.getCurrentCycleTime()/1000));
		vv.doPaint();
	}
	
}