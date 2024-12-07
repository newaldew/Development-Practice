package org.example;

import java.util.LinkedList;
import java.util.Queue;

public class SharedResource {
    private Queue<String>sharedBuffer;
    private int bufferSize;

    public SharedResource(int bufferSize){
        this.bufferSize=bufferSize;
        sharedBuffer = new LinkedList<>();
    }

    public synchronized void produce(String name){
        try{
            while(sharedBuffer.size()==bufferSize){
                System.out.println("Not producing more names as buffer is full!");
                wait();
            }
        }
        catch(Exception e){
            System.out.println("Catching producer exception!");
        }
        sharedBuffer.add(name);
        System.out.println("Produced: " + name);
        notify();
    }
    public synchronized String consume(){
        try{
            while(sharedBuffer.isEmpty()){
                System.out.println("No item to consume please wait");
                wait();
            }
        }
        catch(Exception e){
            System.out.println("Catching consumer exception!");
        }

        String name = sharedBuffer.poll();
        System.out.println("Consumed: " + name);
        notify();
        return name;
    }
}
