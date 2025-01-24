package org.example.StackImplementation;
//SC:O(N) -> But better then Array where we used constant space here we are using dynamic space
class Node{
    int val;
    Node next;

    public Node(int val){
        this.val=val;
        this.next=null;
    }
}
class StackImp{
    Node top;
    int size;

    public StackImp(){
        top=null;
        size=0;
    }
    public void push(int x){//TC:O(1)
        Node temp=new Node(x);
        temp.next=top;
        top=temp;
        size++;
    }
    public int pop(){//TC:O(1)
        if(top==null){
            return -1;
        }
        int temp=top.val;
        top=top.next;
        size--;
        return temp;
    }
    public int top(){//TC:O(1)
        if(top==null){
            return -1;
        }
        return top.val;
    }
    public int size(){//TC:O(1)
        return size;
    }
    public boolean isEmpty(){//TC:O(1)
        return top==null;
    }
}
public class WithLinkedList {
    public static void main(String[] args) {
        StackImp stack = new StackImp();

        stack.push(1);
        stack.push(2);
        stack.push(3);

        System.out.println(stack.pop());
        System.out.println(stack.top());
        System.out.println("Size: " + stack.size());
        System.out.println(stack.isEmpty());
    }
}
