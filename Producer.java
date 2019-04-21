// Producer with method run that inserts values from 1 to 10 on buffer
import java.util.Random;

public class Producer implements Runnable{

	private final static Random GENERATOR = new Random();
	private final Buffer sharedLocation; // shared object reference

	public Producer(Buffer shared) {
		sharedLocation = shared;
	}

	// stores 1 to 10 values in sharedLocation
	public void run() {
		int sum = 0;

		for (int count = 1; count <= 10; count++) {
			//sleeps between 0 and 3 seconds, then puts value in buffer
			try {
				Thread.sleep(GENERATOR.nextInt(3000));
				sharedLocation.set(count);
				sum += count;
				// System.out.printf("\t%2d\n", sum);
			}
			catch(InterruptedException ie) {
				ie.printStackTrace();
			}
		}

		System.out.println("Producer done producing\nTerminating Producer");
	}

}