import java.lang.Thread;

public class ThreadCreator {
	public static void main(String[] args) {
		System.out.println("Creating some threads");

		Thread t1 = new Thread(new PrintTask("task1"));
		Thread t2 = new Thread(new PrintTask("task2"));
		Thread t3 = new Thread(new PrintTask("task3"));

		System.out.println("Threads created, starting tasks");

		t1.start(); // calls run()
		t2.start(); // calls run()
		t3.start(); // calls run()

		System.out.println("Tasks started, main ends\n");
	}
}