import p6.ColorDisplay;
import p6.Controller;
import p6.Color;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.*;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

/**
 * Demo with one letter, basically the presentation itself. Uses Android's Color and Rolf's ColorDisplay. 
 * Has TextController as an associated class. 
 * @author Aliona
 */
public class RunningTextColorDisplay extends JPanel implements ActionListener{
	private TextController ctrl;
	private ColorDisplay display = new ColorDisplay(1, 5, Color.WHITE, Color.GRAY);
	private JTextField usrInput = new JTextField();
	private JButton shiftLeft = new JButton("Go left!");
	private JButton shiftRight = new JButton("Go right!");
	private JPanel lower = new JPanel();
	private String usrText = "";
	
	public RunningTextColorDisplay() {

		setLayout(new BorderLayout());
		lower.setLayout(new GridLayout(1, 3));
		shiftLeft.addActionListener(this);
		shiftRight.addActionListener(this);
//		usrInput.setPreferredSize( new Dimension( 800, 20));
		lower.add(usrInput);
		lower.add(shiftLeft);
		lower.add(shiftRight);
		add(lower, BorderLayout.SOUTH);
		add(display, BorderLayout.CENTER);		
	}
	
	public void setController(TextController controller) {
		this.ctrl = controller;
	}
	
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == shiftLeft) {
			usrText = usrInput.getText();			
			ctrl.moveLeft(usrText);
		} else if (e.getSource() == shiftRight) {
			usrText = usrInput.getText();
			ctrl.moveRight(usrText);
		}
	}
	
	public void updateDisplay(Array7x7[] colours) { 		
		for (int i = 0; i < colours.length; i++) {
			display.setDisplay(colours[i].getArray(), 0, i);
		}
		display.updateDisplay();
	}
}
