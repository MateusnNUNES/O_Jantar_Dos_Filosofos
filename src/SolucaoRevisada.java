import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SolucaoRevisada {
    private Lock[] garfos;
    private String[] filosofos;

    public SolucaoRevisada() {
        garfos = new ReentrantLock[5];
        for (int i = 0; i < 5; i++) {
            garfos[i] = new ReentrantLock();
        }

        filosofos = new String[]{"Platão", "Aristóteles", "Kant", "Sócrates", "Russell"};
    }

    public void iniciarJantar() {
        for (int i = 0; i < 5; i++) {
            Lock garfoEsquerda = garfos[i];
            Lock garfoDireita = garfos[(i + 1) % 5];

            if (i == 4) {
                new Filosofo(filosofos[i], garfoDireita, garfoEsquerda).start();
            } else {
                new Filosofo(filosofos[i], garfoEsquerda, garfoDireita).start();
            }
        }
    }
}
