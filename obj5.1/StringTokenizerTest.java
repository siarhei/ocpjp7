import static java.lang.System.out;
import java.util.StringTokenizer;

public class StringTokenizerTest {
	public static void main(String[] args) {
		StringTokenizer st = new StringTokenizer("ab cd   efg h\t k");
		out.println("st.countTokens():" + st.countTokens());
		while (st.hasMoreTokens()) {
			out.println(">" + st.nextToken() + "<");
		}

		//#with different splitter expression
		StringTokenizer st2 = new StringTokenizer("ab1cd 11 ef1g h\t1k", "1");
		out.println("st2.countTokens():" + st2.countTokens());
		while (st2.hasMoreTokens()) {
			out.println(">" + st2.nextToken() + "<");
		}
	}
}