import java.util.concurrent.locks.*;
import java.util.concurrent.*;
import java.util.Random;

public class LiveLockTest {
	private final Lock first = new ReentrantLock();
	private final Lock second = new ReentrantLock();
	private final Random rnd = new Random(13L);

	public static void main(String[] args) {
		final LiveLockTest llt = new LiveLockTest();
		Runnable r = new Runnable() {
			@Override
			public void run() {
				//llt.doJob();
				llt.doJob2();
			}
		};

		new Thread(r).start();
		new Thread(r).start();
		new Thread(r).start();
	}

	void doJob() {
		try {
			boolean firstLocked = first.tryLock();
			Thread.sleep(100);
			boolean secondLocked = second.tryLock();
			try {
				if (firstLocked && secondLocked) {
					System.out.format("2 lock are acquired by %s%n", Thread.currentThread().getName());
				} else {
					System.out.format("Cannot acquire 2 locks by %s%n", Thread.currentThread().getName());
				}
			} finally {
				if (firstLocked) {
					System.out.format("Unclocking firstLock by %s%n", Thread.currentThread().getName());
					first.unlock();
				}
				if (secondLocked) {
					System.out.format("Unclocking secondLock by %s%n", Thread.currentThread().getName());
					second.unlock();
				}
			}
		} catch (InterruptedException e) {}
	}

	/*avoiding livelocks by introducing randomness sleep*/
	void doJob2() {
		loop:
		while (true) {
			boolean firstLocked = first.tryLock();
			boolean secondLocked = second.tryLock();
			try {
				if (firstLocked && secondLocked) {
					System.out.format("2 lock are acquired by %s%n", Thread.currentThread().getName());
					break loop;
				} else {
					try {
						Thread.sleep(rnd.nextInt(20));
					} catch (InterruptedException ie) {}
				} 
			} finally {
				if (firstLocked) {
					System.out.format("Unclocking firstLock by %s%n", Thread.currentThread().getName());
					first.unlock();
				}
				if (secondLocked) {
					System.out.format("Unclocking secondLock by %s%n", Thread.currentThread().getName());
					second.unlock();
				}
			}
		}//loop:
	}
}