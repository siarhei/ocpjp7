import java.util.*;
import java.util.concurrent.locks.*;

public class ProducerConsumer {
	public static void main(String[] args) {
		Drop drop = new Drop();
        (new Thread(new Producer(drop))).start();
        (new Thread(new Consumer(drop))).start();
	}
}

class Drop {
	// Message sent from producer
    // to consumer.
    private String message;
    // True if consumer should wait
    // for producer to send message,
    // false if producer should wait for
    // consumer to retrieve message.
    private boolean empty = true;

    private final Lock lock = new ReentrantLock();
    private final Condition dropPool = lock.newCondition();

    //public synchronized String take() {
    public String take() {
    	lock.lock();
    	try {
	        while (empty) {
	            try {
	                //wait();
	                dropPool.await();
	            } catch (InterruptedException e) {}
	        }
	        // Toggle status.
	        empty = true;
	        //notifyAll();
	        dropPool.signalAll();
	        return message;
	    } finally {
	    	lock.unlock();
	    }
    }

    //public synchronized void put(String message) {
    public void put(String message) {
        lock.lock();
        try {
	        while (!empty) {
	            try { 
	                //wait();
	                dropPool.await();
	            } catch (InterruptedException e) {}
	        }
	        // Toggle status.
	        empty = false;
	        // Store message.
	        this.message = message;
	        //notifyAll();
	        dropPool.signalAll();
	    } finally {
	    	lock.unlock();
	    }
    }
}

class Producer implements Runnable {
    private Drop drop;

    public Producer(Drop drop) {
        this.drop = drop;
    }

    public void run() {
        String importantInfo[] = {
            "Mares eat oats",
            "Does eat oats",
            "Little lambs eat ivy",
            "A kid will eat ivy too"
        };
        Random random = new Random();

        for (int i = 0; i < importantInfo.length; i++) {
            drop.put(importantInfo[i]);
            try {
                Thread.sleep(random.nextInt(5000));
            } catch (InterruptedException e) {}
        }
        drop.put("DONE");
    }
}

class Consumer implements Runnable {
    private Drop drop;

    public Consumer(Drop drop) {
        this.drop = drop;
    }

    public void run() {
        Random random = new Random();
        for (String message = drop.take(); !message.equals("DONE"); message = drop.take()) {
            System.out.format("MESSAGE RECEIVED: %s%n", message);
            try {
                Thread.sleep(random.nextInt(5000));
            } catch (InterruptedException e) {}
        }
    }
}