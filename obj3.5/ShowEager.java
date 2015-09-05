import java.util.*;

public class ShowEager {
	//eager initialization
	private static final ShowEager INSTANCE = new ShowEager();
	public static ShowEager getInstance() {return INSTANCE;}

	private Set<String> availableSeats;

	private ShowEager(){
		this.availableSeats = new HashSet<String>();
		availableSeats.add("1A");
		availableSeats.add("1B");
	}

	public boolean bookSeat(String seat) {
		return availableSeats.remove(seat);
	}

	public static void main(String[] args) {
		ticketAgentBooks("1A");
		ticketAgentBooks("1A");
	}

	private static void ticketAgentBooks(String seat) {
		ShowEager s = ShowEager.getInstance();
		System.out.println(s.bookSeat(seat));
	}
}