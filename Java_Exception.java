import java.io.FileNotFoundException;
import java.util.*;



import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
/*----------------------------------------------------------------------------------------------------------------------------- */
class TaskException extends Exception {
    public TaskException(String message) {
        super(message);
    }
    public TaskException(String message, Throwable cause) {
        super(message, cause);
    }
}

class FaultyTask implements Callable<String> {
    private final int taskId;
    
    public FaultyTask(int taskId) {
        this.taskId = taskId;
    }

    @Override
    public String call() throws TaskException {
        try {
            // Simulate task processing
            if (taskId % 2 == 0) {
                throw new IllegalStateException("Simulated error in task " + taskId);
            }
            return "Task " + taskId + " completed successfully.";
        } catch (Exception e) {
            throw new TaskException("Task " + taskId + " failed.", e);
        }
    }
}

 class MultithreadedApp {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(4);
        List<Future<String>> futures = new ArrayList<>();
        
        // Submit tasks
        for (int i = 1; i <= 10; i++) {
            futures.add(executor.submit(new FaultyTask(i)));
        }
        
        // Process results
        for (Future<String> future : futures) {
            try {
                System.out.println(future.get());
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt(); // Restore interrupted state
                System.err.println("Task interrupted: " + e.getMessage());
            } catch (ExecutionException e) {
                // Unwrap and handle the underlying exception
                System.err.println("Error during task execution: " + e.getCause().getMessage());
            }
        }
        
        executor.shutdown();
    }
}

/*----------------------------------------------------------------------------------------------------------------------------- */
class DataImportException extends Exception {
    public DataImportException(String message) {
        super(message);
    }
    public DataImportException(String message, Throwable cause) {
        super(message, cause);
    }
}

class CSVDataImporter {
    private static final Logger logger = Logger.getLogger(CSVDataImporter.class.getName());

    public List<String[]> importData(String filePath) throws DataImportException {
        List<String[]> data = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            int lineNumber = 0;
            while ((line = reader.readLine()) != null) {
                lineNumber++;
                try {
                    String[] tokens = parseCSVLine(line);
                    data.add(tokens);
                } catch (IllegalArgumentException e) {
                    // Log and continue processing next lines instead of aborting
                    logger.log(Level.WARNING, "Malformed CSV data at line " + lineNumber + ": " + line, e);
                }
            }
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error reading the CSV file", e);
            throw new DataImportException("Failed to import data from file: " + filePath, e);
        }
        return data;
    }

    // A simple CSV line parser (for illustration purposes)
    private String[] parseCSVLine(String line) {
        if (line == null || line.isEmpty()) {
            throw new IllegalArgumentException("Empty line");
        }
        // For a more robust parser, consider using a CSV library
        return line.split(",");
    }
}

 class DataImportApp {
    public static void main(String[] args) {
        CSVDataImporter importer = new CSVDataImporter();
        String filePath = "data.csv";
        try {
            List<String[]> importedData = importer.importData(filePath);
            System.out.println("Successfully imported " + importedData.size() + " records.");
        } catch (DataImportException e) {
            System.err.println("Data import failed: " + e.getMessage());
        }
    }
}
/*----------------------------------------------------------------------------------------------------------------------------- */
// Custom exception for transaction failures
class TransactionException extends Exception {
    public TransactionException(String message) {
        super(message);
    }
    public TransactionException(String message, Throwable cause) {
        super(message, cause);
    }
}

class BankService {
    private static final Logger logger = Logger.getLogger(BankService.class.getName());
    // Database connection string and credentials (dummy values)
    private static final String DB_URL = "jdbc:yourdb://localhost:3306/bank";
    private static final String USER = "username";
    private static final String PASS = "password";

