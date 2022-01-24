# Executor Service
Avoids creating new threads everytime. Pre-defined number of threads are created and in waiting state.
So what happens here is, inside the for loop, you are not creating new threads. Instead you are creating new tasks and submitting these tasks to the Executor. Within this executor, it internally uses Blocking Queue. This queue holds all these tasks. Each thread performs same 2 steps. 
    1. Fetch next task from the queue
    2. Execute it
Since all 10 threads attempt to pick the task from the queue concurrently, it is important for the queue to be able to handle concurrent operations (thread-safe). 
If we have CPU intensive operations, lets say we have a 4 core server. So, there are 4 CPU OS Threads. Now if your thread pool size is 8, as we know each Java thread is equal to 1 OS thread. So, OS has to do time slicing of CPU between these 8 threads.
But, mostly this may not be the case. Tasks could be IO intensive operations. Like, your service calling another service via http connection or calling database, these tasks do not impact much CPU. In this case, we can have a large thread pool ( larger than CPU Core size)

Type of Thread Pools
1. Fixed Thread pool - Created a pool of threads of fixed size. Tasks wait in Blocking Queue.
2. Cached Thread pool - Queue is of type Synchronized Queue. It holds only 1 Task at any time. You do not define the thread pool size. It is determined dynamically based on the incoming tasks. Lets say, you get 10 Tasks at the same time, 5 of them takes 10 sec to finish and 5 of them takes 2 sec to finish. Now, at T0 sec, 10 threads are created. At T2 sec, 5 threads are back to idle state. Now, after 60 sec, at T3 sec, these threads will be killed. Lets assume the same scenario where all 10 tasks take 10 sec. Now at T4 sec, if a new task comes, new thread will be created. so total live threads count increases to 11. Ideally, there is a chance, N number of threads will be created for N tasks.
3. Scheduled Thread Pool - All tasks will be stored in delayed Queue. This queue does not store tasks in fifo model instead based on the scheduled time. You can set the delay at fixed rate or fixed delays
4. Single Thread pool - Blocking Queue and Single Thread. If you want to ensure tasks are executed sequentially, then we can use this 

# Creating Custom Executor Service
Standard Implementation : ExecutorService es = Executors.newFixedThreadPool(10);
1. Understanding Runnable Interface :  The Runnable interface should be implemented by any class whose instances are intended to be executed by a thread. The class must define a method of no arguments called run. 
2. Let's create a CustomExecutorService
ExecutorService extends Extender interface and it has only 1 method - execute
This method executes the given command at some time in the future.  The command may execute in a new thread, in a pooled thread, or in the calling thread, at the discretion of the {@code Executor} implementation.