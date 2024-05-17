package Program;

import java.util.PriorityQueue;
import java.util.Scanner;

public class ElevatorProgram {

    // method to UserInput
    private static int[] getInputDataandCalculateDistance() {

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Lift A current position:");
        int liftCurrentPositionA = scanner.nextInt();
        System.out.print("Enter Lift B current position:");
        int liftCurrentPositionB = scanner.nextInt();
        System.out.print("Enter Position of lift A (Stationary or Moving):");
        String liftStatusA = scanner.next().toLowerCase();
        int liftDesPositionA = 0;
        if (liftStatusA.equals("moving")) {
            System.out.print("Enter the lift A position which floor is going to...:");
            liftDesPositionA = scanner.nextInt();
        }
        System.out.print("Enter Position of lift B (Stationary or Moving):");
        String liftStatusB = scanner.next().toLowerCase();
        int liftDesPositionB = 0;
        if (liftStatusB.equals("moving")) {
            System.out.print("Enter the lift B position which floor is going to...:");
            liftDesPositionB = scanner.nextInt();
        }
        System.out.print("Enter client current position floor:");
        int clientCurrentFloor = scanner.nextInt();
        System.out.print("Enter client current Direction(Up or Down):");
        String clientDirection = scanner.next().toLowerCase();

        int distanceOfLiftA = calculateDistance(liftStatusA, liftCurrentPositionA, liftDesPositionA, clientCurrentFloor, clientDirection);
        System.out.println("DistanceOfLiftA:" + distanceOfLiftA);
        int distanceOfLiftB = calculateDistance(liftStatusB, liftCurrentPositionB, liftDesPositionB, clientCurrentFloor, clientDirection);
        System.out.println("DistanceOfLiftB:" + distanceOfLiftB);
        int array[] = {distanceOfLiftA, distanceOfLiftB};
        scanner.close();
        return array;
    }

   // method to calculateDistance of Each Elevator
    private static int calculateDistance(String liftStatus, int currentPosOfLift, int destPosOfLift, int clientPositionFloor, String clientDirection) {
        if (liftStatus.equals("moving")) {
            if ((clientDirection.equals("up") && (currentPosOfLift <= clientPositionFloor) && (destPosOfLift >= clientPositionFloor)) ||
                    (clientDirection.equals("down") && (currentPosOfLift >= clientPositionFloor) && (destPosOfLift <= clientPositionFloor))) {
                return Math.abs(currentPosOfLift - clientPositionFloor);
            } else {
                return Math.abs(currentPosOfLift - destPosOfLift) + Math.abs(destPosOfLift - clientPositionFloor);
            }
        } else if (liftStatus.equals("stationary")) {
            if (clientDirection.equals("up") || clientDirection.equals("down")) {
                return Math.abs(clientPositionFloor - currentPosOfLift);
            }
        }
        return Math.abs(clientPositionFloor - currentPosOfLift);
    }

    // method to create the PriorityQueue and insert distances
    private static PriorityQueue<Integer> createPriorityQueue(int distanceOfA, int distanceOfB) {
        PriorityQueue<Integer> distances = new PriorityQueue<>();
        distances.offer(distanceOfA);
        distances.offer(distanceOfB);
        return distances;
    }

    // method to print the result based on the priority of distances
    private static void printResult(PriorityQueue<Integer> distances, int distanceOfA, int distanceOfB) {
        int LowestDistance = distances.peek();
        if (LowestDistance == distanceOfA) {
            System.out.println("-------------------------------------------------------------------");
            System.out.println("Lift A Will Come First");
            System.out.println("-------------------------------------------------------------------");
        } else if (LowestDistance == distanceOfB) {
            System.out.println("-------------------------------------------------------------------");
            System.out.println("Lift B Will Come First");
            System.out.println("-------------------------------------------------------------------");
        } else {
            System.out.println("-------------------------------------------------------------------");
            System.out.println("Lift A Will Come First");
            System.out.println("-------------------------------------------------------------------");
        }
    }

    //main method
    public static void main(String[] args) {
        int[] resultArray =   getInputDataandCalculateDistance();
        PriorityQueue<Integer> liftDistances = createPriorityQueue(resultArray[0], resultArray[1]);
        printResult(liftDistances, resultArray[0], resultArray[1]);
    }
}
