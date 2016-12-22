import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import p6.Color;
import p6.ColorDisplayDemo;

public class TextController {
	private Timer timer;
	private RunningTextColorDisplay display;
	private int[][] charA = {{Color.BLUE,Color.BLUE,Color.BLUE,Color.WHITE,Color.BLUE,Color.BLUE,Color.BLUE},
			{Color.BLUE,Color.BLUE,Color.WHITE,Color.BLUE,Color.WHITE,Color.BLUE,Color.BLUE},
			{Color.BLUE,Color.BLUE,Color.WHITE,Color.BLUE,Color.WHITE,Color.BLUE,Color.BLUE},
			{Color.BLUE,Color.BLUE,Color.WHITE,Color.WHITE,Color.WHITE,Color.BLUE,Color.BLUE},
			{Color.BLUE,Color.WHITE,Color.BLUE,Color.BLUE,Color.BLUE,Color.WHITE,Color.BLUE},
			{Color.BLUE,Color.WHITE,Color.BLUE,Color.BLUE,Color.BLUE,Color.WHITE,Color.BLUE},
			{Color.BLUE,Color.WHITE,Color.BLUE,Color.BLUE,Color.BLUE,Color.WHITE,Color.BLUE}};
	private Array7x7[] backPanel = new Array7x7[5];
	private Array7 trashArray = new Array7();
	
	public TextController(RunningTextColorDisplay display) {
		this.display = display;
		display.setController(this);
		
		for (int i = 0; i < backPanel.length; i++) {
			backPanel[i] = new Array7x7();
		}
	}
	
	public void show() {
		timer = new Timer();
		timer.schedule( new LetterA(), 30, 300);
	}
	
	private class LetterA extends TimerTask {
		Array7x7 charArray = new Array7x7(charA);
		int column = 0;
//		int block = 0;
		
		public void run() {	
			backPanel[0].shiftLeft(trashArray, backPanel[1].getCol(0));
			backPanel[1].shiftLeft(backPanel[0].getCol(6),backPanel[2].getCol(0));
			backPanel[2].shiftLeft(backPanel[1].getCol(6),backPanel[3].getCol(0));
			backPanel[3].shiftLeft(backPanel[2].getCol(6),backPanel[4].getCol(0));
			backPanel[4].shiftLeft(backPanel[3].getCol(6), charArray.getCol(column));
			
			for (int i = 0; i < 5; i++) {
				display.updateDisplay(backPanel);
			}
			
			if (column == 6) {
				column = 0;
			} else {
				column++;	
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
