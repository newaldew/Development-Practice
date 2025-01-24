package org.example.ComparatorVsComparable;

import java.util.*;

class Employee implements Comparable<Employee>{
    int id;
    String name;
    long salary;

    public Employee(int id, String name, long salary){
        this.id=id;
        this.name=name;
        this.salary=salary;
    }

    @Override
    public int compareTo(Employee e) {
        if(this.salary<e.salary){
            return 1;//Means swap it
        }
        else if(this.salary>e.salary){
            return -1;//Don't swap
        }
        else{
            if(this.id>e.id){
                return 1;
            }
            else if(this.id<e.id){
                return -1;
            }
            return 0;//Leave it at it is
        }
    }
}

public class ComparableClass {
    public static void main(String[] args) {

        List<Employee> employeeList=new ArrayList<>();
        employeeList.add(new Employee(103,"Newal",2000));
        employeeList.add(new Employee(105,"Rahul",1000));
        employeeList.add(new Employee(109,"Sunil",5000));
        employeeList.add(new Employee(101,"Rohit",2000));
        employeeList.add(new Employee(102,"Parul",1000));

        Collections.sort(employeeList);

        for(int i=0;i<employeeList.size();i++) {
            System.out.println(employeeList.get(i).id + " " + employeeList.get(i).name + " " + employeeList.get(i).salary);
        }

    }
}
