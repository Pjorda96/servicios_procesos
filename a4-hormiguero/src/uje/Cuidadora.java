package uje;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

public class Cuidadora implements Runnable {
	
	private Reina reina;
	Cuidadora(Reina r){ reina = r; }
	private Hormiguero hormiguero;
	Cuidadora(Hormiguero h){ hormiguero = h; }
	
	public static BlockingQueue<String> feromonaObrera;
	public static BlockingQueue<String> feromonaCuidadora;
	public static BlockingQueue<String> feromonaGuerrera;
	
	Cuidadora() { 
		feromonaObrera = new LinkedBlockingDeque<String>();
		feromonaCuidadora = new LinkedBlockingDeque<String>();
		feromonaGuerrera = new LinkedBlockingDeque<String>();
	}
	
	
	@Override
	public void run() {
		while(true) {
			try {
				this.cogerHuevo();
			} catch (InterruptedException e) {				
				e.printStackTrace();
			}
		}
	}
	
	private void cogerHuevo() throws InterruptedException {
		try {
			this.llevarHuevoAGaleria(Reina.cogerHuevoParent());
		} catch (InterruptedException e) {				
			e.printStackTrace();
		}
		
	}
	
	private void llevarHuevoAGaleria(String colaCuidadora) throws InterruptedException {
		
		try {
			switch(colaCuidadora) {
				case "obrera":
					if (Hormiguero.numeroHuevosGaleriasObrera >= 100) {
						this.soltarFeromonas(colaCuidadora);	
					}
					Hormiguero.numeroHuevosGaleriasObrera++;
					break;
				case "cuidadora":
					if (Hormiguero.numeroHuevosGaleriasCuidadora >= 100) {
						this.soltarFeromonas(colaCuidadora);	
					}
					Hormiguero.numeroHuevosGaleriasCuidadora++;
					break;
				case "guerrera":
					if (Hormiguero.numeroHuevosGaleriasGuerrera >= 100) {
						this.soltarFeromonas(colaCuidadora);	
					}
					Hormiguero.numeroHuevosGaleriasGuerrera++;
					break;
			}
			Thread.sleep((long) Math.random()*(1500-500)+500);
		} catch (InterruptedException e) {				
			e.printStackTrace();
		}
		
	}
	
	private void soltarFeromonas(String colaCuidadora) throws InterruptedException {
		
		switch(colaCuidadora) {
			case "obrera":
				this.feromonaObrera.put("feromonaObrera");
				Hormiguero.numeroHuevosGaleriasObrera = 0;
				break;
			case "cuidadora":
				this.feromonaCuidadora.put("feromonaCuidadora");
				Hormiguero.numeroHuevosGaleriasCuidadora = 0;
				break;
			case "guerrera":
				this.feromonaGuerrera.put("feromonaObrera");
				Hormiguero.numeroHuevosGaleriasGuerrera = 0;
				break;
		}
		
	}
	
	public static void detectarFeromonasParent() throws InterruptedException {
		try {
			if(feromonaObrera.take() != null) {
				Hormiguero.numeroGaleriaObrera++;
			}
			if(feromonaCuidadora.take() != null) {
				Hormiguero.numeroGaleriaCuidadora++;
			}
			if(feromonaGuerrera.take() != null) {
				Hormiguero.numeroGaleriaGuerrera++;
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
