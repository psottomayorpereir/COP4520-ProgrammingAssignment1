# COP4520 - Programming Assignment 1

### _This program was written by: Pedro Henrique Sotto-Mayor Pereira da Silva_

<br />

### Compiling and running the code:

---

1. Open a windows terminal.

2. Go to the directory of **Main.java** file by using `cd` command.

3. Type **javac Main.java** and hit enter to compile the code.

4. Type **java Main** and hit enter to run the code.

5. The code will run and write the output to a file named **primes.txt** on the current directory.

<br />

### Output (also in the **primes.txt** file):

---

> Execution Time: 1316ms Total: 5761455 Sum: 279209790387276 <br />
> Top 10 primes: [99999787, 99999821, 99999827, 99999839, 99999847, 99999931, 99999941, 99999959, 99999971, 99999989]

<br />

### Program design:

---

This program was designed to find the total number of primes from 1 to 10<sup>8</sup>, their sum, the top 10 maximum primes, and the execution time by using parallel programming.

The work was distributed among 8 concurrent threads in a way that each thread will do approximately the same amount of work.

<br />

### Correctness and Efficiency:

---

The code assigns a block with bottom and top values to each thread in a round fashion. For example, thread 0 would find all primes between 2 and 10002 and add them to a navigable set that is used by all threads in a synchronized fashion. Thread 1 would find primes between 10003 and 20003 and also add the primes found to the navigable set. The same idea will be applied to all following threads until all threads have been assigned a block and we would then go back to thread 0 and assign the next block to it and repeat the process until we have assigned all blocks up to 10<sup>8</sup>.<br /><br />
By doing the assignment of blocks to threads in a round fashion, we make sure that the work among the threads is aproximatelly equivalent distributed since the threads are being assigned similar slice sizes every time. So it should take approximately the same amount of work to compute the primes of each block.

<br />

Also, the program runs an efficient algorithm to find all prime numbers within the blocks. We initialize an array of half the size of the block and set all values to 1 as an indication that all odd numbers in the block are primes. Note that the block size used is 10<sup>4</sup>. If the bottom number is the number 2, we add it to the navigable set as it is prime. Then, starting at the first odd number of the block, we keep marking all multiples of it as non-primes in the array. Then we go to the next odd number and keep doing the same steps. After we are done with these calculations, we traverse the array to check what numbers in that block are prime and add each prime number to the navigable set in a synchronous fashion. Note that we only check multiples of odd numbers for better efficiency (even numbers other than 2 are not prime).

<br />

### Experimental evaluation:

---

The code was tested with different test cases to make sure the results were accurate and the output for each test is as follows:

<br />

**_Test 1:_** Primes between 1 and 10<sup>2</sup>.

> Execution Time: 1ms Total: 25 Sum: 1060<br />
> Top 10 primes: [53, 59, 61, 67, 71, 73, 79, 83, 89, 97]

<br />
<br />

**_Test 2:_** Primes between 1 and 10<sup>3</sup>.

> Execution Time: 1ms Total: 168 Sum: 76127<br />
> Top 10 primes: [937, 941, 947, 953, 967, 971, 977, 983, 991, 997]

<br />
<br />

**_Test 3:_** Primes between 1 and 10<sup>4</sup>.

> Execution Time: 4ms Total: 1229 Sum: 5736396<br />
> Top 10 primes: [9887, 9901, 9907, 9923, 9929, 9931, 9941, 9949, 9967, 9973]

<br />
<br />

**_Test 4:_** Primes between 1 and 10<sup>5</sup>.

> Execution Time: 13ms Total: 9592 Sum: 454396537<br />
> Top 10 primes: [99877, 99881, 99901, 99907, 99923, 99929, 99961, 99971, 99989, 99991]

<br />
<br />

**_Test 5:_** Primes between 1 and 10<sup>6</sup>.

> Execution Time: 75ms Total: 78498 Sum: 37550402023<br />
> Top 10 primes: [999863, 999883, 999907, 999917, 999931, 999953, 999959, 999961, 999979, 999983]

<br />
<br />

**_Test 6:_** Primes between 1 and 10<sup>7</sup>.

> Execution Time: 240ms Total: 664579 Sum: 3203324994356<br />
> Top 10 primes: [9999889, 9999901, 9999907, 9999929, 9999931, 9999937, 9999943, 9999971, 9999973, 9999991]
