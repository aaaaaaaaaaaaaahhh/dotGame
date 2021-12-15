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

    public population(int numPop, dot[] parentPop){
        dots = new dot[numPop];
        for (int i = 0; i < numPop; i++) {
            dots[i] = new dot(getParent(parentPop));
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



    public dot getParent(dot[] dots){
        double[] dotScores = new double[dots.length];
        dot best = new dot();
        double fitnessSum = 0;
        for (int i = 0; i < dots.length; i++) {
            dotScores[i] = dots[i].fitness;
            fitnessSum += dots[i].fitness;
        }
        System.out.println("fit p: " + fitnessSum);
        fitnessSum = (int)(fitnessSum*1000000000);
        System.out.println("fit: " + fitnessSum);
        fitnessSum = fitnessSum/1000000000;
        System.out.println(fitnessSum);

        double runningSum = 0;
        double randNum = Math.random() * fitnessSum;
        if (randNum == Double.POSITIVE_INFINITY){
            randNum = fitnessSum*.5;
        }
        for (int i = 0; i < dots.length; i++) {
            runningSum += dots[i].fitness;
            if (runningSum > randNum){
                return dots[i];
            }
        }


        System.out.println(Arrays.deepToString(best.brain.directions));
        return best;
    }
}
