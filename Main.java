/* COP 4520C Assignment 1 
This program was written by: Pedro Henrique Sotto-Mayor Pereira da Silva */ 

import java.util.*;
import java.io.*;
import java.util.ArrayList;
import java.io.FileWriter;
import java.io.IOException;

public class Main {
    //set the number of threads
    static final int THREADS = 8;

    //total number of primes, sum, and partition size for splitting the work among the threads
    static int total=0;
    static long sum=0;
    static int partition=10000;

    //set maximum number to search
    static final int MAX_NUMBER = 100000000;

    //list of all primes up to maximum number
    static NavigableSet<Integer> primeNumbers = new TreeSet<Integer>();

    //list of last 10 primes
    static ArrayList<Integer> lastTenPrimes = new ArrayList<Integer>(10);
    
    public static void main(String[] args) throws Exception{
        //get start time
        long startTime = System.currentTimeMillis();

        //spawn and join threads
        Thread myThreads[] = new Thread[THREADS];
        for (int i=0; i<THREADS; i++){
            myThreads[i] = new Thread(new Primes(i));
            myThreads[i].start();
        }
        for (int i=0; i<THREADS; i++){
            myThreads[i].join();
        }

        //iterate through the primes and get the highest 10
        //also compute the sum of the primes
        Iterator<Integer> reverse = primeNumbers.descendingIterator();
        int count=0;
        while(reverse.hasNext()){
            int number=reverse.next();
            if(count<10){
                lastTenPrimes.add(number);
                count++;
            }
            sum+=number;
        }

        //sort the array with the last 10 primes
        Collections.sort(lastTenPrimes);

        //get the total number of primes found
        total=primeNumbers.size();

        //get end time and calculate the time taken (end - start)
        long endTime = System.currentTimeMillis();
        long timeTaken = (endTime - startTime);

        //write execution time, total number of primes found, sum of primes, and last 10 primes to primes.txt
        try{
            FileWriter myWriter = new FileWriter("primes.txt");
            myWriter.write("Execution Time: " + timeTaken + "ms Total: " + total + " Sum: " + sum);
            myWriter.write('\n' + "Top 10 primes: " + lastTenPrimes.toString());
            myWriter.close();
        }catch (IOException e){
            e.printStackTrace();
            System.exit(1);
        }
        
    }

    public synchronized static void primeToList(int num) {
        //add the prime number found to the list of primes
        primeNumbers.add(num);
    }

    public static void sieve(int bottom, int top) {
        //create an array for the odd numbers and set the values as prime for all
        int[] arr = new int[(top-bottom+1)/2];
        Arrays.fill(arr,1);

        //add 2 as a prime
        if(bottom<=2){
            primeNumbers.add(2);
        }
        
        //loop to check odd numbers within the range [bottom,to]
        for(int i=3; i*i<=top; i+=2){
            //get past the numbers from the previous partition
            int minimum = ((bottom + i - 1)/i)*i;
            if(minimum<i*i){
                minimum=i*i;
            }

            //make the start number an odd one
            if(minimum%2==0){
                minimum+=i;
            }

            //mark all non-primes
            for(int j=minimum;j<=top;j+=2*i){
                int index=j-bottom;
                arr[index/2]=0;
            }
        }

        //compute the total number of primes
        //also add the prime numbers to the navigable set
        for(int i=0;i<(top-bottom+1)/2;i++){
            if(arr[i]==1){
                int num=bottom+(i*2)+1;
                primeToList(num);
            }
        }

        return;
    }

  }

class Primes implements Runnable {
    //thread number
    final int index;

    public Primes(int i){
        //initialize thread number
        index=i;
    }

    public void run(){
        //set the initial bottom and check how many times we need to repeat the loop
        int bottom=2;
        //assign blocks for each thread
        for(int i=0; i<Main.MAX_NUMBER/Main.partition; i++){
            //increment bottom by partition if i!=0
            if(i!=0) bottom+=Main.partition;
            //assign the block to the thread in a round fashion
            if(i%Main.THREADS==index){
                //calculate top number and make sure it does not exceed the MAX_NUMBER
                int top=bottom+Main.partition;
                if(top>Main.MAX_NUMBER){
                    top=Main.MAX_NUMBER;
                }
                //sieve on the designated block
                Main.sieve(bottom,top);
            }
        }

    }
  }