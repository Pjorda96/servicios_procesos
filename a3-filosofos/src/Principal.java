public class Principal {
    public static void main(String[] args) {
        int NUM_FILOSOFOS = 5;
        Filosofo filosofos[] = new Filosofo[NUM_FILOSOFOS];

        GestorCubiertos gestorCubiertos;
        gestorCubiertos = new GestorCubiertos(NUM_FILOSOFOS);
        Thread hilos[] = new Thread[NUM_FILOSOFOS];

        for (int i  =1; i < NUM_FILOSOFOS; i++){
            filosofos[i] = new Filosofo(gestorCubiertos, i, i-1);
            hilos[i] = new Thread(filosofos[i]);
            hilos[i].start();
        }

        filosofos[0] = new Filosofo(gestorCubiertos, 0, 4);
        hilos[0] = new Thread(filosofos[0]);
        hilos[0].start();
    }
}
