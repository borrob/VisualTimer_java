package visualTimer;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Vtimer_Visual extends JFrame {
	/*
	 * Class: Vtimer_Visual
	 * 
	 * This class visualises the timer application. The screen goes to green
	 * when the timer runs out. Then it resets to red again. The user can
	 * change the countdown settings, start/stop/resume the timer and choose if
	 * the countdown is displayed or not.
	 * 
	 * The countdown settings should be either a single integer (countdown
	 * time in seconds), or a comma-separated list of countdown times (times in
	 * seconds). For example: 5,3,2,6,10
	 * There can be spaces, but no other characters than numbers and commas.
	 */
	
	private static final long serialVersionUID = 6622423020958225467L;
	
	private JFrame mainFrame;	
	private int WINDOWWIDTH=700;
	private int WINDOWHEIGHT=400;
	
	private JLabel label;
	
	private JPanel settingsPanel;
	private JButton startStopButton;
	private JButton resumeButton;
	private JTextField intervalTF;
	private JCheckBox showCountdownCB;
	
	private String LABELTEXT = ""; //the countdown in the middle of the screen
	private Boolean FLASH = false; //countdown is reached
	private String intervalText = ""; //the cycleTimes from the Vtimer_Timer class
	
	/*
	 * GETTER and SETTER
	 */
	public String getLABEL() {
		return LABELTEXT;
	}

	public void setLABEL(String lABEL) {
		LABELTEXT = ">" + lABEL + "<";
	}

	public Boolean getFLASH() {
		return FLASH;
	}

	public void setFLASH(Boolean fLASH) {
		FLASH = fLASH;
	}
	
	public String getIntervalText() {
		return intervalText;
	}
	
	public void setIntervalText(String intervaltext) {
		intervalText = intervaltext;
	}
	/*
	 * END GETTER and SETTER
	 */
	
	void init(Vtimer vt){
		//create a new window, fill it with the items and show
		mainFrame = new JFrame("Visual Timer");
		mainFrame.setSize(WINDOWWIDTH, WINDOWHEIGHT);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setLayout(new BorderLayout());
		
		mainFrame.getContentPane().setBackground(Color.RED);
		
		label = new JLabel(LABELTEXT, JLabel.CENTER);
		label.setForeground(Color.WHITE);
		label.setFont(new Font("Courier", Font.BOLD, 256)); //TODO: make font available as setting
		mainFrame.add(label, BorderLayout.CENTER);
		
		settingsPanel = new JPanel();
		settingsPanel.setLayout(new FlowLayout());
		
		startStopButton = new JButton("Stop timer");
		resumeButton = new JButton("Resume timer");
		
		resumeButton.setVisible(false);
		resumeButton.setHorizontalAlignment(JButton.CENTER);
		resumeButton.setSize(100,25);
		resumeButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				//resume clicked: resume the timer, hide resume button and change text on start/stop button 
				vt.vtt.resume(vt);
				resumeButton.setVisible(false);
				startStopButton.setText("Stop timer");
			}
		});
		
		startStopButton.setHorizontalAlignment(JButton.CENTER);
		startStopButton.setSize(100,25);
		startStopButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				//start/stop the timer and show/hide the resume button
				if (vt.vtt.isRunning){
					startStopButton.setText("Restart timer");
					vt.vtt.stop();
					resumeButton.setVisible(true);
				} else {
					startStopButton.setText("Stop timer");
					vt.vtt.start(vt);
					resumeButton.setVisible(false);
				}
			};
		});
		settingsPanel.add(startStopButton);
		settingsPanel.add(resumeButton);
		
		intervalTF = new JTextField();
		intervalTF.setColumns(10);
		intervalTF.setText(intervalText);
		intervalTF.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				//user enters a new (list of) cycleTimes
				String tf = intervalTF.getText();
				String[] cycleTimes = tf.split(","); //split at commas
				int[] ct_int = new int[cycleTimes.length];
				int i = 0;
				for (String ct: cycleTimes){
					ct_int[i]=Integer.parseInt(ct.trim())*1000; //trim ignores spaces, convert seconds to ms
					i++;
				}
				vt.vtt.setCycleTimes(ct_int);
			}
		});
		settingsPanel.add(intervalTF);
		
		JLabel intervalLabel = new JLabel();
		intervalLabel.setText("seconds");
		settingsPanel.add(intervalLabel);
		
		showCountdownCB = new JCheckBox();
		showCountdownCB.setText("Show countdown");
		showCountdownCB.setSelected(true);
		settingsPanel.add(showCountdownCB);
		
		mainFrame.add(settingsPanel, BorderLayout.PAGE_END);
		
		mainFrame.setVisible(true);
	}
	
	public void doPaint(){
		/*
		 * Helper method to repaint the screen with current settings and
		 * variables.
		 */
		mainFrame.getContentPane().setBackground(Color.RED); //red unless timer goes of
		if (FLASH){
			//timer finished --> flash
			mainFrame.getContentPane().setBackground(Color.GREEN);
		}
		label.setText(LABELTEXT);
		label.setVisible(showCountdownCB.isSelected());
	}
}
