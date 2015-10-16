import java.util.concurrent.LinkedTransferQueue;
import java.util.concurrent.TransferQueue;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LinkedTransferQueueTest {
	public static void main(String[] args) {
		TransferQueue<String> exchangeQueue = new LinkedTransferQueue<>();

		new Thread(new Producer(exchangeQueue)).start();
		new Thread(new Consumer(exchangeQueue)).start();
		new Thread(new Consumer(exchangeQueue)).start();
	}

	private static class Producer implements Runnable {
		final TransferQueue<String> q;
		Producer(TransferQueue<String> q) {
			this.q = q;
		}

		@Override
		public void run() {
			for (String s : "one two three four five six seven eight nine ten".split(" ")) {
				try {
					q.transfer(s);
				} catch (InterruptedException e) {}
			}
		}
	}

	private static class Consumer implements Runnable {
		final TransferQueue<String> q;
		final Lock l = new ReentrantLock();

		Consumer(TransferQueue<String> q) {
			this.q = q;
		}

		@Override
		public void run() {
			try {
				while (true) {
					Thread.sleep(500);
					boolean locked = l.tryLock();
					if (locked) {
						try {
							if (q.peek() == null) {break;}
							String s = q.take();
							System.out.format("%s is being taken by %s%n", s, Thread.currentThread().getName());
						} finally {
							l.unlock();
						}
					}
				}
			} catch (InterruptedException ie) {}
		}
	}
}