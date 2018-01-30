class CallMe
{
    void call(String msg)
    {
        System.out.print("[" + msg );
        try{
            Thread.sleep(1000);
        }
        catch (Exception ex){
            System.out.println(ex.getMessage());
        }
        System.out.println("]");
    }
}

class Caller implements Runnable{
    String message;
    CallMe target;
    Thread t;
    Caller(String message, CallMe target){
        this.message = message;
        this.target = target;
        t = new Thread(this);
        t.start();
    }

    public void run() {
        synchronized (target) {
            target.call(message);
        }
    }
}

public class Synch
{
    public static void main(String args[])
    {
        CallMe target = new CallMe();
        Caller object1 = new Caller("Hello", target);
        Caller object2 = new Caller("World", target);
        try{
            object1.t.join();
            object2.t.join();
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }
}
