
public class Array7 {
	private int[] array7 = new int[7];
	
	public Array7() {
		for (int i = 0; i<array7.length; i++) {
			array7[i] = 0;
		}
	}
	
	public void setElement(int pos, int value) {
		array7[pos] = value;
	}
	public int getElement(int pos) {
		return array7[pos];
	}
	
	public void setArray7(Array7 newArray) {
		for (int i = 0; i < array7.length; i++) {
			this.setElement(i, newArray.getElement(i));
		}
	}
	
	public int[] getArray7() {
		return array7;
	}
	
	public String toString() {
		String res = "";
		for (int i = 0; i <array7.length; i++) {
			res += array7[i] + "\t";
		}
		return res;
	}
}
