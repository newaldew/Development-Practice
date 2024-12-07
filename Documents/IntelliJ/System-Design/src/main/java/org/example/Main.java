package org.example;

import java.util.LinkedList;
import java.util.Queue;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        String[] arr = {"Priyal", "Newal", "Pari", " Mo", "Goldie" };

        SharedResource resource = new SharedResource(3);

        Thread t1 = new Thread(()-> {
            try{
                for(int i=0;i<5;i++){
                    resource.produce(arr[i]);
                }
            }
            catch(Exception e){
                System.out.println("Exception:" + e);
            }
        });
        Thread t2 = new Thread(()->{
            try{
                for(int i=0;i<5;i++){
                    resource.consume();
                }
            }
            catch (Exception e){
                System.out.println("Exception in consumer: " + e);
            }
        });

        t1.start();
        t2.start();
    }
}
