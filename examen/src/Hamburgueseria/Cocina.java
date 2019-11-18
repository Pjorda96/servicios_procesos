package Hamburgueseria;

public class Cocina implements Runnable {
    private Restaurante restaurante;
    public double hamburguesa;


    public Cocina (Restaurante r) {
        restaurante = r;
    }

    @Override
    public void run() {
        while(true) {
            if (hamburguesa > 0.5) {
                try {
                    cocinarMcColesterol();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    cocinarMcGrasa();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void cocinarMcColesterol() throws InterruptedException {
        System.out.println("Cocinar McColesterol");
        Thread.sleep(1000);
        restaurante.McColesterol.put("McColesterol");
    }

    private void cocinarMcGrasa() throws InterruptedException {
        System.out.println("Cocinar McGrasa");
        Thread.sleep(500);
        restaurante.McGrasa.put("McGrasa");
    }
}
