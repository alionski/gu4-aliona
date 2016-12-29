import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import p6.Color;
import p6.ColorDisplayDemo;

public class TextController {
	private Timer timer;
	private RunningTextColorDisplay display;
	private Array7x7[] backPanel = new Array7x7[5];
	private Characters charsObj = Characters.getInstance();
	private Array7x7[] charArray;
	
	public TextController(RunningTextColorDisplay display) {
		this.display = display;
		display.setController(this);
		
		for (int i = 0; i < backPanel.length; i++) {
			backPanel[i] = new Array7x7();
		}
	}
	
	public void moveLeft(String usrText) {
		timer = new Timer();
		charArray = new Array7x7[usrText.length() + 5];
		
		for (int index = 0; index < usrText.length(); index++) {
			charArray[index] = new Array7x7();
			charArray[index] = charsObj.getCharacter(usrText.charAt(index));
		}
		
		for (int extra = usrText.length(); extra < charArray.length; extra ++) {
			charArray[extra] = new Array7x7();
			charArray[extra] = charsObj.getCharacter(' ');
		}
		
		timer.schedule( new Leftwards(), 30, 300);
	}
	
	public void moveRight(String usrText) {
		timer = new Timer();
		charArray = new Array7x7[usrText.length() + 5];
		
		for (int extra = 0; extra < 5; extra ++) {
			charArray[extra] = new Array7x7();
			charArray[extra] = charsObj.getCharacter(' ');
		}
			
		int current = usrText.length()-1;
		for (int index = 5; index < charArray.length; index++) {
			charArray[index] = new Array7x7();
			charArray[index] = charsObj.getCharacter(usrText.charAt(current));
			current--;
		}
		
		timer.schedule( new Rightwards(), 30, 300);
	}
	
	private class Leftwards extends TimerTask {
		private int column = 0;
		private int block = 0;
		
		public void run() {	
			backPanel[0].shiftLeft(backPanel[1].getCol(0));
			backPanel[1].shiftLeft(backPanel[0].getCol(6),backPanel[2].getCol(0));
			backPanel[2].shiftLeft(backPanel[1].getCol(6),backPanel[3].getCol(0));
			backPanel[3].shiftLeft(backPanel[2].getCol(6),backPanel[4].getCol(0));
			backPanel[4].shiftLeft(backPanel[3].getCol(6), charArray[block].getCol(column));
			
			display.updateDisplay(backPanel);
			
			if ((column == 6) && (block == charArray.length-1)){
				timer.cancel();
			} else if (column == 6) {
				column = 0;
				block++;
			} else {
				column++;
			}
		}
	}
	
	private class Rightwards extends TimerTask {
		private int column = 6;
		private int block = charArray.length-1;
		
		public void run() {
			
			backPanel[4].shiftRight(backPanel[3].getCol(6));
			backPanel[3].shiftRight(backPanel[2].getCol(6),backPanel[4].getCol(0));
			backPanel[2].shiftRight(backPanel[1].getCol(6),backPanel[3].getCol(0));
			backPanel[1].shiftRight(backPanel[0].getCol(6),backPanel[2].getCol(0));
			backPanel[0].shiftRight(charArray[block].getCol(column), backPanel[1].getCol(0));
					
			display.updateDisplay(backPanel);
			
			if ((column == 0) && (block == 0)){
				timer.cancel();
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
				RunningTextColorDisplay test = new RunningTextColorDisplay();
				new TextController(test);
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
