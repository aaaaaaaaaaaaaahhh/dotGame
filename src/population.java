import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

//-------------------------------------------------------------
//The population class instantiates a number of dots and puts them into a list
public class population {
    dot[] dots;
    public population(int numPop){
        dots = new dot[numPop]
;        for (int i = 0; i < numPop; i++) {
            dots[i] = new dot();
            dots[i].collisions();
        }
    }

    public population(int numPop, dot original){
        dots = new dot[numPop];
        for (int i = 0; i < numPop; i++) {
            dots[i] = new dot(original);
        }
        mutate();
        for (int i = 0; i < numPop; i++) {
            dots[i].collisions();
        }
    }

    public void mutate(){
        int mutate_rate = 25; // on scale of a hundred, so 25 percent
        for (int i = 0; i < dots.length; i++) {
            for (int j = 0; j < dots[i].brain.directions.length; j++) {
                Random rd = new Random();
                if (rd.nextInt(100) <= mutate_rate){
                    if (j != 0){
                        dots[i].brain.directions[j][0] = dots[i].brain.directions[j-1][0] + rd.nextInt()%40;
                        dots[i].brain.directions[j][1] = dots[i].brain.directions[j-1][1] + rd.nextInt()%40;
                    }
                }
            }
        }
    }

    public dot fitFunc(dot[] dots){
        System.out.println("fit func");
        double[] dotScores = new double[dots.length];
        dot best = new dot();
        double bestScore = 500;
        for (int i = 0; i < dots.length; i++) {
            int[] finalDirection = dots[i].brain.directions[dots[i].brain.directions.length-1];
            double score = Math.sqrt(Math.pow((finalDirection[0]-250), 2) + Math.pow((finalDirection[1]-8), 2));
            dotScores[i] = score;
        }

        for (double score : dotScores) {
            if (score < dotScores.length - 1) { // so there is no index error
                if (score <= bestScore) {
                    bestScore = score;
                }
            }
        }

        for (int i = 0; i < dotScores.length; i++) {
            if (dotScores[i] == bestScore){
                best = dots[i];
                System.out.println(Arrays.deepToString(dots[i].brain.directions));
                System.out.println("best score: " + dotScores[i]);
            }
        }
        System.out.println(Arrays.deepToString(best.brain.directions));
        return best;
    }
}
