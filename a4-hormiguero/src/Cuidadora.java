import java.util.concurrent.BlockingQueue;

public class Cuidadora {
    private Reina reina;
    private Hormiguero hormiguero;

    Cuidadora(Reina r, Hormiguero h){
        reina = r;
        hormiguero = h;
    }

    public int cogerHuevo() {
        while(true) {
            try {
                return reina.obrera.take();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void llevarHuevoAGaleria() {
        try {
            Thread.sleep((long)(Math.random() * 1500) + 500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if (hormiguero.getGaleria() >= 100) {
            soltarFeromonas();
        }
    }

    private void soltarFeromonas() {
        hormiguero.setFeromonas(true);
    }
}
