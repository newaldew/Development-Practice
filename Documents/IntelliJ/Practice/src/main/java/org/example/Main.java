package org.example;

import org.w3c.dom.ls.LSOutput;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.

public class Main {
    public static void main(String[] args) {

        List<Employee> list=new ArrayList<>();
        Employee e1=new Employee(1,"Pune");
        Employee e2=new Employee(2,null);
        Employee e3=new Employee(3,null);
        Employee e4=new Employee(4,"Mumbai");

        list.add(e1);
        list.add(e2);
        list.add(e3);
        list.add(e4);

        for(Employee e:list){
            System.out.println(e.getId() + " " + e.getLocation());
        }

        List<Employee>newList=list.stream().peek(e -> {
            if(e.getLocation()==null){
                e.setLocation("Bangaluru");
            }
        }).toList();

        for(Employee e:newList){
            System.out.println(e.getId() + " " + e.getLocation());
        }
    }



}
class Employee{
    private int id;
    private String location;

    public Employee(int id, String location){
        this.id=id;
        this.location=location;
    }
    public int getId(){
        return this.id;
    }
    public String getLocation() {
        return this.location;
    }
    public void setLocation(String location){
        this.location=location;
    }
}