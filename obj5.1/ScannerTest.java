//java ScannerTest "\d{2}"

import java.util.Scanner;
import static java.lang.System.out;
import static java.lang.System.in;

public class ScannerTest {
	public static void main(String[] args) {
		out.println("input: ");
		out.flush();
		try {
			Scanner scr = new Scanner(in);
			String token;
			do {
				token = scr.findInLine(args[0]);
				out.println("found: " + token);
			} while (token != null);
		} catch (Exception e) {
			out.println("Scanner error");
		}
	}
}