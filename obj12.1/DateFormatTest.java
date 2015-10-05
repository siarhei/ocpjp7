import java.util.*;
import java.text.*;
import static java.lang.System.out;

public class DateFormatTest {
	private static Locale US = new Locale("en", "US");

	public static void main(String[] args) {
		Date dt = new Date(1_000_000_000_000L);

		format(dt);
		parse(dt);
	}

	static void format(Date dt) {
		DateFormat[] dfa = {
			DateFormat.getInstance(),
			DateFormat.getDateInstance(),
			DateFormat.getDateInstance(DateFormat.SHORT),
			DateFormat.getDateInstance(DateFormat.MEDIUM),
			DateFormat.getDateInstance(DateFormat.LONG),
			DateFormat.getDateInstance(DateFormat.FULL),
			DateFormat.getDateTimeInstance(),
			DateFormat.getDateTimeInstance(DateFormat.FULL, DateFormat.FULL),
			DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT, Locale.US)
		};

		for (DateFormat df : dfa) {
			out.println(df.format(dt));
		}
	}

	static void parse(Date d) {
		//default locale
		DateFormat df = DateFormat.getDateInstance(DateFormat.FULL);
		//DateFormat df = DateFormat.getDateInstance(DateFormat.FULL, US);
		String dStr = df.format(d);

		DateFormat dtf = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT);
		//DateFormat dtf = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT, US);
		String dtStr = dtf.format(d);
		
		out.println("formatted date: " + dStr);
		out.println("formatted datetime: " + dtStr);

		try {
			out.println("parsed date: " + df.parse(dStr));
			out.println("parsed datetime: " + df.parse(dtStr)); //Unparseable date: "09.09.01 5:46"
		} catch (ParseException pe) {
			out.println("parse exception occurred: " + pe);
			pe.printStackTrace();
		}
	}
}