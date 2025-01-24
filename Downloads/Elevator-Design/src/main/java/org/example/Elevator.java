package org.example;

import java.util.TreeSet;

public class Elevator {
     Direction currentDirection = Direction.UP;
     State currentState = State.IDLE;
     int currentFloor = 0;

    private TreeSet<Request> currentJobs = new TreeSet<>();
    private TreeSet<Request> upPendingJobs = new TreeSet<>();
    private TreeSet<Request> downPendingJobs = new TreeSet<>();

    public void startElevator() {
        System.out.println("The Elevator has started");

        while (true) {
            if (checkIfJob()) {
                if (currentDirection == Direction.UP) {
                    Request request = currentJobs.pollFirst();
                    processUpRequest(request);
                    if (currentJobs.isEmpty()) {
                        addPendingDownJobsToCurrentJobs();
                    }
                }
                if (currentDirection == Direction.DOWN) {
                    Request request = currentJobs.pollLast();
                    processDownRequest(request);
                    if (currentJobs.isEmpty()) {
                        addPendingUpJobsToCurrentJobs();
                    }
                }
            }
            else{
                break;
            }
        }
    }

    private void addPendingDownJobsToCurrentJobs() {
        if (!downPendingJobs.isEmpty()) {
            System.out.println("Pick a pending down job and execute it by putting in current job");
            currentJobs = downPendingJobs;
            currentDirection = Direction.DOWN;
        } else {
            currentState = State.IDLE;
            System.out.println("The elevator is in Idle state");
        }
    }

    private void addPendingUpJobsToCurrentJobs() {
        if (!upPendingJobs.isEmpty()) {
            System.out.println("Pick a pending up job and execute it by putting in current job");
            currentJobs = upPendingJobs;
            currentDirection = Direction.UP;
        } else {
            currentState = State.IDLE;
            System.out.println("The elevator is in Idle state");
        }
    }

    public void addJob(Request request) {
        if (currentState == State.IDLE) {
            if (currentFloor == request.getExternalRequest().getSourceFloor()) {
                System.out.println("Added current queue job - lift state is - " + currentState + " location is - "
                        + currentFloor + " to move to floor - " + request.getInternalRequest().getDestinationFloor());
            }
            else {
                System.out.println("Added current queue job - lift state is - " + currentState + " location is - "
                        + currentFloor + " to move to floor - " + request.getExternalRequest().getSourceFloor());
            }
            currentState = State.MOVING;
            currentDirection = request.getExternalRequest().getDirectionToGo();
            currentJobs.add(request);
        } else if (currentState == State.MOVING) {

            if (request.getExternalRequest().getDirectionToGo() != currentDirection) {
                addtoPendingJobs(request);
            } else if (request.getExternalRequest().getDirectionToGo() == currentDirection) {
                if (currentDirection == Direction.UP
                        && request.getInternalRequest().getDestinationFloor() < currentFloor) {
                    addtoPendingJobs(request);
                } else if (currentDirection == Direction.DOWN
                        && request.getInternalRequest().getDestinationFloor() > currentFloor) {
                    addtoPendingJobs(request);
                } else {
                    currentJobs.add(request);
                }
            }
        }
        startElevator();
    }

    public void addtoPendingJobs(Request request) {
        if (request.getExternalRequest().getDirectionToGo() == Direction.UP) {
            System.out.println("Add to pending up jobs");
            upPendingJobs.add(request);
        } else {
            System.out.println("Add to pending down jobs");
            downPendingJobs.add(request);
        }
    }

    public boolean checkIfJob() {
        if (currentJobs.isEmpty()) {
            return false;
        }
        return true;
    }
    public void moveUpDest(int sourceFloor){
        int startFloor = currentFloor;
        if (startFloor < sourceFloor) {
            for (int i = startFloor+1; i <= sourceFloor; i++) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("We have reached floor: " + i);
                currentFloor = i;
            }
        }

        System.out.println("Reached Source Floor opening door...");
    }
    private void processUpRequest(Request request) {
        int startFloor = currentFloor;
        boolean flag=false;
        if (startFloor < request.getExternalRequest().getSourceFloor()) {
            for (int i = startFloor+1; i <= request.getExternalRequest().getSourceFloor(); i++) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("We have reached floor: " + i);
                currentFloor = i;
                flag=true;
            }
        }
        if(flag){
            System.out.println("Reached Source Floor opening door...");
        }

        startFloor = currentFloor;

        for (int i = startFloor+1; i <= request.getInternalRequest().getDestinationFloor(); i++) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("We have reached floor: " + i);
            currentFloor = i;
            if(i==request.getInternalRequest().getDestinationFloor()){
                System.out.println("Reached destination floor - opening door...");
            }
            if (checkIfNewJobCanBeProcessed(request)) {
                break;
            }
        }
    }
    public void moveDownDest(int sourceFloor){
        int startFloor = currentFloor;
        if (startFloor > sourceFloor) {
            for (int i = startFloor - 1; i >= sourceFloor; i--) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("We have reached floor " + i);
                currentFloor = i;
            }
        }

        System.out.println("Reached Source Floor opening door...");
    }
    private void processDownRequest(Request request) {
        int startFloor = currentFloor;
        boolean flag=false;
        if (startFloor < request.getExternalRequest().getSourceFloor()) {
            for (int i = startFloor-1; i >= request.getExternalRequest().getSourceFloor(); i--) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("We have reached floor " + i);
                flag=true;
                currentFloor = i;
            }
        }

        if(flag){
            System.out.println("Reached Source Floor opening door...");
        }

        startFloor = currentFloor;

        for (int i = startFloor-1; i >= request.getInternalRequest().getDestinationFloor(); i--) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("We have reached floor " + i);
            currentFloor = i;
            if(i==request.getInternalRequest().getDestinationFloor()){
                System.out.println("Reached destination floor - opening door...");
            }
            if (checkIfNewJobCanBeProcessed(request)) {
                break;
            }
        }
    }

    private boolean checkIfNewJobCanBeProcessed(Request currentRequest) {
        if (checkIfJob()) {
            if (currentDirection == Direction.UP) {
                Request request = currentJobs.pollLast();
                if (request.getInternalRequest().getDestinationFloor() < currentRequest.getInternalRequest().getDestinationFloor()) {
                    currentJobs.add(request);
                    currentJobs.add(currentRequest);
                    return true;
                }
                currentJobs.add(request);
            }

            if (currentDirection == Direction.DOWN) {
                Request request = currentJobs.pollFirst();
                if (request.getInternalRequest().getDestinationFloor() > currentRequest.getInternalRequest().getDestinationFloor()) {
                    currentJobs.add(request);
                    currentJobs.add(currentRequest);
                    return true;
                }
                currentJobs.add(request);
            }
        }
        return false;
    }
}
