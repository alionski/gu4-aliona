
public class TestRunningText {
	private Array7x7[] text = new Array7x7[9];
	private Array7x7 display = new Array7x7();
	
	public TestRunningText() {
		for (int i = 0; i < text.length; i++) {
			text[i] = new Array7x7(i+1);
		}
	}
}
