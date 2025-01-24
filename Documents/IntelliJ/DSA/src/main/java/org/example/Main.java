package org.example;

import java.util.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Stack<Integer>stack=new Stack<>();
        System.out.println(stack.push(10));
        System.out.println(stack.add(10));
        System.out.println(stack.size());

        Queue<Integer> queue=new LinkedList<>();
        System.out.println(queue.offer(30));
        System.out.println(queue.add(40));
    }
}