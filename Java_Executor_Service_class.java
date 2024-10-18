import java.util.*;
import java.util.Map;
import java.util.HashMap;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.ArrayDeque;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.concurrent.RecursiveTask;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicInteger;

class ComputeTask extends RecursiveTask<Integer>{
   int start;
   int end;
   ComputeTask(int start,int end){
      this.start=start;
      this.end=end;
   }
   protected Integer compute(){
      if(end-start<=4){
         int totalsum=0;
         for(int i=start;i<=end;i++){
            totalsum+=i;
         }
         return totalsum;
      }else{
         //split the task
         int mid=(start+end)/2;
         ComputeTask lefttask= new ComputeTask(start, mid);
         ComputeTask righttask= new ComputeTask(mid+1, end);

         //fork the subttasks for parallel execution
         lefttask.fork();
         righttask.fork();

         //combine the result of subtasks

         int leftResult= lefttask.join();
         int rightResult = righttask.join();
         return leftResult+rightResult;

      }
   }
}
class Java_Executor_Service_class{
	public static void main(String[] args){
     ExecutorService executor1 = Executors.newFixedThreadPool(5); 
     //#used whenn need to create a Threadpoolexecutor with fixed no of thread, needed when we have exact info about how many async task
    executor1.submit(()-> "this is the new Async task");

   //    ExecutorService executor1 = Executors.newCachedThreadPool(); #create a new Thread dynamically as needed, good for handling burst of short Lived tasks
   //  executor1.submit(()-> "this is the new Async task");


   // ForkJoinPool pool = ForkJoinPool.commonPool();
   // Future<Integer> futureObj = pool.submit(new ComputeTask(0, 100));
   // try {
   //    System.out.println(futureObj.get());
   // } catch (Exception e) {
   //    // TODO: handle exception
   // }
    //Video 38 Concept and coding
   // ExecutorService poolObj = Executors.newFixedThreadPool(5);
   // poolObj.submit(()->{
   //  System.out.println("Thread going to start its work");
   // });

   // poolObj.shutdown();

   // poolObj.submit(()->{
   //  System.out.println("Thread going to start its work");
   // }); //Exception in thread "main" java.util.concurrent.RejectedExecutionException: 

//usercase1
//    ExecutorService poolObj = Executors.newFixedThreadPool(5);
//    poolObj.submit(()->{
//     try{
//      Thread.sleep(5000);
//     }catch(Exception e){

//     }
//     System.out.println("Thread going to start its work");
//    });

//    poolObj.shutdown();

//    System.out.println("Main thread unblocked and finished processing");
// /*Main thread unblocked and finished processing
// Thread going to start its work*/


//usecase2
   //    ExecutorService poolObj = Executors.newFixedThreadPool(5);
   // poolObj.submit(()->{
   //  try{
   //   Thread.sleep(5000);
   //  }catch(Exception e){

   //  }
   //  System.out.println("Thread going to start its work");
   // });

   // poolObj.shutdown();
   //  try{
   //    boolean isTerminated = poolObj.awaitTermination(2,TimeUnit.SECONDS);
   //    System.out.println("is Terminated:"+ isTerminated);
   //  }catch(Exception e){

   //  }

   // System.out.println("Main thread is completed");
/*is Terminated:false
Main thread is completed
Thread going to start its work*/

//uusecase3

  // ExecutorService poolObj = Executors.newFixedThreadPool(5);
  //  poolObj.submit(()->{
  //   try{
  //    Thread.sleep(15000);
  //   }catch(Exception e){

  //   }
  //   System.out.println("task completed");
  //  });

  //  poolObj.shutdownNow();
  //  System.out.println("Main thread is completed");
/*Main thread is completed
task completed*/


ScheduledExecutorService poolObjA = Executors.newScheduledThreadPool(5);
poolObjA.schedule(()->{
  System.out.println("hello");
},5,TimeUnit.SECONDS);

//hello -> printed by 5 second delay


// ScheduledExecutorService poolObj = Executors.newScheduledThreadPool(5);
// Future<String> futureObj = poolObj.schedule(()->{
//   return "hello";
// },5,TimeUnit.SECONDS);

// try {
//    System.out.println(futureObj.get());
// } catch (Exception e) {
//    // TODO: handle exception
// }


ScheduledExecutorService poolObj = Executors.newScheduledThreadPool(5);
poolObj.scheduleAtFixedRate(()->{
  System.out.println("hello");
},3,5,TimeUnit.SECONDS);


/*hello
hello
hello
//  */

//  ScheduledExecutorService poolObj = Executors.newScheduledThreadPool(5);
// Future<?> futureobj= poolObj.scheduleAtFixedRate(()->{
  
//  System.out.println("hello");
// },3,5,TimeUnit.SECONDS);

// try {
//    Thread.sleep(10000);
//    futureobj.cancel(true);
// } catch (Exception e) {
//    // TODO: handle exception
// }

//ThreadLocal video39
//-------------------------------------------------------------------
//ThreadLocal<String> threadLocalObj = new ThreadLocal<>();
// threadLocalObj.set(Thread.currentThread().getName());

// Thread t1= new Thread(()->{
//    threadLocalObj.set(Thread.currentThread().getName());
//    System.out.println("Task1");
// });

// t1.start();
// try {
//    Thread.sleep(2000);
// } catch (Exception e) {
//    // TODO: handle exception
// }

// System.out.println("Main Thread:  "+threadLocalObj.get());

/*Task1
Main Thread:  main */

//Cleaning up if reusing the thread

// ThreadLocal<String> threadLocalObj = new ThreadLocal<>();
// ExecutorService poolObj = Executors.newFixedThreadPool(5);
// poolObj.submit(()->{
//   threadLocalObj.set(Thread.currentThread().getName());
// });

// for(int i=1;i<15;i++){
//    poolObj.submit(()->{
//       System.out.println(threadLocalObj.get());
//    });
// }
/*null
null
null
null
pool-1-thread-1
null
null
null
null
pool-1-thread-1
null
null
null
null */


//so how you can clean thread local object b4 it is assigned to another task
ThreadLocal<String> threadLocalObj = new ThreadLocal<>();
ExecutorService poolObj = Executors.newFixedThreadPool(5);
poolObj.submit(()->{
  threadLocalObj.set(Thread.currentThread().getName());
  threadLocalObj.remove();
});

for(int i=1;i<15;i++){
   poolObj.submit(()->{
      System.out.println(threadLocalObj.get());
   });
}
/*C:\Users\KumarHimansh\Desktop\edit_hsk\TechieDelight>java Java_Prac
null
null
null
null
null
null
null
null
null
null
null
null
null
null */

//Virtual thread vs Platform thread

//Moto: to get higher Throughput  not latency


   }

}

