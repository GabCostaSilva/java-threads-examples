import java.util.Random;

 public class PrintTask implements Runnable{
 	
 	private final int SLEEP_TIME;
 	private final String TASK_NAME;
 	private final static Random generator = new Random();

 	public PrintTask (String name) {
 		TASK_NAME = name;

 		// random sleep time between 0 - 5 seconds
 		SLEEP_TIME = generator.nextInt(5000); //milli
 	}

 	// Method to thread execute
 	public void run() {
 		// puts thread to sleep on the specified time
 		try {
 			System.out.printf("%s going to sleep for %d milliseconds.\n", TASK_NAME, SLEEP_TIME);
 			Thread.sleep(SLEEP_TIME);
 		} 
 		catch(InterruptedException ie) {
 			System.out.printf("%s %s\n", TASK_NAME, "terminated prematurely due to interruption");
 		}

 		System.out.printf("%s done sleeping\n", TASK_NAME);
 	}
 }