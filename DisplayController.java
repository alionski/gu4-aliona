package final_version;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

/**
 * 
 * The controller to the class RunningColorTxt.
 * @author Aliona
 *
 */
public class DisplayController {
	private Timer timer;
	private RunningColorTxt display;
	private Array7x7[] backPanel = new Array7x7[5];
	private Characters charsObj = Characters.getInstance();
	private Array7x7[] charArray;
	
	/**
	 * Contstructor that takes in a reference to a RunningColorTtx object to bind the two together. 
	 * Initialises the Array7x7 objects.
	 * @param display: the display to be linked with this controller.
	 */
	public DisplayController(RunningColorTxt display) {
		this.display = display;
		display.setController(this);
		
		for (int i = 0; i < backPanel.length; i++) {
			backPanel[i] = new Array7x7();
		}
	}
	
	/**
	 * The method invoked when the user clicks "To the left". 
	 * Converts the input text into graphic letters using the Character class and launches the timer.
	 * @param usrText: the String object to be converted into colour arrays.
	 */
	public void moveLeft(String usrText) {
		timer = new Timer();
		charArray = new Array7x7[usrText.length() + 5];
		
		// converting the text into arrays with graphic letter (Arrays7x7's with colour values)
		
		for (int index = 0; index < usrText.length(); index++) {
			charArray[index] = new Array7x7();
			charArray[index] = charsObj.getCharacter(usrText.charAt(index));
		}
		
		/* 
		* Adding some extra empty <space> characters to the end of the 
		* user's text to allows the letters disappear completely from the display and not "freeze" on it when
		* the timer has reached the end of the array.
		*/
		for (int extra = usrText.length(); extra < charArray.length; extra ++) {
			charArray[extra] = new Array7x7();
			charArray[extra] = charsObj.getCharacter(' ');
		}
		
		timer.schedule( new Leftwards(), 30, 100);
	}
	
	/**
	 * The method invoked when the user clicks "To the right". 
	 * Converts the input text into graphic letters using the Character class and launches the timer.
	 * @param usrText: the String object to be converted into colour arrays.
	 */
	public void moveRight(String usrText) {
		timer = new Timer();
		charArray = new Array7x7[usrText.length() + 5];
		
		/* 
		* Adding some extra empty <space> characters to the end of the 
		* user's text to allows the letters disappear completely from the display and not "freeze" on it when
		* the timer has reached the end of the array.
		*/
		
		for (int extra = 0; extra < 5; extra ++) {
			charArray[extra] = new Array7x7();
			charArray[extra] = charsObj.getCharacter(' ');
		}
		
		// converting the text into arrays with colours (Arrays7x7s with colour values).
		
		int current = usrText.length()-1;
		for (int index = 5; index < charArray.length; index++) {
			charArray[index] = new Array7x7();
			charArray[index] = charsObj.getCharacter(usrText.charAt(current));
			current--; // going backwards in the user's string
		}
		
		timer.schedule( new Rightwards(), 30, 100);
	}
	
	public void moveRightReversed(String usrText) {
		timer = new Timer();
		charArray = new Array7x7[usrText.length() + 5];
		
		/* 
		* Adding some extra empty <space> characters to the end of the 
		* user's text to allows the letters disappear completely from the display and not "freeze" on it when
		* the timer has reached the end of the array.
		*/
		
		for (int extra = 0; extra < 5; extra ++) {
			charArray[extra] = new Array7x7();
			charArray[extra] = charsObj.getCharacter(' ');
		}
		
		// converting the text into arrays with colours (Arrays7x7s with colour values).
		
		int current = usrText.length()-1;
		for (int index = 5; index < charArray.length; index++) {
			charArray[index] = new Array7x7();
			charArray[index] = charsObj.getCharacterReversed(usrText.charAt(current));
			current--; // going backwards in the user's string
		}
		
		timer.schedule( new Rightwards(), 30, 100);
	}
	
	/** 
	 * Private TimerTask class used to make text flow to the left.
	 * @author Aliona
	 */
	private class Leftwards extends TimerTask {
		private int column = 0;
		private int block = 0;
		
		/*
		 * The method that shifts columns in each of the five panels one by one. Invokes Array7x7's .shiftRight().
		 * Has to be implemented through selection and by incrementing the index. Using a for-loop e.g. would
		 * shift all text at one, in one execution. 
		 */
		
		public void run() {	
			backPanel[0].shiftLeft(backPanel[1].getCol(0));
			backPanel[1].shiftLeft(backPanel[0].getCol(6),backPanel[2].getCol(0));
			backPanel[2].shiftLeft(backPanel[1].getCol(6),backPanel[3].getCol(0));
			backPanel[3].shiftLeft(backPanel[2].getCol(6),backPanel[4].getCol(0));
			backPanel[4].shiftLeft(backPanel[3].getCol(6), charArray[block].getCol(column));
			
			display.updateDisplay(backPanel);
			
			if ((column == 6) && (block == charArray.length-1)){
				timer.cancel();
				display.enableButtons(true);
			} else if (column == 6) {
				column = 0;
				block++;
			} else {
				column++;
			}
		}
	}
	
	/**
	 * Private TimerTask class used to make the text flow to the right.
	 * @author Aliona
	 *
	 */
	private class Rightwards extends TimerTask {
		private int column = 6;
		private int block = charArray.length-1;
		
		public void run() {
			
			/*
			 * The method that shifts columns in each of the five panels one by one. Invokes Array7x7's .shiftRight().
			 * Has to be implemented through selection and by incrementing the index. Using a for-loop e.g. would
			 * shift all text at once, in one execution. 
			 */
			backPanel[4].shiftRight(backPanel[3].getCol(6));
			backPanel[3].shiftRight(backPanel[2].getCol(6),backPanel[4].getCol(0));
			backPanel[2].shiftRight(backPanel[1].getCol(6),backPanel[3].getCol(0));
			backPanel[1].shiftRight(backPanel[0].getCol(6),backPanel[2].getCol(0));
			backPanel[0].shiftRight(charArray[block].getCol(column), backPanel[1].getCol(0));
					
			display.updateDisplay(backPanel);
			
			if ((column == 0) && (block == 0)) {
				timer.cancel();
				display.enableButtons(true);
			} else if (column == 0) {
				column = 6;
				block--;
			} else {
				column--;
			}
		}		
	}
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				RunningColorTxt test = new RunningColorTxt();
				new DisplayController(test);
				JFrame frame = new JFrame("Test");
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.add(test);
				frame.pack();
				frame.setLocation(400,200);
				frame.setVisible(true);
			}
		});
	}

}
