package org.example.QueueImplementation;

import java.util.Stack;

//Use this approach when lot of push operations are involved and few pop & top operations are involved
class Queue{
    Stack<Integer>s1;
    Stack<Integer>s2;

    public Queue(){
        s1=new Stack<>();
        s2=new Stack<>();
    }
    public void push(int x){//TC:O(1)
        s1.push(x);
    }
    public int pop(){//TC:O(N)
        if(!s2.empty()){
            return s2.pop();
        }
        else{
            if(s1.empty()){
                return -1;
            }
            while(!s1.empty()){
                s2.push(s1.pop());
            }
            return s2.pop();
        }
    }
    public int front(){//TC:O(N)
        if(!s2.empty()){
            return s2.peek();
        }
        else{
            if(s1.empty()){
                return -1;
            }
            while(!s1.empty()){
                s2.push(s1.pop());
            }
            return s2.peek();
        }
    }
    public int size(){//TC:O(1)

        return s1.size() + s2.size();
    }
}
public class WithStack_Approach_2 {
    public static void main(String[] args) {
        Queue queue = new Queue();

        System.out.println(queue.pop());
        System.out.println(queue.front());
        System.out.println("Size:" + queue.size());
        System.out.println();

        queue.push(1);
        queue.push(2);
        queue.push(3);
        System.out.println(queue.pop());
        queue.push(4);
        queue.push(5);


        System.out.println(queue.front());
        System.out.println("Size:" + queue.size());
        System.out.println(queue.front());
    }
}
