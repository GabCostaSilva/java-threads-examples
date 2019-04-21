// Creating a synchronized buffer with ArrayBlockingQueue
import java.util.concurrent.ArrayBlockingQueue;

public class BlockingBuffer implements Buffer{

	private final ArrayBlockingQueue<Integer> BUFFER;

	public BlockingBuffer() {
		BUFFER = new ArrayBlockingQueue<Integer>(1);
	}

	// put value in buffer
	public void set(int value) throws InterruptedException{
		BUFFER.put(value);
		System.out.printf("%s%2d\t%s%d\n", "Producer writes ", value, "Buffer cells occupied: ", BUFFER.size());
	}

	// returns value from buffer
	public int get() throws InterruptedException {
		int readValue = BUFFER.take(); // remove value from buffer
		System.out.printf("%s %2d\t%s%d\n", "Consumer reads ", readValue, "Buffer cells occupied: ", BUFFER.size());

		return readValue;
	}
}