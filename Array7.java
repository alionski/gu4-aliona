
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
	
	public Array7 clone() {
		Array7 array = new Array7();
		for (int i = 0; i < 7; i++) {
			array.setElement(i,this.getElement(i));
//			this.setElement(i, 0);
		}
		return array;
	}
}
