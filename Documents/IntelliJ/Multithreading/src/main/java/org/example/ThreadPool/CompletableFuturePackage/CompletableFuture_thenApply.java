package org.example.ThreadPool.CompletableFuturePackage;

import java.util.concurrent.*;

public class CompletableFuture_thenApply {

    public static void main(String[] args) {

        try{
            ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(2,3,1, TimeUnit.HOURS,
                    new ArrayBlockingQueue<>(10), Executors.defaultThreadFactory(),new ThreadPoolExecutor.AbortPolicy());

            CompletableFuture<String> asyncTask1 = CompletableFuture.supplyAsync(() -> {
                System.out.println(Thread.currentThread().getName());
                return "Newal";
                },poolExecutor)
                    .thenApplyAsync((String val) -> {
                        System.out.println(Thread.currentThread().getName());
                        return val + " Dewangan";
                    });

            System.out.println(asyncTask1.get());

            System.out.println(Thread.currentThread().getName());
            poolExecutor.shutdown();
        }
        catch(Exception e){
            System.out.println("Throwing execption: " + e);
        }
    }
}
