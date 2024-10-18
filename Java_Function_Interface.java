import java.lang.*;  

//Functional interface

@FunctionalInterface
interface Bird{
void canFly(String val);
}




@FunctionalInterface
interface Vechile{
void canDrive(String val);
default boolean canWalk(){
  return false;
}
// default String canSwim(String val){
//    return val;
// }
static void canEat(){

}
public String toString();
}

class Audi implements Vechile{
  public void canDrive(String val){
    
  }
  // public String toString(){
  //   return "Audi";
  // }
}

//lambda expression

class Eagle implements Bird{
  public void canFly(String val){
    System.out.println(val);
  }
}

//using anonymous class

//Type of Functional Interface 
//COnsumer,Supplier,Functional,Predicate

//consumer
@FunctionalInterface
interface Consumer<T>{
  void accept(T t);
}

@FunctionalInterface
interface Supplier<T>{
  T get();
}

@FunctionalInterface
interface Function<T,R>{
  R apply(T t);
}

@FunctionalInterface
interface Predicate<T>{
  boolean test(T t);
}

class Java_Function_Interface{
	public static void main(String[] args){
    //using anonymous class
    Bird bd= new Eagle();
    bd.canFly("yes it can fly");
      Bird b= new Bird() {
        public void canFly(String va){
           System.out.println("val is "+va);
        }
      };
      b.canFly("yes");

       Eagle b1 = new Eagle();
       b1.canFly("Eagle alos can");

       //using lambda expression
        Bird b2 = (String val) -> {
              System.out.println(val);
       };
       b2.canFly("Sparrow Lambda can");

        Bird b3 = (String val) -> System.out.println("can i drive="+val);
       b3.canFly("Sparrow Lambda  one line can");

       Vechile v= (String val) -> System.out.println("yes i can drive");
       System.out.println("can i walk = "+v.canWalk());
       v.canDrive("yes");

       Consumer<Integer> c1= (Integer x) ->{
        if(x%2==0){
          System.out.println("Number is "+x+ " Even");
        }else{
          System.out.println("Number is "+x+ " Odd");
        }
       };
       c1.accept(21);

       Supplier<String> supplier = ()->"This is SParrrow baord";
       System.out.println(supplier.get());

       Function<Integer,String> fn = (Integer num) ->{
        String output = num.toString();
        return output;
       };
      
       System.out.println(fn.apply(64));

       Predicate<Integer> predicate = (Integer num)->{
        if(num%2==0){
          return true;
        }else{
          return false;
        }
       };
       System.out.println(predicate.test(3));

//////////////////////////////////////////////////////////////
       Runnable task1 = new Runnable(){
 
          @Override
          public void run(){
              System.out.println("Task #1 is running");
          }
      };
       
       
      // Thread thread1 = new Thread(task1);
      // thread1.start();

      //or for above code we can write as

    //   Thread thread1 = new Thread(new Runnable() {
    //     @Override
    //     public void run(){
    //         System.out.println("Task #1 is running");
    //     }
    // });

    Thread thread1 = new Thread(()->System.out.println("Task #1 is running"));
     
    thread1.start();

    //////////////////////////////////////////////////////////////

    }
}