import java.util.Arrays;
import java.util.Random;

public class brain {
    int[][] directions;
    int numDirects;

    public brain(int numDirections){ // makes a nested array
        directions = new int[numDirections][2];
        numDirects = numDirections;
    }

    public void randBrain(){ // creates a random brain
        directions[0][0] = 500;
        directions[0][1] = 700;
        Random rd = new Random();
        for (int i = 1; i < directions.length; i++) {
            for (int j = 0; j < directions[0].length; j++) {
                int move = (int)((rd.nextInt()%100)*.2);
                directions[i][j] = directions[i-1][j] + move;
            }
        }
    }
}
