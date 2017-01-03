package final_version;
import java.awt.BorderLayout;
import java.awt.Color;
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
 * A test class that demonstrates how the shifting algorithm works on a big display with five panels.
 * Uses swing and the standard library in general. Experiments with colour (awt.Color and not Android's Color). 
 * @author Aliona
 *
 */
public class TestBigScreenSwing extends JPanel implements ActionListener {
	private Timer timer;
	private Array7x7[] matrixArray = new Array7x7[5];
	private Array7x7[] numbers = new Array7x7[15];
	private JLabel[][][] matrix = new JLabel [5][7][7];
	private JButton shiftRight = new JButton("Shift to the right");
	private JButton shiftLeft = new JButton("Shift to the left");
	
	private Array7[] sideColsArray = new Array7[2];
	private JTextField[][] sideCols = new JTextField[2][7];
	private JPanel left = new JPanel();
	private JPanel down = new JPanel();
	private JPanel right = new JPanel();
	private JPanel[] center = new JPanel[5];
	private JPanel centerMain = new JPanel();
	
	private int column = 0;
	private int block = 0;
	
	/**
	 * Constructor that paints a display and initialises the array with numbers to be shifted. 
	 */
	public TestBigScreenSwing() {
		
		for (int i = 0; i < numbers.length; i++) {
			numbers[i] = new Array7x7();
		}
		
		for (int i = 0; i < numbers.length; i++) {
			for (int j = 0; j < 7; j++) {
				numbers[i].setCol(j, new Array7(j+5*i));
			}
		}	
		
		setLayout( new BorderLayout());
		setPreferredSize( new Dimension(730, 160));
		left.setLayout( new GridLayout(7, 1));
		left.setPreferredSize(new Dimension(20, 140));
		right.setLayout( new GridLayout (7,1));
		right.setPreferredSize(new Dimension(20, 140));
		down.setLayout( new GridLayout(1, 2));
		centerMain.setLayout( new GridLayout(1, 5));
		
		down.add(shiftRight); shiftRight.addActionListener( this );
		down.add(shiftLeft); shiftLeft.addActionListener( this );
		sideColsArray[0] = new Array7();
		sideColsArray[1] = new Array7();
		
		for (int i = 0; i < 7; i++) {
			sideCols[0][i] = new JTextField("*");
			sideCols[1][i] = new JTextField("*");
			sideCols[0][i].setHorizontalAlignment(SwingConstants.CENTER);
			sideCols[1][i].setHorizontalAlignment(SwingConstants.CENTER);
			sideCols[0][i].setBackground(Color.LIGHT_GRAY);
			sideCols[1][i].setBackground(Color.LIGHT_GRAY);
			left.add(sideCols[0][i]);
			right.add(sideCols[1][i]);
		}
		
		for (int m = 0; m < 5; m++) {	
			matrixArray[m] = new Array7x7();
			center[m] = new JPanel();
			center[m].setLayout( new GridLayout(7,7 ));
			center[m].setPreferredSize( new Dimension (140, 140));
			for (int j = 0; j < 7; j++) {
				for (int k = 0; k <7; k++) {	
					matrix[m][j][k] = new JLabel();
					matrix[m][j][k].setHorizontalAlignment(SwingConstants.CENTER);
					matrix[m][j][k].setBorder(BorderFactory.createLineBorder(Color.GRAY));
					matrix[m][j][k].setOpaque(true);
					center[m].add(matrix[m][j][k]);	
				}
			}
			centerMain.add(center[m], BorderLayout.CENTER);	
		}
		
		add(left, BorderLayout.WEST);
		add(right, BorderLayout.EAST);	
		add(down, BorderLayout.SOUTH);
		add(centerMain, BorderLayout.CENTER);
	}
	
	/**
	 * Method that listens to the buttons. 
	 */
	public void actionPerformed(ActionEvent e) {
		try {
			if (e.getSource() == shiftRight) {					
				timer = new Timer(); // new timer has to be created at each click on the button 
				timer.schedule(new ShiftRight(),10, 200);
			} else if (e.getSource() == shiftLeft) {	
				timer = new Timer(); // new timer has to be created at each click on the button 
				timer.schedule(new ShiftLeft(), 10, 200);
			}
		} catch (ArrayIndexOutOfBoundsException err) {
			// pops up if the index entered is invalid.
			JOptionPane.showMessageDialog(null, "Such row or column doesn't exist!");
		} catch (NumberFormatException err2) {
			// pops up if the num of the column/row is null, and if the input arrays are not full --> fix (confusing)
			JOptionPane.showMessageDialog(null, "First enter the number of a column or a row to work with.\n"+
					"Or make sure there's data in the matrix work with. ");
		}
	}
			
	/**
	 * Private class that launches the data to the left on the display and updates it.
	 * @author Aliona
	 *
	 */
	private class ShiftLeft extends TimerTask {
		Array7 right = new Array7();
		boolean reset = false;
		
		public void run() {
			if ((column == 6) && (block == numbers.length-1) && (!reset)) {
				column = 0;
				block = 0;
				reset = true; // needed to make the shifting stop.
			}	
			
			right.setArray7(numbers[block].getCol(column));		
			matrixArray[0].shiftLeft(sideColsArray[0], matrixArray[1].getCol(0));
			matrixArray[1].shiftLeft(matrixArray[0].getCol(6), matrixArray[2].getCol(0));			
			matrixArray[2].shiftLeft(matrixArray[1].getCol(6), matrixArray[3].getCol(0));
			matrixArray[3].shiftLeft(matrixArray[2].getCol(6), matrixArray[4].getCol(0));
			matrixArray[4].shiftLeft(matrixArray[3].getCol(6), right);

			updateDisplay();
			
			if ((column == 6) && (block == numbers.length-1)) {
				timer.cancel();
			} else if (column == 6) {
				column = 0;
				block += 1;
			} else {
				column++;	
			}
		}
	}
	
	/**
	 * Private class that launches the data to the right on the display and updates it.
	 * @author Aliona
	 *
	 */
	private class ShiftRight extends TimerTask {
		Array7 left = new Array7();
		boolean reset = false;
			
		public void run() {	
			if ((column == 0) && (block == 0) && (!reset)) {
				column = 6;
				block = numbers.length - 1;
				reset = true; // needed to make the shifting stop.
			}	
			
			left.setArray7(numbers[block].getCol(column));	
			matrixArray[4].shiftRight(matrixArray[3].getCol(6), sideColsArray[1]);
			matrixArray[3].shiftRight(matrixArray[2].getCol(6), matrixArray[4].getCol(0));
			matrixArray[2].shiftRight(matrixArray[1].getCol(6), matrixArray[3].getCol(0));
			matrixArray[1].shiftRight(matrixArray[0].getCol(6), matrixArray[2].getCol(0));
			matrixArray[0].shiftRight(left, matrixArray[1].getCol(0));		
			updateDisplay();
			
			if ((column == 0) && (block == 0)) {
				timer.cancel();
			} else if (column == 0) {
				column = 6;
				block -= 1;				
			} else {
				column--;
			}
		}
	}
	
	/**
	 * Method that updates the display during each execution of the TimerTask classes.
	 */
	public void updateDisplay() {
		for (int i = 0; i < 7; i++) {
			sideCols[0][i].setText(String.valueOf(sideColsArray[0].getElement(i)));
			sideCols[1][i].setText(String.valueOf(sideColsArray[1].getElement(i)));			
		}
		
		for (int m = 4; m >= 0; m--) {
			for (int p = 6; p >= 0; p--) {
				for (int k = 6; k >= 0; k--) {
					matrix[m][p][k].setText(String.valueOf(matrixArray[m].getElement(p, k)));
					matrix[m][p][k].setBackground(new Color(matrixArray[m].getElement(p, k), matrixArray[m].getElement(p, k), 0));
				}
			}
		}
	}
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				TestBigScreenSwing test = new TestBigScreenSwing();
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
