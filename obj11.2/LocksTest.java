import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.TimeUnit;


public class LocksTest {
	private final Lock lock = new ReentrantLock();

	public static void main(String[] args) {
		final LocksTest lt = new LocksTest();
		Runnable r = new Runnable() {
			public void run() {
				lt.lockMethod();
				//lt.tryLockMethod();
				//lt.tryLockWithWait();
			}
		};
		new Thread(r).start();
		new Thread(r).start();
		new Thread(r).start();
	}
	
	/*Locked by Thread-0
	Locked by Thread-1
	Locked by Thread-2*/
	void lockMethod() {
		//lock.lock(); //java.lang.IllegalMonitorStateException
		lock.lock();
		try {
			System.out.format("Locked by %s%n", Thread.currentThread().getName());
		} finally {
			lock.unlock();
		}
	}
	
	/*Locked by Thread-0
	Cannot aquire lock by Thread-2
	Cannot aquire lock by Thread-1
	Unlocked by Thread-0*/	
	void tryLockMethod() {
		boolean locked = lock.tryLock();
		if (locked) {
			try {
				System.out.format("Locked by %s%n", Thread.currentThread().getName());
			} finally {
				lock.unlock();
				System.out.format("Unlocked by %s%n", Thread.currentThread().getName());
			}	
		} else {
			System.out.format("Cannot aquire lock by %s%n", Thread.currentThread().getName());
		}
	}

	/*Locked by Thread-1
	Unlocked by Thread-1
	Locked by Thread-0
	Unlocked by Thread-0
	Locked by Thread-2
	Unlocked by Thread-2*/
	void tryLockWithWait() {
		try {
			boolean locked = lock.tryLock(5, TimeUnit.SECONDS);
			if (locked) {
				try {
					System.out.format("Locked by %s%n", Thread.currentThread().getName());
				} finally {
					lock.unlock();
					System.out.format("Unlocked by %s%n", Thread.currentThread().getName());
				}	
			} else {
				System.out.format("Cannot aquire lock by %s%n", Thread.currentThread().getName());
			}
		} catch (InterruptedException e) {

		}	
	}

	static class Job {

	}
}