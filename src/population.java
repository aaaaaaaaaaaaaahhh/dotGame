import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

//-------------------------------------------------------------
//The population class instantiates a number of dots and puts them into a list
public class population {
    dot[] dots;
    public population(int numPop){ // initializing the first generation
        dots = new dot[numPop];
        for (int i = 0; i < numPop; i++) {
            dots[i] = new dot();
            dots[i].collisions();
            //System.out.println(dots[i].fitness);
        }
    }

    public population(int numPop, dot[] parentPop){ // handles all generations after the first
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
        int mutate_rate = 25; // on scale of a hundred, so 25 percent of dots get mutated
        for (int i = 0; i < dots.length; i++) {
            for (int j = 0; j < dots[i].brain.directions.length; j++) {
                Random rd = new Random();
                if (rd.nextInt(100) <= mutate_rate){
                    if (j != 0){
                        dots[i].brain.directions[j][0] = dots[i].brain.directions[j-1][0] + rd.nextInt()%20; // mutating directions
                        dots[i].brain.directions[j][1] = dots[i].brain.directions[j-1][1] + rd.nextInt()%20;
                    }
                }
            }
        }
    }



    public dot getParent(dot[] dots){ // taking in an array of dots, getting their fitnesses and adding them to a running sum.
        // Then creating a random number and chosing the best dot if the rand num is less than the running sum
        double[] dotScores = new double[dots.length];
        dot best = new dot();
        double fitnessSum = 0;
        for (int i = 0; i < dots.length; i++) {
            dotScores[i] = dots[i].fitness;
            fitnessSum += dots[i].fitness;
        }
        fitnessSum = (int)(fitnessSum*1000000000); // scaling
        fitnessSum = fitnessSum/1000000000;

        double runningSum = 0;
        double randNum = Math.random() * fitnessSum;
        if (randNum == Double.POSITIVE_INFINITY){ // to prevent infinity values
            randNum = fitnessSum*.5;
        }
        for (int i = 0; i < dots.length; i++) {
            runningSum += dots[i].fitness;
            if (runningSum > randNum){
                return dots[i]; // returns the dot that the random number landed on
            }
        }


        //System.out.println(Arrays.deepToString(best.brain.directions));
        return best; // just in case something goes wrong
    }
}
