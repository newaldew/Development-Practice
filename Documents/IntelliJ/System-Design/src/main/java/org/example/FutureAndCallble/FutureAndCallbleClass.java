package org.example.FutureAndCallble;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class FutureAndCallbleClass {

    public static void main(String[] args) {
        ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor( 3, 3, 1, TimeUnit.HOURS,
                new ArrayBlockingQueue<>(10), Executors.defaultThreadFactory(), new ThreadPoolExecutor. AbortPolicy());

        //submit has 3 flavours showed below in 3 usecase
        //UseCase-1 with Runnable
        Future<?> futureObj1 = poolExecutor.submit(()-> System.out.println("Task1 with Runnable"));
        //'?' means return type is nothing and internally void is implemented. We know run() of Runnable doesn't return anything.
        try{
            Object obj = futureObj1.get();
            System.out.println(obj == null);
        }
        catch(Exception e){
            //Some exception here
        }
        //UseCase-2 with Runnable and return object
        List<Integer> output=new ArrayList<>();
        Future<List<Integer>>futureObj2 = poolExecutor.submit(() -> {
            output.add(100);
            System.out.println("Task2 with Runnable and return object");
        }, output);

        try{
            List<Integer>futureObj2Output = futureObj2.get();
            System.out.println(futureObj2Output.get(0));
        }
        catch (Exception e){
            //Some exception here
        }
        //UseCase-3 with Callable
        Future<List<Integer>>futureObj3 = poolExecutor.submit(() -> {
            System.out.println("Task3 with Callable");
            List<Integer>output2=new ArrayList<>();
            output2.add(200);
            return output2;
        });
        try{
            List<Integer>futureObj3Output = futureObj3.get();
            System.out.println(futureObj3Output.get(0));
        }
        catch(Exception e){
            //SOme exception here
        }
    }
}
