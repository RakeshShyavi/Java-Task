package Program;

import java.util.PriorityQueue;
import java.util.Scanner;


public class Elevator

 {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Lift A current position:");
        int liftCurrentPositionA = scanner.nextInt();
        System.out.print("Enter Lift B current position:");
        int liftCurrentPositionB = scanner.nextInt();
        System.out.print("Enter Position of lift A (Stationary or Moving):");
        String LiftstatusA = scanner.next().toLowerCase();
        int liftDesPositionA = 0;
        if (LiftstatusA.equals("moving")) {
            System.out.print("Enter the lift A position which floor is going to...:");
            liftDesPositionA = scanner.nextInt();
        }
        System.out.print("Enter Position of lift B (Stationary or Moving):");
        String LiftstatusB = scanner.next().toLowerCase();
        int liftDesPositionB = 0;
        if (LiftstatusB.equals("moving")) {
            System.out.print("Enter the lift B position which floor is going to...:");
            liftDesPositionB = scanner.nextInt();
        }
        System.out.print("Enter client current position floor:");
        int clientCurrentFloor = scanner.nextInt();
        System.out.print("Enter client current Direction(Up or Down):");
        String clientDirection=scanner.next().toLowerCase();
        int distanceOfLiftA = calculateDistance(LiftstatusA,  liftCurrentPositionA,liftDesPositionA,clientCurrentFloor,clientDirection);
        System.out.println("DistanceOfLiftA:"+distanceOfLiftA);
        int distanceOfLiftB = calculateDistance(LiftstatusB,liftCurrentPositionB,liftDesPositionB ,clientCurrentFloor,clientDirection);
        System.out.println("DistanceOfLiftB:"+distanceOfLiftB);
        // Create a PriorityQueue to store distances between lifts and client's position
        PriorityQueue<Integer> Liftdistances = new PriorityQueue<>();
        Liftdistances.offer(distanceOfLiftA);
        Liftdistances.offer(distanceOfLiftB);
        printResult(Liftdistances,distanceOfLiftA,distanceOfLiftB);

        scanner.close();
    }

    private static int calculateDistance(String liftStatus, int currentPosOfLift, int destPosOfLift, int clientPositionFloor, String clientDirection) {
        if (liftStatus.equals("moving")) {
            if (clientDirection.equals("up") && (currentPosOfLift <= clientPositionFloor) && (destPosOfLift >= clientPositionFloor) || (clientDirection.equals("down") && (currentPosOfLift >= clientPositionFloor) && (destPosOfLift <= clientPositionFloor)) ) {
                    return Math.abs(currentPosOfLift - clientPositionFloor);
            } else {
                    return Math.abs(currentPosOfLift-destPosOfLift)+Math.abs(destPosOfLift-clientPositionFloor);
                }
        }else if (liftStatus.equals("stationary")) {
            if (clientDirection.equals("up") || (clientDirection.equals("down"))) {
                return Math.abs(clientPositionFloor - currentPosOfLift);
            }
        }
        return Math.abs(clientPositionFloor - currentPosOfLift);
    }
    // Helper method to print the result based on the priority of distances
    private static void printResult(PriorityQueue<Integer> distances,int distanceOfA,int distanceOfB)
    {
        int firstDistance = distances.peek();
            if (firstDistance == distanceOfA) {
                System.out.println("-------------------------------------------------------------------");
                System.out.println("Lift A Will Come First");
                System.out.println("-------------------------------------------------------------------");
            } else if(firstDistance == distanceOfB){
                System.out.println("-------------------------------------------------------------------");
                System.out.println("Lift B Will Come First");
                System.out.println("-------------------------------------------------------------------");
            } else{
                System.out.println("-------------------------------------------------------------------");
                System.out.println("Lift A Will Come First");
                System.out.println("-------------------------------------------------------------------");
            }
    }
}







