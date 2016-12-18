import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

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
	
	private class MyTextListener implements ActionListener {
		public void actionPerformed(ActionEvent t) {
			for (int i = 0; i <7; i++) {
				for (int j = 0; j < 7; j++) {
					if (t.getSource() == matrix[i][j]) {
						int elem = Integer.parseInt(matrix[i][j].getText());
						matrixArray.setElement(i, j, elem );
						System.out.println("it worked, elem: " + elem + " ");
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
			// throws the exception even where there's no data in the input labels because Array7 object are 
			// created with the values 0 in the constructor --> set to null in the constructor maybe. 
			JOptionPane.showMessageDialog(null, "Such row or column doesn't exist!");
		} catch (NumberFormatException err2) {
			// pops up if the num of the column/row is null, and if the input arrays are not full --> fix (confusing)
			JOptionPane.showMessageDialog(null, "First enter the number of a column or a row to work with.\n"+
					"Or make sure there's data in the matrix work with. ");
		}
	}
		
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
