

public class ProducerConsumerDemo1 {
    public static void main(String args[]) {
    int i = 0;
        Thread t1 = new Thread(new Producer1(i));
        Thread t2 = new Thread(new Consumer1("A"));
        Thread t3 = new Thread(new Consumer1("B"));
        t1.start();
        t2.start();
        t3.start();
    }
}

class Producer1 implements Runnable{
    int i;
    Producer1(int i){
        this.i = i;
    }

    synchronized void produce(int i) throws InterruptedException{
        wait();
        System.out.println("Produce element " + i);
    }

    public void run(){
        while(true){
            try{
                produce(i++);
                notify();
            }
            catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}

class Consumer1 implements Runnable{
    int i;
    String name;

    public Consumer1(String name){
        this.name = name;
    }

    synchronized void consume() throws InterruptedException{
        wait();
        System.out.println("consumer " + name + " consumes element " + i);
    }

    public void run(){
        while(true){
            try{
                consume();
                notify();
            }
            catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}