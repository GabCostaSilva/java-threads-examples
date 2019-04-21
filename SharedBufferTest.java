// App with two threads that manipulates unsync buffer
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SharedBufferTest {
	public static void main(String[] args) {
		// creates new pool of threads with two threadsS
		ExecutorService app = Executors.newCachedThreadPool();

		// creates Unsynchronized buffer to store ints
		Buffer sharedLocation = new UnsynchronizedBuffer();

		System.out.println("Action\t\tValue\tSum of Produced\tSum of Consumed");
		System.out.println("------\t\t-----\t---------------\t---------------\n");

		app.execute(new Producer(sharedLocation));
		app.execute(new Consumer(sharedLocation));

		app.shutdown();
	}
}