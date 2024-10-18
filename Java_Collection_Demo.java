
import java.util.*;
import java.util.PriorityQueue;
import java.util.concurrent.*;
class myCompare implements Comparator<Integer>{
    public int compare(Integer a,Integer b){
        return b-a;
    }
}

class Java_Collection_Demo{

   public static Map<Character, Integer> mp1 = new HashMap<>() {{
      put('C', 40);
      put('D', 50);
      put('E', 60);
      put('F', 70);
      put('G', 80);
      put('H', 90);
  }};
  public static  Map<Character, Integer> mp2 = new HashMap<>();

  public static  Map<Character, Integer> mp3 = Map.ofEntries(
      Map.entry('C', 40),
      Map.entry('D', 50),
      Map.entry('E', 60),
      Map.entry('F', 70),
      Map.entry('G', 80),
      Map.entry('H', 90)
  );

  public static Map<Character, Integer> mp = Map.ofEntries(
      Map.entry('C', 40),
      Map.entry('D', 50),
      Map.entry('E', 60),
      Map.entry('F', 70),
      Map.entry('G', 80),
      Map.entry('H', 90)
  );

	public static void main(String[] args){

      for(Map.Entry<Character, Integer> entry : mp2.entrySet()){
         System.out.println(entry.getKey() + " : " + entry.getValue());
     }

//      public static Map<Character, Integer> entries = Map.ofEntries(
//       Map.entry('C', 40),
//       Map.entry('D', 50),
//       Map.entry('E', 60),
//       Map.entry('F', 70),
//       Map.entry('G', 80),
//       Map.entry('H', 90)
//   );


//   public static  Map<Character, Integer> mp3 = entries;

   //public static Map<Character, Integer> mp3 = new HashMap<>(entries);  // Create a mutable copy
//         mp3.put('I', 100);  

// Mutable copy: If you want to modify mp3, use new HashMap<>(entries) to create a mutable copy of entries.
// In both cases, you're correctly using the entries map without trying to pass it into Map.ofEntries(), which only accepts Map.Entry objects.
     

//  public static  Map<Character, Integer> mp3 = Map.of('k', 20);

// String str = "Hello, World";
//         String rev = new StringBuilder(str).reverse().toString();
 
//         System.out.println("The reverse of the given string is " + rev);

      Vector<Integer> v = new Vector<Integer>();
      v.add(2);
      v.add(3);
      v.add(5);

      System.out.println("v="+v);

      List<Integer> values= new ArrayList<>();
      values.add(1);
      values.add(2);
      values.add(3);
      values.add(4);


      Iterator<Integer> iter= v.iterator();
      while(iter.hasNext()){
         int val=iter.next();
       System.out.print(val);
        //iter=;
       if(val==3){
         iter.remove();
       }
         
      }

System.out.println();
System.out.println("v="+v);
      System.out.println(v.get(0));
   System.out.println("printing using for lop");
      for(int x:v){
        System.out.println(x);
      }

      System.out.println("printing using foreach");
      v.forEach(x->System.out.print(x+ " "));
      values.forEach(y->System.out.println(y));
      System.out.println("v size="+v.size());
      System.out.println("values size="+values.size());
      System.out.println("values contains ="+values.contains(1));
      System.out.println("v contains ="+v.contains(1));
      //isEmpty(),contains(),size(),add(),remove(),addAll(),removeAll(),clear(),equals(),iterator(),stream() and ParallelStream()
     
     // v.clear();
      v.addAll(values);
      System.out.println("v size="+v.size());
      // v.clear();
      System.out.println("v size="+v.size());
      System.out.println("v empty = "+v.isEmpty());

      System.out.println("v="+v);
      v.remove(2);
       System.out.println("v="+v);
       v.remove(Integer.valueOf(3));
       System.out.println("v="+v);

       Stack<Integer> stack = new Stack<Integer>();
       stack.add(2);
       System.out.println("st="+stack);
      Collections.sort(v);
     System.out.println("v="+v);

     Queue<Integer> q= new ArrayDeque<>();
     q.add(2);
     q.add(5);
     q.add(3);
     while (!q.isEmpty()) {
      System.out.println(q.peek());
      q.poll();
     }

    PriorityQueue<Integer> pq= new PriorityQueue<>((Integer a,Integer b)-> (b-a));
    
    //PriorityQueue<Integer> pq= new PriorityQueue<>(new myCompare());
     pq.add(3);
     pq.add(2);
     pq.add(5);
     System.out.println("------------------------");
     pq.forEach((Integer p) -> System.out.print(p)); // 523
     System.out.println(); 
     System.out.println("------------------------");
    Integer a[]= {1,2,4,1,6,3};
     Arrays.sort(a,(Integer w,Integer r)-> (w-r)); //Asending order //112346
   //Arrays.sort(a,new myCompare()); // Descendin gorder
   //a.forEach((Integer x)->System.out.println(x));
   for(int x: a){
    System.out.print(x);
   }
   System.out.println("------------------------");
System.out.println();
   List<Integer> list = new ArrayList<>();
   list.add(3);
   list.add(6);
   list.add(1);
   list.add(2,67);
   list.addAll(1,v);
   list.forEach(x->System.out.print(x)); // 322456671

   System.out.println("------------------------");
   if(list.contains(67)==true){
    System.out.println(true);
    System.out.println(list.indexOf(67)); //true //6
   
   }
   
System.out.println();
list.replaceAll((Integer x)-> (-1*x));
list.forEach(x->System.out.println(x));
System.out.println();

   ListIterator<Integer> listIterator = list.listIterator();
    while(listIterator.hasNext()){
       System.out.println(listIterator.next()+ " next Index ="+ listIterator.nextIndex()+ " previous index="+ listIterator.previousIndex());
      }

//       -3 next Index =1 previous index=0
// -2 next Index =2 previous index=1
// -2 next Index =3 previous index=2
// -4 next Index =4 previous index=3
// -5 next Index =5 previous index=4
// -6 next Index =6 previous index=5
// -67 next Index =7 previous index=6
// -1 next Index =8 previous index=7

      System.out.println();
     ListIterator<Integer> listIterator2 = list.listIterator(list.size());
    while(listIterator2.hasPrevious()){
       System.out.println(listIterator2.previous()+ " next Index ="+ listIterator2.nextIndex()+ " previous index="+ listIterator2.previousIndex());
      }

//       -1 next Index =7 previous index=6
// -67 next Index =6 previous index=5
// -6 next Index =5 previous index=4
// -5 next Index =4 previous index=3
// -4 next Index =3 previous index=2
// -2 next Index =2 previous index=1
// -2 next Index =1 previous index=0
// -3 next Index =0 previous index=-1
      
      System.out.println("map prac");
      //size(),isEmpty(),containsKey(),containsValue(),get(),put(key,val),

      LinkedList<Integer> prac = new LinkedList<Integer>();
      prac.addLast(200);
      prac.addLast(100);
      prac.addFirst(300);
      prac.addFirst(800);

      prac.add(0,900);
      prac.add(890);

      System.out.println(prac.get(1)+ " "+prac.get(2)); //800 300

      prac.forEach((Integer x)-> System.out.print(x+" ")); //900 800 300 200 100 890 

      Map<Integer,String> mp = new HashMap<>();
      mp.put(null,"Test"); //null=Test

      mp.put(0,null);
      mp.put(1,"A");

      mp.putIfAbsent(null,"test");
      mp.putIfAbsent(0,"rest");
      mp.putIfAbsent(3,"rest");

      for(Map.Entry<Integer,String> entry : mp.entrySet()){
         Integer key=entry.getKey();
         String value=entry.getValue();
         System.out.println(key+"="+value);
      }
      // null=Test
      // 0=rest
      // 1=A
      // 3=rest
      // 1=A
      // 2=C
      // 21=B

      //LinkedHashMap -> it maintains 2 types of order 1- insertion order , Maintains access order .e the one which is frequently used

      //Similar to HashMap but also uses Double linklist
      //hash , key,valyue , next -> Hashmap
      //hash , key,valyue , next , after,Before -> linked Hashmap

      Map<Integer,String> ms= new LinkedHashMap<>();
      //Map<Integer,String> ms= new LinkedHashMap<>(IntialCapacity=16,loadFactor=0.75F,AccessOrder=true); 
      //when access order true means most frequently used item will be at the end of Map
      ms.put(1,"A");
      ms.put(21,"B");
      ms.put(23,"C");

      Map<Integer,String> threadSafe = Collections.synchronizedMap(new LinkedHashMap<>());

      Map<Integer,String> treemap = new TreeMap<>(); // it is based on red black Tree , sort internally 

      //Map<Integer,String> treemap = new TreeMap<>((Integer k1,Integer k2)-> k2-K1); //sorted in decreasing order
      treemap.put(1,"A");
      treemap.put(21,"B");
      treemap.put(2,"C");

       for(Map.Entry<Integer,String> entry : treemap.entrySet()){
         Integer key=entry.getKey();
         String value=entry.getValue();
         System.out.println(key+"="+value);
      }
//       1=A
// 2=C
// 21=B

      //set does not contain duplicate values as it intenally uses Hashmap. 
      Set<Integer> set1 = new HashSet<Integer>();
      set1.add(10);
      set1.add(2);

      //internally map.put(element, new Object());

      //threadsafe version

      ConcurrentHashMap concurrenthashMap = new ConcurrentHashMap();
      Set<Integer> ThreadSafeset= concurrenthashMap.newKeySet();
      ThreadSafeset.add(1);
      ThreadSafeset.add(30);

      Iterator<Integer> it = ThreadSafeset.iterator();
      while(it.hasNext()){
         int val= it.next();
         if(val==1){
            ThreadSafeset.add(8);
         }
      }
      ThreadSafeset.forEach((Integer val)-> System.out.println(val));
      // 1
      // 8
      // 30
    }
}