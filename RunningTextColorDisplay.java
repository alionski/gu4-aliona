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
 * Most of the methods are half-written atm, only for demo.
 * @author Aliona
 *
 */
public class RunningTextColorDisplay extends JPanel implements ActionListener{
	private TextController ctrl;
	private ColorDisplay display = new ColorDisplay(1, 5, Color.YELLOW, Color.GRAY);
	private JTextField usrInput = new JTextField();
	private JButton go = new JButton("Go!");
	private JButton shiftRight = new JButton("Shift right");
	private JPanel lowerMain = new JPanel();
	private JPanel upper = new JPanel();
	private JPanel lower = new JPanel();
	private String usrText = "";
	
	public RunningTextColorDisplay() {

		setLayout(new BorderLayout());
		lowerMain.setLayout(new GridLayout(2, 1));
		upper.setLayout(new GridLayout(1, 2));
		lower.setLayout( new GridLayout(1,1)); // can be changed if there's need to add more buttons
		upper.add(usrInput);
		upper.add(go);
		lower.add(shiftRight);
		go.addActionListener(this);
		shiftRight.addActionListener(this);
		lowerMain.add(upper);
		lowerMain.add(lower);
		add(lowerMain, BorderLayout.SOUTH);
		add(display, BorderLayout.CENTER);		
	}
	
	public void setController(TextController controller) {
		this.ctrl = controller;
	}
	
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == go) {
			usrText = usrInput.getText();
			ctrl.show();
			// launch the text
		} else if (e.getSource() == shiftRight) {
			usrText = usrInput.getText();
			// launch shifting to the right 
		}
	}
	
	public void updateDisplay(Array7x7[] colours) { 		
		for (int i = 0; i < colours.length; i++) {
			display.setDisplay(colours[i].getArray(), 0, i);
		}
		display.updateDisplay();
	}
}
