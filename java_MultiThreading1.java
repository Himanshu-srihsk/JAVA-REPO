import java.util.*;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.concurrent.locks.StampedLock;

 class XYZ implements Runnable{
  public void run(){
    System.out.println("dede");
  }
 }

class PQR extends Thread{
  public void run(){
    System.out.println("PQR thread");
  }
}
class MonitorLockExample{
  public synchronized void task1(){
    try {
      System.out.println("inside task1");
      Thread.sleep(10000); 
       System.out.println("task1 completed");
    } catch (Exception e) {
      // TODO: handle exception

    }
   }
   public void task2(){
    System.out.println("task2 but b4 sychronized");
    synchronized(this){
      System.out.println("task2 inside sychronized");
    }
   }

   public void task3(){
    System.out.println("task3");
   }
}
class MonitorThreadRunnable implements Runnable{
  MonitorLockExample obj;
  MonitorThreadRunnable(MonitorLockExample obj){
    this.obj=obj;
  }
  public void run(){
    this.obj.task1();
  }
}

class SharedResource{
  boolean itemAvailable = false;
  public synchronized void addItem(){
    itemAvailable = true;
    System.out.println("Item added by: "+ Thread.currentThread().getName() +" invoking all thread whciah are waiting" );
    notifyAll();
  }
  public synchronized void consumeitem(){
    System.out.println("Consume item method invoked by:"+Thread.currentThread().getName());
    while(!itemAvailable){
      try {
        System.out.println("Thread:"+Thread.currentThread().getName()+ " is waiting now");
        this.wait();
      } catch (Exception e) {
        // TODO: handle exception
      }
    }
    System.out.println("item consumed by :"+Thread.currentThread().getName());
    itemAvailable = false;
  }
}

class produceTask implements Runnable{
  SharedResource sharedResource;
  produceTask(SharedResource sharedResource){
    this.sharedResource = sharedResource;
  }
  public void run(){
    System.out.println("Producer Thread :"+Thread.currentThread().getName());
    try {
      Thread.sleep(5000);
    } catch (Exception e) {
      // TODO: handle exception
    }
    this.sharedResource.addItem();
  }

}
class consumeTask implements Runnable{
  SharedResource sharedResource;
  consumeTask(SharedResource sharedResource){
    this.sharedResource = sharedResource;
  }
  public void run(){
    System.out.println("Consumer Thread :"+Thread.currentThread().getName());
   this.sharedResource.consumeitem();
  }

}
//producer consumer problem
class SharedResource1{
  private Queue<Integer> sharedbuffer;
  private int buffersize;
  SharedResource1(int buffersize){
   sharedbuffer = new LinkedList<>();
   this.buffersize = buffersize;
  }
  public synchronized void produce(int item) throws Exception{
    while (sharedbuffer.size() == buffersize) {
      System.out.println("Buffer is full, producers waiting for consumers");
      wait();
    }
    sharedbuffer.add(item);
    System.out.println("produced "+item);
    notify();
  }
  public synchronized int consume() throws Exception{
    try {
      while (sharedbuffer.isEmpty()) {
        System.out.println("Buffer is empty, consumer waiting for producer");
        wait();
     }
    } catch (Exception e) {
    }
    
    int item = sharedbuffer.poll();
    System.out.println("Consumed:"+item);
    notify();
    return item;
  }
}

//custom lock 
//Reentrant lock

class Reentrant{
  boolean itemAvailable = false;
  //ReentrantLock lock = new ReentrantLock();
  
  public void producer(ReentrantLock lock){
    try {
      lock.lock();
       System.out.println("lock acquired by: "+ Thread.currentThread().getName());
       itemAvailable = true;
       Thread.sleep(4000);
    } catch (Exception e) {
      // TODO: handle exception
    }
    finally{
       lock.unlock();
       System.out.println("lock release by: "+ Thread.currentThread().getName());
    }
  }
}

class ReadWrite{
  boolean itemAvailable = false;
  // ReentrantLock lock = new ReentrantLock();
  
  public void producer(ReadWriteLock lock){
    try {
      lock.readLock().lock();
       System.out.println("Read lock acquired by: "+ Thread.currentThread().getName());
      // itemAvailable = true;
       Thread.sleep(8000);
    } catch (Exception e) {
      // TODO: handle exception
    }
    finally{
      lock.readLock().unlock();
       System.out.println("Read lock release by: "+ Thread.currentThread().getName());
    }
  }
  public void consume(ReadWriteLock lock){
    try {
      lock.writeLock().lock();
       System.out.println("write lock acquired by: "+ Thread.currentThread().getName());
       itemAvailable = false;
    } catch (Exception e) {
      // TODO: handle exception
    }
    finally{
      lock.writeLock().unlock();
       System.out.println("Write lock release by: "+ Thread.currentThread().getName());
    }
  }
}

