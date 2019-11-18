import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

public class Reina implements Runnable {
    public BlockingQueue<String> obrera;
    public BlockingQueue<String> cuidadora;
    public BlockingQueue<String> guerrera;

    Reina() {
        obrera = new LinkedBlockingDeque<String>();
        cuidadora = new LinkedBlockingDeque<String>();
        guerrera = new LinkedBlockingDeque<String>();
    }

    @Override
    public void run() {
        while (true) {
            try {
                ponerHuevo();
                incubarHuevo();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void ponerHuevo() throws InterruptedException {
        try {
            long egg = (long)Math.random() * 3;

            if (egg < 1) {
                obrera.put("huevo obrera");
            } else if (egg < 2) {
                cuidadora.put("huevo cuidadora");
            } else {
                guerrera.put("huevo guerrera");
            }

            incubarHuevo();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void incubarHuevo() throws InterruptedException {
        Thread.sleep((long)(Math.random() * (500 - 100) + 100));
    }
}
