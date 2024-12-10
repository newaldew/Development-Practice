package org.example.ThreadPool;

import java.util.concurrent.*;

public class ThreadPoolExec {

    public static void main(String[] args) {

        ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(2,5,1, TimeUnit.HOURS,
                new ArrayBlockingQueue<>(3),
                new CustomThreadFactory(),
                new CustomRejectedHandler());

        poolExecutor.allowCoreThreadTimeOut(true);

        for(int i=0;i<12;i++){
            poolExecutor.submit(() -> {
                try{
                    Thread.sleep (5000);
                    System.out.println("Thread name: " + Thread.currentThread().getName());
                }
                catch(Exception e){
                    //Exception
                }
            });
        }
        System.out.println(Thread.currentThread().getName());
        poolExecutor.shutdown();
    }
}
class CustomThreadFactory implements ThreadFactory{

    @Override
    public Thread newThread(Runnable r) {
        Thread t = new Thread(r);
        return t;
    }
}
class CustomRejectedHandler implements RejectedExecutionHandler{

    @Override
    public void rejectedExecution(Runnable r, java.util.concurrent.ThreadPoolExecutor executor) {
        System.out.println("Task denied: " + r.toString());
    }
}
