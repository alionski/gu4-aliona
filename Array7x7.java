package final_version;
/**
 * Class responsible for creating an Array7x7 object and shifting data in its two-dimensional array.
 * @author Aliona
 *
 */
public class Array7x7 {
	private int[][] array7x7 = new int[7][7];
	Array7 buffer = new Array7();
	
	/**
	 * Constructor that creates an Array7x7 object, where all the values in the array are 0. 
	 */
	public Array7x7() {
		this(0);
	}
	
	/**
	 * Constructor that creates an Array7x7 object, where all the values in the array are the given value. 
	 * @param value: integer to be stores in all of the cells. 
	 */
	public Array7x7(int value) {
		for (int row = 0; row < array7x7.length; row++) {
			for (int col = 0; col < array7x7[row].length; col++) {
				array7x7[row][col] = value;
			}
		}
	}
	
	/**
	 * Constructor that accepts a two-dimensional array and maps it onto this object's array.
	 * @param value: the two-dimensional integer array to take values from.
	 */
	public Array7x7(int[][] value) {
		for (int row = 0; row < array7x7.length; row++) {
			for (int col = 0; col < array7x7[row].length; col++) {
				array7x7[row][col] = value[row][col];
			}
		}
	}
	
	/**
	 * Constructor that takes in another Array7x7 object.
	 * @param array7x7: the Array7x7 object to take values from.
	 */
    public Array7x7(Array7x7 array7x7) {
        setArray(array7x7);
    }	
	
    /**
     * Stores a given value in a particular cell. 
     * @param row: the index of the row.
     * @param col: the index of the column.
     * @param value: the value to be stored. 
     */
	public void setElement(int row, int col, int value) {
		array7x7[row][col] = value;
	}
	
	/**
	 * Maps an Array7 object onto a particular row in the Array7x7 object. 
	 * @param row: the index of the row.
	 * @param theRow: an Array7 object to be transfered to the given row. 
	 */
	public void setRow(int row, Array7 theRow) {
		for (int i=0; i< 7; i++) {
			array7x7[row][i] = theRow.getElement(i);		
		}
	}
	
	/**
	 * Maps an Array7 object onto a particular column in the Array7x7 object. 
	 * @param col: the index of the column.
	 * @param theCol: an Array7 object to be transfered to the given column.
	 */
	public void setCol(int col, Array7 theCol) {
		for (int i=0; i< 7; i++) {
			array7x7[i][col] = theCol.getElement(i);		
		}
	}
		
	/**
	 * Copies values from a given Array7x7 object to this object. 
	 * @param newArray: the Array7x7 object to copy from.
	 */
	public void setArray(Array7x7 newArray) {
		for (int row = 0; row < array7x7.length; row++) {
			for (int col = 0; col < array7x7[row].length; col++) {
				array7x7[row][col] = newArray.getElement(row, col);
			}
		}
	}
	
	/**
	 * Returns the two-dimensional integer array stores in this object. 
	 * @return: int[][] of this Array7x7 object.
	 */
	public int[][] getArray() {
		return array7x7;
	}
	
	/**
	 * Returns an Array7 object containing the column and the given index. 
	 * @param col: the index of the column.
	 * @return Array7 object containing the column.
	 */
	public Array7 getCol(int col) {
		Array7 colObj = new Array7();
		for (int i = 0; i <7; i++) {
			colObj.setElement(i, array7x7[i][col]);
		}
		return colObj;
	}
	
	/**
	 * Method that returns an Array7 object containing a certain row from this object's 2D array.
	 * @param row: the index of the row to extract. 
	 * @return: Array7 object with values from the given row.
	 */
	public Array7 getRow(int row) {
		Array7 rowObj = new Array7();
		for (int i = 0; i<7; i++) {
			rowObj.setElement(i, array7x7[row][i]);
		}
		return rowObj;
	}
	
