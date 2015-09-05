import java.util.*;

//thread safe
//lazy init
//the approach is available in Java 5 and later
public enum ShowEnum {
	INSTANCE;
	static {
		System.out.println("static block"); //2)
	}

	private Set<String> availableSeats;

	private ShowEnum(){
		System.out.println("constructor"); //1)
		this.availableSeats = new HashSet<String>();
		availableSeats.add("1A");
		availableSeats.add("1B");
	}

	public boolean bookSeat(String seat) {
		return availableSeats.remove(seat);
	}

	public static void main(String[] args) {
		System.out.println("main block"); //3)
		ticketAgentBooks("1A");
		ticketAgentBooks("1A");
	}

	private static void ticketAgentBooks(String seat) {
		System.out.println("in ticketAgentBooks"); //4)
		ShowEnum s = ShowEnum.INSTANCE;
		System.out.println(s.bookSeat(seat));
	}
}