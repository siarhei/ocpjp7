import java.util.*;

public class ShowSync {
	private static /*final*/ ShowSync INSTANCE/* = new ShowSync()*/;

	//synchronized access (too slow for each time access. Double checked locking is prefferred)
	public synchronized static ShowSync getInstance() {
		//LAZY init
		if (INSTANCE == null) {
			INSTANCE = new ShowSync();
		}
		return INSTANCE;
	}

	private Set<String> availableSeats;

	private ShowSync(){
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
		ShowSync s = ShowSync.getInstance();
		System.out.println(s.bookSeat(seat));
	}
}