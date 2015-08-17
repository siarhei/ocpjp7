public class StringSplitTest {
	private static final String STR = "ab 123 cd$, 44:5-9 89";

	public static void main(String[] args) {
		print(STR.split("\\d"));
		print(STR.split("\\s"));
	}

	private static void print(String[] tokens) {
		System.out.println("splitting...");
		for (String token : tokens) {
			System.out.println(">" + token + "<");
		}	
	}
}