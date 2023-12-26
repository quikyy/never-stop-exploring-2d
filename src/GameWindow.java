import javax.swing.*;

public class GameWindow extends JFrame {

    public GameWindow() {
        this.setTitle("NeverStopExploring!");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);

        GameScreen gameScreen = new GameScreen();
        this.add(gameScreen);

        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);

        gameScreen.run();
    }

}
