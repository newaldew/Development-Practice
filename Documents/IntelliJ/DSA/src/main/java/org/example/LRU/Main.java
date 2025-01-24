package org.example.LRU;

import java.util.HashMap;
import java.util.Map;

class Node{
    int key;
    int val;
    Node next;
    Node prev;

    public Node(int key,int val){
        this.key=key;
        this.val=val;
    }
}
class LRU{
    int capacity;
    Map<Integer,Node> map;
    Node head = new Node(-1,-1);
    Node tail = new Node(-1,-1);

    public LRU(int capacity){
        this.capacity=capacity;
        map = new HashMap<>();
        head.next=tail;
        tail.prev=head;
    }
    private void addNode(Node newNode){//TC:O(1)
        Node temp = new Node(newNode.key,newNode.val);
        temp.next=head.next;
        temp.prev=head;

        head.next.prev=temp;
        head.next=temp;
    }
    private void deleteNode(Node delNode){//TC:O(1)
        Node temp = delNode.prev;

        temp.next=delNode.next;
        delNode.next.prev=temp;

        delNode.prev=null;
        delNode.next=null;
    }

    public int getKey(int key){//TC:O(N) or Average: O(logN) or Best: O(1)
        if(map.containsKey(key)){
            Node x = map.get(key);
            int ans = x.val;
            map.remove(key);
            deleteNode(x);

            addNode(x);
            map.put(key,head.next);
            return ans;
        }
        else {
            return -1;
        }
    }
    public void put(int key, int val){//TC:O(N) or Average: O(logN) or Best: O(1)
        if(map.containsKey(key)){
            Node x = map.get(key);
            deleteNode(x);
            map.remove(key);
        }
        if(map.size()==capacity){
            Node z = tail.prev;
            deleteNode(z);
            map.remove(z.key);
        }
        addNode(new Node(key,val));
        map.put(key,head.next);
    }
}

public class Main {
    public static void main(String[] args) {
        LRU lru = new LRU(2);

        lru.put(1,1);
        lru.put(2,2);

        System.out.println(lru.getKey(1));
        lru.put(3,3);
        System.out.println(lru.getKey(2));
    }
}
