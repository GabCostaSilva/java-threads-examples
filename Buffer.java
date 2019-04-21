// Buffer interface specifies methods called by Producer and Consumer

public interface Buffer {

	public void set(int value) throws InterruptedException;
	public int get() throws InterruptedException;
}
