import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicInteger;

// class SharedResource{
//   int counter;
//   public void increment(){
//     counter++;
//   }
//   public int get(){
//     return counter;
//   }
// }

class SharedResource{
  AtomicInteger counter = new AtomicInteger(0);
  public void increment(){
    counter.incrementAndGet();
  }
  public int get(){
    return counter.get();
  }
}

class CustomThreadFactory implements ThreadFactory{
  public Thread newThread(Runnable r){
   Thread th= new Thread(r);
   th.setPriority(Thread.NORM_PRIORITY);
   th.setDaemon(false);
   return th;
  }
}


class CustomRejectHandler implements RejectedExecutionHandler{
  public void rejectedExecution(Runnable r,ThreadPoolExecutor executor){
    System.out.println("Task Rejected: "+r.toString());
  }
  
}

class Java_Future_Session{

	public static void main(String[] args){
    SharedResource resource = new SharedResource();
    // for(int i=0;i<400;i++){
    //   resource.increment();
    // }
    // System.out.println(resource.get());

    // Thread t1= new Thread(()->{
    //   for(int i=0;i<200;i++){
    //   resource.increment();
    //  }
    // });

    //  Thread t2= new Thread(()->{
    //   for(int i=0;i<200;i++){
    //   resource.increment();
    //  }
    // });

    // t1.start();
    // t2.start();
    // try{
    //   t1.join();
    //   t2.join();
    // }catch(Exception e){

    // }
    // System.out.println(resource.get());

    ///////////////////////////////////////////////////////////////////////////

    ThreadPoolExecutor executor = new ThreadPoolExecutor(2, 4, 10, 
    TimeUnit.MINUTES, new ArrayBlockingQueue<>(2),new CustomThreadFactory(),new CustomRejectHandler());

     for(int i=1;i<=7;i++){
         executor.submit(()->{
          try {
            Thread.sleep(5000);
           
          } catch (Exception e) {
            // TODO: handle exception
          }
           System.out.println(Thread.currentThread().getName());
         });

     }
     executor.shutdown();

     /*
     Task Rejected: java.util.concurrent.FutureTask@e9e54c2[Not completed, task = java.util.concurrent.Executors$RunnableAdapter@6ce253f1[Wrapped task = Java_Future_Session$$Lambda/0x0000023640001000@53d8d10a]]
300
Thread-0
Thread-1
Thread-3
Thread-2
Thread-0
Thread-1 */
    ///////////////////////////////////////////////////////////////////////////
      //FUTURE  CODE
    //  ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(1, 1, 1, 
    //  TimeUnit.HOURS, new ArrayBlockingQueue<>(10),Executors.defaultThreadFactory(),new ThreadPoolExecutor.AbortPolicy());

    //  Future<?> futureObj = poolExecutor.submit(()->{
    //   try {
    //     Thread.sleep(7000);
    //     System.out.println("this is the task which Thread will execute");
    //   } catch (Exception e) {
    //     // TODO: handle exception
    //   }
    //  });
    //  //caller is checking the status of the thread it created
    //  System.out.println(futureObj.isDone());

    //  try {
    //   futureObj.get(2,TimeUnit.SECONDS);
    //  }catch(TimeoutException e){
    //     System.out.println("Timeout Exception Happened");
    //  } 
    //  catch (Exception e) {
    //   // TODO: handle exception
    //  }
     
    //  try {
    //   futureObj.get();
    //  } catch (Exception e) {
    //   // TODO: handle exception
    //  }

    //   System.out.println("is Done: "+futureObj.isDone());
    //    System.out.println("is Cancelled: "+futureObj.isCancelled());

    
/*C:\Users\KumarHimansh\Desktop\edit_hsk\TechieDelight>java Java_Prac
false
Timeout Exception Happened
this is the task which Thread will execute
is Done: true
is Cancelled: false
^C */


// ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(3, 3, 1, 
//      TimeUnit.MINUTES, new ArrayBlockingQueue<>(10),Executors.defaultThreadFactory(),new ThreadPoolExecutor.AbortPolicy());
//  //Usecase1  Runnable 

//  Future<?> futureobj1= poolExecutor.submit(()->{
//    System.out.println("Task1 with Runnable");
//  });
  
//  try {
//   Object o = futureobj1.get();
//   System.out.println(o==null);
//  } catch (Exception e) {
//   // TODO: handle exception
//  }

 //Usecase2
ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(3, 3, 1, 
     TimeUnit.MINUTES, new ArrayBlockingQueue<>(10),Executors.defaultThreadFactory(),new ThreadPoolExecutor.AbortPolicy());

    List<Integer> output = new ArrayList<>();
  Future<List<Integer>> futureobjList= poolExecutor.submit(new MyRunnable(output),output);
  
//      try {
//       futureobjList.get();
//       System.out.println(output.get(0));

//       //or
//       List<Integer> res=futureobjList.get();
//       System.out.println(res.get(0));

//      } catch (Exception e) {
//       // TODO: handle exception
//      }


//Usecase3 callable
ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(3, 3, 1, 
     TimeUnit.MINUTES, new ArrayBlockingQueue<>(10),Executors.defaultThreadFactory(),new ThreadPoolExecutor.AbortPolicy());

     Future<List<Integer>> futureObj = poolExecutor.submit(()->{
         List<Integer> output = new ArrayList<>();
         output.add(300);
         return output;
     });

     try {
      List<Integer> res= futureObj.get();
      System.out.println(res.get(0));
     } catch (Exception e) {
      // TODO: handle exception
     }


//Completetable Future
   


   }
   
}

class MyRunnable implements Runnable{
  List<Integer> list;
  MyRunnable(List<Integer> list){
    this.list=list;
  }
  public void run(){
    // it has to do somem work
    list.add(300);
  }
}
