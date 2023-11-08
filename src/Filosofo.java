public class Filosofo extends Thread{
    private int id;
    private Garfo garfos;
    public Filosofo(int id){
        this.id = id;
    }
    private void pensar() {
        System.out.println("Filósofo " + id + " está pensando.");
        try {
            Thread.sleep((long) (Math.random() * 1000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    private void comer() {
        System.out.println("Filósofo " + id + " está comendo.");
        try {
            Thread.sleep((long) (Math.random() * 1000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        while (true) {
            this.pensar();
            garfos.pegarGarfo(id);
            this.comer();
            garfos.liberarGarfo(id);
    }
    }
}

