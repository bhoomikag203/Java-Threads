class A{
    synchronized void foo(B b){
        String name = Thread.currentThread().getName();
        System.out.println(name + " entered a.foo()");

        System.out.println(name +" trying to call b.last()");
        b.last();
    }
    synchronized void last(){
        System.out.println("inside A.last");
    }
}

class B{
    synchronized void bar(A a){
        String name = Thread.currentThread().getName();
        System.out.println(name + " entered b.bar()");

        System.out.println(name +" trying to call a.last()");
        a.last();
    }
    synchronized void last(){
        System.out.println("inside B.last");
    }
}
public class DeadLock implements Runnable {
    A a = new A();
    B b = new B();

    DeadLock(){
        Thread.currentThread().setName("MainThread");
        Thread t = new Thread(this,"RacingThread");
        t.start();
        a.foo(b);
        System.out.println("back in main thread");
    }
    public void run()
    {
        b.bar(a);
        System.out.println("back in other thread");
    }
    public static void main(String args[]){
       new DeadLock();
    }
}
