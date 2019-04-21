// Unsynchronized buffer maintains the shared integer wich is accessed by a producer thread and a consumer thread via set and get methods
public class UnsynchronizedBuffer implements Buffer {
	
	private int buffer = -1;

	// puts value in buffer
	public void set(int value) throws InterruptedException {
		System.out.printf("Producer writes\t%2d", value);
		buffer = value;
	}

	// returns buffer value
	public int get() throws InterruptedException {
		System.out.printf("Consumer reads\t%2d", buffer);
		return buffer;
	}

}