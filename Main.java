/* COP 4520C Assignment 1 
This program was written by: Pedro Henrique Sotto-Mayor Pereira da Silva */ 

import java.util.*;
import java.io.*;
import java.util.ArrayList;
import java.io.FileWriter;
import java.io.IOException;

public class Main {
    //set maximum number to search
    static final int MAX_NUMBER = 100000000;

    //set the number of threads
    static final int THREADS = 8;

    //list of all primes up to maximum number
    static ArrayList<Integer> primeNumbers = new ArrayList<Integer>(MAX_NUMBER);

    //list of last 10 primes
    static ArrayList<Integer> lastTenPrimes = new ArrayList<Integer>(10);
    
    public static void main(String[] args) throws Exception{
        //get start time
        long startTime = System.currentTimeMillis();

        //spawn threads
        Thread myThreads[] = new Thread[THREADS];
        Primes.m = new Monitor();
        for (int i=0; i<THREADS; i++){
            myThreads[i] = new Thread(new Primes(i));
            myThreads[i].start();
        }
        for (int i=0; i<THREADS; i++){
            myThreads[i].join();
        }

        //find the total number of primes
        int total = primeNumbers.size();

        //find the sum of all primes found and sort the list
        long sum=0;
        for(int i=0;i<total;i++){
            sum+=primeNumbers.get(i);
        }
        Collections.sort(primeNumbers);

        //find the last 10 primes
        for(int i=total-10;i<total;i++){
            lastTenPrimes.add(primeNumbers.get(i));
        }

        //get end time and calculate the time taken (end - start)
        long endTime = System.currentTimeMillis();
        long timeTaken = (endTime - startTime);

        //write execution time, total number of primes found, and sum of primes to primes.txt
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

    public synchronized static void addPrime(int num) {
        //add the prime number found to the list of primes
        primeNumbers.add(num);
    }

    public static boolean isPrime(int num) {
        //check if the number is prime
        if (num == 2 || num == 3 || num == 5){
            return true;
        }
        if (num <= 1 || num%2 == 0){
            return false;
        }
        for (int i=3; i<=Math.sqrt(num); i+=2){
            if (num%i == 0){
                return false;
            }
        }
        return true;
    }

  }

class Primes implements Runnable {
    public static Monitor m;
    final int index;

    public Primes(int i){
        //initialize thread number
        index=i;
    }

    public void run(){
        //assign numbers for each thread
        //to check if the number is prime or not
        for(int i=0; i<Main.MAX_NUMBER; i++){
            if(i%Main.THREADS == index){
                if(Main.isPrime(i)){
                    m.addPrime(i);
                }
            }
        }
    }
  }

class Monitor {
    public synchronized void addPrime(int num) {
        //add prime number
	    Main.addPrime(num);
    }
}