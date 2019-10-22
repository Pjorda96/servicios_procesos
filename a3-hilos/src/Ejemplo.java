public class Ejemplo implements Runnable{

    public static void main(String[] args) throws InterruptedException {
        // Creamos objetos que implementan la interfaz runnable
        Ejemplo basico1 = new Ejemplo();
        Ejemplo basico2 = new Ejemplo();
        // reamos hilos y les asignamos un objeto que implemente la interfaz Runnable
        Thread hilo1 = new Thread(basico1);
        Thread hilo2 = new Thread(basico2);
        // damos nombre a los hilos
        hilo1.setName("hilo1");
        hilo2.setName("hilo2");
        // arrancamos los hilos
        hilo1.start();
        hilo2.start();
        // podemos esperar a que acaben los otros hilos
        hilo1.join();
        hilo2.join();
        System.out.println("Soy hilo principal acabo ejecuci�n.");
    }

    @Override
    public void run() {
        // El c�digo dentro del m�todo run ser� el que ejecutar�n los hilos
        String nombreThread = Thread.currentThread().getName();
        for(int i = 0; i < 100; i++) {
            System.out.println(nombreThread + " estoy en iteracion " + i);
        }
        System.out.println("Soy " + nombreThread + " acabo ejecuci�n.");
    }
}