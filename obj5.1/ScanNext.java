import java.util.Scanner;
import java.util.regex.Pattern;
import static java.lang.System.out;
import static java.lang.System.in;

public class ScanNext {
	public static void main(String[] args) {
		Scanner[] scanners = new Scanner[] {
			new Scanner(args[0]),
			new Scanner(args[0]).useDelimiter("\\d"),
			new Scanner(args[0]).useDelimiter(Pattern.compile(";"))
		};

		for (Scanner s : scanners) {
			process(s);
		}
	}

	private static void process(Scanner s) {
		out.println("Processing Scanner with delimiter: " + s.delimiter().pattern());
		while (s.hasNext()) {
			out.println(">" + s.next() + "<");
		}
	}
}