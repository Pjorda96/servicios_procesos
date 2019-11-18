package Hamburgueseria;

public class Cliente implements Runnable {
	private Restaurante restaurante;
	private Cocina cocina;
	private double hamburguesa;
	
	Cliente(Restaurante r, Cocina c) {
		restaurante = r;
		cocina = c;

		hamburguesa = Math.random();
	}

	@Override
	public void run() {
		while (true) {
			if (hamburguesa > 0.5) {
				try {
					System.out.println("Recoger McColesterol");
					restaurante.McColesterol.take();
					buscarSilla();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			} else {
				try {
					System.out.println("Recoger McGrasa");
					restaurante.McGrasa.take();
					buscarSilla();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	private void buscarSilla() throws InterruptedException {
		int silla;
		do {
			System.out.println("Buscando silla");
			silla = restaurante.cogerSillaLibre();

			if (silla == -1) {
				Thread.sleep(5);
			}
		} while (silla == -1);

		System.out.println("Comiendo hamburguesa");
		Thread.sleep(1000);

		System.out.println("Dejo la silla libre");
		restaurante.liberarSilla(silla);
	}
}
