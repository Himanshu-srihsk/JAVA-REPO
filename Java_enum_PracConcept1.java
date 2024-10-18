import java.util.*;
import java.lang.*;  

class Print<T>{
  T val;
  public void setdisplay(T x){
    this.val=x;
  }
  public T getdisplay(){
    return val;
  }
}

enum Target{
  Monday,
  Tuesday,
  Wednesday;
  
}

enum Custom{
  x(2,"ram Birthday"),
  y(3,"ramu Birthday"),
  z(4,"sram Birthday");
  int val;
  String comment;
  Custom(int val,String comment){
    this.val=val;
    this.comment=comment;
  }
  int getval(){
    return val;
  }
  String getComment(){
    return comment;
  }
  public static Custom getEnumValuesOf(int v){
      for(Custom c: Custom.values()){
        if(c.getval()==v){
          return c;
        }
      }
      return null;
  }
}

enum dummySample{
  Monday{
    @Override
    public void dummyMethod(){
    System.out.println("printing Monday not dummu");
  }
  },
  Tuesday,
  Wednesday;
  public void dummyMethod(){
    System.out.println("printing dummy val");
  }
}
/**
 * InnerJava_Prac
 */
interface iDummy {
 public String toLowerCase();
  
}

enum Dummy implements iDummy{
MON,
TUES,
WED;
 public String toLowerCase(){
    return this.name().toLowerCase();
  }
}
// class DBConnection1{
//   private static DBConnection1 db = new DBConnection1();
//   private DBConnection1(){

//   }
//   public static DBConnection1 getInstance(){
//           return db;
//   }
// }

// class DBConnection{
//   private static DBConnection db=null;
//   private DBConnection(){

//   }
//   public static DBConnection getInstance(){
//           if(db==null){
//             db= new DBConnection();
//           }
//           return db;
//   }
// }

// class DBConnection{
//   private static volatile DBConnection db;
//   private static Object ob;
//   private DBConnection(){

//   }
//   public static DBConnection getInstance(){
//     if(db==null){
//        synchronized(DBConnection.class){
//           if(db==null){
//               db= new DBConnection();
//             }
            
//       }
      
//     }
//     return db;
    
//   }
// }

class DBConnection{
  //when you start the appln all the nested class donot get loaded inot the memory.
  //only when they are refered they are loaded
  private static class DBConnectionHelper{
       private static  DBConnection db = new DBConnection();
  }
  public static DBConnection getInstance(){
         return DBConnectionHelper.db;
  }
}

class myImmutableClass{
  private final String name;
  private final List<Object> petNames;
  myImmutableClass(String name,List<Object> petNames){
    this.name=name;
    this.petNames=petNames;
  }
  public String getName(){
    return name;
  }
  public List<Object> getpetNames(){
    //return petNames;
    return new ArrayList<>(petNames);
  }
}
interface landAnimal{
  public boolean canBreathe();
}
interface waterAnimal{
  public boolean canBreathe();
}
class crocodile implements landAnimal,waterAnimal {
 public boolean canBreathe(){
  return true;
 }
  
}

//lectrure 14
/*
 * 
 * using interca ce we can achieve abstraction, polymorphism and multiple inheritance
 */

//nested interface can only be public

// interface Bird{
//   public void canFly();
//   public interface nonFlyingBird{
//     public void canRun();
//   }
// }

// class Eagle implements Bird{
//   public void canFly(){

//   }
// }
// class Eaglei implements Bird.nonFlyingBird{
//   public void canRun(){

//   }
// }

// class Eagle implements Bird,Bird.nonFlyingBird{
//   public void canFly(){

//   }
//    public void canRun(){

//   }
  
// }
interface Birding{
  public void canFly();
  public void canswim();
}
abstract class mui implements Birding{
  public void canFly(){

  }
  public abstract void canswim();
}

//interface within the class can be public, protected , private
class Bird{
   protected interface nonFlyingBird {
     public void canRun();
    
   }
}
class peakcldo extends Bird{

}

