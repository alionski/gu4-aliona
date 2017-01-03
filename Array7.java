package final_version;
/**
 * Exercise 3.2.1 (Moment 1)
 * Class that contains an integer array and constructors and methods to store and retrieve data in/from it. 
 * @author Aliona
 *
 */
public class Array7 {
	private int[] array7 = new int[7];
	
	/**
	 * Constructor that creates an Array7 object, where the array contains zeroes. 
	 */
	public Array7() {
		this(0);
	}
	
	/**
	 * Constructor that creates an Array7 object, where the array has the user's values. 
	 * @param value: the integer to be stored in each cell of the array.
	 */
	public Array7(int value) {
		for (int i = 0; i<array7.length; i++) {
			array7[i] = value;
		}
	}
	
	/**
	 * Method to set a particular value in the array.
	 * @param pos: index of the element in the array.
	 * @param value: new value to be stored at the specified index. 
	 */
	public void setElement(int pos, int value) {
		array7[pos] = value;
	}
	
	/**
	 * Method to replace the given object's array's value with the values from another Array7 object.
	 * @param newArray: Array7 object to take values from. 
	 */
	public void setArray7(Array7 newArray) {
		for (int i = 0; i < array7.length; i++) {
			this.setElement(i, newArray.getElement(i));
		}
	}
	
	/**
	 * Method that returns the object's array.
	 * @return array7
	 */
	public int[] getArray7() {
		return array7;
	}
	
	/**
	 * Method that returns the value at a particular position.
	 * @param pos: the index of the needed value. 
	 * @return value at the given position in the object's array.
	 */
	public int getElement(int pos) {
		return array7[pos];
	}
	
	/**
	 * Returns the array as a printable String representation. 
	 */
	public String toString() {
		String res = "";
		for (int i = 0; i <array7.length; i++) {
			res += array7[i] + "\t";
		}
		return res;
	}
}
