// Importa as classes necessárias
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

// Classe que configura o cenário para o problema dos filósofos jantando
public class SolucaoRevisada {
    // Cria 5 garfos, que são representados por objetos ReentrantLock
    private Lock[] garfos;
    // Define os nomes dos 5 filósofos
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
            // Cada filósofo recebe dois garfos - um à esquerda e um à direita
            Lock garfoEsquerda = garfos[i];
            Lock garfoDireita = garfos[(i + 1) % 5];

            // O último filósofo pega os garfos na ordem inversa para evitar um impasse
            if (i == 4) {
                new Filosofo(filosofos[i], garfoDireita, garfoEsquerda).start();
            } else {
                new Filosofo(filosofos[i], garfoEsquerda, garfoDireita).start();
            }
        }
    }
}