class pigeon extends Bird implements Bird.nonFlyingBird{
  public void canRun(){

  }
}
class Eagle implements Bird.nonFlyingBird{
  public void canRun(){

  }
}

//statuc and Default method video 15
//java9 feature default method in interface
interface Birdi{
  public void canFly();
  default int getMinFlyWeight(){
    return 100;
  }
}
interface Cirdi{
  default int getMinFlyWeight(){
    return 100;
  }
}
class xyz implements Birdi,Cirdi{
  public void canFly(){
    
  }
  public int getMinFlyWeight(){
    return 100;
  }
}
class Sparrow implements Birdi{
  public void canFly(){

  }
}
class Eaglei implements Birdi{
  public void canFly(){
    
  }
}

interface living{
  default boolean canBreathe(){
    return true;
  }
}
interface l3 extends living{

}

class EagleM implements l3{

}
interface l2 extends living{
  boolean canBreathe();
}

class Eaglek implements l2{
  public boolean canBreathe(){
    return true;
  }
}
interface l1 extends living{
  default boolean canBreathe(){
    boolean b= living.super.canBreathe();
    return b;
  }
}
class Eaglej implements l1{

}

//static in interface

//static method cannot be overriden by the classes which implememnts it while default method  can be overridden
interface bir{
  static boolean canBreathe(){
    return true;
  }
}

class EagleN implements bir{
  public void digestiveSystem(){
    if(bir.canBreathe()){

    }
  }
}


//java9 private method and private static 
//private static method cannot be abstarct means we have to provid ethe definition
// it  can be used insiode of partiuclar interface only
interface biridi{
  void canFly(); //equilvalent to public abstract void canFly();
  public default void MinimumWeightforFly(){
    myStaticPublicMethod(); //calling static method
    myPrivateMethod(); // calling private method
    myPrivateStaticMethod(); // calling private static method
  }
  static void myStaticPublicMethod(){
    myPrivateStaticMethod();
  }
  private void myPrivateMethod(){
//java9 feature 
//private can be access only bt default or static method . it cannot be accessed outside the interface
  }
  private static void myPrivateStaticMethod(){
//java9 feature
  }
}
class Java_enum_PracConcept1{
	public static void main(String[] args){
      Print<Integer> p = new Print<>();
      p.setdisplay(2);
      Integer x=p.getdisplay();
      System.out.println(x);
     for(Target t:Target.values()){
      System.out.println(t.ordinal());
     }
     Target t = Target.valueOf("Tuesday");
     System.out.println(t.name());

     for(Custom c: Custom.values()){
      System.out.println(c.name()+" "+ c.ordinal()+" "+ c.getval()+" "+ c.getComment());
     }

     Custom customObj = Custom.getEnumValuesOf(3);
     System.out.println("______________________________");
     System.out.println(customObj.name());
     System.out.println("______________________________");

    //  for(dummySample t1:dummySample.values()){
    //   System.out.println(t1.ordinal());
    //  }

    dummySample d= dummySample.valueOf("Monday");
      d.dummyMethod();
      d= dummySample.valueOf("Tuesday");
      d.dummyMethod();

      for(Dummy p1:Dummy.values()){
        System.out.println(p1.toLowerCase());
      }


      //singleton class

      DBConnection db = DBConnection.getInstance();


      List<Object> petNames= new ArrayList<>();
      petNames.add("dog");
      petNames.add("pran");
      myImmutableClass mc= new myImmutableClass("ramu", petNames);
      mc.getpetNames().add("tommy");
      System.out.println(mc.getpetNames());
         
      crocodile c= new crocodile();
      System.out.println(c.canBreathe());
     
      Bird.nonFlyingBird obj= new Eagle();
      obj.canRun();

      Eaglei i = new Eaglei();
      System.out.println(i.getMinFlyWeight());

      pigeon pss = new pigeon();

    }

    
  
}