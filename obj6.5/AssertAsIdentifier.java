//javac -source 1.3 must be used for success compilation

public class AssertAsIdentifier {
	public static void main(String[] args) {
		int assert = 5; //error: as of release 1.4, 'assert' is a keyword, and may not be used as an identifier
		System.out.println("Using assert as identifier: " + assert);
	}
}