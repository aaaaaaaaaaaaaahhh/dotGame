import javax.swing.*;
import java.awt.*;

public class game {
    JFrame frame = new JFrame("thing");
    panel panel = new panel();
    
    public static void main(String[] args) throws InterruptedException {
        new game();
    }

    public game() throws InterruptedException {
        frame.setSize(1000, 1000);
        frame.setLayout(new GridLayout(1, 1));
        frame.add(panel);
        //frame.setResizable(false);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
