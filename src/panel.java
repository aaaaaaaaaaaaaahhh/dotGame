import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;
import javax.swing.Timer;
import java.util.concurrent.*;

public class panel extends JPanel implements ActionListener{
    Timer timer;
    population pop;
    int i = 0;
    public panel() {
        timer = new Timer(2, this);
        timer.start();
        pop = new population(500);
        for (int j = 0; j < pop.dots.length; j++) {
            //System.out.println(Arrays.deepToString(pop.dots[j].brain.directions));// for debugging
        }
        //System.out.println(pop.dots.length);
    }



    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.setColor(Color.red);
        g.fillRect(246, 4, 8, 8); //drawing goal
        g.setColor(Color.cyan);
        g.fillRect(500/4, 500/3, 250, 25);
        g.setColor(Color.black);
        for (int j = 0; j < pop.dots.length; j++) {// drawing each dot on one call of the task update
            g.fillRect(pop.dots[j].brain.directions[i][0]-2, pop.dots[j].brain.directions[i][1]-2, 4, 4);
            if (i >= pop.dots[j].brain.directions.length-1) { // loops through vertical part of directions(400 by 2 array)
                if (j >= pop.dots.length-1){
                    pop = new population(500, pop.dots);
                    i = 0;
                }
            }
        }
        //System.out.println(i);
        i++;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
    }



}
