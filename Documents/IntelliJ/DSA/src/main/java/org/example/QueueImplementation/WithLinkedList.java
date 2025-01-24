package org.example.QueueImplementation;
//SC:O(N)
class Node{
    int val;
    Node next;

    public Node(int val){
        this.val=val;
        this.next=null;
    }
}
class QueueImpl{
    Node front;
    Node rear;
    int size;

    public QueueImpl(){
        front=null;
        rear=null;
        size=0;
    }
    public void push(int x){//TC:O(1)
        if(rear==null){
            rear=new Node(x);
            front=rear;
        }
        else{
            Node temp=new Node(x);
            rear.next=temp;
            rear=rear.next;
        }
        size++;
    }
    public int pop(){//TC:O(1)
        if(size==0){
            return -1;
        }
        int ans=front.val;
        front=front.next;
        size--;
        return ans;
    }
    public int top(){//TC:O(1)
        if(size==0){
            return -1;
        }
        return front.val;
    }
    public int size(){
        return size;
    }
}
public class WithLinkedList {
    public static void main(String[] args) {
        QueueImpl queue=new QueueImpl();

        queue.push(1);
        queue.push(2);
        queue.push(3);

        System.out.println(queue.pop());
        System.out.println(queue.pop());

        System.out.println();

        System.out.println(queue.top());
        System.out.println(queue.size());
    }
}
