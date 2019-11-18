package uje;

public class Obrera implements Runnable {
	
	private Cuidadora cuidadora;
	Obrera(Cuidadora c){ cuidadora = c; }
	
	Obrera() {
		
	}

	@Override
	public void run() {
		while(true) {
			try {
				this.detectarFeromonas();
			} catch (InterruptedException e) {				
				e.printStackTrace();
			}
		}
	}
	
	public void detectarFeromonas() throws InterruptedException {
		try {
			Cuidadora.detectarFeromonasParent();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
