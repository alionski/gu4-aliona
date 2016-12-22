import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * Test of Array7x7. One of the first assignments. 
 * @author Aliona
 *
 */

public class TestArray7x7 extends JPanel implements ActionListener {
	private JTextField[] rows = new JTextField[7];
	private JTextField[] cols = new JTextField[7];
	private JLabel[][] matrix = new JLabel[7][7];
	private JButton readRow = new JButton("Read row");
	private JButton writeRow = new JButton("Write row");
	private JButton readCol = new JButton("Read col");
	private JButton writeCol = new JButton("Write col");
	private JTextField inputRow = new JTextField();
	private JTextField inputCol = new JTextField();
	private Array7 colsArray = new Array7();
	private Array7 rowsArray = new Array7();
	private Array7x7 matrixArray = new Array7x7();
	
	private JPanel left = new JPanel();
	private JPanel down = new JPanel();
	private JPanel right = new JPanel();
	private JPanel center = new JPanel();
	
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
		
		right.add(readRow); right.add(writeRow);
		readRow.setBackground(Color.DARK_GRAY); readRow.setForeground(Color.WHITE);
		writeRow.setBackground(Color.DARK_GRAY); writeRow.setForeground(Color.WHITE);
		readRow.addActionListener( this ); writeRow.addActionListener( this);
		right.add(inputRow); inputRow.setHorizontalAlignment(SwingConstants.CENTER);	
		right.add(readCol); right.add(writeCol);
		readCol.setBackground(Color.DARK_GRAY); readCol.setForeground(Color.WHITE);
		writeCol.setBackground(Color.DARK_GRAY); writeCol.setForeground(Color.WHITE);
		readCol.addActionListener( this ); writeCol.addActionListener( this );
		right.add(inputCol); inputCol.setHorizontalAlignment(SwingConstants.CENTER);	
		
		add(left);
		add(down);
		add(center);
		add(right);	
	}
	
	public void actionPerformed(ActionEvent e) {
		try {
			if (e.getSource() == readRow) {					
				readRowAction();				
			} else if (e.getSource() == writeRow) {								
				writeRowAction();				
			} else if (e.getSource() == readCol) {					
				readColAction();				
			} else if (e.getSource() == writeCol) {					
				writeColAction();				
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

	
	public void readRowAction() throws ArrayIndexOutOfBoundsException, NumberFormatException {
		int row = Integer.parseInt(inputRow.getText())-1;		
		for (int i = 0; i < 7; i++) {
			int value = matrixArray.getRow(row).getElement(i);
			rowsArray.setElement(i, value);
			rows[i].setText(String.valueOf(value));				
		}
	}
	
	public void writeRowAction() throws ArrayIndexOutOfBoundsException, NumberFormatException {
		int row = Integer.parseInt(inputRow.getText())-1;	
		for (int i = 0; i < 7; i++) {
			int value = Integer.parseInt(rows[i].getText());
			rowsArray.setElement(i, value);	
			matrix[row][i].setText(String.valueOf(value));
		}
		matrixArray.setRow(row, rowsArray);
	}
	
	public void readColAction() throws ArrayIndexOutOfBoundsException, NumberFormatException {	
		int col = Integer.parseInt(inputCol.getText())-1;	
		for (int i = 0; i < 7; i++) {
			int data = matrixArray.getCol(col).getElement(i);
			cols[i].setText(String.valueOf(data));				
		}
	}
	
	public void writeColAction() throws ArrayIndexOutOfBoundsException, NumberFormatException {
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
