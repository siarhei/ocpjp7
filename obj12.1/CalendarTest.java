import java.util.*;
import static java.lang.System.out;

public class CalendarTest {
	public static void main(String[] args) {
		Date dt = new Date(1_000_000_000_000L);

		Calendar cl = Calendar.getInstance();
		cl.setTime(dt);

		switch (cl.getFirstDayOfWeek()) {
			case Calendar.SUNDAY: out.println("Sunday is the first day of week"); 
								  break;
		    case Calendar.MONDAY: out.println("Monday is the first day of week"); 
								  break;
			default: throw new AssertionError("unknown day");
		}

		out.println("original: " + dt);
		out.println("day of week was: " + cl.get(Calendar.DAY_OF_WEEK));
		out.println("day of month was: " + cl.get(Calendar.DAY_OF_MONTH));
		out.println("day of year was: " + cl.get(Calendar.DAY_OF_YEAR));
		out.println("month was: " + cl.get(Calendar.MONTH));

		cl.add(Calendar.MONTH, 2);
		out.println("added 2 m: " + cl.getTime());

		cl.roll(Calendar.DAY_OF_MONTH, 31);
		out.println("rolled 31 days: " + cl.getTime());
	}
}