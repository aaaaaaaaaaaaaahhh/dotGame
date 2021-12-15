import java.util.Arrays;

public class dot {
//--------------------------------------------------------------
// The dot class represents a single agent, or dot. Each dot has a random brain to start with.
// This class also handles the boundaries of the dots
    brain brain;
    double thisScore;
    double fitness;
    public dot(){
        brain = new brain(400);
        brain.randBrain();
        calcFit();
        thisScore = 500;
    }

    public dot(dot original){
        brain = new brain(400);
        for (int i = 0; i < brain.numDirects; i++) {
            brain.directions[i] = Arrays.copyOf(original.brain.directions[i], original.brain.directions[i].length);
            //System.out.println("thing: " + Arrays.toString(original.brain.directions[i]));
            //System.out.println(Arrays.toString(brain.directions[i]));
        }
        calcFit();
    }

    public void calcFit(){
        int[] finalDirection = brain.directions[brain.directions.length-1];
        double distToGoal = Math.sqrt(Math.pow((finalDirection[0]-250), 2) + Math.pow((finalDirection[1]-8), 2));
        fitness = 1/(distToGoal*distToGoal);
    }

    public void collisions(){
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
            if ((brain.directions[i][1] >= 4 && brain.directions[i][1] <= 12) && (brain.directions[i][0] >= 246 && brain.directions[i][0] <= 254)) {
                brain.directions[i][1] = 8;
                brain.directions[i][0] = 250;
                brain.directions[i+1][1] = brain.directions[i][1];
                brain.directions[i+1][0] = brain.directions[i][0];
            }

            if ((brain.directions[i][1] >= (500/3) && brain.directions[i][1] <= (500/3)+25) && (brain.directions[i][0] >= 500/4 && brain.directions[i][0] <= (500/4)+250)){
                brain.directions[i+1][1] = brain.directions[i][1];
                brain.directions[i+1][0] = brain.directions[i][0];
            }
            /*
            if ((brain.directions[i][1] >= 200 && brain.directions[i][1] <= 225 && (brain.directions[i][0] >= 250 && brain.directions[i][0] <= 500))){
                brain.directions[i+1][1] = brain.directions[i][1];
                brain.directions[i+1][0] = brain.directions[i][0];
            }*/
        }
    }

}
