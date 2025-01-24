package org.example.StackImplementation;

import java.util.LinkedList;
import java.util.Queue;

class MyStack{
    public Queue<Integer>queue;

    public MyStack(){
        this.queue = new LinkedList<>();
    }
    public void push(int x){//TC:O(N)
        queue.add(x);
        for(int i=0;i<queue.size()-1;i++){
            queue.add(queue.poll());
        }
    }
    public int pop(){//TC:O(1)
       return queue.remove();
    }
    public int top(){//TC:O(1)
        return queue.peek();
    }
    public boolean isEmpty(){//TC:O(1)
        return queue.isEmpty();
    }
}
public class WithQueue {
    public static void main(String[] args) {
        MyStack stack = new MyStack();

        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);

        System.out.println(stack.isEmpty());
        System.out.println(stack.top());
        System.out.println(stack.pop());

        System.out.println(stack.top());
    }
}
