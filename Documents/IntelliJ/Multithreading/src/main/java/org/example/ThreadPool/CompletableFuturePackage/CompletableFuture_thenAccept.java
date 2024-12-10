package org.example.ThreadPool.CompletableFuturePackage;

import java.util.concurrent.*;

public class CompletableFuture_thenAccept {

    public static void main(String[] args) {

        try{
            ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(2,3,1, TimeUnit.HOURS,
                    new ArrayBlockingQueue<>(10), Executors.defaultThreadFactory(),new ThreadPoolExecutor.AbortPolicy());

            CompletableFuture<Void> asyncTask1 = CompletableFuture.supplyAsync(() -> {
                        System.out.println(Thread.currentThread().getName());
                        return "Newal";
                    },poolExecutor)
                    .thenAcceptAsync((String val1) -> {
                        System.out.println(Thread.currentThread().getName());
                            System.out.println("Using thenAccept method " + val1);}, poolExecutor);

            System.out.println(asyncTask1.get());

            System.out.println(Thread.currentThread().getName());
            poolExecutor.shutdown();
        }
        catch(Exception e){
            System.out.println("Throwing execption: " + e);
        }
    }
}
