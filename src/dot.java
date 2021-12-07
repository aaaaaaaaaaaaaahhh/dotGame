import java.util.Arrays;

public class dot {
//--------------------------------------------------------------
// The dot class represents a single agent, or dot. Each dot has a random brain to start with.
// This class also handles the boundaries of the dots
    brain brain;
    double thisScore;
    public dot(){
        brain = new brain(400);
        brain.randBrain();
        thisScore = 500;
    }

    public dot(dot original){
        brain = new brain(400);
        for (int i = 0; i < brain.numDirects; i++) {
            brain.directions[i] = Arrays.copyOf(original.brain.directions[i], original.brain.directions[i].length);
            System.out.println("thing: " + Arrays.toString(original.brain.directions[i]));
            System.out.println(Arrays.toString(brain.directions[i]));
        }
    }

    public void collisions(){
        for (int i = 0; i < brain.directions.length-1; i++) {
            if (brain.directions[i][0] <= 0 || brain.directions[i][0] <= 4) {
                brain.directions[i][0] = 4;
                brain.directions[i+1][0] = brain.directions[i][0];
                brain.directions[i+1][1] = brain.directions[i][1];
            }
            if(brain.directions[i][0] >= 500 || brain.directions[i][0] >= 498){
                brain.directions[i][0] = 498;
                brain.directions[i+1][0] = brain.directions[i][0];
                brain.directions[i+1][1] = brain.directions[i][1];
            }
            if (brain.directions[i][1] <= 0 || brain.directions[i][1] <= 2){
                brain.directions[i][1] = 2;
                brain.directions[i+1][1] = brain.directions[i][1];
                brain.directions[i+1][0] = brain.directions[i][0];
            }
            if (brain.directions[i][1] >= 500 || brain.directions[i][1] >= 474) {
                brain.directions[i][1] = 474;// top bar is included in frame, easiest solution
                brain.directions[i+1][1] = brain.directions[i][1];
                brain.directions[i+1][0] = brain.directions[i][0];
            }
            if ((brain.directions[i][1] >= 4 && brain.directions[i][1] <= 12) && (brain.directions[i][0] >= 246 && brain.directions[i][0] <= 254)) {
                brain.directions[i][1] = 8;
                brain.directions[i][0] = 250;
                brain.directions[i+1][1] = brain.directions[i][1];
                brain.directions[i+1][0] = brain.directions[i][0];
            }
        }
    }

    public dot fitFunc(dot[] dots){
        double[] dotScores = new double[dots.length];
        dot best = new dot();
        double bestScore = 0;
        for (int i = 0; i < dots.length; i++) {
            int[] finalDirection = dots[i].brain.directions[dots[i].brain.directions.length-1];
            double score = Math.sqrt(Math.pow((finalDirection[0]-250), 2) + Math.pow((finalDirection[1]-8), 2));
            dotScores[i] = score;
        }

        for (double dotScore : dotScores) {
            if (dotScore < dotScores.length - 1) { // so there is no index error
                if (dotScore >= bestScore) {
                    bestScore = dotScore;
                }
            }
        }

        for (int i = 0; i < dotScores.length; i++) {
            if (dotScores[i] == bestScore){
                best = dots[i];
            }
        }

        return best;
    }
}