class Stamped{
  boolean itemAvailable = false;
  StampedLock lock = new StampedLock();
  
  public void producer(){
    long stamp= lock.readLock();
    try {
       System.out.println("Read lock acquired by: "+ Thread.currentThread().getName());
       itemAvailable = true;
       Thread.sleep(6000);
    } catch (Exception e) {
      // TODO: handle exception
    }
    finally{
      lock.unlockRead(stamp);
       System.out.println("Read lock release by: "+ Thread.currentThread().getName());
    }
  }

  public void consume(){
    long stamp = lock.writeLock();
    try {
       System.out.println("write lock acquired by: "+ Thread.currentThread().getName());
       itemAvailable = false;
    } catch (Exception e) {
      // TODO: handle exception
    }
    finally{
      lock.unlockWrite(stamp);
       System.out.println("Write lock release by: "+ Thread.currentThread().getName());
    }
  }
}

class StampedOptimistic{
  int a=10;
  StampedLock lock = new StampedLock();
  
  public void producer(){
    long stamp= lock.tryOptimisticRead();
    try {
       System.out.println("taken optimistic read");
       a=11;
      Thread.sleep(6000);
       if(lock.validate(stamp)){
        System.out.println("updated a value successfully");
       }else{
        System.out.println("rollback of work");
        a=10;//rollback
       }
    } catch (Exception e) {
      // TODO: handle exception
    }
   
  }

  public void consumer(){
    long stamp = lock.writeLock();
    try {
       System.out.println("performing work");
       a=9;
    } catch (Exception e) {
      // TODO: handle exception
    }
    finally{
      lock.unlockWrite(stamp);
       System.out.println("Write lock release by: "+ Thread.currentThread().getName());
    }
  }
}

//Semaphore

class SemaphoreCheck{
  boolean itemAvailable = false;
  Semaphore lock = new Semaphore(2);

  public void producer(){
    try {
      lock.acquire();
       System.out.println("lock acquired by: "+ Thread.currentThread().getName());
       itemAvailable = true;
       Thread.sleep(4000);
    } catch (Exception e) {
      // TODO: handle exception
    }
    finally{
      lock.release();
       System.out.println("lock release by: "+ Thread.currentThread().getName());
    }
  }

}

//Inter thread COmmunication
class ConditionCheck{
  boolean itemAvailable = false;
  ReentrantLock lock = new ReentrantLock();
  Condition condition = lock.newCondition();
  
  public void producer(){
    try {
      lock.lock();
       System.out.println("produce lock acquired by: "+ Thread.currentThread().getName());
       if(itemAvailable){
        System.out.println("produce thread is waiting: "+ Thread.currentThread().getName());
        condition.await();
       }
       itemAvailable = true;
       condition.signal();
    } catch (Exception e) {
      // TODO: handle exception
    }
    finally{
      lock.unlock();
       System.out.println("produce lock release by: "+ Thread.currentThread().getName());
    }
  }
  public void consume(){
    try {
      Thread.sleep(1000);
      lock.lock();
       System.out.println("consume lock acquired by: "+ Thread.currentThread().getName());
       if(!itemAvailable){
        System.out.println("consume thread is waiting: "+ Thread.currentThread().getName());
        condition.await();
       }
       itemAvailable = false;
       condition.signal();
    } catch (Exception e) {
      // TODO: handle exception
    }
    finally{
      lock.unlock();
       System.out.println("consume lock release by: "+ Thread.currentThread().getName());
    }
  }
}
class java_MultiThreading1{

