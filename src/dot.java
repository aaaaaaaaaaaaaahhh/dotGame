import java.util.Arrays;

public class dot {
//--------------------------------------------------------------
// The dot class represents a single agent, or dot. Each dot has a random brain to start with.
// This class also handles the boundaries of the dots
    brain brain;
    double thisScore;
    double fitness;
    public dot(){
        brain = new brain(800);
        brain.randBrain();
        collisions();
        calcFit();
        thisScore = 500;
    }

    public dot(dot original){
        brain = new brain(800);
        for (int i = 0; i < brain.numDirects; i++) {
            brain.directions[i] = Arrays.copyOf(original.brain.directions[i], original.brain.directions[i].length);
            //System.out.println("thing: " + Arrays.toString(original.brain.directions[i]));
            //System.out.println(Arrays.toString(brain.directions[i]));
        }
        calcFit();
    }

    public void calcFit(){
        int[] finalDirection = brain.directions[brain.directions.length-1];
        double steps = 1;

        if (finalDirection[0] == 500 && finalDirection[1] == 8){
            //double steps = 1; // count is the number of steps that a dot takes to get to the goal
            for (int i = 0; i < brain.directions.length; i++) {
                if (i >=1){
                    if ((brain.directions[i][0] == 500 && brain.directions[i][1] == 8) && (brain.directions[i-1][0] != 500 && brain.directions[i-1][1] != 8)){ // checking for first step in the goal
                        steps = i-1;
                    }
                }
            }
            // System.out.println(steps);
            fitness = 1/steps;
            // System.out.println("fit:" + fitness);
        } else {
            double distToGoal = Math.sqrt(Math.pow((finalDirection[0]-500), 2) + Math.pow((finalDirection[1]-8), 2)); // for if the dot does not reach the goal
            fitness = 1/(distToGoal*distToGoal);
        }
    }

    public void collisions(){ // handles when the dot goes out of bounds
        for (int i = 0; i < brain.directions.length-1; i++) {
            if (brain.directions[i][0] <= 0 || brain.directions[i][0] <= 2) {
                brain.directions[i][0] = 2;
                brain.directions[i+1][0] = brain.directions[i][0];
                brain.directions[i+1][1] = brain.directions[i][1];
            }
            if(brain.directions[i][0] >= 1000 || brain.directions[i][0] >= 983){
                brain.directions[i][0] = 983;
                brain.directions[i+1][0] = brain.directions[i][0];
                brain.directions[i+1][1] = brain.directions[i][1];
            }
            if (brain.directions[i][1] <= 0 || brain.directions[i][1] <= 2){
                brain.directions[i][1] = 2;
                brain.directions[i+1][1] = brain.directions[i][1];
                brain.directions[i+1][0] = brain.directions[i][0];
            }
            if (brain.directions[i][1] >= 1000 || brain.directions[i][1] >= 960) {
                brain.directions[i][1] = 960;// top bar is included in frame, easiest solution
                brain.directions[i+1][1] = brain.directions[i][1];
                brain.directions[i+1][0] = brain.directions[i][0];
            }
            if ((brain.directions[i][1] >= 4 && brain.directions[i][1] <= 12) && (brain.directions[i][0] >= 496 && brain.directions[i][0] <= 504)) {
                brain.directions[i][1] = 8;
                brain.directions[i][0] = 500;
                brain.directions[i+1][1] = brain.directions[i][1];
                brain.directions[i+1][0] = brain.directions[i][0];
            }
            if ((brain.directions[i][1] >= (1000/3) && brain.directions[i][1] <= (1000/3)+25) && (brain.directions[i][0] >= 1000/4 && brain.directions[i][0] <= (1000/4)+500)){
                brain.directions[i+1][1] = brain.directions[i][1];
                brain.directions[i+1][0] = brain.directions[i][0];
                fitness = fitness - (fitness/2);
            }
            /*
            if ((brain.directions[i][1] >= 200 && brain.directions[i][1] <= 225 && (brain.directions[i][0] >= 250 && brain.directions[i][0] <= 500))){
                brain.directions[i+1][1] = brain.directions[i][1];
                brain.directions[i+1][0] = brain.directions[i][0];
            }*/
        }
    }

}
