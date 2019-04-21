// Synching the access to a shared integer with Lock and Condition interfaces
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Condition;

public class SynchronizedBuffer implements Buffer{
	// Blocking to control synchronization with this buffer
	private final Lock accessLock = new ReentrantLock();

	// Conditions to control read and write operations
	private final Condition canWrite = accessLock.newCondition();
	private final Condition canRead = accessLock.newCondition();

	private int buffer = 1; // shared with producer and consumer threads
	private boolean occupied = false; // if buffer is busy

	// puts int value in buffer
	public void set(int value) throws InterruptedException {
		accessLock.lock(); // blocks this object

		// sends thread info and buffer info to output, then waits
		try {
			while(occupied) {
				System.out.println("Producer tries to write.");
				displayState("Buffer full. Producer waits.");
				canWrite.await(); // waits until buffer is empty (temporaly free Lock from Synchronized Buffer)
			}

			buffer = value; // sets new value to buffer

			// indicates that producer can't store another value
			// until consumer retrieve actual buffer value
			occupied = true;

			displayState("Producer writes " + buffer);

			// sinalizes thread that is waiting to read from buffer
			canRead.signal();
		} finally {
			accessLock.unlock(); // unlocks this object
		}
	}

	// returns value of buffer
	public void get() throws InterruptedException {
		int readValue = 0; // initializes read value from buffer
		accessLock.lock() // blocks this object

		// sends thread info and buffer info to output, then waits
		try {
			// if theres no data to be read, put thread on waiting status
			while(!occupied) {
				System.out.println("Consumer tries to read.");
				displayState("Buffer empty. Consumer waits.");
				canRead.await(); // waits until buffer become full
			}

			// indicates that producer can store another value
			// because consumer ended retrieving value from buffer
			occupied = false;

			readValue = buffer; // retrieves value from buffer
			displayState("Consumer reads: " + readValue);

			// sinalizes thread that is waiting buffer become empty
			canWrite.signal() 

		} finally {
			accessLock.lock(); // unlocks this object
		}

		return readValue;

	}

	public void displayState(String operation) {
		System.out.printf("%-40s%d\t\t%b\n\n", operation, buffer, occupied);
	}
}
