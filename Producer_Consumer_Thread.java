import java.util.concurrent.*;
import java.util.*;
import java.util.Queue;
class SharedResource{
	private Queue<Integer> sharedBuffer;
	int BufferSize;
	public SharedResource(int BufferSize){
		 this.sharedBuffer = new LinkedList<>();
       this.BufferSize=BufferSize;
      
	}
	public synchronized void produce(int item) throws Exception{
		while(sharedBuffer.size() == BufferSize){
			 System.out.println("Buffer is full , producer waiting for consumer ");
               wait();
		}
		sharedBuffer.add(item);
		System.out.println("Produced :"+ item);
		notify();

	} 
	public synchronized int consume()  throws Exception{
		
		while(sharedBuffer.isEmpty()){
			System.out.println("Buffer is empty , consumer waiting for  producer");
               wait();
		}
		int item = sharedBuffer.poll();
		System.out.println("Item consumed :"+ item);
		notify();
		return item;
	}
}
public class Producer_Consumer_Thread{
	
	public static void main(String[] args){
		SharedResource sharedBuffer = new SharedResource(3);
		
        Thread ProducerThread = new Thread(() -> {
           try{
             for(int i=1;i<=6;i++){
             	sharedBuffer.produce(i);
             }
           }
           catch(Exception e){

           }
        });

        Thread ConsumerThread = new Thread(() -> {
           try{
             for(int i=1;i<=6;i++){
             	sharedBuffer.consume();
             }
           }
           catch(Exception e){

           }
        });
        ProducerThread.start();
        ConsumerThread.start();
	}
}