package final_version;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * Test of Array7x7. 
 * Exercise 4.2.1 (Moment 2).
 * @author Aliona
 */

public class TestArray7x7 extends JPanel implements ActionListener {
	private JTextField[] rows = new JTextField[7];
	private JTextField[] cols = new JTextField[7];
	private JLabel[][] matrix = new JLabel[7][7];
	private JButton readRowBtn = new JButton("Read row");
	private JButton writeRowBtn = new JButton("Write row");
	private JButton readColBtn = new JButton("Read col");
	private JButton writeColBtn = new JButton("Write col");
	private JTextField inputRow = new JTextField();
	private JTextField inputCol = new JTextField();
	private Array7 colsArray = new Array7();
	private Array7 rowsArray = new Array7();
	private Array7x7 matrixArray = new Array7x7();
	
	private JPanel left = new JPanel();
	private JPanel down = new JPanel();
	private JPanel right = new JPanel();
	private JPanel center = new JPanel();
	
	/**
	 * Constructor that creates and  displays an interactive panel to write and read numbers. 
	 */
	public TestArray7x7(){
		setPreferredSize( new Dimension(565,430));
		setLayout(null);
		setBackground(Color.BLACK);
		left.setLayout( new GridLayout(7, 1));
		left.setBounds(10, 10, 50, 350);
		down.setLayout( new GridLayout(1, 7));
		down.setBounds(70, 370, 350, 50);
		center.setLayout( new GridLayout(7,7));
		center.setBounds(70, 10, 350, 350);
		right.setLayout(new GridLayout(6, 1));
		right.setBounds(450, 10, 100, 350);
		
		for (int i = 0; i<7; i++) {
			cols[i] = new JTextField();
			rows[i] = new JTextField();
			cols[i].setHorizontalAlignment(SwingConstants.CENTER);
			rows[i].setHorizontalAlignment(SwingConstants.CENTER);		
			left.add(cols[i]);
			down.add(rows[i]);
			for (int j=0; j < 7; j++) {
				matrix[i][j] = new JLabel();
				matrix[i][j].setHorizontalAlignment(SwingConstants.CENTER);
				matrix[i][j].setVerticalAlignment(SwingConstants.CENTER);
				matrix[i][j].setBorder(BorderFactory.createLineBorder(Color.BLACK));
				center.add(matrix[i][j]);
			}
		}
		
		right.add(readRowBtn); right.add(writeRowBtn);
		readRowBtn.setBackground(Color.DARK_GRAY); readRowBtn.setForeground(Color.WHITE);
		writeRowBtn.setBackground(Color.DARK_GRAY); writeRowBtn.setForeground(Color.WHITE);
		readRowBtn.addActionListener( this ); writeRowBtn.addActionListener( this);
		right.add(inputRow); inputRow.setHorizontalAlignment(SwingConstants.CENTER);	
		right.add(readColBtn); right.add(writeColBtn);
		readColBtn.setBackground(Color.DARK_GRAY); readColBtn.setForeground(Color.WHITE);
		writeColBtn.setBackground(Color.DARK_GRAY); writeColBtn.setForeground(Color.WHITE);
		readColBtn.addActionListener( this ); writeColBtn.addActionListener( this );
		right.add(inputCol); inputCol.setHorizontalAlignment(SwingConstants.CENTER);	
		
		add(left);
		add(down);
		add(center);
		add(right);	
	}
	
	/**
	 * ActionListener that listens to the four buttons.
	 */
	public void actionPerformed(ActionEvent e) {
		try {
			if (e.getSource() == readRowBtn) {					
				readRow();				
			} else if (e.getSource() == writeRowBtn) {								
				writeRow();				
			} else if (e.getSource() == readColBtn) {					
				readCol();				
			} else if (e.getSource() == writeColBtn) {					
				writeCol();				
			}
		} catch (ArrayIndexOutOfBoundsException err) {
			// when the user enters an invalid column/row index.
			JOptionPane.showMessageDialog(null, "Such row or column doesn't exist!");
		} catch (NumberFormatException err2) {
			// pops up if the num of the column/row is null, or if the input array (one of the two separate arrays on the
			// sides, depending on the source button) is not full.
			JOptionPane.showMessageDialog(null, "First enter the number of a column or a row to work with.\n"+
					"Or make sure there's data in the side column and row to work with. ");
		}
	}

	/**
	 * Method invoked when user presses "Read Row" button. 
	 * Extracts data from the given row in the Array7x7 object, stores it in the side row, and shows it 
	 * on the display.
	 * @throws ArrayIndexOutOfBoundsException: in case the user tries to write to/read from an invalid row.
	 * @throws NumberFormatException: in case the user enters a String or a floating point number. 
	 */
	public void readRow() throws ArrayIndexOutOfBoundsException, NumberFormatException {
		int row = Integer.parseInt(inputRow.getText())-1;		
		for (int i = 0; i < 7; i++) {
			int value = matrixArray.getRow(row).getElement(i);
			rowsArray.setElement(i, value);
			rows[i].setText(String.valueOf(value));				
		}
	}
	
	/**
	 * Method invoked when user presses "Write Row" button. 
	 * Extracts data from the lower row, stores it in the given row in the Array7x7 object, and shows it 
	 * on the display. 
	 * @throws ArrayIndexOutOfBoundsException: in case the user tries to write to/read from an invalid row.
	 * @throws NumberFormatException: in case the user enters a String or a floating point number. 
	 */
	public void writeRow() throws ArrayIndexOutOfBoundsException, NumberFormatException {
		int row = Integer.parseInt(inputRow.getText())-1;	
		for (int i = 0; i < 7; i++) {
			int value = Integer.parseInt(rows[i].getText());
			rowsArray.setElement(i, value);	
			matrix[row][i].setText(String.valueOf(value));
		}
		matrixArray.setRow(row, rowsArray);
	}
	
	/**
	 * Method invoked when user presses "Read Col" button. 
	 * Extracts data from the given column in the Array7x7 object, stores it in the side column, and shows it 
	 * on the display. 
	 * @throws ArrayIndexOutOfBoundsException: in case the user tries to write to/read from an invalid column.
	 * @throws NumberFormatException: in case the user enters a String or a floating point number. 
	 */
	public void readCol() throws ArrayIndexOutOfBoundsException, NumberFormatException {	
		int col = Integer.parseInt(inputCol.getText())-1;	
		for (int i = 0; i < 7; i++) {
			int data = matrixArray.getCol(col).getElement(i);
			cols[i].setText(String.valueOf(data));				
		}
	}
	
	/**
	 * Method invoked when user presses "Write Col" button. 
	 * Extracts data from the side column, stores it in the given column in the Array7x7 object and shows it 
	 * on the display. 
	 * @throws ArrayIndexOutOfBoundsException: in case the user tries to write to/read from an invalid column.
	 * @throws NumberFormatException: in case the user enters a String or a floating point number. 
	 */
	public void writeCol() throws ArrayIndexOutOfBoundsException, NumberFormatException {
		int col = Integer.parseInt(inputCol.getText())-1;		
		for (int i = 0; i < 7; i++) {
			int value = Integer.parseInt(cols[i].getText());
			colsArray.setElement(i, value);	
			matrix[i][col].setText(String.valueOf(value));
		}
		matrixArray.setCol(col, colsArray);
	}
	
	public static void main(String[] args) {
		TestArray7x7 test = new TestArray7x7();
		JFrame frame = new JFrame("Test");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(test);
		frame.pack();
		frame.setLocation(400,200);
		frame.setVisible(true);
	}

}
