package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Main {
	public static void main(String args[]) {

		Elevator elevator = new Elevator();

		ProcessJobWorker processJobWorker = new ProcessJobWorker(elevator);
		Thread t2 = new Thread(processJobWorker);
		t2.start();

		while(true){
			Scanner input1 = new Scanner(System.in);
			System.out.println("Press \n 1. UP \n 2. DOWN");
			int exReq = input1.nextInt();
			Scanner input2 = new Scanner(System.in);
			System.out.println("Please provide your current floor: ");
			int sourceFloor = input2.nextInt();

			if(exReq==2 && sourceFloor==0 || exReq==1 && sourceFloor==5){//Edge case
				System.out.println("Could not process request, please try again");
				continue;
			}

			Direction dir= Direction.UP;
			if(exReq==2){
				dir=Direction.DOWN;
			}
			ExternalRequest er = new ExternalRequest(dir, sourceFloor);

			if(elevator.currentFloor!=sourceFloor){
				System.out.println("Elevator is currently at: " + elevator.currentFloor);
				//System.out.println(er);
				if(elevator.currentFloor<sourceFloor){
					elevator.moveUpDest(sourceFloor);
				}
				else{
					elevator.moveDownDest(sourceFloor);
				}
			}
			else{
				System.out.println("Doors opening...");
			}
			Scanner input3 = new Scanner(System.in);
			System.out.println("Welcome onboard please select destination floor: " );
			int destFloor = input3.nextInt();

			InternalRequest ir = new InternalRequest(destFloor);

//			if(dir ==destFloor<= elevator.currentFloor){
//				System.out.println("Could not process request, please try again");
//				continue;
//			}


//			listOfRequest.add(request1);
//
//			if(listOfRequest.size()<2){
//				continue;
//			}

//			for(int i=0;i<listOfRequest.size();i++){
//				Thread t = new Thread(new AddJobWorker(elevator, listOfRequest.get(i)));
//				t.start();
//				try {
//					Thread.sleep(300);
//				} catch (InterruptedException e) {
//					e.printStackTrace();
//				}
//			}

			Request request1 = new Request(ir, er);
			Thread t = new Thread(new AddJobWorker(elevator, request1));
//			Request request2 = new Request(new InternalRequest(4), new ExternalRequest(Direction.UP, 2));
//			Thread t1 = new Thread(new AddJobWorker(elevator, request2));
//			t1.start();

			try {
				Thread.sleep(300);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			t.start();

            try {
                t.join();
				//t1.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}


//		try {
//			Thread.sleep(3000);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}

//		ExternalRequest er = new ExternalRequest(Direction.UP, 2);
//		System.out.println(er.toString());
//		InternalRequest ir = new InternalRequest(4);

//		Request request1 = new Request(ir, er);

//		new Thread(new AddJobWorker(elevator, request1)).start();


//		try {
//			Thread.sleep(3000);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
//		while(true) {

//			Scanner input = new Scanner(System.in);
//			System.out.println("Enter choice (number): \n 1. Internal Request \n 2. External Request");
//			int choice = input.nextInt();
//			InternalRequest ir=null;
//			ExternalRequest er=new ExternalRequest(Direction.UP, 0);
//
//			if(choice == 1){
//				input = new Scanner(System.in);
//				System.out.println("Enter the destination floor number: ");
//				int reqFloor = input.nextInt();
//
//				ir = new InternalRequest(reqFloor);
//
//				System.out.println("Elevator - " + elevator.currentDirection + " | Current floor - " + elevator.currentFloor
//						+ " | Status - " + elevator.currentState);
//			}
//
//			if(choice == 2) {
//				input = new Scanner(System.in);
//				System.out.println("Enter the direction in which you want to move: \n 1. UP \n 2. DOWN");
//				int reqDir = input.nextInt();
//
//				input = new Scanner(System.in);
//				System.out.println("Enter the floor where elevator is requested from: ");
//				int curFloor = input.nextInt();
//
//				if(reqDir==1){
//					 er = new ExternalRequest(Direction.UP, curFloor);
//				}
//				else{
//					er = new ExternalRequest(Direction.DOWN, curFloor);
//				}
//
//				request1= new Request(ir, er);
//			}
//			new Thread(new AddJobWorker(elevator, request1)).start();
//
//			try {
//				Thread.sleep(3000);
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
		}
}
