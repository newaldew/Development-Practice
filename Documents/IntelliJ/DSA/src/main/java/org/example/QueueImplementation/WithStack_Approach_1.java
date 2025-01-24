package org.example.QueueImplementation;

import java.util.Stack;
//Use this approach when lot of pop, top & size operations are involved and few push operations are involved
//SC:O(N)
class QueueImp {
    Stack<Integer> s1;
    Stack<Integer> s2;

    public QueueImp(){
        s1=new Stack<>();
        s2=new Stack<>();
    }
    public void push(int x){//TC:O(2N)
        while(!s1.empty()){
            s2.push(s1.pop());
        }
        s1.push(x);
        while(!s2.empty()) {
            s1.push(s2.pop());
        }
    }
    public int pop(){//TC:O(1)
        return s1.pop();
    }
    public int front(){//TC:O(1)
        return s1.peek();
    }
    public int size(){//TC:O(1)
        return s1.size();
    }
}
public class WithStack_Approach_1 {
    public static void main(String[] args) {
        QueueImp queue = new QueueImp();

        queue.push(1);
        queue.push(2);
        queue.push(3);
        queue.push(4);

        System.out.println(queue.pop());
        System.out.println(queue.front());
        System.out.println(queue.size());
    }
}
