
public class Array7x7 {
	private int[][] array7x7 = new int[7][7];
	
	public Array7x7() {
		for (int row = 0; row < array7x7.length; row++) {
			for (int col = 0; col < array7x7[row].length; col++) {
				array7x7[row][col] = 0;
			}
		}
	}
	
	public Array7x7(int value) {
		for (int row = 0; row < array7x7.length; row++) {
			for (int col = 0; col < array7x7[row].length; col++) {
				array7x7[row][col] = value;
			}
		}
	}
	
	public void setElement(int row, int col, int value) {
		array7x7[row][col] = value;
	}
	
	public int getElement(int row, int col) {
		return array7x7[row][col];
	}
	
	public void setRow(int row, Array7 theRow) {
		for (int i=0; i< 7; i++) {
			array7x7[row][i] = theRow.getElement(i);		
		}
	}
	
	public Array7 getRow(int row) {
		Array7 rowObj = new Array7();
		for (int i = 0; i<7; i++) {
			rowObj.setElement(i, array7x7[row][i]);
		}
		return rowObj;
	}
	
	public void setCol(int col, Array7 theCol) {
		for (int i=0; i< 7; i++) {
			array7x7[i][col] = theCol.getElement(i);		
		}
	}
	
	public Array7 getCol(int col) {
		Array7 colObj = new Array7();
		for (int i = 0; i <7; i++) {
			colObj.setElement(i, array7x7[i][col]);
		}
		return colObj;
	}
	
	public void shiftLeft(Array7 left, Array7 right) {				
		Array7 buffer = new Array7();
		
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
	
	public void shiftRight(Array7 left, Array7 right) {		
		Array7 buffer = new Array7();
		
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
