public class Case {

    /**
     * Attributs
     */
    private int x;
    private int y;

    public int value;

    public boolean nord,sud, est,ouest;

    /**
     * Constructeur de la case
     * @param x étant l'abscisse
     * @param y étant l'ordonnée
     */
    public Case(int x, int y){
        this.x = x;
        this.y = y;
        this.value = 0;
        this.nord = false;
        this.sud = false;
        this.est = false;
        this.ouest = false;
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }
}