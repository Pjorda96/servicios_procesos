public class Animal implements Runnable{
    private int n_etapas= 5;

    public static void  main(String[] args) throws InterruptedException {

        Animal cheetah = new Animal();
        Animal hare = new Animal();
        Animal turtle = new Animal();

        Thread animal1 = new Thread(cheetah);
        Thread animal2 = new Thread(hare);
        Thread animal3 = new Thread(turtle);

        animal1.setName("Guepardo");
        animal2.setName("Liebre");
        animal3.setName("Tortuga");

        animal1.start();
        animal2.start();
        animal3.start();

        animal1.join();
        animal2.join();
        animal3.join();
        System.out.println(("La carrera ha terminado"));
    }

    @Override
    public void run() {
        String animal = Thread.currentThread().getName();

        for(int i = 0; i < n_etapas; i++) {
            if (animal.equals("Liebre")) {
                try {
                   Thread.sleep(5);
                }
                 catch (Exception e) {
                    System.out.println(e);
                }
        }
            if (animal.equals("Tortuga")) {
                try {
                    Thread.sleep(10);
                }
                catch (Exception e) {
                    System.out.println(e);
                }
            }
            System.out.println(animal + " estoy en etapa " + i);
        }
        System.out.println(animal + " ha terminado la carrera");
    }
}
