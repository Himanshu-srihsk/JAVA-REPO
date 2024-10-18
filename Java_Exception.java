import java.io.FileNotFoundException;
import java.util.*;

class MyCustomException extends RuntimeException{
  MyCustomException(String message){
    super(message);
  }
}
class Java_Exception{

	public static void main(String[] args) throws ClassNotFoundException,MyCustomException,ArithmeticException{
       Java_Exception j = new Java_Exception();
       //j.method1();

       Object o = 0;
      // System.out.println((String)o); //Exception in thread "main" java.lang.ClassCastException: 

      String ans=null;
     // System.out.println(ans.charAt(0)); //Exception in thread "main" java.lang.NullPointerException: 

    // int val= Integer.parseInt("abc"); //Exception in thread "main" java.lang.NumberFormatException:
      
   // bar(); //Java_Prac.java:22: error: unreported exception ClassNotFoundException;

    // check();
     /*Exception in thread "main" java.lang.ClassNotFoundException
        at Java_Exception.check(Java_Exception.java:102)
        at Java_Exception.main(Java_Exception.java:25) */

   // cam();
    /*java.lang.ClassNotFoundException
        at Java_Exception.cam(Java_Exception.java:98)
        at Java_Exception.main(Java_Exception.java:30)
 */

    try{
       ram();
    }catch(ClassNotFoundException ex){

    }
    
    try{
      m1("dummy");
    }
    // catch(ClassNotFoundException ex){

    // }catch(InterruptedException ex){

    // }
    catch(ClassNotFoundException | InterruptedException ex){
      
    }
    // catch(FileNotFoundException ex){ //Java_Prac.java:37: error: exception FileNotFoundException is never thrown in body of corresponding try statement

    // }
    catch( Exception e){

    }

// meth();

try{
  meth();
}catch(MyCustomException e){
    System.out.println("Caught MyCustomException: " + e.getMessage());  // Print the exception message
   throw e;
}finally {
            System.out.println("This will run even if exception is re-thrown.");
        }


     
     try {
            System.out.println("In try block");
            int result = 10 / 0;  // This will throw an exception
        } catch (ArithmeticException e) {
            System.out.println("Caught ArithmeticException: " + e.getMessage());
        } finally {
            System.out.println("In finally block");
            try {
                // This code may throw an exception
                String str = null;
                System.out.println(str.length());  // NullPointerException will occur here
            } catch (NullPointerException e) {
                System.out.println("Caught NullPointerException inside finally block: " + e.getMessage());
            }
        }

//     In try block
//Caught ArithmeticException: / by zero
//In finally block
//Caught NullPointerException inside finally block: Cannot invoke "String.length()" because "str" is null

     try{
       ms();
       return;
     }
    finally{
     System.out.println("inside finallay");
    }
   
   }


   ///////////////////////////
   
   public static void meth(){
    throw new MyCustomException("Some issue with COcaine");
   }
   public static void ms(){

   }
    public static void m1(String name) throws ClassNotFoundException,InterruptedException{
      if(name.equals("dummy")){
        throw new ClassNotFoundException();
      }
      else if(name.equals("interrupted")){
        throw new InterruptedException();
      }
    }

    public static void ram() throws ClassNotFoundException{
      throw new ClassNotFoundException();
    }
    public static void cam(){
      try{
        throw new ClassNotFoundException();
      }catch(ClassNotFoundException ex){
         ex.printStackTrace();
      }
    }

    private static void check() throws ClassNotFoundException{
      throw new ClassNotFoundException();
    }

    // private static void bar(){
    //   throw new ClassNotFoundException();
    // }
    private void method1(){
     method2();
     throw new ArithmeticException();
    }
    private void method2(){
      method3();
    }
    private void method3(){
     int x= 5/0;
    }
}