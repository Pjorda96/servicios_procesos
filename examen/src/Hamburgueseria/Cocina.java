package Hamburgueseria;

public class Cocina implements Runnable {
    private Restaurante restaurante;
    private double hamburguesa;


    public Cocina (Restaurante r, double h) {
        restaurante = r;
        hamburguesa = h;
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

    /*La cocina está eternamente esperando a que lleguen pedidos de los
    clientes.


    b. Cuando la cocina recibe un pedido se pone a cocinarlo. Dependiendo
    de los pedidos, la cocina tarda los siguientes tiempos:
    i. McColesterol: 1000 milisegundos
    ii. McGrasa: 500 milisegundos


    c. Una vez acabado el pedido, la cocina lo deja en la cinta transportadora
    correspondiente para que los clientes lo cojan.*/



    /*Barberia barberia;

    public Barbero (Barberia b) {barberia = b;}

    @Override
    public void run() {
        while(true) {
            //System.out.println("Barbero: Busco cliente que atender;");
            Silla sillaAtendida = barberia.atenderCliente();
            if(sillaAtendida != null) {
                try {
                    afeitar(sillaAtendida);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    descansar();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void afeitar(Silla silla) throws InterruptedException {
        System.out.println("Afeito a cliente");
        Thread.sleep(1500);
        silla.cliente.afeitado = true;
    }

    private void descansar() throws InterruptedException {
        Thread.sleep((long) (Math.random() * 2000));
    }*/
}
