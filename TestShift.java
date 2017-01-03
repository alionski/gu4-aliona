package final_version;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/** 
 * Exercise 5.1.2 (Moment 3).
 * Class that tests the use of Array7x7 when shifting the contents to the right/left.
 * Don't forget to hit enter when adding numbers through the display. 
 * User must press enter after typing a number in a cell to enable MyTextListener to save it in the array.
 * @author Aliona
 */

public class TestShift extends JPanel implements ActionListener {
	private JTextField[][] sideCols = new JTextField[2][7];
	private JTextField[][] matrix = new JTextField[7][7];
	private JButton shiftRight = new JButton("Shift to the right");
	private JButton shiftLeft = new JButton("Shift to the left");
	
	private Array7[] sideColsArray = new Array7[2];
	private Array7x7 matrixArray = new Array7x7();
	
	private JPanel left = new JPanel();
	private JPanel down = new JPanel();
	private JPanel right = new JPanel();
	private JPanel center = new JPanel();
	
	/**
	 * Constructor that paints an interactive swing display. 
	 */
	public TestShift() {
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
	 * ActionListenet that listens to the JTextFields. Once the user hits enter, it stores the data in corresponding 
	 * cells in the corresponding array.
	 * @author Aliona
	 *
	 */
	private class MyTextListener implements ActionListener {
		public void actionPerformed(ActionEvent enter) {
			
			for (int row = 0; row <7; row++) {
				for (int col = 0; col < 7; col++) {
					if (enter.getSource() == matrix[row][col]) {
						int elem = Integer.parseInt(matrix[row][col].getText());
						matrixArray.setElement(row, col, elem );
						// debug print:
//						System.out.println("it worked, elem: " + elem + " ");
					}
				}
			}
			
			for (int rowSide = 0; rowSide <2; rowSide++) {
				for (int colSide = 0; colSide < 7; colSide++) {
					if (enter.getSource() == sideCols[rowSide][colSide]) {
						sideColsArray[rowSide].setElement(colSide, Integer.parseInt(sideCols[rowSide][colSide].getText()));
					}
				}
			}
		}
	}
	
	/**
	 * Method that listens to the buttons on the right side, transfers the specified data and updates the display.
	 */
	public void actionPerformed(ActionEvent e) {
		try {
			if (e.getSource() == shiftRight) {					
				matrixArray.shiftRight(sideColsArray[0], sideColsArray[1]);
				// debug print:
				System.out.println("Left column:\n" + sideColsArray[0].toString());
				System.out.println(matrixArray.toString());
				System.out.println("Right column:\n " + sideColsArray[1].toString());	
				updateDisplay();
			} else if (e.getSource() == shiftLeft) {								
				matrixArray.shiftLeft(sideColsArray[0], sideColsArray[1]);
				// debug print:
				System.out.println("Left column:\n" + sideColsArray[0].toString());
				System.out.println(matrixArray.toString());
				System.out.println("Right column:\n " + sideColsArray[1].toString());
				updateDisplay();
			}
		} catch (ArrayIndexOutOfBoundsException err) {
			// pops up if the entered index is invalid.
			JOptionPane.showMessageDialog(null, "Such row or column doesn't exist!");
		} catch (NumberFormatException err2) {
			// pops up if the index of the column/row is null and if the input arrays are not full
			JOptionPane.showMessageDialog(null, "First enter the number of a column or a row to work with.\n"+
					"Or make sure there's data in the matrix and side columns to work with. ");
		}
	}
		
	/**
	 * Method that updates the data on the display. It's needed because it doesn't happen automatically when the data 
	 * in the matrix and side column and row change. 
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
		TestShift test = new TestShift();
		JFrame frame = new JFrame("Test");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(test);
		frame.pack();
		frame.setLocation(400,200);
		frame.setVisible(true);
	}

}