    /**
     * Transfer funds between two accounts.
     * 
     * @param fromAccountId ID of the account to debit.
     * @param toAccountId ID of the account to credit.
     * @param amount The amount to transfer.
     * @throws TransactionException if the transaction fails.
     */
    public void transferFunds(int fromAccountId, int toAccountId, double amount) throws TransactionException {
        // Simulate obtaining a database connection.
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS)) {
            // Disable auto-commit mode for transaction management.
            conn.setAutoCommit(false);

            try {
                // Debit from source account.
                updateAccountBalance(conn, fromAccountId, -amount);

                // Credit destination account.
                updateAccountBalance(conn, toAccountId, amount);

                // If both updates succeed, commit the transaction.
                conn.commit();
                logger.info("Transaction committed successfully.");
            } catch (SQLException e) {
                // If any error occurs, roll back the transaction.
                conn.rollback();
                logger.log(Level.SEVERE, "Transaction rolled back due to error.", e);
                throw new TransactionException("Funds transfer failed; transaction rolled back.", e);
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Database connection error.", e);
            throw new TransactionException("Could not obtain database connection.", e);
        }
    }

    /**
     * Update the balance of an account.
     * 
     * @param conn Active database connection.
     * @param accountId The account ID.
     * @param delta The change in balance (positive for credit, negative for debit).
     * @throws SQLException if the update fails.
     */
    private void updateAccountBalance(Connection conn, int accountId, double delta) throws SQLException {
        String updateQuery = "UPDATE accounts SET balance = balance + ? WHERE account_id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(updateQuery)) {
            pstmt.setDouble(1, delta);
            pstmt.setInt(2, accountId);
            int affectedRows = pstmt.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("No rows affected for account: " + accountId);
            }
            logger.info("Updated account " + accountId + " with delta " + delta);
        }
    }
}

 class BankingTransactionApp {
    public static void main(String[] args) {
        BankService bankService = new BankService();
        int fromAccount = 101;
        int toAccount = 202;
        double transferAmount = 500.00;

        try {
            bankService.transferFunds(fromAccount, toAccount, transferAmount);
            System.out.println("Funds transferred successfully.");
        } catch (TransactionException e) {
            System.err.println("Transaction failed: " + e.getMessage());
            // Additional logging or compensating actions can be added here.
        }
    }
}

/*----------------------------------------------------------------------------------------------------------------------------- */
// Custom exception for order processing errors
class OrderProcessingException extends Exception {
    public OrderProcessingException(String message) {
        super(message);
    }

    public OrderProcessingException(String message, Throwable cause) {
        super(message, cause);
    }
}

class OrderService {
    private static final Logger logger = Logger.getLogger(OrderService.class.getName());

    // Simulate order processing from an input file
    public void processOrders(String filePath) throws OrderProcessingException {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(filePath));
            String order;
            while ((order = reader.readLine()) != null) {
                processOrder(order);
            }
        } catch (IOException e) {
            // Log and wrap the IOException into a custom exception
            logger.log(Level.SEVERE, "Error reading orders file.", e);
            throw new OrderProcessingException("Failed to read orders file.", e);
        } finally {
            // Ensure the resource is closed properly
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    logger.log(Level.WARNING, "Error closing the reader.", e);
                }
            }
            logger.info("Finished processing orders from file: " + filePath);
        }
    }

    // Simulate processing a single order
    private void processOrder(String order) throws OrderProcessingException {
        try {
            // Validate order format
            if (order == null || order.trim().isEmpty()) {
                throw new OrderProcessingException("Order is empty or null.");
            }
            // Imagine we perform various checks and processing steps here.
            // For demonstration, we simulate an error if order contains the word "ERROR".
            if (order.contains("ERROR")) {
                throw new IllegalArgumentException("Invalid order format detected.");
            }
            // Process the order (simulate processing time)
            logger.info("Processing order: " + order);
            // ...
        } catch (IllegalArgumentException e) {
            logger.log(Level.SEVERE, "Error processing order: " + order, e);
            throw new OrderProcessingException("Failed to process order: " + order, e);
        }
    }
}

 class OrderProcessingApp {
    public static void main(String[] args) {
        OrderService orderService = new OrderService();
        String ordersFile = "orders.txt";  // Assume orders.txt exists in the working directory

        try {
            orderService.processOrders(ordersFile);
            System.out.println("All orders processed successfully.");
        } catch (OrderProcessingException e) {
            System.err.println("Order processing failed: " + e.getMessage());
            // Optionally, more detailed logging or corrective actions could be taken here.
        }
    }
}
/*----------------------------------------------------------------------------------------------------------------------------- */
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
   /*Even if there's a return statement in the try block, the finally block will execute before the method returns. */
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