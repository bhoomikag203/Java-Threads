
import java.lang.Thread;
class NewThread implements Runnable {
    Thread t;
    String name;

    NewThread( String threadname) {
        name = threadname;
        t = new Thread(this,name);
        System.out.println("child thread " + t);
        System.out.println(t.getPriority());
        t.start();
    }

    public void run() {
        try {
                for (int i = 0; i < 5; i++) {
                    System.out.println("child thread "+ name+" " + i);
                    Thread.sleep(500);
                }
        }
        catch (Exception ex) {
            System.out.print(ex.getMessage());
         }
        System.out.println("child thread "+ name + " exiting");
    }
}

class ThreadDemo
{
    public static void main(String args[])
    {
        NewThread obj1 = new NewThread("one");
        obj1.t.setPriority(Thread.NORM_PRIORITY + 2);
        System.out.println("thread one priority is "+ obj1.t.getPriority());
        NewThread obj2 = new NewThread("two");
        System.out.println("parent thread");
        System.out.println("Thread one is alive " + obj1.t.isAlive() );
        System.out.println("Thread two is alive " + obj2.t.isAlive() );
        try {
            obj1.t.join();
            obj2.t.join();
        }
        catch (Exception ex) {
            System.out.print(ex.getMessage());
        }
        System.out.println("Thread one is alive " + obj1.t.isAlive() );
        System.out.println("Thread two is alive " + obj2.t.isAlive() );
        System.out.println("parent thread exiting");
    }
}
