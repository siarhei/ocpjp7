import java.util.concurrent.CopyOnWriteArrayList;
import java.util.*;

public class CopyOnWriteArrayTest {
	private static final Random R = new Random(41);

	public static void main(String[] args) {
		CopyOnWriteArrayTest test = new CopyOnWriteArrayTest();
		final CopyOnWriteArrayTest.ArrayHolder ah = test.new ArrayHolder();

		Runnable[] writers = {
			new Runnable() {
				public void run() {
					for (String s : "one two three four five six seven eight nine".split(" ")) {
						ah.add(s);
					}
				}
			},
			new Runnable() {
				public void run() {
					for (String s : "1 2 3 4 5 6 7 8 9".split(" ")) {
						ah.add(s);
					}
				}
			}			
		};

		Runnable reader = new Runnable() {
			public void run() {
				ah.printState();
			}
		};

		for (Runnable writer : writers) {
			new Thread(writer).start();
		}
		for (int i = 0; i < 7; i++) {
			new Thread(reader).start();
		}

	}

	static String threadName() {
		return Thread.currentThread().getName();
	}

	static void sleep() {
		try {
			Thread.sleep(R.nextInt(500));
		} catch (InterruptedException ie) {}
	}

	static void sleep(int ms) {
		try {
			Thread.sleep(R.nextInt(ms));
		} catch (InterruptedException ie) {}
	}

	private class ArrayHolder {
		private final List<String> list = new CopyOnWriteArrayList<>();

		void add(String element) {
			sleep();
			System.out.format("Adding %s by %s%n", element, threadName());
			list.add(element);
		}

		void printState() {
			sleep(5000);
			StringBuilder sb = new StringBuilder();
			for (String element : list) {
				sb.append(element).append(' ');
			}

			//sb.deleteCharAt(sb.length()-1);
			System.out.format("Reading by %s: %s%n", threadName(), sb);
		}
	}
}

//OUTPUT
/*
Adding 1 by Thread-1
Reading by Thread-7: 1 
Adding one by Thread-0
Adding 2 by Thread-1
Reading by Thread-8: 1 one 2 
Adding two by Thread-0
Adding three by Thread-0
Reading by Thread-6: 1 one 2 two three
*/