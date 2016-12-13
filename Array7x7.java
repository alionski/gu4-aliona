
public class Array7x7 {
	private int[][] array7x7 = new int[7][7];
	
	public Array7x7() {
		for (int row = 0; row < array7x7.length; row++) {
			for (int col = 0; col < array7x7[row].length; col++) {
				array7x7[row][col] = 0;
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
	
	public void cleanCol(int col) {
		for (int i=0; i<7; i++) {
			array7x7[i][col] = 0;
		}
	}
}