	public static void main(String[] args) throws ClassNotFoundException,MyCustomException{
    System.out.println(Thread.currentThread().getName());
    //  XYZ x = new XYZ();
    //  Thread th = new Thread(x);
    //  th.start();
    //  System.out.println(Thread.currentThread().getName());


    //  PQR p =new PQR();
    //  p.start();
    //   System.out.println(Thread.currentThread().getName());

// MonitorLockExample monitorLockExample = new MonitorLockExample();
// MonitorThreadRunnable runnableObj = new MonitorThreadRunnable(monitorLockExample);
// Thread t1 = new Thread(runnableObj);

// // Thread t1 = new Thread(()->{monitorLockExample.task1();});

// Thread t2 = new Thread(()->{monitorLockExample.task2();});
// Thread t3 = new Thread(()->{monitorLockExample.task3();});

// t1.start();
// t2.start();
// t3.start();

// SharedResource sharedResource = new SharedResource();
//  Thread producThread= new Thread(new produceTask(sharedResource));
//  //Thread consumeThread= new Thread(new consumeTask(sharedResource));

//  Thread consumeThread = new Thread(()->{sharedResource.consumeitem();});
//  producThread.start();
//  consumeThread.start();
//  System.out.println("Main Method End");

 // SharedResource1 sharedResource1 = new SharedResource1(3);
 //  Thread pThread = new Thread(()->{
 //  try {
 //    for(int i=1;i<=6;i++){
 //      sharedResource1.produce(i);
 //    }
 //  } catch (Exception e) {
 //    // TODO: handle exception
 //  }
 //  });

 //    Thread cThread = new Thread(()->{
 //  try {
 //    for(int i=1;i<=6;i++){
 //      sharedResource1.consume();
 //    }
 //  } catch (Exception e) {
 //    // TODO: handle exception
 //  }
 //  });

 //  pThread.start();
 //  cThread.start();

  /*produced 1
Consumed:1
Buffer is empty, consumer waiting for producer
produced 2
Consumed:2
Buffer is empty, consumer waiting for producer
produced 3
Consumed:3
Buffer is empty, consumer waiting for producer
produced 4
Consumed:4
Buffer is empty, consumer waiting for producer
produced 5
Consumed:5
Buffer is empty, consumer waiting for producer
produced 6
Consumed:6 */

// ReentrantLock lock = new ReentrantLock();
// Reentrant reentrant1= new Reentrant();
// Thread th1s = new Thread(()->{
//   reentrant1.producer(lock);
// });

// Reentrant reentrant2= new Reentrant();

// Thread th2s = new Thread(()->{
//   reentrant2.producer(lock);
// });

// th1s.start();
// th2s.start();


//ReadWriteLock

// ReadWrite readWrite = new ReadWrite();
// ReadWriteLock lock = new ReentrantReadWriteLock();

// Thread th1r = new Thread(()->{
//   readWrite.producer(lock);
// });


// Thread th2r = new Thread(()->{
//   readWrite.producer(lock);
// });

// ReadWrite readWrite1 = new ReadWrite();
// Thread th3r = new Thread(()->{
//   readWrite1.consume(lock);
// });

// th1r.start();
// th2r.start();
// th3r.start();

/*C:\Users\KumarHimansh\Desktop\edit_hsk\TechieDelight>java Java_Prac
main
Read lock acquired by: Thread-0
Read lock acquired by: Thread-1
Read lock release by: Thread-0
Read lock release by: Thread-1
write lock acquired by: Thread-2
Write lock release by: Thread-2 */


//StampedLock

// Stamped  stamped = new Stamped();
// Thread th1s = new Thread(()->{
//   stamped.producer();
// });


// Thread th2s = new Thread(()->{
//   stamped.producer();
// });

// th1s.start();
// th2s.start();

StampedOptimistic stampedOptimistic = new StampedOptimistic();
Thread th1 = new Thread(()->{
  stampedOptimistic.producer();
});


Thread th2 = new Thread(()->{
  stampedOptimistic.consumer();
});

th1.start();
th2.start();

// main
// taken optimistic read
// performing work
// Write lock release by: Thread-1
// rollback of work

// SemaphoreCheck semaphoreCheck = new SemaphoreCheck();
// Thread th1 = new Thread(()->{
//   semaphoreCheck.producer();
// });


// Thread th2 = new Thread(()->{
//   semaphoreCheck.producer();
// });

// Thread th3 = new Thread(()->{
//   semaphoreCheck.producer();
// });


// Thread th4 = new Thread(()->{
//   semaphoreCheck.producer();
// });

// th1.start();
// th2.start();
// th3.start();
// th4.start();

/*main
lock acquired by: Thread-0
lock acquired by: Thread-1
lock acquired by: Thread-2
lock acquired by: Thread-3
lock release by: Thread-0
lock release by: Thread-1
lock release by: Thread-2
lock release by: Thread-3*/

//condition IPC
// ConditionCheck conditionCheck = new ConditionCheck();
// Thread th1 = new Thread(()->{
//   for(int i=0;i<2;i++){
//     conditionCheck.producer();
//   }
// });


// Thread th2 = new Thread(()->{
//   for(int i=0;i<2;i++){
//     conditionCheck.consume();
//   }
// });

// th1.start();
// th2.start();

/*main
produce lock acquired by: Thread-0
produce lock release by: Thread-0
produce lock acquired by: Thread-0
produce thread is waiting: Thread-0
consume lock acquired by: Thread-1
produce lock release by: Thread-0
consume lock release by: Thread-1
consume lock acquired by: Thread-1
consume lock release by: Thread-1*/

   }
   
}