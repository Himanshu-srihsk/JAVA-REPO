//printer  
// Thread1 : 1,4,7 etc
// Thread2 : 2,5,8 etc
// Thread3 : 3,6,9 etc
import java.util.concurrent.*;
import java.util.*;
enum PrinterType{
    One,
    Two,
    Three;
}
class Stat{
    private PrinterType next;
    Stat(PrinterType next){
        this.next=next;
    }
     void setPrinterType(PrinterType next){
        this.next= next;
    }
      PrinterType getPrinterType(){
        return this.next;
    }
}
class MyPrinter implements Runnable{
    private final PrinterType currPrinter;
    private final PrinterType nextPrinter;
    private  int start;
    private final int end;
    private final int increment;
    private final Stat s;


    MyPrinter(int start,int end,PrinterType c,PrinterType p,int increment,Stat s){ 
        this.start=start;
        this.end=end;
        this.currPrinter = c;
        this.nextPrinter=p;
        this.increment=increment;
        this.s=s;
    }
    public void run(){
        while(this.start<=this.end){
            synchronized(s){
                while(s.getPrinterType() != this.currPrinter){
                    try {
                        s.wait();
                    } catch (Exception e) {
                        // TODO: handle exception
                    }
                }
                System.out.println(Thread.currentThread().getName()+" : "+ this.start);
                s.notifyAll();
                this.start += increment;
                s.setPrinterType(this.nextPrinter);
            }
            
        }
            
    }
}

public class MultiThreading1 {
    public static void main(String[] args){
      Stat stat = new Stat(PrinterType.One);
      final MyPrinter p1= new MyPrinter(1,30,PrinterType.One,PrinterType.Two,3,stat);
      final MyPrinter p2= new MyPrinter(2,30,PrinterType.Two,PrinterType.Three,3,stat);
      final MyPrinter p3= new MyPrinter(3,30,PrinterType.Three,PrinterType.One,3,stat);
      final Thread t1= new Thread(p1);
      final Thread t2=new Thread(p2);
      final Thread t3= new Thread(p3);
      t1.start();
      t2.start();
      t3.start();
    }
}

/* Output:
Thread-0 : 1
Thread-1 : 2
Thread-2 : 3
Thread-0 : 4
Thread-1 : 5
Thread-2 : 6
Thread-0 : 7
Thread-1 : 8
Thread-2 : 9
Thread-0 : 10
Thread-1 : 11
Thread-2 : 12
Thread-0 : 13
Thread-1 : 14
Thread-2 : 15
Thread-0 : 16
Thread-1 : 17
Thread-2 : 18
Thread-0 : 19
Thread-1 : 20
Thread-2 : 21
Thread-0 : 22
Thread-1 : 23
Thread-2 : 24
Thread-0 : 25
Thread-1 : 26
Thread-2 : 27
Thread-0 : 28
Thread-1 : 29
Thread-2 : 30 */