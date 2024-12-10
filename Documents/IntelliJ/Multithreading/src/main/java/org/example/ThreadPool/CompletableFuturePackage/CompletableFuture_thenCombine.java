package org.example.ThreadPool.CompletableFuturePackage;

import java.util.concurrent.*;

public class CompletableFuture_thenCombine {

    public static void main(String[] args) {

        try{
            ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(1,1,1, TimeUnit.HOURS,
                    new ArrayBlockingQueue<>(10), Executors.defaultThreadFactory(), new ThreadPoolExecutor.AbortPolicy());

            CompletableFuture<Integer>asyncTask1 = CompletableFuture.supplyAsync(() -> {
                return 10;
            },poolExecutor);

            CompletableFuture<String>asyncTask2 = CompletableFuture.supplyAsync(() -> {
                return "K";
            },poolExecutor);

            CompletableFuture<String> completableFuture = asyncTask1.thenCombine(asyncTask2, (Integer val1, String val2) -> val1 + val2);

            System.out.println(completableFuture.get());

            System.out.println(Thread.currentThread().getName());
            poolExecutor.shutdown();

        }
        catch(Exception e){
            //
        }

    }
}
