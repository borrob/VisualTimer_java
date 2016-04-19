package visualTimer;

public class Vtimer{	
		
	Vtimer_Visual2 vv;
	Vtimer_Timer vtt;
	
	public static void main(String[] args) {
		
		Vtimer vt = new Vtimer();
		
		vt.vv = new Vtimer_Visual2();
		vt.vtt = new Vtimer_Timer();
		
		vt.vv.setLABEL(((Integer) (vt.vtt.getINTERVAL()/1000)).toString());
		vt.vv.setFLASH(vt.vtt.getINTERVAL()<=0);
		vt.vv.setIntervalText(Integer.toString(vt.vtt.getPERIOD()/1000));
		
		vt.vv.init(vt);
		vt.vtt.start(vt);
	}
	
	void doUpdate(){		
		vv.setLABEL(((Integer) (vtt.getINTERVAL()/1000)).toString());
		vv.setFLASH(vtt.getINTERVAL()<=0);
		vv.setIntervalText(Integer.toString(vtt.getPERIOD()/1000));
		vv.doPaint();
	}
	
	void stopTimer(){
		vtt.stop();
	}
	
}