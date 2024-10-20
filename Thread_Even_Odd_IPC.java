import java.util.concurrent.*;
import java.util.*;


enum PrinterType {
    EVEN,
    ODD
}
class State {
     private PrinterType nextToPrint;

    public State(final PrinterType nextToPrint) {
        this.nextToPrint = nextToPrint;
    }
    PrinterType getNextToPrint(){
    	return this.nextToPrint;
    }
    void setNextToPrint(final PrinterType nextToPrint){
    	this.nextToPrint =nextToPrint;
    }
}
class Printer implements Runnable{
	private final int step;
	private final State state;
	private int currentValue;
	private final PrinterType currentPrinterType;
	private final PrinterType nextPrinterType;
	private final int maxValue;
	 static Object lock = new Object();  
	public Printer(final Integer currentValue,final Integer step,final State state, final PrinterType currentPrinterType,final PrinterType nextPrinterType,Integer maxValue){
        this.currentValue = currentValue;
        this.step = step;
        this.state = state;
        this.currentPrinterType = currentPrinterType;
        this.nextPrinterType = nextPrinterType;
        this.maxValue = maxValue;
	}
	 public void run() {
        while (currentValue <= maxValue) {
            synchronized (state) {
                while (this.currentPrinterType != state.getNextToPrint()) {
                  try{
                     state.wait();
                  }catch (InterruptedException e){
                        e.printStackTrace();  
                  }
                   
                }
                System.out.println(currentPrinterType.toString() + ": " + currentValue);
                this.currentValue += step;
                state.setNextToPrint(this.nextPrinterType);
                state.notifyAll();
            }
        }
    }
}

class Thread_Even_Odd_IPC{
	public static void main(String[] args){
      final State state = new State(PrinterType.ODD);
        final Printer oddPrinter = new Printer(1, 2, state, PrinterType.ODD, PrinterType.EVEN, 50);
        final Printer evenPrinter = new Printer(2, 2, state, PrinterType.EVEN, PrinterType.ODD, 50);

        final Thread oddThread = new Thread(oddPrinter);
        final Thread evenThread = new Thread(evenPrinter);

        oddThread.start();
        evenThread.start();
	}
}