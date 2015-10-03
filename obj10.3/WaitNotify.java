import java.util.*;

public class WaitNotify {
	public static void main(String[] args) throws InterruptedException {
		int threadsNumber = 200;
		Thread.currentThread().setName("Main");
		Queue q = new LinkedList();
		Thread t = null;
		for (int i = 0; i < threadsNumber; i++) {
			//t = new Reader(q);
			//t.join();
			new Reader(q).start();
			//Thread.sleep(500);
			//new Writer(q).start();
		}
		Thread.sleep(500);
		for (int i = 0; i<threadsNumber; i++) {
			new Writer(q).start();
		}
		/*t = new Writer(q);
		t.start();*/
	}
}

class Writer extends Thread {
	private static int counter;
	private final int id = ++counter;
	private final String name = "Writer" + id;
	private final Queue q;

	Writer(Queue q) {
		super();
		System.out.format("created %s%n", name);
		super.setName(name);
		this.q = q;
	}

	@Override
	public void run() {
		synchronized (q) {
			try {
				while (!q.isEmpty()) {
					System.out.format("%s goes sleep%n", Thread.currentThread().getName());
					q.wait();
				}
			} catch (InterruptedException e) {}
			System.out.format("%s is working%n", Thread.currentThread().getName());
			for (int i = 0; i < 10; i++) {
				q.offer(i);
				//q.notifyAll();
			}
			q.notifyAll();
			//System.out.format("%s write %d%n", Thread.currentThread().getName(), id);
			//q.offer(id);			
		}
	}
}

class Reader extends Thread {
	private static int counter;
	private final int id = ++counter;
	private final String name = "Reader" + id;
	private final Queue q;

	Reader(Queue q) {
		super();
		System.out.format("created %s%n", name);
		super.setName(name);
		this.q = q;
	}

	@Override
	public void run() {
		synchronized(q) {
			try {
				while(q.isEmpty()) {
					System.out.format("%s goes sleep%n", Thread.currentThread().getName());
					q.wait();
				}
			} catch (InterruptedException e) {}
			while(q.peek() != null) {
				System.out.format("%s read %d%n", Thread.currentThread().getName(), q.poll());
			}
			q.notifyAll();
		}
	}
}