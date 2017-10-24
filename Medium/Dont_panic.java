import java.util.*;
import java.io.*;
import java.math.*;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
class Player {

    public static void main(String args[]) {
        List<Floor> Floors = new ArrayList<>();
        
        Scanner in = new Scanner(System.in);
        int nbFloors = in.nextInt(); // number of floors
        
        for(int i = 0; i < nbFloors; ++i) Floors.add(new Floor());
        
        int width = in.nextInt(); // width of the area
        int nbRounds = in.nextInt(); // maximum number of rounds
        int exitFloor = in.nextInt(); // floor on which the exit is found
        int exitPos = in.nextInt(); // position of the exit on its floor
        int nbTotalClones = in.nextInt(); // number of generated clones
        int nbAdditionalElevators = in.nextInt(); // ignore (always zero)
        int nbElevators = in.nextInt(); // number of elevators
        for (int i = 0; i < nbElevators; i++) {
            int elevatorFloor = in.nextInt(); // floor on which this elevator is found
            int elevatorPos = in.nextInt(); // position of the elevator on its floor
            
            Floors.get(elevatorFloor).ElevatorPosition(elevatorPos);
        }
        
        Floors.get(exitFloor).ElevatorPosition(exitPos);

        // game loop
        while (true) {
            int cloneFloor = in.nextInt(); // floor of the leading clone
            int clonePos = in.nextInt(); // position of the leading clone on its floor
            String direction = in.next(); // direction of the leading clone: LEFT or RIGHT

            String Cmd = "WAIT";
            if(cloneFloor != -1)
            {
                if(direction.equals("RIGHT") && clonePos > Floors.get(cloneFloor).ElevatorPosition() ||
                    direction.equals("LEFT") && clonePos < Floors.get(cloneFloor).ElevatorPosition())
                    Cmd = "BLOCK";
            }

            System.out.println(Cmd); // action: WAIT or BLOCK
        }
    }
}

class Floor
{
    private int ElevatorPos;
    public int ElevatorPosition() { return this.ElevatorPos; }
    public void ElevatorPosition(int value) { this.ElevatorPos = value; }
    
    public Floor()
    {
        this.ElevatorPos = 0;
    }
}