import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.Buffer;
import java.nio.file.Path;

public class Visuel extends JPanel {

    private Jeu jeu;

    //attributs visuels
    private GUIMaze guiMaze;
    private JPanel top;

    private JComboBox<String> comboBox;
    private JButton create;
    private JButton recreate;
    private JButton solve;
    private JCheckBox checkBox;
    private JButton screen;

    public Visuel() {
        this.jeu = new Jeu();

        String s1[] = {"Depth First Search", "fusion aléatoire chemin"};
        this.comboBox = new JComboBox<String>(s1);
        this.comboBox.setSelectedIndex(0);

        this.checkBox = new JCheckBox("voir chemins testés");

        this.guiMaze = new GUIMaze(jeu.getGrille(), this.checkBox.isSelected());

        this.create = new JButton("Create");
        this.create.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createMaze();
                printCurrentMaze();
                repaint();
                hideCreate();
            }
        });

        this.recreate = new JButton("Recreate");
        this.recreate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actualiseMaze();
                createMaze();
                printCurrentMaze();
                repaint();
            }
        });
        this.recreate.setVisible(false);


        this.solve = new JButton("Solve");
        this.solve.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                solveMaze();
                printCurrentMaze();
                repaint();
            }
        });

        this.screen = new JButton("Screenshot");
        this.screen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                screenshot();
            }
        });

        this.top = new JPanel();
        //this.top.add(this.comboBox);
        this.top.add(this.create);
        this.top.add(this.recreate);
        this.top.add(this.checkBox);
        this.top.add(this.solve);
        this.top.add(this.screen);
        this.top.setBackground(Color.cyan);

        this.setLayout(new BorderLayout());
        this.add(top, BorderLayout.NORTH);
        this.add(guiMaze, BorderLayout.CENTER);
    }

    /**
     * Méthode pour afficher la création du labyrinthe
     */
    public void createMaze() {

        this.jeu.initialMaze(0,0);
        /**switch (this.comboBox.getSelectedItem().toString()){
            case "Depth First Search":

                break;
            case "fusion aléatoire chemin":
                this.jeu.initialMaze();
                break;
            default:
                System.out.println("Error");
                break;
        }**/
    }

    /**
     * Méthode pour afficher le labyrinthe courant
     */
    public void printCurrentMaze() {
        this.guiMaze.setT(this.jeu.getGrille());
        this.guiMaze.setPaths(this.checkBox.isSelected());
    }


    /**
     * Méthode pour cacher le bouton Create et le remplacer par Recreate
     */
    public void hideCreate() {
        this.create.setVisible(false);
        this.recreate.setVisible(true);
    }


    /**
     * Méthode pour recréer à l'infini de nouveaux labyrinthes
     */
    public void actualiseMaze(){
        //actualisation de la grille
        this.jeu.resetGrille();
    }


    /**
     * Méthode pour appeler la résolution du labyrinthe
     */
    public void solveMaze(){
        this.jeu.solvingMaze(0,0);
    }


    /**
     * Méthode faisant le screenshot du labyrinthe à l'instant t
     */
    public void screenshot() {
        //créer l'image du labyrinthe
        try {
            BufferedImage bi = getScreenShot(this.guiMaze);
            File outputfile = new File("savedImageMaze.png");
            ImageIO.write(bi, "png", outputfile);
            System.out.println("Image enregistree dans le projet à la localisation : " + outputfile.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Fonction récupérée du site http://blog.gtiwari333.com/2012/04/java-capturesave-image-jframe-jpanel.html
     * @param component Composant qui va être mis en tant qu'image
     * @return retourne une image de type "BufferedImage" du composant mis en paramètre
     */
    public static BufferedImage getScreenShot(Component component) {

        BufferedImage image = new BufferedImage(component.getWidth(), component.getHeight(), BufferedImage.TYPE_INT_RGB);
        // paints into image's Graphics
        component.paint(image.getGraphics());
        return image;
    }
}