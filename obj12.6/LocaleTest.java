import java.util.Locale;
import java.util.Date;
import java.util.Calendar;
import java.text.DateFormat;

public class LocaleTest {
	public static void main(String[] args) {
		final Calendar cal = Calendar.getInstance();
		cal.set(2015, 4, 11); //11.05.2015
		final Date dt = cal.getTime();

		print(dt, new Locale("ru", "RU"));
		print(dt, new Locale("en", "US"));
		print(dt, new Locale("it", "IT"));
		print(dt, new Locale("it", "CH"));
		print(dt, new Locale("en", "UK"));
		print(dt, new Locale("pt", "BR"));
		print(dt, new Locale("ja"));
		print(dt, new Locale("pl", "PL"));
		print(dt, new Locale("de"));
		print(dt, new Locale("fr"));
		print(dt, new Locale("da", "DK"));
		print(dt, new Locale("lt"));
		print(dt, new Locale("lv"));
	}

	static void print(Date dt, Locale loc) {
		DateFormat df = DateFormat.getDateInstance(DateFormat.FULL, loc);
		System.out.println(loc.toString() + "\t=>\t" + df.format(dt));
	}

}