# COP4520 - Programming Assignment 1

### _This program was written by: Pedro Henrique Sotto-Mayor Pereira da Silva_

<br />

## `Compiling and running the code:`

1. Open a windows terminal.

2. Go to the directory of **Main.java** file by using `cd` command.

3. Type **java Main.java** and hit enter.

4. The code will run and write the output to a file named **primes.txt** on the current directory.

<br />

## `Program design:`

#### This program was designed to find all prime numbers from 1 to 10<sup>8</sup>, their sum, the top 10 maximum primes, and the execution time by using parallel programming.

#### The work was distributed among 8 concurrent threads in a way that each thread will do approximately the same amount of work.

<br />

## `Correctness and Efficiency:`

#### The code assigns a prime candidate to each thread in a round fashion. For example, thread 0 would find if the number 0 is a prime, thread 1 would check the number 1, thread 2 would check the number 2, and so on up to the last thread (thread 7) that would check the number 7. At the point where we assigned all threads a number, we would go back to thread 0 and assign the next number to be checked (8 in this case) and repeat the process until we reach the limit 10<sup>8</sup>. By doing this, we make sure that the work among the threads is as close as possible to be equally distributed since finding the factors of numbers that are close to each other should take approximately the same amount of time.

<br />

#### The program runs an efficient algorithm to check if a number is prime. First, we check if the number equals to 2, 3, or 5. If so, we return true as the number is prime. Then we check if the number is less than or equal to 1 or if it is even. If so, we return false as the number is not prime. If none of the returns is triggered, we run a for loop to check if the number is divisible by every odd number starting at 3 up to the square root of the number we are checking. If at some point the number we are checking is divisible by another number within the defined range, leaving a remainder of 0, we return false as the number is not prime. Else, we return true as the number is prime.

<br />

## `Experimental evaluation:`

#### The code was tested with different test cases and the output for each test is as follows:

<br />

### **_Test 1:_** Primes between 1 and 10<sup>2</sup>.

> Execution Time: 1ms Total: 25 Sum: 1060 <br />
> Top 10 primes: [53, 59, 61, 67, 71, 73, 79, 83, 89, 97]

<br />
<br />

### **_Test 2:_** Primes between 1 and 10<sup>3</sup>.

> Execution Time: 1ms Total: 168 Sum: 76127 <br />
> Top 10 primes: [937, 941, 947, 953, 967, 971, 977, 983, 991, 997]

<br />
<br />

### **_Test 3:_** Primes between 1 and 10<sup>4</sup>.

> Execution Time: 3ms Total: 1229 Sum: 5736396 <br />
> Top 10 primes: [9887, 9901, 9907, 9923, 9929, 9931, 9941, 9949, 9967, 9973]

<br />
<br />

### **_Test 4:_** Primes between 1 and 10<sup>5</sup>.

> Execution Time: 23ms Total: 9592 Sum: 454396537 <br />
> Top 10 primes: [99877, 99881, 99901, 99907, 99923, 99929, 99961, 99971, 99989, 99991]

<br />
<br />

### **_Test 5:_** Primes between 1 and 10<sup>6</sup>.

> Execution Time: 55ms Total: 78498 Sum: 37550402023 <br />
> Top 10 primes: [999863, 999883, 999907, 999917, 999931, 999953, 999959, 999961, 999979, 999983]

<br />
<br />

### **_Test 6:_** Primes between 1 and 10<sup>7</sup>.

> Execution Time: 464ms Total: 664579 Sum: 3203324994356 <br />
> Top 10 primes: [9999889, 9999901, 9999907, 9999929, 9999931, 9999937, 9999943, 9999971, 9999973, 9999991]
