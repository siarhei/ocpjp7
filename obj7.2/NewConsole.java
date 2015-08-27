import java.io.Console;

public class NewConsole {
	public static void main(String[] args) {
		Console con = System.console();

		char[] pwd = null;
		char[] pwd2 = null;
		boolean isCorrect = true;
		do {
			if (!isCorrect) {
				con.format("%s", "Password doesn't match. Please, try again\n");
			}
			pwd = con.readPassword("%s", "enter password: ");
			pwd2 = con.readPassword("%s", "repeat password: ");
		} while (!(isCorrect = compare(pwd, pwd2)));

		con.format("%s", "password is correct\n");
		String email = con.readLine("%s", "enter email: ");
		con.format("An instruction will be sent to %s in %d seconds", email, 5);
	}

	private static boolean compare(char[] p1, char[] p2) {
		if (p1 != null && p2 != null) {
			if (p1.length == p2.length) {
				for (int i = 0; i < p1.length; i++) {
					if (p1[i] != p2[i]) {
						return false;
					}
				}
				return true;
			}
		}
		return false;
	}
}