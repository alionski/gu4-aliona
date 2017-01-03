package final_version;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

/**
 * Class that demonstrates how to move data (numbers) on a swing display (i.e. without Rolf's ColorDisplay)
 * Can be easily adapted to shift other arrays with a different type of data (colours) as the logic is the same.
 * No need to enter any numbers because it uses a predefined array with numbers to show the flow of data. The gray columns
 * on both sides are basically useless.
 * @author Aliona
 *
 */
public class TestRunningText extends JPanel implements ActionListener {
	private Timer timer;
	private Array7x7 matrixArray = new Array7x7();
	private Array7x7[] numbers = new Array7x7[7];
	private JTextField[][] matrix = new JTextField[7][7];
	private JButton shiftRight = new JButton("Shift to the right");
	private JButton shiftLeft = new JButton("Shift to the left");
	private Array7[] sideColsArray = new Array7[2];
	private JTextField[][] sideCols = new JTextField[2][7];
	private JPanel left = new JPanel();
	private JPanel down = new JPanel();
	private JPanel right = new JPanel();
	private JPanel center = new JPanel();

	public TestRunningText() {
		
		for (int i = 0; i < numbers.length; i++) {
			numbers[i] = new Array7x7();
		}
		
		for (int i = 0; i < numbers.length; i++) {
			for (int j = 0; j < 7; j++) {
				numbers[i].setCol(j, new Array7(j+10*i));
			}
		}	
		
		setLayout( new BorderLayout());
		setPreferredSize( new Dimension(360,300));
		left.setLayout( new GridLayout(7, 1));
		left.setPreferredSize(new Dimension(40, 280));
		right.setLayout( new GridLayout (7,1));
		right.setPreferredSize(new Dimension(40, 280));
		down.setLayout( new GridLayout(1, 2));
		center.setLayout( new GridLayout(7,7 ));
		center.setPreferredSize( new Dimension (280, 280));
		
		down.add(shiftRight); shiftRight.addActionListener( this );
		down.add(shiftLeft); shiftLeft.addActionListener( this );
		sideColsArray[0] = new Array7();
		sideColsArray[1] = new Array7();
		
		for (int i = 0; i < 7; i++) {
			sideCols[0][i] = new JTextField("");
			sideCols[0][i].addActionListener( new MyTextListener());
			sideCols[1][i] = new JTextField("");
			sideCols[1][i].addActionListener( new MyTextListener());
			sideCols[0][i].setHorizontalAlignment(SwingConstants.CENTER);
			sideCols[1][i].setHorizontalAlignment(SwingConstants.CENTER);
			sideCols[0][i].setBackground(Color.LIGHT_GRAY);
			sideCols[1][i].setBackground(Color.LIGHT_GRAY);
			left.add(sideCols[0][i]);
			right.add(sideCols[1][i]);
			for (int j = 0; j < 7; j++) {
				matrix[i][j] = new JTextField("");
				matrix[i][j].addActionListener( new MyTextListener());
				matrix[i][j].setHorizontalAlignment(SwingConstants.CENTER);
				matrix[i][j].setBorder(BorderFactory.createLineBorder(Color.BLACK));
				matrix[i][j].setBackground(Color.YELLOW);
				center.add(matrix[i][j]);				
			}
		}
		
		add(left, BorderLayout.WEST);
		add(right, BorderLayout.EAST);	
		add(down, BorderLayout.SOUTH);
		add(center, BorderLayout.CENTER);	
	}
	
	/**
	 * Class that listens to the user input in JTextFields. The user must hit enter each time to store the new value in
	 * the arrays. 
	 * @author Aliona
	 */
	private class MyTextListener implements ActionListener {
		public void actionPerformed(ActionEvent t) {
			for (int i = 0; i <7; i++) {
				for (int j = 0; j < 7; j++) {
					if (t.getSource() == matrix[i][j]) {
						int elem = Integer.parseInt(matrix[i][j].getText());
						matrixArray.setElement(i, j, elem );
//						System.out.println("it worked, elem: " + elem + " ");
					}
				}
			}
			for (int k = 0; k <2; k++) {
				for (int l = 0; l < 7; l++) {
					if (t.getSource() == sideCols[k][l]) {
						sideColsArray[k].setElement(l, Integer.parseInt(sideCols[k][l].getText()));
					}
				}
			}
		}
	}
	
	/**
	 * Method that listens to the buttons. 
	 */
	public void actionPerformed(ActionEvent e) {
		try {
			if (e.getSource() == shiftRight) {					
				timer = new Timer();
				timer.schedule(new ShiftRight(), 300, 300);
			} else if (e.getSource() == shiftLeft) {	
				timer = new Timer();
				timer.schedule(new ShiftLeft(), 300, 300);
			}
			// catching exceptions is unnecessary in this class, but I'll let them be here because I just
			// ctrl+C, ctrl+V'ed when I was writing it :)
		} catch (ArrayIndexOutOfBoundsException err) {
			// pops up if the index entered is invalid.
			JOptionPane.showMessageDialog(null, "Such row or column doesn't exist!");
		} catch (NumberFormatException err2) {
			// pops up if the num of the column/row is null, and if the input arrays are not full
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
		private int column = 0;
		private int block = 0;
		private Array7 right = new Array7();
		
		public void run() {
			right.setArray7(numbers[block].getCol(column));		
			matrixArray.shiftLeft(sideColsArray[0], right);
			// debug print:
			System.out.println(matrixArray.toString());
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
	 */
	private class ShiftRight extends TimerTask {
		private int column = 6;
		private int block = numbers.length-1;
		private Array7 left = new Array7();
		
		public void run() {				
			left.setArray7(numbers[block].getCol(column));		
			matrixArray.shiftRight(left, sideColsArray[1]);
			// debug print:
			System.out.println(matrixArray.toString());
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
	 * Method that updates the screen after each execution of the TimerTask cklasses' methods. 
	 */
	public void updateDisplay() {
		for (int i = 0; i < 7; i++) {
			sideCols[0][i].setText(String.valueOf(sideColsArray[0].getElement(i)));
			sideCols[1][i].setText(String.valueOf(sideColsArray[1].getElement(i)));
			
			for (int k = 0; k <7; k++) {
				matrix[i][k].setText(String.valueOf(matrixArray.getElement(i, k)));
			}			
		}
	}
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				TestRunningText test = new TestRunningText();
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
