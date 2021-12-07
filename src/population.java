import java.util.ArrayList;
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
            dots[i].collisions();
        }
    }
}
