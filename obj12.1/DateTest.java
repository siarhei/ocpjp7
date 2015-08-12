import java.util.Date;

public class DateTest {
	public static void main(String []args) {
		Date dt = new Date(1_000_000_000_000L);
		System.out.println("original date: " + dt.toString());

		dt.setTime(dt.getTime() + 3_600_000L); //add 1 h
		System.out.println("new date: " + dt.toString());
	}
}