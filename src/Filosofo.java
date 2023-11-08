import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Filosofo extends Thread{
    private final Lock garfoEsquerda;
    private final Lock garfoDireita;

    public Filosofo(String nome, Lock garfoEsquerda, Lock garfoDireita) {
        super(nome);
        this.garfoEsquerda = garfoEsquerda;
        this.garfoDireita = garfoDireita;
    }

    @Override
    public void run() {
        while (true) {
            pensar();
            pegarGarfos();
            comer();
            soltarGarfos();
        }
    }

    private void pensar() {
        System.out.println(Thread.currentThread().getName() + " está pensando");
    }

    private void pegarGarfos() {
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
        System.out.println(Thread.currentThread().getName() + " pegou os dois garfos");
    }

    private void comer() {
        System.out.println(Thread.currentThread().getName() + " está comendo");
    }

    private void soltarGarfos() {
        garfoEsquerda.unlock();
        System.out.println(Thread.currentThread().getName() + " soltou o garfo esquerdo");
        garfoDireita.unlock();
        System.out.println(Thread.currentThread().getName() + " soltou o garfo direito");
    }
}