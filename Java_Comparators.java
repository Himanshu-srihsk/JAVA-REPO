import java.util.ArrayList;
import java.util.*;
import java.io.IOException;
import java.util.Comparator;


class Car1{
   public String name;
   public String model;
   public Car1(){}
   public Car1(String name, String model){
      this.name=name;
      this.model=model;
   }
   public String toString(){
      return (this.name+" "+this.model);
   }
}

class Car2 implements Comparable<Car2>{
   public String name;
   public String model;
   public Car2(){}
   public Car2(String name, String model){
      this.name=name;
      this.model=model;
   }
   public String toString(){
      return (this.name+" "+this.model);
   }

   public int compareTo(Car2 o1){
      return (this.name.compareTo(o1.name));
   }
}

// class Car2 implements Comparator<Car1>{
//    public String name;
//    public String model;
//    public Car2(){}
//    public Car2(String name, String model){
//       this.name=name;
//       this.model=model;
//    }
//    public String toString(){
//       return (this.name+" "+this.model);
//    }

//    public int compare(Car1 o1,Car1 o2){
//       return (o1.name.compareTo(o2.name));
//    }
// }

class Car{
   public String name;
   public String model;
   public Car(){}
   public Car(String name, String model){
      this.name=name;
      this.model=model;
   }
   public String toString(){
      return (this.name+" "+this.model);
   }
}

class CarComparator implements Comparator<Car>{
     public int compare(Car o1,Car o2){
      return (o2.name.compareTo(o1.name));
   }
}



class sortedCar implements Comparator<Car>{
    public int compare(Car c1, Car c2){
      return c1.name.compareTo(c2.name);
    }
}


public class Java_Comparators{


 public static void main(String[] args)
   {  
//      Vector<Integer> v= new Vector<Integer>();
//      v.add(1);
//       v.add(2);
//        v.add(3);
//         v.add(4);
//      System.out.println(v.get(0)); 
//       Iterator<Integer> i= v.iterator();
//       while(i.hasNext()){
//          System.out.println(i.next()); 
//          //int i=i.next();
//       }
      
//       for(Integer x:v){
//          System.out.println(x); 
//       }
// System.out.println("usig  for eavh method");
//       v.forEach((Integer x)->System.out.println(x));

//       System.out.println("dwmd emd");

//       v.forEach(x->{
//          if(x%2==0){
//             System.out.println(x);
//          }
//       });

//       v.remove((Integer)2);

//       v.forEach((Integer x)->System.out.println(x));
//       Stack<Integer> stack = new Stack<Integer>();
//    stack.add(6);
//    stack.add(7);
//    v.addAll(stack);
//  v.forEach((Integer x)->System.out.println(x));
// v.remove(4);
//  System.out.println("See from here \n");
//  boolean x= v.containsAll(stack);
//   System.out.println("x="+x);

// System.out.println("See from here PriorityQueue\n");
  ///////////////////COmparator and comparable ///////////////////
   //    PriorityQueue<Integer> minPQ = new PriorityQueue<>();
   //  minPQ.add(4);
   //  minPQ.add(1);
   //  minPQ.add(2);
   // minPQ.forEach(k -> System.out.println(k));
   // System.out.println("printing.....");
   // while(!minPQ.isEmpty()){
   //     int val=minPQ.poll();
   //     System.out.println(val);
   // }


   //   PriorityQueue<Integer> minPQ = new PriorityQueue<>((Integer a,Integer b)-> b-a);
   //  minPQ.add(1);
   //  minPQ.add(4);
   //  minPQ.add(2);
   // minPQ.forEach((Integer k) -> System.out.println(k));
   // System.out.println("printing.....");
   // while(!minPQ.isEmpty()){
   //     int val=minPQ.poll();
   //     System.out.println(val);
   // }

   Integer arr[] ={1,4,2,3};
   Arrays.sort(arr,(Integer a,Integer b)->(a-b));
   for(int z:arr){
      System.out.println(z);
   }
  Car2[] car2 = new Car2[3];
  car2[0] = new Car2("effe Desire","SUV");
  car2[1] = new Car2("fedfef","Lamborghini");
  car2[2] = new Car2("nkdncd","TATA");
  Arrays.sort(car2);
 Arrays.asList(car2).forEach(c -> System.out.println(c.toString() + "  ")); 
System.out.println("-------------------------");
  Car[] carw = new Car[3];
  carw[0] = new Car("Dark Desire","SUV");
  carw[1] = new Car("Ferari","Lamborghini");
  carw[2] = new Car("Maruti","TATA");

  for(Car c :carw){
   System.out.println(c);
  }

  System.out.println("sorting car");
  Arrays.sort(carw,(Car o1,Car o2)-> o2.name.compareTo(o1.name));  
  for(Car c :carw){
   System.out.println(c);
  }
  System.out.println("check car above sorted");
  Arrays.sort(carw,new sortedCar());
  for(Car c :carw){
   System.out.println(c);
  }
  System.out.println("check car above sorted in ascendign");

   ArrayList<Car> car = new ArrayList<Car>();
   car.add(new Car("Dark Desire","SUV"));
car.add(new Car("Ferari","Lamborghini"));
car.add(new Car("Maruti","TATA"));

Collections.sort(car,new CarComparator());

  
//   for(Car c :car){
//    System.out.println(c);
//   }
  
car.forEach((Car c)-> System.out.println(c));

  //ArrayDeque
  ArrayDeque<Integer> ad= new ArrayDeque<>();
  ad.addLast(1);
  ad.addLast(5);
  ad.addLast(10);

  int ele= ad.removeFirst();
  System.out.println(ele);
System.out.println(ad.removeLast());
}

}
