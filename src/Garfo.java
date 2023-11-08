import java.util.concurrent.Semaphore;

public class Garfo {
    public int id;
    public int numGarfos;
    Semaphore[] garfos = new Semaphore[numGarfos];
        public void Garfos() {

            for (int i = 0; i < numGarfos; i++) {
                garfos[i] = new Semaphore(1);
            }
        }

        public void pegarGarfo(int id) throws InterruptedException {
            try{
                garfos[id].acquire();
                garfos[(id + 1) ].acquire();
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }

        public void liberarGarfo(int id) {
                garfos[id].release();
                garfos[(id + 1) ].release();
        }
    }

