import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

 
class Data implements Comparable<Data>
{
    int value, count, index;
 
    public Data(int value, int count, int index)
    {
        this.value = value;
        this.count = count;
        this.index = index;
    }
 
    public int compareTo(Data obj)
    {
        // If two elements have different frequencies, then
        // the one which has more frequency should come first
        if (this.count != obj.count) {
            return obj.count - this.count;
        }
 
        // If two elements have the same frequencies, then the
        // one which has less index should come first
        return this.index - obj.index;
    }
}

class java_Stream{

	public static void main(String[] args) throws ClassNotFoundException,MyCustomException{
       int var=2;
       String val1 = switch(var){
        case 1 ->"one";
        case 2 -> "Two";
        default ->"None";
       };
       System.out.println(val1); //Two

      String val2 = switch(var){
        case 1 -> {
          //some code 
          yield "one";
        }
        case 2 -> {
            //some code 
          yield "two";
        }
        default -> "none";
      };
      System.out.println(val2);//two


      //characters array to string how to convert

      
         /*
         char s[] = { 'g', 'e', 'e', 'k', 's', 'f', 'o',
                     'r', 'g', 'e', 'e', 'k', 's' };
          // Creating object of String class
        StringBuilder sb = new StringBuilder();
 
        // Creating a string using append() method
        for (int i = 0; i < a.length; i++) {
            sb.append(s[i]);
        }
 
        return sb.toString();


        or 

        // Creating an object of String class
        String string = String.valueOf(s);
 
        return string;


        or

        String string = new String(s);
 
        return string;
         */            

char[] charr = { 'g', 'e', 'e', 'k', 's', 'f', 'o',
                         'r', 'g', 'e', 'e', 'k', 's' };
 
        // Using collectors to collect array elements and
        // later using joining method to return a single
        // string
        System.out.println(charr);
        String str= Stream.of(charr).map(e-> new String(e)).collect(Collectors.joining());
       
        // Printing the stream received from Collectors
        System.out.println(str);
        //geeksforgeeks


        int[] arrss = { -8, -3, -6, -2, -5, -4 };

        int max = Arrays.stream(arrss).max().getAsInt();
        System.out.println("Max is "+max);

        // OptionalInt mx= Arrays.stream(arrss).max();
        // System.out.println("---------------------------------------");
        // System.out.println(mx.getAsInt());
        // System.out.println("---------------------------------------");
        //Max is -2

        Set<String> dict = Stream.of("this", "th", "is", "famous",
                    "Word", "break", "b", "r", "e", "a", "k",
                    "br", "bre", "brea", "ak", "problem")
                .collect(Collectors.toSet());

                Set<String> words = Stream.of("STAR", "NOTE", "SAND", "STONE")
                                .collect(Collectors.toSet());

                                // Set<String> words = Stream.of("STAR", "NOTE", "SAND", "STONE")
                                //   .sorted((p, q) -> q.compareTo(p))  // Reverse sort
                                //   .collect(Collectors.toCollection(LinkedHashSet::new));

String word="RAM";
   String sorted=   Stream.of(word.toCharArray()).map(a->{
    Arrays.sort(a);
    return new String(a);
   }).collect(Collectors.joining(word));
   System.out.println("---------------------------------------");
 System.out.println("word is:"+ sorted);
 System.out.println("---------------------------------------");
//word is:AMR

int[] arr23 = {1,5,2,3,2,3,3,3,3,4,4,4};
System.out.println("Arr before---");
for (int i : arr23) {
  System.out.print(i+" ");
}
System.out.println("..................................");

/*
 * 
 * Arr before---
1 5 2 3 2 3 3 3 3 4 4 4 ..................................
Arr aftre---[I@4f3f5b24
3 3 3 3 3 4 4 4 2 2 1 5 ..................................
 */
//sort it based on higher frequency....
 Map<Integer, Data> hm = new HashMap<>();
        for (int i = 0; i < arr23.length; i++)
        {
            hm.putIfAbsent(arr23[i], new Data(arr23[i], 0, i));
            hm.get(arr23[i]).count++;
        }
 
        // sort the values based on a custom comparator
        List<Data> values = hm.values().stream()
                .sorted()
                .collect(Collectors.toList());

                 int k1 = 0;
        for (Data data: values)
        {
            for (int j = 0; j < data.count; j++) {
                arr23[k1++] = data.value;
            }
        }
        System.out.println("Arr aftre---"+arr23.toString());
        for (int i : arr23) {
          System.out.print(i+" ");
        }
        System.out.println("..................................");

  // get the sum of all elements in the set
 int[] Sv = { 7, 3, 5, 12, 2, 1, 5, 3, 8, 4, 6, 4 };
        int sum = IntStream.of(Sv).sum();
        System.out.println("\n.......................sum is="+sum);

        int[] sumLeft = new int[4];
        Arrays.fill(sumLeft, sum/4);

        /*
          TreeMap<Integer, List<Player>> finalPointsToPlayerMap = new TreeMap<>(Collections.reverseOrder());
              Integer totalPoints = playerIdToRoundStatsMap.get(playerId).stream().mapToInt(RoundStats::getTotalPoints).sum();
            List<Player> players = finalPointsToPlayerMap.getOrDefault(totalPoints, new ArrayList<>());
            Optional<Player> player = playerQueue.stream().filter(player1 -> player1.getPlayerId().equals(playerId)).findFirst();

        */

String Y = "puja";

 int nx=2;
 System.out.println("set is..."+Y.substring(0, nx));
  Set<String> SC = Stream.of(Y.substring(0, nx)).collect(Collectors.toSet());
  System.out.println("set is..."+SC);

  List<String> wordssss = Arrays.asList("apple", "banana", "cherry");

  // Method reference to an instance method of an arbitrary object (String)
  wordssss.forEach(String::toUpperCase);  // Calls toUpperCase on each element in the list

 /*

 if (X.charAt(m - 1) == Y.charAt(n - 1))
        {
            // find all SCS of substring `X[0…m-2]` and `Y[0…n-2]`
            Set<String> scs = SCS(X, Y, m - 1, n - 1, lookup);
 
            // append the current character `X[m-1]` or `Y[n-1]` to all SCS of
            // substring `X[0…m-2]` and `Y[0…n-2]`
 
            return scs.stream()
                    .map(str -> str + X.charAt(m-1))
                    .collect(Collectors.toSet());
        }

        simmilar to 



         vector<string> scs = SCS(X, Y, m - 1, n - 1, lookup);
 
        // append the current character `X[m-1]` or `Y[n-1]` to all SCS of
        // substring `X[0…m-2]` and `Y[0…n-2]`
 
        for (string &str: scs) {        // don't remove `&`
            str.push_back(X[m - 1]);
        }
 
        return scs;

        // create a list with one empty string and return
            return new ArrayList<>(Collections.nCopies(1, ""));

             List<String> top = LCS(X, Y, m - 1, n, lookup);
        List<String> left = LCS(X, Y, m, n - 1, lookup);
 
        // merge two lists and return
        top.addAll(left);


         int[] curr = new int[n + 1];
        int[] prev = new int[n + 1];
         // replace contents of the previous array with the current array
            System.arraycopy(curr, 0, prev, 0, n + 1);
            
 */

        ///////////////////////////////////////////


          Map<String, Integer> fruitBasket = new HashMap<>();
        fruitBasket.put("Apple", 10);
        fruitBasket.put("Orange", 20);
        fruitBasket.put("Banana", 5);

        for(Map.Entry<String,Integer> ms : fruitBasket.entrySet()){
          System.out.println("Key is:"+ms.getKey()+" and value is :"+ms.getValue());
        }


fruitBasket.entrySet().stream()
    .forEach(entry -> System.out.println(entry.getKey() + " - " + entry.getValue()));

 fruitBasket.entrySet().stream()
    .filter(entry -> entry.getValue() > 10)
    .forEach(entry -> System.out.println(entry.getKey() + " - " + entry.getValue()));

fruitBasket.entrySet().stream()
    .map(entry -> entry.getKey() + " - " + (entry.getValue() * 2))
    .forEach(System.out::println);

    /*Key is:Apple and value is :10
Key is:Orange and value is :20
Key is:Banana and value is :5
Apple - 10
Orange - 20
Banana - 5
Orange - 20
Apple - 20
Orange - 40
Banana - 10 */

        ////////////////////////////////////////////

int[] freq = { 25, 10, 20 };
 // get the current node's cost
//let say i=0,j=2
int i1=1;
int j1=2;
int total_sum = IntStream.of(freq).sum();
int total_sum1 = IntStream.rangeClosed(i1,j1).sum();
int total = IntStream.rangeClosed(i1, j1).map(k -> freq[k]).sum();
System.out.println("Range sum is from "+i1+" to "+ j1+" is "+total+" while total sum is :"+total_sum+" checklis:"+total_sum1);
//Arrays.stream(freq).forEach(e->System.out.println(e));

//Range sum is from 1 to 2 is 30 while total sum is :55 checklis:3
        
        int[] arrm = { 2, 0, 6, 1, 5, 3, 7 };
       Set<Integer> S = IntStream.of(arrm)        // Returns IntStream
                                .boxed() // Used to convert IntStream to Stream<Integer>
                                .collect(Collectors.toSet());
     System.out.println("S="+S);
//S=[0, 1, 2, 3, 5, 6, 7]
     String szz = "CABCBC";
        int k = 2;
 
        // get reverse of s
        String rev = new StringBuilder(szz).reverse().toString();
        System.out.println("reveresed String is "+rev);
        //reveresed String is CBCBAC
         

        int[] arr = { 12, 3, 18, 24, 0, 5, -2 };
        int[] aux = Arrays.copyOf(arr, arr.length);

        List<String> wordsa = Arrays.asList("CARS", "REPAID", "DUES", "NOSE", "SIGNED",
                "LANE", "PAIRED", "ARCS", "GRAB", "USED", "ONES", "BRAG",
                "SUED", "LEAN", "SCAR", "DESIGN");

              //   List<String> wordsb = wordsa.stream().map(a->{
              //     char[] b = a.toCharArray();
              //     Arrays.sort(b);
              //    return  new String( b);
              // }).collect(Collectors.toList( ));

                    List<String> list = wordsa.stream()
                .map(s -> Stream.of(s.split("")).sorted()
                        .collect(Collectors.joining()))
                .collect(Collectors.toList());

                System.out.println("List="+list);
                //List=[ACRS, ADEIPR, DESU, ENOS, DEGINS, AELN, ADEIPR, ACRS, ABGR, DESU, ENOS, ABGR, DESU, AELN, ACRS, DEGINS]


                List<String> list1 = wordsa.stream()
                 .map(sss ->  Stream.of(sss.toCharArray()).map(sjk-> {Arrays.sort(sjk); return new String(sjk);})
                 .collect(Collectors.joining()))
                 .collect(Collectors.toList());

                System.out.println("List1="+list1);
//List1=[ACRS, ADEIPR, DESU, ENOS, DEGINS, AELN, ADEIPR, ACRS, ABGR, DESU, ENOS, ABGR, DESU, AELN, ACRS, DEGINS]
                 // Sort the characters of the current word and insert it into the Trie.
            // The original word gets stored in the leaf node
 
            String sorteda = Stream.of(word.toCharArray())
                    .map(chars -> { Arrays.sort(chars); return new String(chars); })
                    .collect(Collectors.joining());
            System.out.println("My word is :"+word+" and answer sorteda is :"+sorteda);        
           //My word is :RAM and answer sorteda is :AMR
        
           int[]slot = {12,-1,23,22,2,1,-1,7,8};

           System.out.println("The scheduled jobs are " +
                Arrays.stream(slot).filter(val -> val != -1).boxed()
                        .collect(Collectors.toList()));
//The scheduled jobs are [12, 23, 22, 2, 1, 7, 8]
                /*

                Set<String> collection = entry.getValue().stream()
                    .map(i -> words.get(i))
                    .collect(Collectors.toSet());
                    
                    Collections.sort(jobs, (a, b) -> b.profit - a.profit);
                   System.out.println("The scheduled jobs are " +
                Arrays.stream(slot).filter(val -> val != -1).boxed()
                        .collect(Collectors.toList()));
                */

            /*

            
               / construct a map from the given list of tickets (departure —> arrival)

                // input: list of tickets
        String[][] input = new String[][]{
                {"LAX", "DXB"},
                {"DFW", "JFK"},
                {"LHR", "DFW"},
                {"JFK", "LAX"}
        };
        
        Map<String, String> tickets = Stream.of(input)
                .collect(Collectors.toMap(p -> p[0], p -> p[1]));

            */
  ////////////////////////////////////////
//             return getFrequencyMap(first).equals(getFrequencyMap(second));
// // Utility function to create a frequency map
//     public static Map<Character, Long> getFrequencyMap(char[] chars)
//     {
//         return new String(chars).chars().mapToObj(ch -> (char) ch)
//                 .collect(Collectors.groupingBy(Function.identity(),
//                     Collectors.counting()));

//Create a Frequency Map in Java 8 and above
// In Java 8, we can convert the given set of words to stream and use a collector to count the occurrences of elements in a stream.

// The groupingBy(classifier, downstream) collector converts the collection of elements into a map by grouping elements according to a classification method and then performing a reduction operation on the values associated with a given key using the specified downstream Collector.

String[] chars = { "A", "B", "C", "A", "C", "A" };
 
Map<String, Long> freqe = Stream.of(chars)
            .collect(Collectors.groupingBy(Function.identity(),
                            Collectors.counting()));

System.out.println(freqe);
  

        ///////////////////////////////////////////Start here/////////////////////////////////
              int[] samb= {23,24,12,33,45};
              int sumHukr= IntStream.of(samb).sum();

              IntStream st= Arrays.stream(samb);
              Stream<Integer> shdj = Arrays.stream(samb).boxed();
              int sumHukr1= st.sum();
              System.out.println("-----------------------------");
              System.out.println("Sumhukr:"+ sumHukr+ " and Sumhukr1="+sumHukr1);
              System.out.println("-----------------------------");
                
///----------------------------------------------------------------//---------------------------------------------------//---------------------------
   List<Integer> salarylist = new ArrayList<>();
   salarylist.add(2000);
   salarylist.add(20000);
   salarylist.add(12000);
   salarylist.add(1000);
   salarylist.add(200);
   
   long output = salarylist.stream().filter((Integer x)-> x>=2000).count();
   System.out.println("Total emplyee with >=2000 salaray ="+ output);

     //Different ways to create stream

     List<Integer> saList = Arrays.asList(2000,3000,1000);

     int summmd = saList.stream()
                     .mapToInt(Integer::intValue)  // Convert Stream<Integer> to IntStream
                     .sum();  // Sum the elements


     Stream<Integer> sl = saList.stream();
     long summe=sl.count();

    Integer[] slarray = {30000,2000,1000};
    
    Stream<Integer> slar= Arrays.stream(slarray);

    int[] x= {2,3,4};
    Stream<Integer> mk = Arrays.stream(x).boxed();
    long bhu = mk.filter((Integer f)-> f>=3).count();

    Stream<Integer> s= Stream.of(1000,20000,2000,3000);
    Stream<Integer> s22= Stream.of(slarray);
    
    Stream.Builder<Integer> sb= Stream.builder();
    sb.add(1000).add(2000).add(3000);
    Stream<Integer> sbc= sb.build();

    Stream<Integer> streamIterate = Stream.iterate(1000, (Integer n)-> n + 5000).limit(5);
///////////////////////////////////////

//dIFFEREENT  intermediate operations
    Stream<String> nameStream = Stream.of("Hello","Everyone","How","are","you");
    Stream<String> filStream = nameStream.filter((String name)-> name.length() <=3);
    List<String> ans = filStream.collect(Collectors.toList());
    System.out.println(ans);

//[How, are, you]
    /////////////////

    Stream<String> nameStream2 = Stream.of("Hello","Everyone","How","are","you");
    Stream<String> filStream2 = nameStream2.map((String name)-> name.toUpperCase());
    List<String> ans2 = filStream2.collect(Collectors.toList());
     System.out.println(ans2);
//[HELLO, EVERYONE, HOW, ARE, YOU]
     //////////////////////

     List<List<String>> seLists = Arrays.asList(
      Arrays.asList("I","Love","java"),
      Arrays.asList("Microservices","are","good"),
      Arrays.asList("Mouse","pointer","nknvd")
     );

     Stream<String> nameStream3 = seLists.stream().flatMap((List<String> sentence)-> sentence.stream());
     List<String> ans3 = nameStream3.collect(Collectors.toList());
     System.out.println(ans3);
   //
   //[I, Love, java, Microservices, are, good, Mouse, pointer, nknvd]

      Stream<String> nameStream4 =seLists.stream().flatMap((List<String> sent)-> sent.stream().map(e-> e.toUpperCase()));
      List<String> ans4 = nameStream4.collect(Collectors.toList());
      System.out.println("ans4 is:"+ans4);

       //[I, LOVE, JAVA, MICROSERVICES, ARE, GOOD, MOUSE, POINTER, NKNVD]

       //////////////////////
       
       Integer[] arrz= {1,3,2,4,4,5,2,2,1,1};
       Stream<Integer> s1 = Arrays.stream(arrz).distinct();
        List<Integer> ans5 = s1.collect(Collectors.toList());
        System.out.println(ans5);
        //[1, 3, 2, 4, 5]

        //////////////////////

        Integer[] arr1= {1,3,2,4,4,5,2,2,1,1};
       Stream<Integer> s2 = Arrays.stream(arr1).sorted();
       Stream<Integer> s3 = Arrays.stream(arr1).sorted((Integer i,Integer j) -> j-i);

        List<Integer> ans6 = s2.collect(Collectors.toList());
          List<Integer> ans7 = s3.collect(Collectors.toList());

        System.out.println(ans6);
        //[1, 1, 1, 2, 2, 2, 3, 4, 4, 5]
       System.out.println(ans7);
       //[5, 4, 4, 3, 2, 2, 2, 1, 1, 1]

         //////////////////////

         List<Integer> numbers = Arrays.asList(2,1,3,6,5);
         Stream<Integer> numbStream = numbers.stream()
                                       .filter((Integer v)-> v>2)
                                       .peek((Integer v)->System.out.println(v))
                                       .map((Integer v)-> -1*v);
         List<Integer> ans8 = numbStream.collect(Collectors.toList());
         System.out.println(ans8);
        //  3
        //  6
        //  5
         //[-3, -6, -5]

         //////////////////////
         List<Integer> numb = Arrays.asList(2,1,3,6,5);
         Stream<Integer> numbStream2 = numb.stream().limit(3); //limit truncate the stream  
         List<Integer> ans9 = numbStream2.collect(Collectors.toList());
         System.out.println(ans9);
         //[2, 1, 3]

           //////////////////////

           List<Integer> num1 = Arrays.asList(2,1,3,6,5);
         Stream<Integer> numbStream3 = num1.stream().skip(3); 
         List<Integer> ans10 = numbStream3.collect(Collectors.toList());
         System.out.println(ans10);
         //[6, 5]
          //////////////////////
          List<String> ni = Arrays.asList("2","1","3","5","4");
        IntStream nuIntStream = ni.stream().mapToInt((String v)-> Integer.parseInt(v));

        int[] numarray = nuIntStream.toArray();

        for(int x1: numarray){
          System.out.print(x1+ " ");
        }
        //2 1 3 5 4
          System.out.println();
        int[] numarray1= {2,1,4,7,5};
        IntStream nuIntStream2 =Arrays.stream(numarray1); //IntStream will be used in Array case
        nuIntStream2=nuIntStream2.filter((int v)-> v>2);
        int[] filteredarray = nuIntStream2.toArray();
        for(int x1: filteredarray){
          System.out.print(x1+ " ");
        }
          //4 7 5
         //////////////////////
System.out.println();
         List<Integer> prac = Arrays.asList(2,1,4,7,10);
         Stream<Integer> numstStream = prac.stream()
                                       .filter((Integer x1)-> x1>=3)
                                       .peek((Integer x1)->System.out.println("after filter:"+x))
                                       .map((Integer x1)-> (x1*-1))
                                        .peek((Integer x1)->System.out.println("after map:"+x))
                                        .sorted()
                                         .peek((Integer x1)->System.out.println("after sorted:"+x));

             List<Integer> ansprac = numstStream.collect(Collectors.toList());

             /* after filter:4
after map:-4
after filter:7
after map:-7
after filter:10
after map:-10
after sorted:-10
after sorted:-7
after sorted:-4 */

 ///////////////////////////////////////////
 //Terminal Operations

 List<Integer> a1= Arrays.asList(2,1,4,3,9);
 a1.stream().filter((Integer x1)-> x1>=2).forEach((Integer x1)->System.out.println(x1));

 //////////////////////////////////

   List<Integer> a2= Arrays.asList(2,1,4,3,9);
   Object[] filObjects = a2.stream().filter((Integer x1)-> x1>=3).toArray();

  //  Integer[] filObjects = a2.stream()
  //                                .filter(x1 -> x1 >= 3)  // Filter elements greater than or equal to 3
  //                                .toArray(Integer[]::new);  // Convert to Integer[]


   Integer[] filIntegers = a2.stream().filter((Integer x1)-> x1>=3).toArray((int size)-> new Integer[size]);
   System.out.println("filitegers="+filIntegers);
 //////////////////////////////////

 List<Integer> a3=Arrays.asList(2,1,4,7,10);
 Optional<Integer> reducedOptional= a3.stream().reduce((Integer v1,Integer v2)->v1+v2);
 System.out.println(reducedOptional.get()); //ans= 24

  //////////////////////////////////
  List<Integer> a4=Arrays.asList(2,1,4,7,10);
  List<Integer> fList = a4.stream().filter((Integer x1) -> x1>=3).collect(Collectors.toList());
  System.out.println(fList); 

    //////////////////////////////////
List<Integer> a5=Arrays.asList(2,1,4,7,10);
Optional<Integer> miOptional = a5.stream().filter((Integer x1)-> x1>=3).min((Integer a,Integer b) -> a-b);

System.out.println("minoptionas =="+miOptional.get());
//minoptionas ==4
    //////////////////////////////////
    List<Integer> a6=Arrays.asList(2,1,4,7,10);
 long noofvalpresent = a6.stream().filter((Integer x1)-> x1>=3).count();
 System.out.println(noofvalpresent); //3

   //////////////////////////////////
 List<Integer> a7=Arrays.asList(2,1,4,7,10);
 boolean hasValueGreaterthan3 = a7.stream().anyMatch((Integer x1)-> x1>3);
  System.out.println(hasValueGreaterthan3); //true
  //////////////////////////////////

  //allMatch
  //noneMatch

  //
  List<Integer> a8=Arrays.asList(2,1,4,7,10);
  Optional<Integer> fOptional = a8.stream().filter((Integer x1)-> x1>=3).findFirst();
           System.out.println(fOptional.get()); //4   
        ////////////////////////////////////
        
        //parallel stream


        
   }
   
  
}

