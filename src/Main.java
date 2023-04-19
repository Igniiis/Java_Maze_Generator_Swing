import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class Main extends JFrame{

    public Main() {
        super("Maze generator / Solver");

        WindowListener l = new WindowAdapter() {
            public void windowClosing(WindowEvent e){
                System.exit(0);
            }
        };

        addWindowListener(l);


        //icone de l'application
        ImageIcon imageIcon = new ImageIcon("image/logo.png");
        setIconImage(imageIcon.getImage());


        Visuel panneau = new Visuel();
        setContentPane(panneau);
        setSize(800,800);
        setVisible(true);
    }

    public static void main(String [] args){
        JFrame frame = new Main();
    }
}