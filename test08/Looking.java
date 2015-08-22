import java.util.*;

public class Looking {
	public static void main(String[] args) {
		String input = "1 2 a 3 45 6";
		Scanner sc = new Scanner(input);
		int x = 0;
		/*do {
			x = sc.nextInt();
			System.out.print(x + " ");
		//} while (x!=0);
		} while (sc.hasNextInt());*/

		//this block prints: 1 2 3 45 6
		while (sc.hasNext()) {
			if (sc.hasNextInt()) {
				x = sc.nextInt();
				System.out.print(x + " ");
			} else {
				sc.next();
			}
		}
	}
}

//:1 2 Exception in thread "main" java.util.InputMismatchException