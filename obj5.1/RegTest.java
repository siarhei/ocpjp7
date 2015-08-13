import java.util.regex.*;

class RegTest {
	public static void main(String [] args) {
		Pattern p = Pattern.compile(args[0]); //regex pattern
		Matcher m = p.matcher(args[1]); // string to search
		System.out.println("\nsource: " + args[1]);
		System.out.println(" index: 01234567890123456\n"); // the index
		System.out.println("expression: " + m.pattern()); // the search expression
		System.out.print("match positions: "); // matches positions
		while(m.find()) {
			System.out.print(m.start() + " ");
		}
		System.out.println("");
	}
}