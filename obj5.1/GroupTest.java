import java.util.regex.*;
import static java.lang.System.out;

public class GroupTest {
	public static void main(String[] args) {
		Pattern p = Pattern.compile(args[0]);
		Matcher m = p.matcher(args[1]);
		out.println("\nsource: " + args[1]);
		out.println(" index: 012345678901234567890\n");
		out.println("expression: " + m.pattern());
		while(m.find()) {
			out.println(m.start() + "\t" + m.group());
		}
	}
}