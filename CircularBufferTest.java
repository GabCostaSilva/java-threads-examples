import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CircularBufferTest {
	public static void main(String[] args) {
		// creates new pool of threads with 2 threads
		ExecutorService app = Executors.newCachedThreadPool();

		// creates CircularBuffer to store ints
		CircularBuffer sharedLocation = new CircularBuffer();

		// exhibits CircularBuffer initial state
		sharedLocation.displayState("Initial State");

		// executes Producer and Consumer tasks
		app.execute(new Producer(sharedLocation));
		app.execute(new Consumer(sharedLocation));

		app.shutdown();
	}
}