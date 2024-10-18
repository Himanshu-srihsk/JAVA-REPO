import java.util.*;
import java.lang.*;
import java.lang.annotation.Target;
import java.lang.annotation.ElementType;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;  

class Mobile{
  @Deprecated
  public void dummyMethod(){

  }
}

interface Bird{
  public boolean fly();
}
class Eagle implements Bird{
  @Override
  public boolean fly(){
      return true;
  }
}

@FunctionalInterface
interface birdI{
  public void fly();
}

class log{
  @SafeVarargs //save from HeapPollution
  public static void printlogValues(List<Integer>...lognumLists){
    Object[] objectlist=lognumLists;
    List<String> stringvalList = new ArrayList<>();
    stringvalList.add("Hello");
    objectlist[0]=stringvalList;
  }
}

/* 
 * 
@Target(ElementType.METHOD)
public @interface Override{
  
}
/////////////////////////////////
@Target({ElementType.METHOD,ElementType.CONSTRUCTOR})
public @interface SafeVarags{
  
}
///////////////////////////////


 * 
*/

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@interface MyCustomAnnotation{

}

@MyCustomAnnotation
class TestClass{

}

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Repeatable(Categories.class)
@interface Category{
   String name();
}

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@interface Categories{
  Category[] value();
}

@Category(name="Bird")
@Category(name="Living bing")
class Eaglei{
  public void fly(){

  }
}
@Retention(RetentionPolicy.RUNTIME)
@interface MyCustomAnnotationi{
  String name();
}
@MyCustomAnnotationi(name="testing")
class Eaglis{
 public void fly(){

 }
}
//---------------------------------------------
@Retention(RetentionPolicy.RUNTIME) 
@interface MyCustomAnnotationh{
  String name() default "hello";
}
@MyCustomAnnotationh
class Eaglish{
  public void fly(){
 
  }
 }
//---------------------------------------------
class Java_Annotation{
  @SuppressWarnings("deprecation")
  // @SuppressWarnings("all")
  // @SuppressWarnings("unused")
	public static void main(String[] args) throws InvocationTargetException, InstantiationException, NoSuchFieldException, IllegalAccessException{
       Mobile m = new Mobile();
       m.dummyMethod();

       System.out.println(new TestClass().getClass().getAnnotation(MyCustomAnnotation.class));

       Category[] categories = new Eaglei().getClass().getAnnotationsByType(Category.class);
       for(Category annotation: categories){
        System.out.println(annotation.name());
       }
//output
//        @MyCustomAnnotation()
// Bird
// Living bing


MyCustomAnnotationi ms = new Eaglis().getClass().getAnnotation(MyCustomAnnotationi.class);
//   for (MyCustomAnnotationi mss: ms){
//     System.out.println(mss.name());
//   }
  System.out.println(ms.name());
  
      System.out.println(Eaglish.class.getAnnotation(MyCustomAnnotationh.class).name() );
    }
}