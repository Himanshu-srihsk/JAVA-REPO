import java.util.*;
import java.lang.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;  

//Reflection Java
class Bird{}

class Eagle{
  public String breed;
  private boolean canSwim;

  public void fly(){
    System.out.println("fly");
  }

   public void eat(){
    System.out.println("eat");
  }
  private void swim(){
    System.out.println("swim");
  }
}
class Sparrow{
  Sparrow(){}
  public void fly(int p,boolean b,String s){
    System.out.println("fly int="+ p+ " fly boolean ="+ b+ " fly String ="+ s);
  }
}

class Bd{
  private Bd(){}
  public void fly(){
    System.out.println("fly");
  }
}


class Java_Reflection{
	public static void main(String[] args) throws InvocationTargetException, InstantiationException, NoSuchFieldException, IllegalAccessException{
    // try{
    //     Class birdClass = Class.forName("Bird");
    // }catch(Exception e){
      
    // }
      

       //Class birdClass = Bird.class;

      //  Bird birdObj = new Bird();
      //  Class birdClass = birdObj.getClass();

       Class eagleClass = Eagle.class;
       System.out.println(eagleClass.getName());
       System.out.println(Modifier.toString(eagleClass.getModifiers()));
       /*Eagle
        * Public
        */

       //reflection of method

       //Method[] methods = eagleClass.getMethods();
       Method[] methods = eagleClass.getDeclaredMethods();
       for(Method m:methods){
        System.out.println("method name="+m.getName());
         System.out.println("method Return tyoe name="+m.getReturnType());
          System.out.println("class name="+m.getDeclaringClass());
         System.out.println("******************");

         /*method name=eat
method Return tyoe name=void
class name=class Eagle
******************
method name=swim
method Return tyoe name=void
class name=class Eagle
******************
method name=fly
method Return tyoe name=void
class name=class Eagle
****************** */
       }
      try{
        // Class sparrowClass = Class.forName("Sparrow");
         //Object sparrObject = sparrowClass.newInstance();
          
          //Sparrow sparrObject = new Sparrow();

         // Method fly= sparrowClass.getMethod("fly", int.class,boolean.class,String.class);
        // Method fly= sparrowClass.getDeclaredMethod("fly",int.class,boolean.class,String.class);

          //fly.invoke(sparrObject, 1,true,"hello");
      }catch(Exception e){

      }
     //getFields()


      Class eagleCl = Eagle.class;
         Eagle eagleObject = new Eagle();
      Field  field = eagleCl.getDeclaredField("breed");
      field.set(eagleObject, "Sparrow flyer breed");
      System.out.println(eagleObject.breed);
 
      Field  field1 = eagleCl.getDeclaredField("canSwim");
          field1.setAccessible(true);
          field1.set(eagleObject, true);
          if(field1.getBoolean(eagleObject)){
            System.out.println("val is set to be true");
          }

          //reflection in singleton class
         System.out.println("************************");
         Class bdClass = Bd.class;
         Constructor[] bdConstructors = bdClass.getDeclaredConstructors();
         for(Constructor c: bdConstructors){
          System.out.println("modifier:"+Modifier.toString(c.getModifiers()));
           c.setAccessible(true);
           Bd bdobj = (Bd) c.newInstance();
           bdobj.fly();
         }
    }
}