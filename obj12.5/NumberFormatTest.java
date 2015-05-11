import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;
import static java.lang.System.*;

public class NumberFormatTest {
	public static void main(String[] args) {
		float pi = 3.14F;

		printNumber(pi, Locale.GERMANY);
		printCurrency(pi, Locale.GERMANY);

		printNumber(pi, new Locale("en", "US"));
		printCurrency(pi, new Locale("en", "US"));

		printNumber(pi, new Locale("pl"));
		printCurrency(pi, new Locale("it"));

		test();
	}

	static void printNumber(double num, Locale loc) {
		NumberFormat nf = NumberFormat.getInstance(loc);
		out.println("number ("+ loc.toString() + "):\t=>\t" + nf.format(num));
	}

	static void printCurrency(double num, Locale loc) {
		NumberFormat nf = NumberFormat.getCurrencyInstance(loc);
		out.println("currency (" + loc.toString() + "):\t=>\t" + nf.format(num));
	}

	static void test() {
		System.out.println("test:\n");
		float f = 123.45678f;
		NumberFormat nf = NumberFormat.getInstance(Locale.US);
		out.print(nf.getMaximumFractionDigits() + "\t");
		out.print(nf.format(f) + '\t');

		nf.setMaximumFractionDigits(5);
		out.print(nf.getMaximumFractionDigits() + "\t");
		out.println(nf.format(f) + '\t');

		try {
			out.println(nf.parse("123.456"));
			nf.setParseIntegerOnly(true);
			out.println(nf.parse("123.456"));
		} catch (ParseException e) {
			err.println("parsing error occurred");
		}
	}
}