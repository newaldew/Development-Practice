package org.example.ComparatorVsComparable;

import java.util.*;

class Employee1 {
   int id;
   String name;
   long salary;

   public Employee1(int id,String name, long salary){
       this.id=id;
       this.name=name;
       this.salary=salary;
   }
}
class SalaryComp implements Comparator<Employee1>{

    @Override
    public int compare(Employee1 e1, Employee1 e2) {
        if(e1.salary<e2.salary){
            return 1;
        }
        else if(e1.salary>e2.salary){
            return -1;
        }
        else{
            return (new NameComp()).compare(e1,e2);
        }
    }
}
class NameComp implements Comparator<Employee1>{

    @Override
    public int compare(Employee1 e1, Employee1 e2) {
        int n1=e1.name.length();
        int n2=e2.name.length();
        if(n1<n2){
            return -1;
        }
        else if(n1>n2){
            return 1;
        }
        else{
            String s1=e1.name;
            String s2=e2.name;

            for(int i=0;i<n1;i++){
                if(s1.charAt(i)-'a'>s2.charAt(i)-'a'){
                    return 1;
                }
                else if(s1.charAt(i)-'a'<s2.charAt(i)-'a'){
                    return -1;
                }
            }
        }
        return 0;
    }
}
public class ComparatorClass {
    public static void main(String[] args) {

        List<Employee1> employeeList=new ArrayList<>();
        employeeList.add(new Employee1(103,"Newal",2000));
        employeeList.add(new Employee1(105,"Rahul",1000));
        employeeList.add(new Employee1(109,"Sunil",5000));
        employeeList.add(new Employee1(101,"Rohit",2000));
        employeeList.add(new Employee1(102,"Parul",1000));

        //Collections.sort(employeeList, new NameComp());

//        for(int i=0;i<employeeList.size();i++) {
//            System.out.println(employeeList.get(i).id + " " + employeeList.get(i).name + " " + employeeList.get(i).salary);
//        }
        PriorityQueue<Employee1> pq=new PriorityQueue<>((e1, e2) -> (int)e2.salary - (int)e1.salary);

        for(int i=0;i<5;i++){
            pq.offer(employeeList.get(i));
        }
        while(!pq.isEmpty()){
            Employee1 e=pq.poll();
            System.out.println(e.id + " " + e.name + " " + e.salary);
        }
    }
}
