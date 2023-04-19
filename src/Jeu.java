import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;

public class Jeu {

    /**
     * Attributs
     */
    private Case [][] grille;
    private int tailleN;

    /**
     * Constructeur avec la taille du tableau à définir
     *
     * @param tailleN
     * le nombre de case par coté du tableau
     */
    public Jeu(int tailleN) {
        this.tailleN = tailleN;
        this.grille = new Case[tailleN][tailleN];
        resetGrille();
    }

    /**
     * Constructeur par défaut qui créé un tableau tel que
     * Longueur = Largeur = tailleN
     */
    public Jeu() {
        this.tailleN = 25;
        this.grille = new Case[tailleN][tailleN];
        resetGrille();
    }

    public void resetGrille() {
        for (int i = 0; i < tailleN; i++) {
            for (int j = 0; j < tailleN; j++) {
                this.grille[i][j] = new Case(i,j);
            }
        }
    }

    public Case[][] getGrille() {
        return grille;
    }

    /**
     * Initialisation de la création du labyrinthe, commençant à une coordonnée précise
     * On utilise l'algorithme depth-first search
     * @param x abscisse de la case de base
     * @param y ordonnée de la case de base
     */
    public void initialMaze(int x, int y){
        generateMaze(x,y);
    }


    /**
     * Initialisation de la création du labyrinthe, dans ce cas,
     * en utilisant l'algorithme de fusion aléatoire de chemin
     */
    /**public void initialMaze(){
        //pour cette partie, on doit créé des tableaux de murs car ce sont eux qui nous intéressent

        boolean[][] mursVerticaux = new boolean[tailleN+1][tailleN];
        for (int i = 0; i <tailleN+1; i++) {
            for (int j = 0; j < tailleN; j++) {
                mursVerticaux[i][j] = false;
            }
        }

        boolean[][] mursHorizontaux = new boolean[tailleN][tailleN+1];
        for (int i = 0; i <tailleN; i++) {
            for (int j = 0; j < tailleN+1; j++) {
                mursHorizontaux[i][j] = false;
            }
        }


        generateMaze(mursVerticaux, mursHorizontaux);


        //après la génération du labyrinthe, on refait une dernière fois une modification du labyrinthe,
        //pour remettre toutes les cases à la valeur 1
        for (int i = 0; i < tailleN; i++) {
            for (int j = 0; j < tailleN; j++) {
                this.grille[i][j].value = 1;
            }
        }
    }**/

    public void generateMaze(int x, int y){

        //Création d'une liste "stack"
        ArrayList<Case> list = new ArrayList<>();
        list.add(this.grille[x][y]);
        //On marque la cellule comme visité
        this.grille[x][y].value = 1;

        int newX = x;
        int newY = y;

        while (true){
            //cas pour arreter
            if (list.isEmpty() && newX==x && newY==y){
                //si on est retourné au point de départ et que la liste est vide
                //alors on arrête la boucle
                break;
            }


            //on choisit la nouvelle cellule
            int r = chooseCell(newX,newY);

            if(r == -1){ //si la case n'a pas de voisins non visité
                Case c = list.get(list.size()-1); // on récupère la case précédente et on en fait la case actuelle
                newX = c.getX();
                newY = c.getY();
                list.remove(list.size()-1);
            }else{  //dans le cas où un voisin a été trouvé

                switch (r){ // on se déplace d'une case en fonction de r
                    case 0:
                        this.grille[newX][newY].est = true;
                        this.grille[newX+1][newY].ouest = true;
                        newX = newX + 1;
                        break;
                    case 1:
                        this.grille[newX][newY].sud = true;
                        this.grille[newX][newY+1].nord = true;
                        newY = newY + 1;
                        break;
                    case 2:
                        this.grille[newX][newY].ouest = true;
                        this.grille[newX-1][newY].est = true;
                        newX = newX - 1;
                        break;
                    case 3:
                        this.grille[newX][newY].nord = true;
                        this.grille[newX][newY-1].sud = true;
                        newY = newY - 1;
                        break;
                }

                //on note cette case comme visité
                this.grille[newX][newY].value = 1;

                //on rajoute à la liste la nouvelle case
                list.add(this.grille[newX][newY]);

            }
        }
    }

