// Manages an int array to be shared among multiples threads

// ATENTION: NOT SECURE FOR THREADS!

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.*;
public class SimpleArray {

	private final int[] ARRAY;
	private int writeIndex = 0;
	private final static Random GENERATOR = new Random();

	public SimpleArray(int size) {
		ARRAY = new int[size];
	}

	public synchronized void add(int value) {

		// stores record index
		int position = writeIndex;

		try {
			Thread.sleep(GENERATOR.nextInt(500));
		} catch(InterruptedException ie) {
			ie.printStackTrace();
		}

		// put value on correct element
		ARRAY[position] = value;

		System.out.printf("%s wrote %2d to element %d.\n",
			Thread.currentThread().getName(), value, position);

		// increments index to be recorded later
		++writeIndex;

		System.out.printf("Next write index: %d\n", writeIndex);
	}

	public String toString() {
		return "\nContents of Array:\n" + Arrays.toString(ARRAY);
	}
}