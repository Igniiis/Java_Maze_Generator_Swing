import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class GUIMaze extends JPanel {

    private Case[][] t;
    private static final int tailleCase = 25; //taille en pixel du coté de la case du labyrinthe
    private boolean paths;

    public GUIMaze(Case [][] grille) {
        this.t = grille;
        this.paths = true;
    }

    public GUIMaze(Case [][] grille, boolean path) {
        this.t = grille;
        this.paths = path;
    }

    public void setT(Case [][] grille) {
        t = grille;
    }

    public void setPaths(boolean paths) {
        this.paths = paths;
    }

    @Override
    public void paint(Graphics g) {

        super.paint(g);

        g.translate(50,50);

        for (int i = 0; i < t.length; i++) {
            for (int j = 0; j < t[i].length; j++) {
                Color c;

                switch (t[i][j].value){
                    case 0:
                        c = Color.WHITE;
                        break;
                    case 1:
                        c = Color.WHITE;
                        break;
                    case 2:
                        if (this.paths){ //si c'est check alors on affiche en cyan
                            c = Color.CYAN;
                        }else{ // sinon en blanc (on voit pas)
                            c = Color.white;
                        }
                        break;
                    case 3:
                        c = Color.PINK;
                        break;
                    default:
                        c = Color.RED;
                        break;
                }

                g.setColor(c);
                g.fillRect(tailleCase*i, tailleCase*j, tailleCase, tailleCase);
                g.setColor(Color.BLACK);
                g.drawRect(tailleCase*i, tailleCase*j, tailleCase, tailleCase);

                g.setColor(Color.white);
                if (this.t[i][j].est){
                    //si l'est est true, alors n'y pas de mur ici et on peut casser la cloison
                    g.drawLine(tailleCase*(1+i),tailleCase*j,tailleCase*(1+i), tailleCase*(j+1));
                }

                if (this.t[i][j].ouest){
                    g.drawLine(tailleCase*i,tailleCase*j,tailleCase*i,tailleCase*(j+1));
                }

                if (this.t[i][j].sud){
                    g.drawLine(tailleCase*i,tailleCase*(j+1),tailleCase*(i+1),tailleCase*(j+1));
                }

                if (this.t[i][j].nord){
                    g.drawLine(tailleCase*i,tailleCase*j,tailleCase*(1+i),tailleCase*j);
                }

            }
        }

    }
}

