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

    /*Un restaurante tiene un número determinado de sillas que se le pasa
    como parámetro. Las sillas pueden estar ocupadas o libres
    dependiendo de si hay un cliente comiendo en ellas.*/


    /*Silla[] sillas;

    public Barberia(int numSillas) {
        sillas = new Silla[numSillas];
        for(int i = 0; i < numSillas; i++) {
            sillas[i] = new Silla();
        }
    }

    public synchronized int cogerSillaLibre(Cliente cliente) {
        for(int i = 0; i < sillas.length; i++) {
            if(!sillas[i].ocupada) {
                System.out.println("Silla " + i + " libre");
                sillas[i].ocupada = true;
                sillas[i].cliente = cliente;
                return i;
            }
        }
        System.out.println("No hay sillas libres");
        return -1;
    }

    public synchronized Silla atenderCliente() {
        for(int i = 0; i < sillas.length; i++) {
            if(sillas[i].ocupada && !sillas[i].atendida) {
                sillas[i].atendida = true;
                return sillas[i];
            }
        }
        return null;
    }

    public synchronized void liberarSilla(int i) {
        sillas[i].atendida = false;
        sillas[i].cliente = null;
        sillas[i].ocupada = false;
    }*/
}
