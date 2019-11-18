package Hamburgueseria;

public class Cliente implements Runnable {
	private Restaurante restaurante;
	private double hamburguesa;
	
	Cliente(Restaurante r) {
		restaurante = r;
		hamburguesa = Math.random();
		Cocina cocina = new Cocina(restaurante, hamburguesa);
	}

	@Override
	public void run() {
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

		/*int indiceSilla = barberia.cogerSillaLibre(this);
		if(indiceSilla != -1) {
			System.out.println("Entro a la barberia");
			while(!afeitado) {
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {					
					e.printStackTrace();
				}
			}
			System.out.println("Ya estoy afeitado, me voy");
			barberia.liberarSilla(indiceSilla);			
		} else {
			System.out.println("No consigo silla, me voy");
		}*/
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

	/*Cuando un cliente llega a la hamburguesería hace un pedido. Puede
	pedir cualquiera de las dos hamburguesas con la misma probabilidad
			(50%).
	b. Una vez hecho y pagado el pedido, espera en la cinta transportadora a
	que salga su pedido.
	c. Cuando consigue su hamburguesa, busca silla libre o si no hay, espera
	5 milisegundos antes de volver a buscar silla. Cuando consigue una
	silla libre, se sienta y se come la hamburguesa, lo que le lleva 1000
	milisegundos.*/
}
