import java.util.concurrent.Semaphore;

public class Main {
    private static final int NUM_FILOSOFOS = 5;
    private Garfo garfos;
    public static void main(String[] args) {
        for (int i = 0; i < NUM_FILOSOFOS; i++) {
            garfos[i] = new Semaphore(1); // Inicialmente, todos os garfos estão disponíveis
        }

        Filosofo[] filosofos = new Filosofo[NUM_FILOSOFOS];

        for (int i = 0; i < NUM_FILOSOFOS; i++) {
            filosofos[i] = new Filosofo(i);
            filosofos[i].start();
        }

    }
}