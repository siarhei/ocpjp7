import java.util.concurrent.atomic.*;

public class AtomicInt {
	public static void main(String[] args) throws InterruptedException {
		final Counter c = new Counter();
		Runnable r = new Runnable() {
			@Override
			public void run() {
				for (int i=0; i<10000000; i++) {
					c.increment();
					if (i % 100000 == 0) {
						try {
							Thread.sleep(1);
						} catch (InterruptedException ie) {}
					}
				}
			}
		};
		for (int i=15; i>0; --i) {
			Thread t = new Thread(r);
			t.start();
			t.join();	
		}
		System.out.println(c.getCount());
	}
}

class Counter {
	//private int count;
	private AtomicInteger count = new AtomicInteger();

	int getCount() {
		return count.intValue();
	}
	void increment() {
		//count++;
		count.incrementAndGet();
	}
}