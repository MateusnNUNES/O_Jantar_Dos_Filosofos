// Importa as classes necessárias
import java.util.concurrent.locks.Lock;

// Classe que estende a classe Thread e representa um filósofo
public class Filosofo extends Thread{
    // Cada filósofo tem um garfo à esquerda e um à direita
    private final Lock garfoEsquerda;
    private final Lock garfoDireita;

    public Filosofo(String nome, Lock garfoEsquerda, Lock garfoDireita) {
        super(nome);
        this.garfoEsquerda = garfoEsquerda;
        this.garfoDireita = garfoDireita;
    }

    @Override
    public void run() {
        // O filósofo executa um ciclo infinito de pensar, pegar garfos, comer e soltar garfos
        while (true) {
            pensar();
            pegarGarfos();
            comer();
            soltarGarfos();
        }
    }

    private void pensar() {
        // Imprime uma mensagem indicando que o filósofo está pensando
        System.out.println(Thread.currentThread().getName() + " está pensando");
    }

    private void pegarGarfos() {
        // Tenta adquirir ambos os garfos. Se não conseguir adquirir ambos, ele libera o garfo que conseguiu e tenta novamente
        boolean pegouGarfos = false;
        while (!pegouGarfos) {
            if (garfoEsquerda.tryLock()) {
                if (garfoDireita.tryLock()) {
                    pegouGarfos = true;
                } else {
                    garfoEsquerda.unlock();
                }
            }
        }
        // Imprime uma mensagem indicando que o filósofo pegou os garfos
        System.out.println(Thread.currentThread().getName() + " pegou os dois garfos");
    }

    private void comer() {
        // Imprime uma mensagem indicando que o filósofo está comendo
        System.out.println(Thread.currentThread().getName() + " está comendo");
    }

    private void soltarGarfos() {
        // Libera ambos os garfos e imprime uma mensagem indicando que o filósofo soltou os garfos
        garfoEsquerda.unlock();
        System.out.println(Thread.currentThread().getName() + " soltou o garfo esquerdo");
        garfoDireita.unlock();
        System.out.println(Thread.currentThread().getName() + " soltou o garfo direito");
    }
}