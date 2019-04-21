// Synchronizing access to a limited buffer of 3 shared elements

public class CircularBuffer implements Buffer{

	private final int[] buffer = { -1, -1, -1 }; // shared buffer
	
	private int occupiedCells = 0; // count utilized buffers
	private int writeIndex = 0; // next element to write index
	private int readIndex = 0; // next element to read index
 
	public synchronized void set(int value) throws InterruptedException {

		// waits until buffer has available space, then writes value
		// while not empty positions, puts thread on blocked state
		while(occupiedCells == buffer.length) {
			System.out.printf("Buffer is full. Producer waits.\n");
			wait();
		}

		buffer[writeIndex] = value;

		// updates circular write index
		writeIndex = (writeIndex + 1) % buffer.length;

		// another cell of buffer is full
		++occupiedCells;
		displayState("Producer writes " + value);
		notifyAll(); // notifies threads that are waiting to read from buffer
	}

	public synchronized int get() throws InterruptedException {

		// waits until buffer has data, then reads value
		// while data not read, puts thread on waiting state
		while(occupiedCells == 0) {
			System.out.printf("Buffer is empty. Consumer waits.\n");
			wait();
		}

		int readValue = buffer[readIndex];

		// updates circular read index
		readIndex = (readIndex + 1) % buffer.length;

		// some cells are occupied
		--occupiedCells;
		displayState("Consumer reads " + readValue);
		notifyAll(); // notifies threads that are waiting to write to buffer

		return readValue;
	}

	public void displayState(String operation) {
		System.out.printf("%s%s%d)\n%s",
		 operation, " (buffer cells occupied: ", occupiedCells, "buffer cells: ");

		for (int value : buffer) 
			System.out.printf(" %2d ", value);
		

		System.out.print("\n              ");

		for (int i = 0; i < buffer.length; i++) 
			System.out.print("---- ");
		
		System.out.print("\n              ");

		for (int i = 0; i < buffer.length; i++) {
			if (i == writeIndex && i == readIndex) 
				System.out.print(" WR"); // write and read index
			else if (i == writeIndex)
				System.out.print(" W  "); // only write index
			else if (i == readIndex)
				System.out.print("  R  "); // only read index
			else 
				System.out.print("    "); // any index
		}

		System.out.println("\n");
	}

}