    /**
     * Méthode permettant de savoir quelles cases peuvent être visité depuis une case
     * @param x abscisse de la case de base
     * @param y ordonnée de la case de base
     * @return retourne soit -1 quand il n'y a pas de voisins possible, soit un nombre entre 0 et 3
     */
    public int chooseCell(int x,int y){

        //Création d'une liste pour obtenir tous les voisins
        ArrayList<Integer> badNeighbors = new ArrayList<>();

        if(x<1){
            badNeighbors.add(2);
        }

        if (y<1){
            badNeighbors.add(3);
        }

        if (x==tailleN-1){
            badNeighbors.add(0);
        }

        if (y==tailleN-1){
            badNeighbors.add(1);
        }

        Random random = new Random();

        while (badNeighbors.size()<4){
            int r = random.nextInt(4);
            if(!badNeighbors.contains(r)) { // si cette case n'a pas déjà été vérifié
                switch (r) { // on se déplace d'une case en fonction de r
                    case 0:
                        if (this.grille[x+1][y].value==0) {// on vérifie si elle est bonne
                            return r;
                        } else {
                            badNeighbors.add(r);
                        }
                        break;
                    case 1:
                        if (this.grille[x][y+1].value==0) {
                            return r;
                        } else {
                            badNeighbors.add(r);
                        }
                        break;
                    case 2:
                        if (this.grille[x-1][y].value==0) {
                            return r;
                        } else {
                            badNeighbors.add(r);
                        }
                        break;
                    case 3:
                        if (this.grille[x][y-1].value==0) {
                            return r;
                        } else {
                            badNeighbors.add(r);
                        }
                        break;
                }
            }
        }

        //retourne -1 si les 4 voisins sont négatifs
        return -1;
    }





    /**
     * Génération du labyrinthe par l'algorithme de fusion aléatoire des chemins
     */
    /**public void generateMaze(boolean[][] mursVerticaux, boolean[][] mursHorizontaux) {

        Random r = new Random();

        boolean test = true;
        while (test) {
            //on prend un mur au hasard
            int x = r.nextInt(tailleN);
            int y = r.nextInt(tailleN);

            System.out.println("x=" + x + "  y=" + y);

            if(x!=tailleN-1 && x!=0 && y!=tailleN-1 && y!=0 ) {
                //on regarde si il est déjà cassé où si ces 2 cases sont déjà utilisé
                if (!murs[x][y] && this.grille[x-1][y-1]==) {

                }
            }
        }
    }**/






    /**
     * Méthode de résolution du labyrinthe par la méthode  Depth-First Search
     * Cette méthode est récursive et n'utilise pas de stack, à la place elle va utiliser la valeur de chaque case
     * pour savoir si cette dernière à déjà été visité
     *
     * value=1 : jamais visité
     * value=2 : déjà visité
     * value=3 : chemin pour solution
     *
     * @param x abscisse de la case
     * @param y ordonnée de la case
     * @return on renvoie un nombre pour savoir si le chemin est possible ou pas depuis cette voie
     * true = chemin possible
     * false = chemin impossible
     */
    public boolean solvingMaze(int x, int y){

        if (x==0 && y==0 && this.grille[0][0].value==3){
            //quand on reclique sur "Solve" sans rien avoir changé
            return false;
        }

        //on commence par dire que cette case à bien été visité
        this.grille[x][y].value = 3;

        if (x==tailleN-1 && y==tailleN-1){
            return true;
        }

        //maintenant on regarde quel sont les cases autour possible à aller chercher

        //pour l'ouest
        if (this.grille[x][y].ouest && this.grille[x-1][y].value==1) {
            if(solvingMaze(x-1,y)){
                return true;
            }
        }

        //pour l'est
        if (this.grille[x][y].est && this.grille[x+1][y].value==1){
            if(solvingMaze(x+1,y)){
                return true;
            }
        }

        //pour le nord
        if (this.grille[x][y].nord && this.grille[x][y-1].value==1){
            if(solvingMaze(x,y-1)){
                return true;
            }
        }

        //pour le sud
        if (this.grille[x][y].sud && this.grille[x][y+1].value==1){
            if(solvingMaze(x,y+1)){
                return true;
            }
        }


        //si aucun mur n'est possible alors on repart en arrière
        this.grille[x][y].value=2;
        return false;
    }

}