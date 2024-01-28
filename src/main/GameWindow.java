package main;
import javax.swing.*;

public class GameWindow {
    final private JFrame jframe;
    public GameWindow() {
        jframe = new JFrame();
        jframe.setSize(400, 400);
        jframe.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jframe.setVisible(true);

    }

}
