package Hamburgueseria;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

public class Restaurante {
    Silla[] sillas;
    public BlockingQueue<String> McColesterol;
    public BlockingQueue<String> McGrasa;

    public Restaurante(int numSillas) {
        sillas = new Silla[numSillas];
        for(int i = 0; i < numSillas; i++) {
            sillas[i] = new Silla();
        }

        McColesterol = new LinkedBlockingDeque<String>();
        McGrasa = new LinkedBlockingDeque<String>();
    }

    public synchronized int cogerSillaLibre() {
        for(int i = 0; i < sillas.length; i++) {
            if(!sillas[i].ocupada) {
                System.out.println("Silla " + i + " libre");
                sillas[i].ocupada = true;
                return i;
            }
        }
        System.out.println("No hay sillas libres");
        return -1;
    }

    public synchronized void liberarSilla(int i) {
        sillas[i].ocupada = false;
    }
}
