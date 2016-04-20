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
	
	private String LABELTEXT = "";
	private Boolean FLASH = false;
	private String intervalText = "";
	
	/*
	 * GETTER and SETTER
	 */
	public String getLABEL() {
		return LABELTEXT;
	}

	public void setLABEL(String lABEL) {
		LABELTEXT = lABEL;
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
		mainFrame = new JFrame("Visual Timer");
		mainFrame.setSize(WINDOWWIDTH, WINDOWHEIGHT);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setLayout(new BorderLayout());
		
		mainFrame.getContentPane().setBackground(Color.RED);
		
		label = new JLabel(LABELTEXT, JLabel.CENTER);
		label.setForeground(Color.WHITE);
		label.setFont(new Font("Courier", Font.BOLD, 128)); //TODO: make font available as setting
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
				vt.vtt.resume(vt);
				resumeButton.setVisible(false);
				startStopButton.setText("Stop timer");
			}
		});
		
		startStopButton.setHorizontalAlignment(JButton.CENTER);
		startStopButton.setSize(100,25);
		startStopButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
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
				String tf = intervalTF.getText();
				String[] cycleTimes = tf.split(",");
				int[] ct_int = new int[cycleTimes.length];
				int i = 0;
				for (String ct: cycleTimes){
					ct_int[i]=Integer.parseInt(ct.trim())*1000;
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
		mainFrame.getContentPane().setBackground(Color.RED);
		if (FLASH){
			mainFrame.getContentPane().setBackground(Color.BLUE);
		}
		label.setText(LABELTEXT);
		label.setVisible(showCountdownCB.isSelected());
	}
}
