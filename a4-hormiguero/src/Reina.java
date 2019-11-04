import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

public class Reina {
    public BlockingQueue<Integer> obrera;
    public BlockingQueue<Integer> cuidadora;
    public BlockingQueue<Integer> guerrera;

    Reina() {
        obrera = new LinkedBlockingDeque<Integer>();
        cuidadora = new LinkedBlockingDeque<Integer>();
        guerrera = new LinkedBlockingDeque<Integer>();
    }

    private void ponerHuevo() throws InterruptedException {
        try {
            long egg = (long)Math.random() * 3;

            if (egg < 1) {
                obrera.put((int)egg);
            } else if (egg < 2) {
                cuidadora.put((int)egg);
            } else {
                guerrera.put((int)egg);
            }

            incubarHuevo();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void incubarHuevo() throws InterruptedException {
        Thread.sleep((long)(Math.random() * 500) + 100);
    }
}
