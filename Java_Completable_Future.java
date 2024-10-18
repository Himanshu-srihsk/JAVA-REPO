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
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicInteger;

class Java_Completable_Future{
	public static void main(String[] args){
      try{
         ThreadPoolExecutor poolexecutor = new ThreadPoolExecutor(1,1,1,
            TimeUnit.HOURS, new ArrayBlockingQueue<>(10),Executors.defaultThreadFactory(),new ThreadPoolExecutor.AbortPolicy());
         
      // //    CompletableFuture<String> asyncTask1 = CompletableFuture.supplyAsync(()->{
      // //       return "task Compeletd"; 
      // //    },poolexecutor);

       CompletableFuture<String> asyncTask1 = CompletableFuture.supplyAsync(()->{
            return "concept and ";
         },poolexecutor).thenApply((String val)->{
            return val+" coding";
         });

         System.out.println(asyncTask1.get());


         CompletableFuture<String> aa = CompletableFuture.supplyAsync(()->{
            return "concept and ";
         },poolexecutor);
         CompletableFuture<String> abb = aa.thenApply((String val)->{
            return val+" coding";
         });

        System.out.println(abb.get());
      }catch(Exception e){

      }

//concept and  coding

      //thenapply and then applyAsync

// try{
         ThreadPoolExecutor poolexecutor = new ThreadPoolExecutor(1,1,1,
            TimeUnit.HOURS, new ArrayBlockingQueue<>(10),Executors.defaultThreadFactory(),new ThreadPoolExecutor.AbortPolicy());
         

       CompletableFuture<String> asyncTask1 = CompletableFuture.supplyAsync(()->{
            try {
               System.out.println("threadName of SupplyAsync:"+ Thread.currentThread().getName());
              Thread.sleep(5000); 
            } catch (Exception e) {
               // TODO: handle exception
            }
            return "concept and ";
         },poolexecutor);

         CompletableFuture<String> asyncTask2 = asyncTask1.thenApply((String val)->{
           System.out.println("threadName of then Apply:"+ Thread.currentThread().getName());
              return val+" coding";
            
         });
// //output of above :\Users\KumarHimansh\Desktop\edit_hsk\TechieDelight>java Java_Prac
// // threadName of SupplyAsync:pool-1-thread-1
// // threadName of then Apply:pool-1-thread-1

         CompletableFuture<String> asyncTask21 = asyncTask1.thenApplyAsync((String val)->{
           System.out.println("threadName of then Apply:"+ Thread.currentThread().getName());
              return val+" coding";
            
         });
// //C:\Users\KumarHimansh\Desktop\edit_hsk\TechieDelight>java Java_Prac
// // threadName of SupplyAsync:pool-1-thread-1
// // threadName of then Apply:ForkJoinPool.commonPool-worker-1


//       //   System.out.println(asyncTask1.get());
//       }catch(Exception e){

//       }

//thenCompose and thenComposeAsync

//   try{
//          ThreadPoolExecutor poolexecutor = new ThreadPoolExecutor(5,5,1,
//             TimeUnit.HOURS, new ArrayBlockingQueue<>(10),Executors.defaultThreadFactory(),new ThreadPoolExecutor.AbortPolicy());
         
//          // CompletableFuture<String> asyncTask1 = CompletableFuture.supplyAsync(()->{
//          //    return "hello";
//          // },poolexecutor);

//          // CompletableFuture<String> asyncTask2 =  asyncTask1.thenCompose((String val)->{
//          //    return CompletableFuture.supplyAsync(()-> val+"world");
//          // });

//           CompletableFuture<String> asyncTask1 = CompletableFuture.supplyAsync(()->{
//             return "hello";
//          },poolexecutor)
//          .thenComposeAsync((String val)->{
//             return CompletableFuture.supplyAsync(()-> val+"world");
//          });

//         System.out.println(asyncTask1.get());
//       }catch(Exception e){

//       }




//  try{
//          ThreadPoolExecutor poolexecutor = new ThreadPoolExecutor(1,1,1,
//             TimeUnit.HOURS, new ArrayBlockingQueue<>(10),Executors.defaultThreadFactory(),new ThreadPoolExecutor.AbortPolicy());
         
//          CompletableFuture<String> asyncTask1 = CompletableFuture.supplyAsync(()->{
//             return "hello";
//          },poolexecutor);
//           CompletableFuture<Void> asyncTask2 = asyncTask1.thenAccept((String val)->{
//               System.out.println("printing value :"+val);
//            });
      
//         System.out.println(asyncTask1.get());
//       }catch(Exception e){

//       }

//    }


//  try{
//          ThreadPoolExecutor poolexecutor = new ThreadPoolExecutor(1,1,1,
//             TimeUnit.HOURS, new ArrayBlockingQueue<>(10),Executors.defaultThreadFactory(),new ThreadPoolExecutor.AbortPolicy());
         
//          CompletableFuture<Integer> asyncTask1 = CompletableFuture.supplyAsync(()->{
//             return 10;
//          },poolexecutor);

//           CompletableFuture<String> asyncTask2 = asyncTask1.supplyAsync(()->{
//              return "k ";
//            },poolexecutor);

//             CompletableFuture<String> CompletableFutureObj = asyncTask1.thenCombine(asyncTask2, (Integer v1,String v2)-> v1+v2);
      
//         System.out.println(CompletableFutureObj.get());
//       }catch(Exception e){

//       }

      /*
       C:\Users\KumarHimansh\Desktop\edit_hsk\TechieDelight>java Java_Prac
      10k
      ^C
       */
   }

}

