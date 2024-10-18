import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.util.PriorityQueue;
class java_Learning{
    public static Map<Character, Integer> mapping = Map.ofEntries(
            Map.entry('I', 1),
            Map.entry('V', 5),
            Map.entry('X', 10),
            Map.entry('L', 50),
            Map.entry('C', 100),
            Map.entry('D', 500),
            Map.entry('M', 1000)
    );
    
    
	public static void main(String[] args){
		Map<Character, Integer> mapping = Map.ofEntries(
            Map.entry('I', 1),
            Map.entry('V', 5),
            Map.entry('X', 10),
            Map.entry('L', 50),
            Map.entry('C', 100),
            Map.entry('D', 500),
            Map.entry('M', 1000)
    );

         String Y = "puja";
 int n=2;
  Set<String> SC = Stream.of(Y.substring(0, n)).collect(Collectors.toSet());

    int var=2;
   String val = switch(var){
        case 1 ->"one";
        case 2 -> "Two";
        default ->"None";
       };
       System.out.println(val);

       String ans = Stream.of("STAR", "NOTE", "SAND", "STONE")
                                .collect(Collectors.joining(","));

                                System.out.println(ans);
                                //STAR,NOTE,SAND,STONE

                                String word="RAM";
                             String sorted = Stream.of(word.toCharArray())
                    .map(chars -> { Arrays.sort(chars); return new String(chars); })
                    .collect(Collectors.joining());   
 
 System.out.println("word is:"+ sorted); //word is:AMR

   // for (char[] chars: mat) {
   //          System.out.println(Arrays.toString(chars).replaceAll(",", ""));
   //      }


     // for (int i = 0; i < N; i++) {
     //        Arrays.fill(mat[i], '–');
     //    }

  // List<List<Integer>> result;
  // List<Integer> comb;
  //    result.add(new ArrayList<>(comb));

 // char[] digits;
 //  String curr = new String(digits);

 String str = "Java is My favourite";
 Optional<String> optional = Optional.ofNullable(str);
 System.out.println(optional.isPresent()); //true
 System.out.println(optional.get()); //Java is My favourite
  System.out.println(optional.orElse("No Value in this Object")); //Java is My favourite

      StringBuffer GfG1 = new StringBuffer("Hello"); 
        StringBuffer GfG2 = new StringBuffer(" World"); 
        GfG1.append(GfG2); 
        System.out.println(GfG1); //Hello World

         String str1 = "java"; 
        char arr[] = { 'j', 'a', 'v', 'a', ' ', 'p',  
        'r', 'o', 'g', 'r', 'a', 'm', 'm', 'i', 'n', 'g' }; 
        String str2 = new String(arr); 
        System.out.println(str1); //java
        System.out.println(str2);//java programming

        byte[] arr11 = { 97, 98, 99, 100, 101 }; 
        String str3 = new String(arr11); 
  
        System.out.println(str3); 

        String str9= "Java Programming"; 
        char ch = str9.charAt(2); 
        System.out.println(ch);  //v

        String str23 = "Java Programming"; 
        char arr1[] = new char[20]; 
        arr1 = str23.toCharArray(); 
        System.out.println(arr1); //Java Programming

        String strw = "    Java          Programming                  "; 
  
        System.out.println(strw.trim()); //Java          Programming
           //Java          Programming
        //The trim( ) method returns a copy of the invoking string from which any leading and trailing whitespace has been removed.
        // But it does not remove the spaces present between two words 


        // String str = "Java Programming"; 
        // String str1 = "Java"; 
  
        // System.out.println(str.indexOf("a")); 
        // System.out.println(str.indexOf("m")); 
  
        // System.out.println(str.lastIndexOf("a")); 
        // System.out.println(str.lastIndexOf("m")); 
        /*
            1
            11
            10
            12
        */

        //In Java, final variable becomes 0 by default. 
        //Only three ways to initialize the final variable 1. using constructor 2. initialization block 3. At the time of variable declaration.
          int[] A = { 4, 6, 3, 9, 7, 10 };
         int[] arrm = Arrays.copyOf(A, A.length);

         // count.put(arr[i], count.getOrDefault(arr[i], 0) + c);

         Arrays.sort(A);

         List<Integer> l = Arrays.asList(5,1,6,8);
          System.out.println("l B4 sort="+l);
         l.sort((a,b)-> {return a-b;});
         System.out.println("l after sort="+l);

         /*

         class Transaction
        {
            String name;        // Transaction name
            String record;        // Data object from the database
            int timestamp;      // Timestamp of the current transaction
            char operation;     // Operation type: Read/Write
         
            public Transaction(String name, String record, int timestamp, char operation)
            {
                this.name = name;
                this.record = record;
                this.timestamp = timestamp;
                this.operation = operation;
            }
        }

            List<Transaction> t
            t.sort((x, y) -> {
            // compare database records first
            if (!x.record.equals(y.record)) {
                return x.record.compareTo(y.record);
            }
            // compare based on access time when database records are equal
            return x.timestamp - y.timestamp;
        });
         */

        List<String> name= Arrays.asList("ram","shayam","geeta");
       int[] freq = { 25, 10, 20 };
 // get the current node's cost
//let say i=0,j=2
int i1=0;
int j1=1;
                    int total = IntStream.rangeClosed(i1, j1).map(k -> freq[k]).sum();

                    System.out.println(total+ " + total");


 List<String> words = Arrays.asList("CARS", "REPAID", "DUES", "NOSE", "SIGNED",
                "LANE", "PAIRED", "ARCS", "GRAB", "USED", "ONES", "BRAG",
                "SUED", "LEAN", "SCAR", "DESIGN");

                    List<String> list = words.stream()
                .map(s -> Stream.of(s.split("")).sorted()
                        .collect(Collectors.joining()))
                .collect(Collectors.toList()); 

                System.out.println("List="+list);


             //System.out.println(Arrays.toString(arr));
                
      
        List<Integer> b= Arrays.asList(1,4,7,2,3,2,1);
        Collections.sort(b, (Integer x,Integer y)->(y-x));
        int x= b.get(0);
        System.out.println("List b="+b.size());



	}
}
