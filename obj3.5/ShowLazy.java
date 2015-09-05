import java.util.*;

public class ShowLazy {
	private static /*final*/ ShowLazy INSTANCE/* = new ShowLazy()*/;
	public static ShowLazy getInstance() {
		//LAZY init
		if (INSTANCE == null) {
			INSTANCE = new ShowLazy();
		}
		return INSTANCE;
	}

	private Set<String> availableSeats;

	private ShowLazy(){
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
		ShowLazy s = ShowLazy.getInstance();
		System.out.println(s.bookSeat(seat));
	}
}