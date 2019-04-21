// Two threads that manipulates correctly a blocking buffer
// Implements consumer/producer relationship
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BlockingBufferTest {
	public static void main(String[] args) {
		
		// creates new thread pool with two threads
		ExecutorService app = Executors.newCachedThreadPool();

		// creates BlockingBuffer to store ints
		Buffer sharedLocation = new BlockingBuffer();

		app.execute(new Producer(sharedLocation));
		app.execute(new Consumer(sharedLocation));

		app.shutdown();
	}
}
