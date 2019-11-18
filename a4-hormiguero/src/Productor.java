import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

public class Productor implements Runnable {
	private BlockingQueue<Integer> queue;
	
	Productor() { 
		queue = new LinkedBlockingDeque<Integer>();
	}

	@Override
	public void run() {		
		while (true) {
			try {
				this.queue.put(this.produce());
			} catch (InterruptedException e) {				
				e.printStackTrace();
			}
		}				
	}
	
	private int produce() throws InterruptedException {
		Thread.sleep((long)Math.random() * 1000);
		return (int)((Math.random() * 10.0) + 1);
	}
	
	public int getValor() throws InterruptedException {
		return this.queue.take();
	}
	
}
