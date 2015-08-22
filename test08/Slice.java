import java.text.*;
import java.util.Locale;

public class Slice {

private static Locale ORIGIN = Locale.getDefault();

static { Locale.setDefault(Locale.US); }

	public static void main(String[] args) {
		String s = "987.123456";
		double d = 987.123456d;
		NumberFormat nf = NumberFormat.getInstance();
		nf.setMaximumFractionDigits(5); //applies to the formatting, but not the parsing
		System.out.println(nf.format(d) + " ");
		try {
			System.out.println(nf.parse(s));
		} catch (Exception e) { 
			System.out.println("got exc"); 
		} finally {
			Locale.setDefault(ORIGIN);
		}
	}
}