package boggle;

public class Hourglass extends Thread {
	
	boolean running = true;
	
	int delay = 1000;
	int time; // in seconds
	int startTime;
	
	Display display;
	
	public Hourglass(Display d) {
		super();
		this.time = 181;
		this.startTime = this.time;
		this.display = d;
	}
	
	public Hourglass(int time, Display d) {
		super();
		this.time = time;
		this.startTime = this.time;
		this.display = d;
	}
	
	public void run() {
		while (running) {
			if (this.time > 0) {
				// Count down
				this.time--; // deduct 1
				display.repaint();
				
				// Tick
				try {
					Thread.sleep(delay);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
			}
			else {
				running = false; // stop thread
				display.repaint();
			}
		}
	}
	
	public void reset() {
		this.time = startTime;
	}
	
	public String getTime() {
		String minutes = String.format("%d", time / 60);
		String seconds = String.format("%02d", time % 60); // two-digit always
		
		String ms = minutes + ":" + seconds; // mm:ss
		
		return ms;
	}

}