	/**
	 * Method that returns the element at the specified row and column indices. 
	 * @param row: the index of the row.
	 * @param col: the index of the column.
	 * @return: integer at the specicified position in the 2D array.
	 */
	public int getElement(int row, int col) {
		return array7x7[row][col];
	}
	
	/**
	 * Method used by various classes to shift all the elements in an Array7x7 object one index to the left. 
	 * @param left: Array7 object to transfer data to from the zero-eth column of the Array7x7 object. 
	 * @param right:  Array7 object which serves as a "donor" and from which data gets transfered to the last 
	 * column of the Array7x7 object. 
	 */
	public void shiftLeft(Array7 left, Array7 right) {				
		
		//copying from array7x7[0] to the column on the left side. 
		left.setArray7(getCol(0));
		
		// shifting the 7x7 columns and copying from the right column. 	
		for (int i =0; i < array7x7.length; i++) {
			if (i == 6) {			
				buffer.setArray7(right);
				right.setArray7( new Array7());
			} else {
				buffer.setArray7(this.getCol(i+1));
			}			
			for (int p = 0; p < 7; p++) {
				this.setCol(i, buffer);
			}			
		}
	}
		
	/**
	 * Overloaded version of the method above. Used by various classes to shift all the elements in an Array7x7 
	 * object one index to the left. Used when there's no need to shift data further to the left beyond the 
	 * boundaries of the Array7x7 object. 
	 * @param right:  Array7 object which serves as a "donor" and from which data gets transfered to the last 
	 * column of the Array7x7 object. 
	 */
	public void shiftLeft(Array7 right) {				
	
		// shifting the 7x7 columns and copying from the right column. 	
		for (int i =0; i < array7x7.length; i++) {
			if (i == 6) {			
				buffer.setArray7(right);
				right.setArray7( new Array7());
			} else {
				buffer.setArray7(this.getCol(i+1));
			}			
			for (int p = 0; p < 7; p++) {
				this.setCol(i, buffer);
			}			
		}
	}
	
	/**
	 * Method used by various classes to shift all the elements in an Array7x7 object one index to the right. 
	 * @param left: Array7 object which serves as a "donor" and from which data gets transfered to the zero-eth 
	 * column of the Array7x7 object. 
	 * @param right: Array7 object to transfer data to from the last column of the Array7x7 object.   
	 */
	public void shiftRight(Array7 left, Array7 right) {		
		
		// copying from the last 7x7 column to the right.
		right.setArray7(this.getCol(6));	
		// shifting the 7x7 columns and copying from the left column. 	
		for (int i = array7x7.length-1; i >= 0; i--) {
			if (i == 0) {
				buffer.setArray7(left);
				left.setArray7( new Array7());
			} else {
				buffer.setArray7(this.getCol(i-1));				
			}					
			for (int p = 0; p < 7; p++) {
				this.setCol(i, buffer);
			}
		}
	}
	
	/**
	 * Overloaded version of the method above. Used by various classes to shift all the elements in an Array7x7 
	 * object one index to the right. Used when there's no need to shift data further to the right beyond the 
	 * boundaries of the Array7x7 object. 
	 * @param left:  Array7 object which serves as a "donor" and from which data gets transfered to the zero-eth
	 * column of the Array7x7 object. 
	 */
	public void shiftRight(Array7 left) {		

		// shifting the 7x7 columns and copying from the left column. 	
		for (int i = array7x7.length-1; i >= 0; i--) {
			if (i == 0) {
				buffer.setArray7(left);
				left.setArray7( new Array7());
			} else {
				buffer.setArray7(this.getCol(i-1));				
			}					
			for (int p = 0; p < 7; p++) {
				this.setCol(i, buffer);
			}
		}
	}
	
	/**
	 * Returns a printable representation of this object's 2D array.
	 */
	public String toString() {
		String res = "Array 7x7:\n";
		for (int i = 0; i < array7x7.length; i++) {
			for (int j = 0; j < array7x7[i].length; j++) {
				res += array7x7[i][j] + "\t";
			}
			res += "\n";
		}
		return res;
	}
}
