package visualTimer;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Vtimer_Visual extends JPanel {

	private static final long serialVersionUID = 1568598189296336937L;
	
	/* Set the window height and width */
	//TODO: when adjusting window --> adjust settings
	int WINDOW_WIDTH = 400;
	int WINDOW_HEIGHT = 400;
	
	private String LABEL = "";
	private Boolean FLASH = false;
	
	/*
	 * GETTER and SETTER
	 */
	public String getLABEL() {
		return LABEL;
	}

	public void setLABEL(String lABEL) {
		LABEL = lABEL;
	}

	public Boolean getFLASH() {
		return FLASH;
	}

	public void setFLASH(Boolean fLASH) {
		FLASH = fLASH;
	}
	/*
	 * END GETTER and SETTER
	 */

	public void init(){
	
		//create the window and frame
		JFrame jf = new JFrame();
		jf.getContentPane().add(this);
		jf.setSize(this.WINDOW_WIDTH, this.WINDOW_HEIGHT);
		jf.setVisible(true);
	}
	
	public void doPaint(){
		Graphics g = getGraphics();
		paintComponent(g);
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		drawBackground(g);
		setTheLabel(g);
	}
	
	private void setTheLabel(Graphics g){
		Graphics2D g2d = (Graphics2D) g;
		
		g2d.setColor(Color.WHITE);
		g2d.setFont(new Font("Courier", Font.BOLD, 128)); //TODO: make font available as setting
		int width = (int) g2d.getFontMetrics().getStringBounds(LABEL, g2d).getWidth();
		g2d.drawString(LABEL, (this.WINDOW_WIDTH-width)/2, 300);
	}
	
	private void drawBackground(Graphics g){
		Graphics2D g2d = (Graphics2D) g;
		
		Rectangle2D bg = new Rectangle2D.Double(0,0,this.WINDOW_WIDTH, this.WINDOW_HEIGHT);

		if (FLASH){
			g2d.setColor(Color.BLUE);
		}
		else {
			g2d.setColor(Color.RED);
		}
		
		g2d.fill(bg);
	}
}
