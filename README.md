The above is the github repository for the 2nd Task of the mentorship programme by ProjectX, VJTI.

==============DETAILS & GUIDELINES=============
Code is written in Java.
The main code comprising the three solution files(Tasks, Cache, Simulator) are present inside the '/src' folder. 
The screenshot of the output is attached on the outside in the 'main' branch of the repository.
Assumption: Any memory block is assumed to be located inside the RAM. 
========CODE & LOGIC========
Implemented only the FCFS algorithm of CPU scheduling. In this the first task is processed first fully and only then does the CPU proceed to the next task.
This is of slight disadvantage as if there is any task that requires small amount of time, has to wait for the bigger task to be completed. This is slightly inefficient.
First let us get some terms clear.
The memory blocks (M1, M2, ..., M15) are the ones that tasks are continuously in search of. 
The CPU searches for memory to be allocated to the current task in the order: L1 -> L2 -> L3 -> RAM. 
Each task takes a certain amount of time which is referred to by the term 'BURST'. Burst is basically the no. of cycles required to complete the execution of a task.
Cache memory works on the principle of FIFO (and hence we have also implemented them using a queue).
-> The 'src/Task.java' file creates a class Task which has characteristics for a given task such as: id, memory blocks, currentMemoryIndex.
The function 'getNextMemoryRequest' returns the memory blocks for that given task.
-> The 'src/Cache.java' file is a class for the cache memories. It had characteristics such as capacity, queue and a hashset of memory blocks.
The 'contains' function checks whether given block is present inside the Cache memory. Useful for checking hits and misses.
The 'insert' function inserts a new memory block. If the capacity is full, the first added block is deleted in order to make space for the new block.
The 'getState' function displays the entire contents of the cache memory as an arraylist of type string.
-> The 'src/Simulator.java' is the actual main code that processes the entire simulation of the CPU processing.
The objects L1, L2, L3 are created as the cache memory levels. 
We send the input file to the 'loadtasks' function and get a queue of the tasks to be processed. 
From every task in the 'readyQueue' we 'pop' a task and process it using the 'processMemoryRequest' function. 
After the 'readyQueue' is empty, the final result is displayed which indicates the details such as tasks executed, no. of bursts, latency
and the final contents of the three cache memory blocks.
