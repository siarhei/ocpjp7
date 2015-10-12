import java.util.concurrent.locks.*;
import java.util.*;

public class ReadWriteLockTest {

	public static void main(String[] args) {
		final ProtectedCollection pc = new ProtectedCollection();

		Runnable writer = new Runnable() {
			private final String[] names = {
				"cranium",
				"taran2L",
				"bobbyLee",
				"noodless",
				"beatles",
				"keks",
				"d1ma"				
			};
			private final Random r = new Random(111L);

			public void run() {
				for (String name : names) {
					pc.put(name, Long.valueOf(r.nextInt(10000)));
				}
			}
		};

		Runnable reader = new Runnable() {
			public void run() {
				System.out.format("Top names: %s%n", Arrays.asList(pc.names()));
			}
		};

		pc.clear();
		new Thread(reader).start();
		new Thread(writer).start();
		new Thread(reader).start();
		new Thread(reader).start();
	}

	static class ProtectedCollection {
		private final ReadWriteLock rwl = new ReentrantReadWriteLock();
		private final Lock r = rwl.readLock();
		private final Lock w = rwl.writeLock();
		private final Map<String, Long> scores = new TreeMap<>();

		void put(String name, Long score) {
			w.lock();
			try {
				scores.put(name, score);
			} finally {
				w.unlock();
			}
		}

		void clear() {
			w.lock();
			try {
				scores.clear();
			} finally {
				w.unlock();
			}
		}

		Long get(String name) {
			r.lock();
			try {
				return scores.get(name);
			} finally {
				r.unlock();
			}
		}

		String[] names() {
			r.lock();
			try {
				return scores.keySet().toArray(new String[0]);
			} finally {
				r.unlock();
			}
		}
	}

}