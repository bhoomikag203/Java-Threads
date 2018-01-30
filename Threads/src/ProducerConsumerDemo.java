import java.util.ArrayList;
import java.util.List;

public class ProducerConsumerDemo {
    public static void main(String args[]){
        List<Integer> sharedList = new ArrayList<Integer>();
        Thread t1 = new Thread(new Producer(sharedList));
        Thread t2 = new Thread(new Consumer(sharedList, "A"));
        Thread t3 = new Thread(new Consumer(sharedList, "B" ));
        Thread t4 = new Thread(new Consumer(sharedList, "C" ));
        t1.start();
        t2.start();
        t3.start();
        t4.start();
    }
}

class Producer implements Runnable{
    final int MAX = 5;
    List<Integer> sharedList = null;
    int i = 0;

    public Producer(List<Integer> sharedList){
        super();
        this.sharedList = sharedList;
    }

    public void produce(int i) throws InterruptedException{
        synchronized (sharedList){
            while(sharedList.size() == MAX) {
                System.out.println("shared list is full ,waits for consumer to consume");
                sharedList.wait();
            }
        }

        synchronized (sharedList){
            sharedList.add(i);
            System.out.println("produced the element" + i);
            Thread.sleep(50);
            sharedList.notify();
        }
    }

    public void run()
    {
        try {
            while (true) {
                produce(i++);
            }
        }
        catch (InterruptedException e)
        {
            e.getMessage();
        }
    }
}

class Consumer implements Runnable{
        List<Integer> sharedList = null;
   String name;

    public Consumer(List<Integer> sharedList, String name){
        super();
        this.sharedList = sharedList;
        this.name = name;
    }

    public void consume() throws InterruptedException{
    synchronized (sharedList){
            while(sharedList.isEmpty()) {
            System.out.println("shared list is empty ,waits for producer to produce");
            sharedList.wait();
            }
            }

    synchronized (sharedList){
        System.out.println("consumer " + name + " consumed the element "+ sharedList.remove(0));
            Thread.sleep(2000);
            sharedList.notify();
            }
            }

    public void run()
                {
                    try {
                    while (true) {
                        consume();
                    }
                }
                    catch (InterruptedException e)
                    {
                        e.getMessage();
                    }
                }
}