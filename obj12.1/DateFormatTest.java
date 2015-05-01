import java.util.*;
import java.text.*;
import static java.lang.System.out;

public class DateFormatTest {
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
			DateFormat.getDateTimeInstance(DateFormat.FULL, DateFormat.FULL)
		};

		for (DateFormat df : dfa) {
			out.println(df.format(dt));
		}
	}

	static void parse(Date d) {
		DateFormat df = DateFormat.getDateInstance(DateFormat.FULL);
		String dStr = df.format(d);
		String dtStr = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT).format(d);
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