import java.util.regex.*;

class Quetico {
	public static void main(String [] args) {
		Pattern p = Pattern.compile(args[0]);
		Matcher m = p.matcher(args[1]);
		System.out.print("match positions: ");
		while(m.find()) {
			System.out.print(m.start() + " ");
		}
		System.out.println("");
	}
}
//java Quetico "\b" "^23 *$76 bc"
//match positions: 1 3 6 8 9 11

//java Quetico "\B" "^23 *$76 bc"
//match positions: 0 2 4 5 7 10

//java Quetico "\S" "^23 *$76 bc"
//match positions: 0 1 2 4 5 6 7 9 10

//java Quetico "\W" "^23 *$76 bc"
//match positions: 0 3 4 5 8