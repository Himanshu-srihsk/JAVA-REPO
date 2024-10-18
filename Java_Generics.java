import java.util.*;
import java.lang.*; 
class Print{
	Object value;
	public Object getPrintValue(){
         return value;
	}
	public void setPrintValue(Object value){
          this.value = value; 
	}
}
class Print1<T>{
  T val;
  public void setdisplay(T x){
    this.val=x;
  }
  public T getdisplay(){
    return val;
  }
}

class ColorPrint extends Print1<String>{

}

class ColorPrint3<T> extends Print1<String> {
  T color;  // Example of using T for some purpose

  public void setColor(T color) {
      this.color = color;
  }

  public T getColor() {
      return color;
  }
}

class ColorPrint1<T> extends Print1<T>{

}

class Pair<K,V>{
	private K key;
	private V value;
	public void put(K key, V value){
		this.key=key;
		this.value=value;
	}
	public K getKey(){
		return key;
	}
}

//generic Method
class GenericMethod{
	public <K,V> void printValue(Pair<K,V> pair1,Pair<K,V> pair2){
		if(pair2.getKey().equals(pair1.getKey())){
			//do Something
		}
	}
}

class PJ{
	public <T> void setValue(T setObj){

	}
}
class Car{

}

//Bounded generics
//UpperBound
class PrintUP<T extends Number>{
	T val;
  public void setdisplay(T x){
    this.val=x;
  }
  public T getdisplay(){
    return val;
  }
}
interface Interface1{

}
interface Interface2{

}
interface Interface3{

}
class ParentClass{

}
class A extends ParentClass implements Interface1,Interface2{

}
class Z extends ParentClass implements Interface1,Interface2, Interface3{

}
class PrintMutliBound<T extends ParentClass & Interface1 & Interface2>{
	T val;
	public void setdisplay(T x){
    this.val=x;
  }
  public T getdisplay(){
    return val;
  }

}
class Vechile{

}
class Cycle extends Vechile{

}
class Bus extends Vechile{

}

class PrintPass{
  public void setPrintValue(List<Vechile> vechileList){

  }
}

//WildCards
class PrintWD{
	public void setPrintValue(List<? extends Vechile> vlist){

	}
	public void setdisplay(List<? super Vechile> vlist){

	}
}

class Project{
	//wildcards
	public void setPrintValue(List<? extends Number> src, List<? extends Number> dest){

	}
	//generic type method
	public <T extends Number> void setPrintValue1(List<T> src,List<T> dest){

	}

  public <T,R extends Number> void setPrintValuem(List<T> src,List<R> dest){

	}

	/*
        
	*/

	//unbounded wildcard

	public void setPrintValue2(List<?> src,List<?> dest){
		//here you can accept anything list of interger, string, objects etc 
		//when you donot know what kind so fobject will it have
	}
}

//Type Erasure

class PQR<T>{
	T val;
	public void setVal(T val){
		this.val=val;
	}
}

class ABC{
	public <T extends Number> void setVal(T val){
		
	}
}

class PQRErasure{
	Object val;
	public void setVal(Object val){
		this.val=val;
	}
}

class Java_Generics{
	public static void main(String[] args){
        Print printObj1= new Print();
        printObj1.setPrintValue(1);

        Object printValue = printObj1.getPrintValue();

        if((int) printValue==1){
        	//do something
        }

        Print1<Integer> p1 = new Print1<Integer>();
        p1.setdisplay(1);

        Integer ans= p1.getdisplay();
        if(ans==1){
        	//do Something
        }

        ColorPrint c = new ColorPrint();
        c.setdisplay("2");

        ColorPrint1<String> c1 = new ColorPrint1<>();
        c1.setdisplay("2");

        ColorPrint3<Integer> c2 = new ColorPrint3<>();  // Generic type for T is Integer
        c2.setdisplay("Red");  // This works with String since Print1<String> expects String
        c2.setColor(255);      // Here, T is Integer, so we set an Integer value

        System.out.println("................"+c2.getdisplay());  // Outputs: Red
        System.out.println("................"+c2.getColor());    // Outputs: 255

        Pair<String,Integer> pairObj = new Pair<String,Integer>();
        pairObj.put("hello",123);

        PJ pj= new PJ();
        pj.setValue(new Car());

        PrintUP<Integer> pup = new PrintUP<Integer>();

        //since string is not the child of number hence it will not work
       // PrintUP<String> pup = new PrintUP<String>();

        A a= new A();
        PrintMutliBound<A> aBound = new PrintMutliBound<A>();
 PrintMutliBound<Z> zBound = new PrintMutliBound<>();  // Works fine with Z
        zBound.setdisplay(new Z());

        System.out.println("Value in zBound: " + zBound.getdisplay());


        List<Vechile> vechileList = new ArrayList<Vechile>();

        vechileList.add(new Cycle());
        vechileList.add(new Bus());

        List<Bus> busList = new ArrayList<>();

        Vechile vobj = new Vechile();
        Bus bobj = new Bus();
        vobj = bobj; //works
      //  bobj = vobj; // not work

        //vechileobj ==busobj ok

       // vechileList = busList;   //NO
       // busList = vechilelist;   //NO
       

       PrintPass pobj = new PrintPass();

       pobj.setPrintValue(vechileList); // works

      //   pobj.setPrintValue(busList); // will not works becauyse of type safety enforced by java so need to use java generics
      

      PrintWD pwd = new PrintWD();
      pwd.setPrintValue(vechileList); //works 

      pwd.setPrintValue(busList); //works

      pwd.setdisplay(vechileList); // works super means vehile and above it

     // pwd.setdisplay(busList);  // donot works as it i s not above vechile

      List<Object> listobj = new ArrayList<>();
      pwd.setdisplay(listobj); //works

      Project p = new Project();

      List<Integer> wildcardsym = new ArrayList<>();
      List<Float> wwildcards = new ArrayList<>();

      p.setPrintValue(wildcardsym,wwildcards); //accepted

     // p.setPrintValue1(wildcardsym,wwildcards); //throws errors as all should have same type
        p.setPrintValue1(wildcardsym,wildcardsym); 
        p.setPrintValuem(wildcardsym,wwildcards);

           ABC abc = new ABC();
        
        abc.setVal(10);        // T is Integer
        abc.setVal(5.5);       // T is Double
        abc.setVal(2.34f);     // T is Float
        abc.setVal(100L);      // T is Long

	}
}