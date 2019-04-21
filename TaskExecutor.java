// Using ExecutorService to execute Runnables
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;

public class TaskExecutor {
	public static void main(String[] args) {
		
		Thread t1 = new Thread(new PrintTask("task1"));
		Thread t2 = new Thread(new PrintTask("task2"));
		Thread t3 = new Thread(new PrintTask("task3"));

		System.out.println("Executor comensing!");

		// create Executor to manage threads
		ExecutorService threadExecutor = Executors.newCachedThreadPool();

		// initiates thread and put it on executable state
		threadExecutor.execute(t1);
		threadExecutor.execute(t2);
		threadExecutor.execute(t3);

		threadExecutor.shutdown();

		System.out.println("Tasks started, main ends. \n");
	}
}