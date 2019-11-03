import java.util.Random;

public class Filosofo implements Runnable {
    private GestorCubiertos gestorCubiertos;
    private int cubiertoIzq,cubiertoDer;
    
    public Filosofo(GestorCubiertos g, int cIzq, int cDer){
        this.gestorCubiertos = g;
        this.cubiertoDer = cDer;
        this.cubiertoIzq = cIzq;
    }

    public void run() {
        while (true){
            boolean cubiertosCogidos;

            cubiertosCogidos = this.gestorCubiertos.cogerCubiertos(cubiertoIzq, cubiertoDer);
            if (cubiertosCogidos){
                comer();
                this.gestorCubiertos.liberarCubiertos(cubiertoIzq, cubiertoDer);
                pensar();
            }
        }
    }

    private void comer() {
        System.out.println("Filosofo "
                + Thread.currentThread().getName()
                + " comiendo nyam nyam");
        esperar();
    }

    private void pensar(){
        System.out.println("Filosofo "
                + Thread.currentThread().getName()
                + " pensando mmh mmh");
        esperar();
    }

    private void esperar() {
        long msAzar = (long)(Math.random() * 5000.0) + 1000;
        try {
            Thread.sleep(msAzar);
        } catch (InterruptedException ex) {
            System.out.println("Fallo la espera");
        }
    }
}
