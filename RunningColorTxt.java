package final_version;
import p6.ColorDisplay;
import p6.Color;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;


/**
 * Demo with one letter, basically the presentation itself. Uses Android's Color and Rolf's ColorDisplay. 
 * Has TextController as an associated class. 
 * Size: 35x7 (5 panels).
 * Start the demo from DisplayController.
 * @author Aliona
 */
public class RunningColorTxt extends JPanel implements ActionListener{
	private DisplayController ctrl;
	private ColorDisplay display = new ColorDisplay(1, 5, Color.WHITE, Color.GRAY);
	private JTextField usrInput = new JTextField();
	private JButton shiftLeft = new JButton("Go left!");
	private JButton shiftRight = new JButton("Go right!");
	private JButton shiftRightMirrored = new JButton("Go right (mirrored)!");
	private JPanel lower = new JPanel();
	private String usrText = "";
	
	/**
	 * Constructor. Creates a display using swing and a ColorDisplay object.
	 */
	public RunningColorTxt() {

		setLayout(new BorderLayout());
		lower.setLayout(new GridLayout(1, 4));
		shiftLeft.addActionListener(this);
		shiftRight.addActionListener(this);
		shiftRightMirrored.addActionListener(this);
		lower.add(usrInput);
		lower.add(shiftLeft);
		lower.add(shiftRight);
		lower.add(shiftRightMirrored);
		add(lower, BorderLayout.SOUTH);
		add(display, BorderLayout.CENTER);		
	}
	
	/**
	 * Method used to create a two-way link between the current object and a controller object.
	 * Invoked by the DisplayController's constructor.
	 * @param controller: the controller object to be linked to this RunningColorTxt object. 
	 * 
	 */
	public void setController(DisplayController controller) {
		this.ctrl = controller;
	}
	
	/**
	 * Button listener. 
	 * After a button is clicked, the control goes over to the controller.
	 */
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == shiftLeft) {
			usrText = usrInput.getText();			
			ctrl.moveLeft(usrText);
			enableButtons(false); // the buttons are disabled, re-enabled by the controller when the timer is cancelled
		} else if (e.getSource() == shiftRight) {
			usrText = usrInput.getText();
			ctrl.moveRight(usrText);
			enableButtons(false); // the buttons are disabled, re-enabled by the controller when the timer is cancelled
		} else if (e.getSource() == shiftRightMirrored) {
			usrText = usrInput.getText();
			ctrl.moveRightReversed(usrText);
			enableButtons(false);
		}
	}
	
	/**
	 * Method used by the current object and the controller to dis-/enable buttons. 
	 * @param OnOff: boolean; true stands for "enable", false for "disable". 
	 */
	public void enableButtons(boolean OnOff) {
		shiftLeft.setEnabled(OnOff);
		shiftRight.setEnabled(OnOff);
		shiftRightMirrored.setEnabled(OnOff);
		
	}
	
	/**
	 * Method invoked by the controller each at each call of the run() method in TimerTask classes, i.e.
	 * it is called after each shift of the text for one column.
	 * In its turn, it calls two methods of its ColorDisplay object.
	 * @param colours
	 */
	public void updateDisplay(Array7x7[] colours) { 		
		for (int i = 0; i < colours.length; i++) {
			display.setDisplay(colours[i].getArray(), 0, i);
		}
		display.updateDisplay();
	}
}
